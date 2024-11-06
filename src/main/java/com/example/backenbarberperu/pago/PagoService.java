package com.example.backenbarberperu.pago;

import com.example.backenbarberperu.reserva.ReservaEntity;
import com.example.backenbarberperu.reserva.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    public List<PagoDto> obtenerTodosLosPagos() {
        return pagoRepository.findAll().stream()
                .map(this::convertirAPagoDto)
                .collect(Collectors.toList());
    }

    public Optional<PagoDto> obtenerPagoPorId(Long id) {
        return pagoRepository.findById(id).map(this::convertirAPagoDto);
    }

    public void crearPago(PagoDto pagoDto) {
        PagoEntity pago = new PagoEntity();
        pago.setMonto(pagoDto.getMonto());
        pago.setFechaPago(pagoDto.getFechaPago());
        pago.setMetodoPago(pagoDto.getMetodoPago());

        Optional<ReservaEntity> reserva = reservaRepository.findById(pagoDto.getIdReserva());
        reserva.ifPresent(pago::setReserva);

        pagoRepository.save(pago);
    }

    public boolean actualizarPago(Long id, PagoDto pagoDto) {
        Optional<PagoEntity> pagoExistente = pagoRepository.findById(id);
        if (pagoExistente.isPresent()) {
            PagoEntity pago = pagoExistente.get();
            pago.setMonto(pagoDto.getMonto());
            pago.setFechaPago(pagoDto.getFechaPago());
            pago.setMetodoPago(pagoDto.getMetodoPago());

            Optional<ReservaEntity> reserva = reservaRepository.findById(pagoDto.getIdReserva());
            reserva.ifPresent(pago::setReserva);

            pagoRepository.save(pago);
            return true;
        }
        return false;
    }

    public boolean eliminarPago(Long id) {
        if (pagoRepository.existsById(id)) {
            pagoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private PagoDto convertirAPagoDto(PagoEntity pagoEntity) {
        PagoDto pagoDto = new PagoDto();
        pagoDto.setIdPago(pagoEntity.getIdPago());
        pagoDto.setMonto(pagoEntity.getMonto());
        pagoDto.setFechaPago(pagoEntity.getFechaPago());
        pagoDto.setMetodoPago(pagoEntity.getMetodoPago());
        pagoDto.setIdReserva(pagoEntity.getReserva() != null ? pagoEntity.getReserva().getIdReserva() : null);
        return pagoDto;
    }
}
