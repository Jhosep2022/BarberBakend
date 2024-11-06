package com.example.backenbarberperu.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Long idCliente;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String direccion;
    private LocalDateTime fechaRegistro;
    private Long usuarioId;
}
