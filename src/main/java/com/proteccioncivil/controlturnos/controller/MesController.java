package com.proteccioncivil.controlturnos.controller;

import com.proteccioncivil.controlturnos.dto.MesResponseDTO;
import com.proteccioncivil.controlturnos.service.MesService;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;

@RestController
@RequestMapping("/mes")
public class MesController {

    private final MesService mesService;

    public MesController(MesService mesService) {
        this.mesService = mesService;
    }

    @GetMapping("/{mes}")
    public MesResponseDTO obtenerMes(
            @PathVariable String mes,
            @RequestHeader("X-Usuario-Id") String usuarioId
    ) {
        return mesService.obtenerMes(usuarioId, YearMonth.parse(mes));
    }
}
