package com.proteccioncivil.controlturnos.controller;

import com.proteccioncivil.controlturnos.dto.LoginRequestDTO;
import com.proteccioncivil.controlturnos.dto.LoginResponseDTO;
import com.proteccioncivil.controlturnos.model.Usuario;
import com.proteccioncivil.controlturnos.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {

        Usuario usuario = usuarioService
                .autenticar(request.username, request.password)
                .orElseThrow(() -> new IllegalArgumentException("Credenciales incorrectas"));

        LoginResponseDTO response = new LoginResponseDTO();
        response.token = "fake-token-" + usuario.getId();

        LoginResponseDTO.UsuarioDTO usuarioDTO = new LoginResponseDTO.UsuarioDTO();
        usuarioDTO.id = usuario.getId();
        usuarioDTO.nombre = usuario.getNombre();

        response.usuario = usuarioDTO;

        return response;
    }
}
