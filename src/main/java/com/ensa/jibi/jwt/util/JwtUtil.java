package com.ensa.jibi.jwt.util;

import com.ensa.jibi.backend.services.UserService;
import com.ensa.jibi.cmi.services.impl.ComptePaiementServiceImpl;
import com.ensa.jibi.jwt.models.UserPrincipal;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

  private final ComptePaiementServiceImpl comptePaiementService;
  private final UserService userService;
  private final ObjectMapper objectMapper;


  private String SECRET_KEY="8fhgeigayuf64oq8ltw36pi84wl87trf8afpw7ty934t7fwaeGTFyugfldlsjfdglja";

  public JwtUtil(ComptePaiementServiceImpl comptePaiementService, UserService userService, ObjectMapper objectMapper) {
    this.comptePaiementService = comptePaiementService;
    this.userService = userService;
    this.objectMapper = objectMapper;
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .getBody();
  }

  private Boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  public String generateToken(UserPrincipal userDetails) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", userDetails.getRole());
    claims.put("user", userDetails);
    claims.put("userInfos", userService.getUserByUsername(userDetails.getUsername()));
    if(!(userDetails.getRole().equals("ROLE_ADMIN") || userDetails.getRole().equals("ROLE_AGENT")))
      claims.put("compte", comptePaiementService.getComptePaiement(userDetails.getPhone()));
    return createToken(claims, userDetails.getUsername());
  }

  private String createToken(Map<String, Object> claims, String subject) {
    return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .serializeToJsonWith(new JacksonSerializer<>(objectMapper))
            .compact();
  }

  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}