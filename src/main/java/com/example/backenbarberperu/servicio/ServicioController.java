package com.example.backenbarberperu.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    // Obtener todos los servicios
    @GetMapping
    public ResponseEntity<List<ServicioEntity>> obtenerTodosLosServicios() {
        List<ServicioEntity> servicios = servicioService.obtenerTodosLosServicios();
        return ResponseEntity.ok(servicios);
    }

    // Obtener servicio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicioEntity> obtenerServicioPorId(@PathVariable Long id) {
        Optional<ServicioEntity> servicio = servicioService.obtenerServicioPorId(id);
        return servicio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo servicio
    @PostMapping
    public ResponseEntity<ServicioEntity> crearServicio(@RequestBody ServicioDto servicioDto) {
        ServicioEntity nuevoServicio = servicioService.crearServicio(servicioDto);
        return ResponseEntity.ok(nuevoServicio);
    }

    // Actualizar un servicio existente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarServicio(@PathVariable Long id, @RequestBody ServicioDto servicioDto) {
        boolean actualizado = servicioService.actualizarServicio(id, servicioDto);
        if (actualizado) {
            return ResponseEntity.ok("Servicio actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un servicio
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarServicio(@PathVariable Long id) {
        boolean eliminado = servicioService.eliminarServicio(id);
        if (eliminado) {
            return ResponseEntity.ok("Servicio eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
