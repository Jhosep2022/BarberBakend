package com.example.backenbarberperu.pago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.example.backenbarberperu.reserva.ReservaEntity;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    private BigDecimal monto;
    private LocalDateTime fechaPago;
    private String metodoPago;

    @ManyToOne
    @JoinColumn(name = "idReserva")
    private ReservaEntity reserva;
}
