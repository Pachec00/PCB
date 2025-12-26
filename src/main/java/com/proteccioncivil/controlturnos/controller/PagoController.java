package com.proteccioncivil.controlturnos.controller;

import com.proteccioncivil.controlturnos.dto.EstadoPagoRequestDTO;
import com.proteccioncivil.controlturnos.dto.EstadoPagoResponseDTO;
import com.proteccioncivil.controlturnos.service.PagoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public List<EstadoPagoResponseDTO> obtenerEstados() {
        return pagoService.obtenerEstados();
    }

    @PutMapping("/{mes}")
    public EstadoPagoResponseDTO actualizarEstado(
            @PathVariable String mes,
            @RequestBody EstadoPagoRequestDTO dto
    ) {
        return pagoService.actualizarEstado(mes, dto);
    }
}
