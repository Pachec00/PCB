package com.proteccioncivil.controlturnos.dto;

import java.util.ArrayList;
import java.util.List;

public class MesResponseDTO {

    public String usuario;
    public String mes;
    public boolean pagado;
    public boolean editable;

    public Resumen resumen = new Resumen();
    public List<TurnoResponseDTO> turnos = new ArrayList<>();

    public static class Resumen {
        public double totalHoras = 0;
        public double totalRemuneracion = 0;
    }
}
