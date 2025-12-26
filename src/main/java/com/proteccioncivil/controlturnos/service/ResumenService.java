package com.proteccioncivil.controlturnos.service;

import com.proteccioncivil.controlturnos.dto.ResumenMensualDTO;
import com.proteccioncivil.controlturnos.model.EstadoPago;
import com.proteccioncivil.controlturnos.model.Turno;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResumenService {

    private final TurnoService turnoService;
    private final PagoService pagoService;

    public ResumenService(TurnoService turnoService, PagoService pagoService) {
        this.turnoService = turnoService;
        this.pagoService = pagoService;
    }

    public List<ResumenMensualDTO> obtenerResumenMensual() {

        List<Turno> turnos = turnoService.obtenerTurnosInternos();
        List<EstadoPago> estados = pagoService.obtenerEstadosInternos();

        Map<YearMonth, List<Turno>> turnosPorMes = turnos.stream()
                .collect(Collectors.groupingBy(
                        t -> YearMonth.from(t.getFecha())
                ));

        return turnosPorMes.entrySet().stream().map(entry -> {

            YearMonth mes = entry.getKey();
            List<Turno> turnosMes = entry.getValue();

            double totalHoras = turnosMes.stream()
                    .mapToDouble(Turno::getHorasTotales)
                    .sum();

            double totalRemuneracion = turnosMes.stream()
                    .mapToDouble(Turno::getRemuneracion)
                    .sum();

            boolean pagado = estados.stream()
                    .filter(e -> e.getMes().equals(mes))
                    .findFirst()
                    .map(EstadoPago::isPagado)
                    .orElse(false);

            ResumenMensualDTO dto = new ResumenMensualDTO();
            dto.mes = mes.toString();
            dto.totalTurnos = turnosMes.size();
            dto.totalHoras = totalHoras;
            dto.totalRemuneracion = totalRemuneracion;
            dto.pagado = pagado;

            return dto;

        }).toList();
    }
}

