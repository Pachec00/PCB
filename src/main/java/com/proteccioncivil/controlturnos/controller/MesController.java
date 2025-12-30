package com.proteccioncivil.controlturnos.controller;

import com.proteccioncivil.controlturnos.dto.MesResponseDTO;
import com.proteccioncivil.controlturnos.service.MesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mes")
@CrossOrigin
public class MesController {

    private final MesService mesService;

    public MesController(MesService mesService) {
        this.mesService = mesService;
    }

    @GetMapping("/{mes}")
    public MesResponseDTO getMes(
            @PathVariable String mes,
            @RequestHeader("X-Usuario") String usuarioId
    ) {
        return mesService.getMes(usuarioId, mes);
    }
}
