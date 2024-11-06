package com.example.backenbarberperu.servicio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioDto {
    private Long idServicio;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer duracion;
}
