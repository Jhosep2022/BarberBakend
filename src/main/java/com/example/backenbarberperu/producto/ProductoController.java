package com.example.backenbarberperu.producto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerTodosLosProductos() {
        List<ProductoDto> productos = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok(productos);
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerProductoPorId(@PathVariable Long id) {
        Optional<ProductoDto> producto = productoService.obtenerProductoPorId(id);
        return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear producto
    @PostMapping
    public ResponseEntity<String> crearProducto(@RequestBody ProductoDto productoDto) {
        productoService.crearProducto(productoDto);
        return ResponseEntity.ok("Producto creado exitosamente");
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDto productoDto) {
        boolean actualizado = productoService.actualizarProducto(id, productoDto);
        if (actualizado) {
            return ResponseEntity.ok("Producto actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        boolean eliminado = productoService.eliminarProducto(id);
        if (eliminado) {
            return ResponseEntity.ok("Producto eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
