package com.example.backenbarberperu.servicio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServicioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idServicio;

    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer duracion;
}
