package com.example.backenbarberperu.reserva;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Obtener todas las reservas
    @GetMapping
    public ResponseEntity<List<ReservaDto>> obtenerTodasLasReservas() {
        List<ReservaDto> reservas = reservaService.obtenerTodasLasReservas();
        return ResponseEntity.ok(reservas);
    }

    // Obtener reserva por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> obtenerReservaPorId(@PathVariable Long id) {
        Optional<ReservaDto> reserva = reservaService.obtenerReservaPorId(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear reserva
    @PostMapping
    public ResponseEntity<String> crearReserva(@RequestBody ReservaDto reservaDto) {
        reservaService.crearReserva(reservaDto);
        return ResponseEntity.ok("Reserva creada exitosamente");
    }

    // Actualizar reserva
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarReserva(@PathVariable Long id, @RequestBody ReservaDto reservaDto) {
        boolean actualizada = reservaService.actualizarReserva(id, reservaDto);
        if (actualizada) {
            return ResponseEntity.ok("Reserva actualizada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long id) {
        boolean eliminada = reservaService.eliminarReserva(id);
        if (eliminada) {
            return ResponseEntity.ok("Reserva eliminada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
