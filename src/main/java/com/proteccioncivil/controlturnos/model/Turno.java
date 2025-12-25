package com.proteccioncivil.controlturnos.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Turno {

    private String id;
    private String usuarioId;

    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    private String rol;
    private String observaciones;

    private List<String> practicas;
    private List<String> companeros;

    private boolean especial;

    private double horasTotales;
    private double remuneracion;

    // getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<String> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<String> practicas) {
        this.practicas = practicas;
    }

    public List<String> getCompaneros() {
        return companeros;
    }

    public void setCompaneros(List<String> companeros) {
        this.companeros = companeros;
    }

    public boolean isEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public double getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(double horasTotales) {
        this.horasTotales = horasTotales;
    }

    public double getRemuneracion() {
        return remuneracion;
    }

    public void setRemuneracion(double remuneracion) {
        this.remuneracion = remuneracion;
    }
}
