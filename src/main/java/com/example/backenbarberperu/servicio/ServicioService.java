package com.example.backenbarberperu.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<ServicioEntity> obtenerTodosLosServicios() {
        return servicioRepository.findAll();
    }

    public Optional<ServicioEntity> obtenerServicioPorId(Long id) {
        return servicioRepository.findById(id);
    }

    public ServicioEntity crearServicio(ServicioDto servicioDto) {
        ServicioEntity servicio = new ServicioEntity();
        servicio.setNombre(servicioDto.getNombre());
        servicio.setDescripcion(servicioDto.getDescripcion());
        servicio.setPrecio(servicioDto.getPrecio());
        servicio.setDuracion(servicioDto.getDuracion());

        return servicioRepository.save(servicio);
    }

    public boolean actualizarServicio(Long id, ServicioDto servicioDto) {
        Optional<ServicioEntity> servicioExistente = servicioRepository.findById(id);
        if (servicioExistente.isPresent()) {
            ServicioEntity servicio = servicioExistente.get();
            servicio.setNombre(servicioDto.getNombre());
            servicio.setDescripcion(servicioDto.getDescripcion());
            servicio.setPrecio(servicioDto.getPrecio());
            servicio.setDuracion(servicioDto.getDuracion());

            servicioRepository.save(servicio);
            return true;
        }
        return false;
    }

    public boolean eliminarServicio(Long id) {
        if (servicioRepository.existsById(id)) {
            servicioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
