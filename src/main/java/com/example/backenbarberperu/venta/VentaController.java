package com.example.backenbarberperu.venta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // Obtener todas las ventas
    @GetMapping
    public ResponseEntity<List<VentaEntity>> obtenerTodasLasVentas() {
        List<VentaEntity> ventas = ventaService.obtenerTodasLasVentas();
        return ResponseEntity.ok(ventas);
    }

    // Obtener una venta por ID
    @GetMapping("/{id}")
    public ResponseEntity<VentaEntity> obtenerVentaPorId(@PathVariable Long id) {
        Optional<VentaEntity> venta = ventaService.obtenerVentaPorId(id);
        return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva venta
    @PostMapping
    public ResponseEntity<String> crearVenta(@RequestBody VentaDto ventaDto) {
        ventaService.crearVenta(ventaDto);
        return ResponseEntity.ok("Venta creada exitosamente");
    }

    // Actualizar una venta existente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarVenta(@PathVariable Long id, @RequestBody VentaDto ventaDto) {
        boolean actualizado = ventaService.actualizarVenta(id, ventaDto);
        if (actualizado) {
            return ResponseEntity.ok("Venta actualizada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una venta
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long id) {
        boolean eliminado = ventaService.eliminarVenta(id);
        if (eliminado) {
            return ResponseEntity.ok("Venta eliminada exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
