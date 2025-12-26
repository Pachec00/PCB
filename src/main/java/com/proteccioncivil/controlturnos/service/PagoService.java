package com.proteccioncivil.controlturnos.service;

import com.proteccioncivil.controlturnos.dto.EstadoPagoRequestDTO;
import com.proteccioncivil.controlturnos.dto.EstadoPagoResponseDTO;
import com.proteccioncivil.controlturnos.model.EstadoPago;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class PagoService {

    // almacenamiento temporal (luego BD)
    private final List<EstadoPago> estados = new ArrayList<>();

    public List<EstadoPagoResponseDTO> obtenerEstados() {
        return estados.stream().map(this::toResponse).toList();
    }

    public EstadoPagoResponseDTO actualizarEstado(String mesStr, EstadoPagoRequestDTO dto) {

        YearMonth mes = YearMonth.parse(mesStr);

        EstadoPago estado = estados.stream()
                .filter(e -> e.getMes().equals(mes))
                .findFirst()
                .orElseGet(() -> {
                    EstadoPago nuevo = new EstadoPago(mes, false);
                    estados.add(nuevo);
                    return nuevo;
                });

        estado.setPagado(dto.pagado);

        return toResponse(estado);
    }

    private EstadoPagoResponseDTO toResponse(EstadoPago estado) {
        EstadoPagoResponseDTO dto = new EstadoPagoResponseDTO();
        dto.mes = estado.getMes().toString();
        dto.pagado = estado.isPagado();
        return dto;
    }

    public List<EstadoPago> obtenerEstadosInternos() {
        return estados;
    }

    public boolean estaMesPagado(YearMonth mes) {
        return estados.stream()
                .filter(e -> e.getMes().equals(mes))
                .findFirst()
                .map(EstadoPago::isPagado)
                .orElse(false);
    }


}
