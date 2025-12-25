package com.proteccioncivil.controlturnos.service;

import com.proteccioncivil.controlturnos.dto.TurnoRequestDTO;
import com.proteccioncivil.controlturnos.dto.TurnoResponseDTO;
import com.proteccioncivil.controlturnos.model.Turno;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TurnoService {

    // almacenamiento temporal en memoria (luego Firestore)
    private final List<Turno> turnos = new ArrayList<>();

    public List<TurnoResponseDTO> obtenerTurnos() {
        return turnos.stream().map(this::toResponse).toList();
    }

    public TurnoResponseDTO crearTurno(TurnoRequestDTO dto) {

        Turno turno = new Turno();
        turno.setId(UUID.randomUUID().toString());

        turno.setFecha(LocalDate.parse(dto.fecha));
        turno.setHoraInicio(LocalTime.parse(dto.horaInicio));
        turno.setHoraFin(LocalTime.parse(dto.horaFin));

        turno.setRol(dto.rol);
        turno.setObservaciones(dto.observaciones);
        turno.setPracticas(dto.practicas);
        turno.setCompaneros(dto.companeros);
        turno.setEspecial(dto.especial);

        // cálculo horas
        double horas = calcularHoras(turno.getHoraInicio(), turno.getHoraFin());
        turno.setHorasTotales(horas);

        // cálculo remuneración (ejemplo simple)
        turno.setRemuneracion(calcularRemuneracion(horas, turno.isEspecial()));

        turnos.add(turno);

        return toResponse(turno);
    }

    private double calcularHoras(LocalTime inicio, LocalTime fin) {
        return Duration.between(inicio, fin).toMinutes() / 60.0;
    }

    private double calcularRemuneracion(double horas, boolean especial) {
        double precioHora = especial ? 15.0 : 10.0;
        return horas * precioHora;
    }

    private TurnoResponseDTO toResponse(Turno turno) {
        TurnoResponseDTO dto = new TurnoResponseDTO();

        dto.id = turno.getId();
        dto.fecha = turno.getFecha().toString();
        dto.horaInicio = turno.getHoraInicio().toString();
        dto.horaFin = turno.getHoraFin().toString();

        dto.rol = turno.getRol();
        dto.observaciones = turno.getObservaciones();
        dto.practicas = turno.getPracticas();
        dto.companeros = turno.getCompaneros();
        dto.especial = turno.isEspecial();

        dto.horasTotales = turno.getHorasTotales();
        dto.remuneracion = turno.getRemuneracion();

        return dto;
    }
}
