package com.example.backenbarberperu.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<ClienteEntity>> obtenerTodosLosClientes() {
        List<ClienteEntity> clientes = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok(clientes);
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> obtenerClientePorId(@PathVariable Long id) {
        Optional<ClienteEntity> cliente = clienteService.obtenerClientePorId(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        boolean actualizado = clienteService.actualizarCliente(id, clienteDto);
        if (actualizado) {
            return ResponseEntity.ok("Cliente actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id) {
        boolean eliminado = clienteService.eliminarCliente(id);
        if (eliminado) {
            return ResponseEntity.ok("Cliente eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
