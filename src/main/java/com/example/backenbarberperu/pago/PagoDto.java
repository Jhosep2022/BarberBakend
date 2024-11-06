package com.example.backenbarberperu.pago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoDto {
    private Long idPago;
    private BigDecimal monto;
    private LocalDateTime fechaPago;
    private String metodoPago;
    private Long idReserva; // Representa el ID de la Reserva asociada
}
