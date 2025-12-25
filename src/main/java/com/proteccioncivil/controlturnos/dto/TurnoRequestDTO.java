package com.proteccioncivil.controlturnos.dto;

import java.util.List;

public class TurnoRequestDTO {

    public String fecha;        // yyyy-MM-dd
    public String horaInicio;   // HH:mm
    public String horaFin;      // HH:mm

    public String rol;
    public String observaciones;

    public List<String> practicas;
    public List<String> companeros;

    public boolean especial;
}
