package com.example.backenbarberperu.usuario;

import com.example.backenbarberperu.barbero.BarberoEntity;
import com.example.backenbarberperu.barbero.BarberoRepository;
import com.example.backenbarberperu.cliente.ClienteEntity;
import com.example.backenbarberperu.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarberoRepository barberoRepository;

    public UsuarioEntity crearUsuarioConRol(UsuarioDto usuarioDto, Object datos) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setRol(UsuarioEntity.Rol.valueOf(usuarioDto.getRol().toUpperCase()));
        UsuarioEntity usuarioGuardado = usuarioRepository.save(usuario);

        if (usuario.getRol() == UsuarioEntity.Rol.CLIENTE) {
            if (datos instanceof ClienteEntity cliente) {
                cliente.setUsuario(usuarioGuardado);
                clienteRepository.save(cliente);
            } else {
                throw new IllegalArgumentException("Datos inválidos para el rol CLIENTE");
            }
        } else if (usuario.getRol() == UsuarioEntity.Rol.BARBERO) {
            if (datos instanceof BarberoEntity barbero) {
                barbero.setUsuario(usuarioGuardado);
                barberoRepository.save(barbero);
            } else {
                throw new IllegalArgumentException("Datos inválidos para el rol BARBERO");
            }
        }
        return usuarioGuardado;
    }

    public Optional<UsuarioEntity> login(String email, String password) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && usuario.get().getPassword().equals(password)) {
            return usuario;
        }
        return Optional.empty();
    }

    public List<UsuarioEntity> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public boolean eliminarUsuario(Long id) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findById(id);

        if (usuario.isPresent()) {
            UsuarioEntity usuarioEntity = usuario.get();

            // Verifica el rol del usuario y elimina la entidad relacionada
            if (usuarioEntity.getRol() == UsuarioEntity.Rol.CLIENTE) {
                ClienteEntity cliente = clienteRepository.findByUsuarioId(usuarioEntity.getId());
                if (cliente != null) {
                    clienteRepository.delete(cliente);
                }
            } else if (usuarioEntity.getRol() == UsuarioEntity.Rol.BARBERO) {
                BarberoEntity barbero = barberoRepository.findByUsuarioId(usuarioEntity.getId());
                if (barbero != null) {
                    barberoRepository.delete(barbero);
                }
            }

            // Luego de eliminar la entidad relacionada, elimina el usuario
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
