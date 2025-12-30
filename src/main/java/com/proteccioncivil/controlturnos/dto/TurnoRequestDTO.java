package com.proteccioncivil.controlturnos.dto;

import java.util.List;

public class TurnoRequestDTO {

    public String fecha;        // yyyy-MM-dd
    public String horaInicio;   // HH:mm
    public String horaFin;      // HH:mm

    public String rol;
    public String observaciones;

    public boolean practicas;
    public List<String> companeros;

    public Boolean especial;

    public String usuarioId; // "B-16"

}
