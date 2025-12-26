package com.proteccioncivil.controlturnos.dto;

import java.util.List;

public class TurnoResponseDTO {

    public String id;
    public String fecha;
    public String horaInicio;
    public String horaFin;

    public String rol;
    public String observaciones;

    public List<String> practicas;
    public List<String> companeros;

    public Boolean especial;

    public Double horasTotales;
    public Double remuneracion;
}
