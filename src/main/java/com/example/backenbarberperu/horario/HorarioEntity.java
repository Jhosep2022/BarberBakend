package com.example.backenbarberperu.horario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;
import java.time.LocalTime;
import com.example.backenbarberperu.barbero.BarberoEntity;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HorarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHorario;

    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "idBarbero")
    private BarberoEntity barbero;
}
