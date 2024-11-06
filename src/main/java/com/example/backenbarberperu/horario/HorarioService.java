package com.example.backenbarberperu.horario;

import com.example.backenbarberperu.barbero.BarberoEntity;
import com.example.backenbarberperu.barbero.BarberoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository horarioRepository;

    @Autowired
    private BarberoRepository barberoRepository;

    public List<HorarioDto> obtenerTodosLosHorarios() {
        return horarioRepository.findAll().stream()
                .map(this::convertirAHorarioDto)
                .collect(Collectors.toList());
    }

    public Optional<HorarioDto> obtenerHorarioPorId(Long id) {
        return horarioRepository.findById(id).map(this::convertirAHorarioDto);
    }

    public void crearHorario(HorarioDto horarioDto) {
        HorarioEntity horario = new HorarioEntity();
        horario.setDiaSemana(horarioDto.getDiaSemana());
        horario.setHoraInicio(horarioDto.getHoraInicio());
        horario.setHoraFin(horarioDto.getHoraFin());

        Optional<BarberoEntity> barbero = barberoRepository.findById(horarioDto.getIdBarbero());
        barbero.ifPresent(horario::setBarbero);

        horarioRepository.save(horario);
    }

    public boolean actualizarHorario(Long id, HorarioDto horarioDto) {
        Optional<HorarioEntity> horarioExistente = horarioRepository.findById(id);
        if (horarioExistente.isPresent()) {
            HorarioEntity horario = horarioExistente.get();
            horario.setDiaSemana(horarioDto.getDiaSemana());
            horario.setHoraInicio(horarioDto.getHoraInicio());
            horario.setHoraFin(horarioDto.getHoraFin());

            Optional<BarberoEntity> barbero = barberoRepository.findById(horarioDto.getIdBarbero());
            barbero.ifPresent(horario::setBarbero);

            horarioRepository.save(horario);
            return true;
        }
        return false;
    }

    public boolean eliminarHorario(Long id) {
        if (horarioRepository.existsById(id)) {
            horarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private HorarioDto convertirAHorarioDto(HorarioEntity horarioEntity) {
        HorarioDto horarioDto = new HorarioDto();
        horarioDto.setIdHorario(horarioEntity.getIdHorario());
        horarioDto.setDiaSemana(horarioEntity.getDiaSemana());
        horarioDto.setHoraInicio(horarioEntity.getHoraInicio());
        horarioDto.setHoraFin(horarioEntity.getHoraFin());
        horarioDto.setNombreBarbero(horarioEntity.getBarbero() != null ? horarioEntity.getBarbero().getNombre() : null);
        horarioDto.setIdBarbero(horarioEntity.getBarbero() != null ? horarioEntity.getBarbero().getIdBarbero() : null);
        return horarioDto;
    }
}
