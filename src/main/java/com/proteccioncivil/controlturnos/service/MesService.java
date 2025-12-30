package com.proteccioncivil.controlturnos.service;

import com.proteccioncivil.controlturnos.dto.MesResponseDTO;
import com.proteccioncivil.controlturnos.dto.TurnoResponseDTO;
import com.proteccioncivil.controlturnos.model.Turno;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
public class MesService {

    private final TurnoService turnoService;
    private final PagoService pagoService;

    public MesService(TurnoService turnoService, PagoService pagoService) {
        this.turnoService = turnoService;
        this.pagoService = pagoService;
    }

    public MesResponseDTO getMes(String usuarioId, String mesStr) {

        System.out.println("➡ getMes()");
        System.out.println("Usuario: " + usuarioId);
        System.out.println("Mes raw: " + mesStr);

        YearMonth mes;
        try {
            mes = YearMonth.parse(mesStr); // yyyy-MM
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato de mes inválido: " + mesStr);
        }

        List<Turno> turnos = turnoService.obtenerTurnosDeUsuarioYMes(usuarioId, mes);

        List<TurnoResponseDTO> turnosDTO = turnos.stream()
                .map(turnoService::toResponse)
                .toList();

        MesResponseDTO dto = new MesResponseDTO();
        dto.usuario = usuarioId;
        dto.mes = mes.toString();
        dto.pagado = pagoService.estaMesPagado(mes);
        dto.editable = !dto.pagado;
        dto.turnos = turnosDTO;

        dto.resumen.totalHoras = turnosDTO.stream()
                .mapToDouble(t -> t.horasTotales)
                .sum();

        dto.resumen.totalRemuneracion = turnosDTO.stream()
                .mapToDouble(t -> t.remuneracion)
                .sum();

        return dto;
    }
}
