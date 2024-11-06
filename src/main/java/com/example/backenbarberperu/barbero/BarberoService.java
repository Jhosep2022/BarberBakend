package com.example.backenbarberperu.barbero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberoService {

    @Autowired
    private BarberoRepository barberoRepository;

    public List<BarberoEntity> obtenerTodosLosBarberos() {
        return barberoRepository.findAll();
    }

    public Optional<BarberoEntity> obtenerBarberoPorId(Long id) {
        return barberoRepository.findById(id);
    }

    public BarberoEntity actualizarBarbero(Long id, BarberoDto barberoDto) {
        Optional<BarberoEntity> barberoOpt = barberoRepository.findById(id);
        if (barberoOpt.isPresent()) {
            BarberoEntity barbero = barberoOpt.get();
            barbero.setNombre(barberoDto.getNombre());
            barbero.setApellido(barberoDto.getApellido());
            barbero.setTelefono(barberoDto.getTelefono());
            barbero.setEspecialidad(barberoDto.getEspecialidad());
            barbero.setDisponibilidad(barberoDto.getDisponibilidad());
            return barberoRepository.save(barbero);
        } else {
            throw new RuntimeException("Barbero no encontrado con ID: " + id);
        }
    }

    public boolean eliminarBarbero(Long id) {
        if (barberoRepository.existsById(id)) {
            barberoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
