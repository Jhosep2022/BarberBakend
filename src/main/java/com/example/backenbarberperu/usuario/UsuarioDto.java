package com.example.backenbarberperu.usuario;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private Long id;
    private String email;
    private String password;
    private String rol;
}
