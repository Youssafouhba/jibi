package com.ensa.jibi.backend.controllers;


import com.ensa.jibi.backend.domain.requests.LoginRequest;
import com.ensa.jibi.backend.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthenticateController {
  private final AuthenticationService authenticateService;


  @GetMapping("/index")
  public String home() {
    return "index";
  }

  @PostMapping(value = "/login")
  public ResponseEntity<?> authenticate(
    @RequestBody LoginRequest loginRequest
  ) throws Exception {
    return authenticateService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
  }

  @PutMapping("/{userId}/set-password")
  public ResponseEntity<String> setPassword(@PathVariable Long userId, @RequestParam String newPassword) {
    try {
      authenticateService.setPassword(userId, newPassword);
      return ResponseEntity.ok().build();
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }
}
