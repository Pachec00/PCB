package com.proteccioncivil.controlturnos.controller;

import com.proteccioncivil.controlturnos.dto.TurnoRequestDTO;
import com.proteccioncivil.controlturnos.dto.TurnoResponseDTO;
import com.proteccioncivil.controlturnos.service.TurnoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @GetMapping
    public List<TurnoResponseDTO> obtenerTurnos() {
        return turnoService.obtenerTurnos();
    }

    @PostMapping
    public TurnoResponseDTO crearTurno(@RequestBody TurnoRequestDTO dto) {
        System.out.println("DTO recibido:");
        System.out.println("usuarioId = " + dto.usuarioId);
        System.out.println("fecha = " + dto.fecha);
        System.out.println("horaInicio = " + dto.horaInicio);
        System.out.println("horaFin = " + dto.horaFin);
        System.out.println("rol = " + dto.rol);
        System.out.println("especial = " + dto.especial);

        return turnoService.crearTurno(dto);
    }


    @PutMapping("/{id}")
    public TurnoResponseDTO actualizarTurno(
            @PathVariable String id,
            @RequestBody TurnoRequestDTO dto
    ) {
        return turnoService.actualizarTurno(id, dto);
    }

    @DeleteMapping("/{id}")
    public void borrarTurno(@PathVariable String id) {
        turnoService.borrarTurno(id);
    }



}
