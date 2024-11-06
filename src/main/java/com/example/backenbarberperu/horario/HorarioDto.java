package com.example.backenbarberperu.horario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HorarioDto {
    private Long idHorario;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private String nombreBarbero;
    private Long idBarbero;
}
