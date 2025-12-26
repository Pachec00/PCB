package com.proteccioncivil.controlturnos.model;

public class Usuario {

    private String id;        // EJ: "B-16"
    private String username;  // puede ser el mismo o distinto
    private String password;
    private String nombre;

    public Usuario(String id, String username, String password, String nombre) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }
}
