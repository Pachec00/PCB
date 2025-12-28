package com.proteccioncivil.controlturnos.controller;

import com.proteccioncivil.controlturnos.dto.LoginRequestDTO;
import com.proteccioncivil.controlturnos.dto.LoginRequestDTO;
import com.proteccioncivil.controlturnos.dto.LoginResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {

        if ("B-16".equals(request.getUsername())
                && "1234".equals(request.getPassword())) {

            LoginResponseDTO response = new LoginResponseDTO();
            response.token = ""; // más adelante JWT / Firebase

            LoginResponseDTO.UsuarioDTO usuario = new LoginResponseDTO.UsuarioDTO();
            usuario.id = "B-16";
            usuario.nombre = "Usuario B-16";

            response.usuario = usuario;
            return response;
        }

        throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "Credenciales incorrectas"
        );
    }
}
