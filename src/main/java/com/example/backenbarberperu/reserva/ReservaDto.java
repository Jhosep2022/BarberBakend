package com.example.backenbarberperu.reserva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto {
    private Long idReserva;
    private LocalDate fechaReserva;
    private LocalTime horaReserva;
    private String estado;
    private Long idCliente;
    private Long idBarbero;
    private Long idServicio;
}
