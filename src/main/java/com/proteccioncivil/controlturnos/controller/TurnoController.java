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
        return turnoService.crearTurno(dto);
    }
}
