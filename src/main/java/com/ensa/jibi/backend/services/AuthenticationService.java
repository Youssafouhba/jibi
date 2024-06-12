package com.ensa.jibi.backend.services;


import com.ensa.jibi.backend.domain.entities.Role;
import com.ensa.jibi.backend.domain.entities.User;
import com.ensa.jibi.backend.repositories.RoleRepository;
import com.ensa.jibi.backend.repositories.UserRepository;
import com.ensa.jibi.jwt.models.AuthenticationResponse;
import com.ensa.jibi.jwt.models.UserPrincipal;
import com.ensa.jibi.jwt.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {

  public final UserService userService;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final ModelMapper mapper;
  private final JwtUtil jwtUtil;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    var userEntity = userService.getUserByUsername(email);
    return new UserPrincipal(userEntity);
  }


  public ResponseEntity<?> authenticate(String username, String password)
          throws NoSuchAlgorithmException {
    if (username.isEmpty() || password.isEmpty()) {
      throw new BadCredentialsException("Unauthorized");
    }

    var userEntity = userService.getUserByUsername(username);

    if (userEntity == null) {
      return ResponseEntity.badRequest().body("{\"message\":\"There are no account with these credentials\"}");
    }

    if (!passwordEncoder.matches(password, userEntity.getPassword())) {
      return ResponseEntity.badRequest().body("{\"message\":\"Password Incorrect !\"}");
    }

    Role role = getRole(userEntity.getId());
    UserPrincipal userDetails = new UserPrincipal(userEntity);
    var jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok().body( new AuthenticationResponse(role,jwt,userEntity.getId()));
  }

  public void setPassword(Long userId, String newPassword) {
    User user = userService.getUserById(userId);
    if (user != null) {
      user.setPassword(passwordEncoder.encode(newPassword));
      user.setFirstLogin(false);
      userRepository.save(user);
    } else {
      throw new RuntimeException("User not found with ID: " + userId);
    }
  }
  public Role getRole(Long id){
    return this.roleRepository.findRoleByUsersIn(Arrays.asList(mapper.map(userService.getUserById(id),User.class)));
  }

}
