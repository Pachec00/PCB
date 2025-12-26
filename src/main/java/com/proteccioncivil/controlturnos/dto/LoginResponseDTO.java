package com.proteccioncivil.controlturnos.dto;

public class LoginResponseDTO {

    public String token;
    public UsuarioDTO usuario;

    public static class UsuarioDTO {
        public String id;
        public String nombre;
    }
}
