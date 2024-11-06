package com.example.backenbarberperu.horario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    @Autowired
    private HorarioService horarioService;

    // Obtener todos los horarios
    @GetMapping
    public ResponseEntity<List<HorarioDto>> obtenerTodosLosHorarios() {
        List<HorarioDto> horarios = horarioService.obtenerTodosLosHorarios();
        return ResponseEntity.ok(horarios);
    }

    // Obtener horario por ID
    @GetMapping("/{id}")
    public ResponseEntity<HorarioDto> obtenerHorarioPorId(@PathVariable Long id) {
        Optional<HorarioDto> horario = horarioService.obtenerHorarioPorId(id);
        return horario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear horario
    @PostMapping
    public ResponseEntity<String> crearHorario(@RequestBody HorarioDto horarioDto) {
        horarioService.crearHorario(horarioDto);
        return ResponseEntity.ok("Horario creado exitosamente");
    }

    // Actualizar horario
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarHorario(@PathVariable Long id, @RequestBody HorarioDto horarioDto) {
        boolean actualizado = horarioService.actualizarHorario(id, horarioDto);
        if (actualizado) {
            return ResponseEntity.ok("Horario actualizado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar horario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHorario(@PathVariable Long id) {
        boolean eliminado = horarioService.eliminarHorario(id);
        if (eliminado) {
            return ResponseEntity.ok("Horario eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
