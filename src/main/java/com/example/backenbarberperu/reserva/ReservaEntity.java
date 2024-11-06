package com.example.backenbarberperu.reserva;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import com.example.backenbarberperu.cliente.ClienteEntity;
import com.example.backenbarberperu.barbero.BarberoEntity;
import com.example.backenbarberperu.servicio.ServicioEntity;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReservaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReserva;

    private LocalDate fechaReserva;
    private LocalTime horaReserva;
    private String estado;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "idBarbero")
    private BarberoEntity barbero;

    @ManyToOne
    @JoinColumn(name = "idServicio")
    private ServicioEntity servicio;
}
