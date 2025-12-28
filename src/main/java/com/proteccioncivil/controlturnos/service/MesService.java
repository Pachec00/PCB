package com.proteccioncivil.controlturnos.service;

import com.proteccioncivil.controlturnos.dto.MesResponseDTO;
import com.proteccioncivil.controlturnos.dto.TurnoResponseDTO;
import com.proteccioncivil.controlturnos.model.Turno;
import com.proteccioncivil.controlturnos.model.Usuario;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
public class MesService {

    private final TurnoService turnoService;
    private final PagoService pagoService;
    private final UsuarioService usuarioService;

    public MesService(TurnoService turnoService,
                      PagoService pagoService,
                      UsuarioService usuarioService) {
        this.turnoService = turnoService;
        this.pagoService = pagoService;
        this.usuarioService = usuarioService;
    }

    public MesResponseDTO obtenerMes(String usuarioId, YearMonth mes) {

        Usuario usuario = usuarioService.buscarPorId(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        List<Turno> turnos = turnoService.obtenerTurnosDeUsuarioYMes(usuarioId, mes);

        boolean pagado = pagoService.estaMesPagado(mes);
        boolean editable = !pagado;

        double totalHoras = turnos.stream()
                .mapToDouble(Turno::getHorasTotales)
                .sum();

        double totalRemuneracion = turnos.stream()
                .mapToDouble(Turno::getRemuneracion)
                .sum();

        MesResponseDTO response = new MesResponseDTO();

        MesResponseDTO.UsuarioDTO usuarioDTO = new MesResponseDTO.UsuarioDTO();
        usuarioDTO.id = usuario.getId();
        usuarioDTO.nombre = usuario.getNombre();

        MesResponseDTO.ResumenDTO resumenDTO = new MesResponseDTO.ResumenDTO();
        resumenDTO.totalHoras = totalHoras;
        resumenDTO.totalRemuneracion = totalRemuneracion;

        response.usuario = usuarioDTO;
        response.mes = mes.toString();
        response.pagado = pagado;
        response.editable = editable;
        response.resumen = resumenDTO;
        response.turnos = turnos.stream()
                .map(turnoService::toResponse)
                .toList();

        return response;
    }
}
