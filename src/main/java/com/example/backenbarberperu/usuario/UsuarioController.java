package com.example.backenbarberperu.usuario;

import com.example.backenbarberperu.barbero.BarberoEntity;
import com.example.backenbarberperu.cliente.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearUsuario(@RequestBody Map<String, Object> payload) {
        try {
            String email = (String) payload.get("email");
            String password = (String) payload.get("password");
            String rol = (String) payload.get("rol");

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setEmail(email);
            usuarioDto.setPassword(password);
            usuarioDto.setRol(rol);

            if ("CLIENTE".equalsIgnoreCase(rol)) {
                ClienteEntity cliente = new ClienteEntity();
                Map<String, Object> clienteData = (Map<String, Object>) payload.get("cliente");
                cliente.setNombre((String) clienteData.get("nombre"));
                cliente.setApellido((String) clienteData.get("apellido"));
                cliente.setTelefono((String) clienteData.get("telefono"));
                cliente.setCorreo((String) clienteData.get("correo"));
                cliente.setDireccion((String) clienteData.get("direccion"));
                cliente.setFechaRegistro(LocalDateTime.parse((String) clienteData.get("fechaRegistro")));

                usuarioService.crearUsuarioConRol(usuarioDto, cliente);
            } else if ("BARBERO".equalsIgnoreCase(rol)) {
                BarberoEntity barbero = new BarberoEntity();
                Map<String, Object> barberoData = (Map<String, Object>) payload.get("barbero");
                barbero.setNombre((String) barberoData.get("nombre"));
                barbero.setApellido((String) barberoData.get("apellido"));
                barbero.setTelefono((String) barberoData.get("telefono"));
                barbero.setEspecialidad((String) barberoData.get("especialidad"));
                barbero.setDisponibilidad((String) barberoData.get("disponibilidad"));

                usuarioService.crearUsuarioConRol(usuarioDto, barbero);
            } else {
                return ResponseEntity.badRequest().body("Rol no válido");
            }
            return ResponseEntity.ok("Usuario creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear usuario: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");

        Optional<UsuarioEntity> usuario = usuarioService.login(email, password);
        if (usuario.isPresent()) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> obtenerTodosLosUsuarios() {
        List<UsuarioEntity> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> obtenerUsuarioPorId(@PathVariable Long id) {
        Optional<UsuarioEntity> usuario = usuarioService.obtenerUsuarioPorId(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) {
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.ok("Usuario eliminado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
