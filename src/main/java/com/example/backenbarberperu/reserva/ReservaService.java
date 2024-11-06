package com.example.backenbarberperu.reserva;

import com.example.backenbarberperu.cliente.ClienteEntity;
import com.example.backenbarberperu.cliente.ClienteRepository;
import com.example.backenbarberperu.barbero.BarberoEntity;
import com.example.backenbarberperu.barbero.BarberoRepository;
import com.example.backenbarberperu.servicio.ServicioEntity;
import com.example.backenbarberperu.servicio.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarberoRepository barberoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    public List<ReservaDto> obtenerTodasLasReservas() {
        return reservaRepository.findAll().stream()
                .map(this::convertirAReservaDto)
                .collect(Collectors.toList());
    }

    public Optional<ReservaDto> obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id).map(this::convertirAReservaDto);
    }

    public void crearReserva(ReservaDto reservaDto) {
        ReservaEntity reserva = new ReservaEntity();
        reserva.setFechaReserva(reservaDto.getFechaReserva());
        reserva.setHoraReserva(reservaDto.getHoraReserva());
        reserva.setEstado(reservaDto.getEstado());

        Optional<ClienteEntity> cliente = clienteRepository.findById(reservaDto.getIdCliente());
        Optional<BarberoEntity> barbero = barberoRepository.findById(reservaDto.getIdBarbero());
        Optional<ServicioEntity> servicio = servicioRepository.findById(reservaDto.getIdServicio());

        cliente.ifPresent(reserva::setCliente);
        barbero.ifPresent(reserva::setBarbero);
        servicio.ifPresent(reserva::setServicio);

        reservaRepository.save(reserva);
    }

    public boolean actualizarReserva(Long id, ReservaDto reservaDto) {
        Optional<ReservaEntity> reservaExistente = reservaRepository.findById(id);
        if (reservaExistente.isPresent()) {
            ReservaEntity reserva = reservaExistente.get();
            reserva.setFechaReserva(reservaDto.getFechaReserva());
            reserva.setHoraReserva(reservaDto.getHoraReserva());
            reserva.setEstado(reservaDto.getEstado());

            Optional<ClienteEntity> cliente = clienteRepository.findById(reservaDto.getIdCliente());
            Optional<BarberoEntity> barbero = barberoRepository.findById(reservaDto.getIdBarbero());
            Optional<ServicioEntity> servicio = servicioRepository.findById(reservaDto.getIdServicio());

            cliente.ifPresent(reserva::setCliente);
            barbero.ifPresent(reserva::setBarbero);
            servicio.ifPresent(reserva::setServicio);

            reservaRepository.save(reserva);
            return true;
        }
        return false;
    }

    public boolean eliminarReserva(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ReservaDto convertirAReservaDto(ReservaEntity reservaEntity) {
        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setIdReserva(reservaEntity.getIdReserva());
        reservaDto.setFechaReserva(reservaEntity.getFechaReserva());
        reservaDto.setHoraReserva(reservaEntity.getHoraReserva());
        reservaDto.setEstado(reservaEntity.getEstado());

        if (reservaEntity.getCliente() != null) {
            reservaDto.setIdCliente(reservaEntity.getCliente().getIdCliente());
        }

        if (reservaEntity.getBarbero() != null) {
            reservaDto.setIdBarbero(reservaEntity.getBarbero().getIdBarbero());
        }

        if (reservaEntity.getServicio() != null) {
            reservaDto.setIdServicio(reservaEntity.getServicio().getIdServicio());
        }

        return reservaDto;
    }

}
