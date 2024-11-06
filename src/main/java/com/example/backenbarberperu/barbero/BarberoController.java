package com.example.backenbarberperu.barbero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/barberos")
public class BarberoController {

    @Autowired
    private BarberoService barberoService;

    @GetMapping
    public ResponseEntity<List<BarberoEntity>> obtenerTodosLosBarberos() {
        List<BarberoEntity> barberos = barberoService.obtenerTodosLosBarberos();
        return ResponseEntity.ok(barberos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberoEntity> obtenerBarberoPorId(@PathVariable Long id) {
        Optional<BarberoEntity> barbero = barberoService.obtenerBarberoPorId(id);
        return barbero.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarberoEntity> actualizarBarbero(@PathVariable Long id, @RequestBody BarberoDto barberoDto) {
        try {
            BarberoEntity barberoActualizado = barberoService.actualizarBarbero(id, barberoDto);
            return ResponseEntity.ok(barberoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarBarbero(@PathVariable Long id) {
        boolean eliminado = barberoService.eliminarBarbero(id);
        if (eliminado) {
            return ResponseEntity.ok("Barbero eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
