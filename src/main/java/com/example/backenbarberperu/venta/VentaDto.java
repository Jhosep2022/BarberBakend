package com.example.backenbarberperu.venta;

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
public class VentaDto {
    private Long idVenta;
    private Integer cantidad;
    private BigDecimal total;
    private LocalDateTime fechaVenta;
    private String metodoPago;
    private Long idCliente;
    private Long idProducto;
}
