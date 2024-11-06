package com.example.backenbarberperu.barbero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarberoDto {
    private Long idBarbero;
    private String nombre;
    private String apellido;
    private String telefono;
    private String especialidad;
    private String disponibilidad;
    private Long usuarioId;
}
