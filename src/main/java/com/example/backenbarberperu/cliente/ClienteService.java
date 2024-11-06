package com.example.backenbarberperu.cliente;

import com.example.backenbarberperu.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<ClienteEntity> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public boolean actualizarCliente(Long id, ClienteDto clienteDto) {
        Optional<ClienteEntity> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            ClienteEntity cliente = clienteExistente.get();
            cliente.setNombre(clienteDto.getNombre());
            cliente.setApellido(clienteDto.getApellido());
            cliente.setTelefono(clienteDto.getTelefono());
            cliente.setCorreo(clienteDto.getCorreo());
            cliente.setDireccion(clienteDto.getDireccion());
            cliente.setFechaRegistro(clienteDto.getFechaRegistro());
            clienteRepository.save(cliente);
            return true;
        }
        return false;
    }

    public boolean eliminarCliente(Long id) {
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
