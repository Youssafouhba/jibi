package com.ensa.jibi.jwt.models;


import com.ensa.jibi.backend.domain.entities.Admin;
import com.ensa.jibi.backend.domain.entities.User;
import com.ensa.jibi.cmi.domain.entities.ComptePaiement;
import com.ensa.jibi.cmi.services.impl.ComptePaiementServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class UserPrincipal implements UserDetails {

  private final User user;

  public UserPrincipal(User user){
      this.user = user;
  }


  public String getRole() {
    return user.getRoles().iterator().next().getName();
  }

  public Long getId() {
    return user.getId();
  }

  public String getPhone() {
    return user.getNumTel();
  }


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();

    if (user instanceof Admin) {
      String role = "ROLE_ADMIN";
      authorities.add(new SimpleGrantedAuthority(role));

    }

    return authorities;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
   return this.user.getUsername();
  }


  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

}
