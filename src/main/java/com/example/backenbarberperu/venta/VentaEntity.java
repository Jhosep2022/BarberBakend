package com.example.backenbarberperu.venta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.example.backenbarberperu.cliente.ClienteEntity;
import com.example.backenbarberperu.producto.ProductoEntity;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    private Integer cantidad;
    private BigDecimal total;
    private LocalDateTime fechaVenta;
    private String metodoPago;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private ProductoEntity producto;
}
