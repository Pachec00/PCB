package com.proteccioncivil.controlturnos.dto;

import java.util.List;

public class MesResponseDTO {

    public UsuarioDTO usuario;
    public String mes;
    public boolean pagado;
    public boolean editable;
    public ResumenDTO resumen;
    public List<TurnoResponseDTO> turnos;

    public static class UsuarioDTO {
        public String id;      // B-16
        public String nombre;
    }

    public static class ResumenDTO {
        public double totalHoras;
        public double totalRemuneracion;
    }
}
