package com.example.backenbarberperu.pago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    // Obtener todos los pagos
    @GetMapping
    public ResponseEntity<List<PagoDto>> obtenerTodosLosPagos() {
        List<PagoDto> pagos = pagoService.obtenerTodosLosPagos();
        return ResponseEntity.ok(pagos);
    }

    // Obtener pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<PagoDto> obtenerPagoPorId(@PathVariable Long id) {
        Optional<PagoDto> pago = pagoService.obtenerPagoPorId(id);
        return pago.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear pago
    @PostMapping
    public ResponseEntity<String> crearPago(@RequestBody PagoDto pagoDto) {
        pagoService.crearPago(pagoDto);
        return ResponseEntity.ok("Pago creado exitosamente");
    }

    // Actualizar pago
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarPago(@PathVariable Long id, @RequestBody PagoDto pagoDto) {
        boolean actualizado = pagoService.actualizarPago(id, pagoDto);
        if (actualizado) {
            return ResponseEntity.ok("Pago actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar pago
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPago(@PathVariable Long id) {
        boolean eliminado = pagoService.eliminarPago(id);
        if (eliminado) {
            return ResponseEntity.ok("Pago eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
