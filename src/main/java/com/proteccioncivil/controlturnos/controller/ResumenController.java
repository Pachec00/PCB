package com.proteccioncivil.controlturnos.controller;

import com.proteccioncivil.controlturnos.dto.ResumenMensualDTO;
import com.proteccioncivil.controlturnos.service.ResumenService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResumenController {

    private final ResumenService resumenService;

    public ResumenController(ResumenService resumenService) {
        this.resumenService = resumenService;
    }

    @GetMapping("/resumen-mensual")
    public List<ResumenMensualDTO> obtenerResumen() {
        return resumenService.obtenerResumenMensual();
    }
}
