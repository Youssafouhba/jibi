package com.ensa.jibi.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto{
    private Long id;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private boolean firstLogin;

}
