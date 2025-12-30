package com.proteccioncivil.controlturnos.service;

import com.proteccioncivil.controlturnos.dto.TurnoRequestDTO;
import com.proteccioncivil.controlturnos.dto.TurnoResponseDTO;
import com.proteccioncivil.controlturnos.model.Turno;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.time.YearMonth;


@Service
public class TurnoService {

    private final PagoService pagoService;

    public TurnoService(PagoService pagoService) {
        this.pagoService = pagoService;
    }


    // almacenamiento temporal en memoria (luego Firestore)
    private final List<Turno> turnos = new ArrayList<>();

    public List<TurnoResponseDTO> obtenerTurnos() {
        return turnos.stream().map(this::toResponse).toList();
    }

    public TurnoResponseDTO crearTurno(TurnoRequestDTO dto) {

        YearMonth mes = YearMonth.from(LocalDate.parse(dto.fecha));

        if (pagoService.estaMesPagado(mes)) {
            throw new IllegalArgumentException(
                    "No se pueden crear turnos en un mes ya pagado (" + mes + ")"
            );
        }


        if (dto.fecha == null || dto.horaInicio == null || dto.horaFin == null) {
            throw new IllegalArgumentException("Fecha y horas son obligatorias");
        }

        LocalTime inicio = LocalTime.parse(dto.horaInicio);
        LocalTime fin = LocalTime.parse(dto.horaFin);

        if (fin.isBefore(inicio)) {
            throw new IllegalArgumentException("La hora fin no puede ser anterior a la hora inicio");
        }

        Turno turno = new Turno();
        turno.setId(UUID.randomUUID().toString());

        turno.setFecha(LocalDate.parse(dto.fecha));
        turno.setHoraInicio(LocalTime.parse(dto.horaInicio));
        turno.setHoraFin(LocalTime.parse(dto.horaFin));

        turno.setRol(dto.rol);
        turno.setObservaciones(dto.observaciones);
        turno.setPracticas(dto.practicas);
        turno.setCompaneros(dto.companeros != null ? dto.companeros : new ArrayList<>());
        turno.setEspecial(dto.especial);

        turno.setUsuarioId(dto.usuarioId);

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

    TurnoResponseDTO toResponse(Turno turno) {
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

    public TurnoResponseDTO actualizarTurno(String id, TurnoRequestDTO dto) {

        Turno turno = turnos.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado"));

        YearMonth mes = YearMonth.from(turno.getFecha());

        if (pagoService.estaMesPagado(mes)) {
            throw new IllegalArgumentException(
                    "No se pueden modificar turnos de un mes ya pagado (" + mes + ")"
            );
        }


        if (dto.fecha != null) {
            turno.setFecha(LocalDate.parse(dto.fecha));
        }
        if (dto.horaInicio != null) {
            turno.setHoraInicio(LocalTime.parse(dto.horaInicio));
        }
        if (dto.horaFin != null) {
            turno.setHoraFin(LocalTime.parse(dto.horaFin));
        }

        turno.setRol(dto.rol);
        turno.setObservaciones(dto.observaciones);
        turno.setPracticas(dto.practicas);
        turno.setCompaneros(dto.companeros);

        if (dto.especial != null) {
            turno.setEspecial(dto.especial);
        }

        double horas = calcularHoras(turno.getHoraInicio(), turno.getHoraFin());
        turno.setHorasTotales(horas);
        turno.setRemuneracion(calcularRemuneracion(horas, turno.isEspecial()));

        return toResponse(turno);
    }


    public void borrarTurno(String id) {

        Turno turno = turnos.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Turno no encontrado"));

        YearMonth mes = YearMonth.from(turno.getFecha());

        if (pagoService.estaMesPagado(mes)) {
            throw new IllegalArgumentException(
                    "No se pueden borrar turnos de un mes ya pagado (" + mes + ")"
            );
        }

        turnos.remove(turno);
    }


    public List<Turno> obtenerTurnosInternos() {
        return turnos;
    }

    public List<Turno> obtenerTurnosDeUsuarioYMes(String usuarioId, YearMonth mes) {
        return turnos.stream()
                .filter(t -> t.getUsuarioId().equalsIgnoreCase(usuarioId))
                .filter(t -> YearMonth.from(t.getFecha()).equals(mes))
                .toList();
    }



}
