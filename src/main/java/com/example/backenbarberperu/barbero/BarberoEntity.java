package com.example.backenbarberperu.barbero;

import com.example.backenbarberperu.horario.HorarioEntity;
import com.example.backenbarberperu.reserva.ReservaEntity;
import com.example.backenbarberperu.usuario.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BarberoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBarbero;

    private String nombre;
    private String apellido;
    private String telefono;
    private String especialidad;
    private String disponibilidad;

    @OneToMany(mappedBy = "barbero")
    private List<HorarioEntity> horarios;

    @OneToMany(mappedBy = "barbero")
    private List<ReservaEntity> reservas;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioEntity usuario;
}
