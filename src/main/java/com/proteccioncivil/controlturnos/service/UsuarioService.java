package com.proteccioncivil.controlturnos.service;

import com.proteccioncivil.controlturnos.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final List<Usuario> usuarios = List.of(
            new Usuario("B-16", "b16", "1234", "Juan Pérez"),
            new Usuario("A-03", "a03", "1234", "Ana López")
    );

    public Optional<Usuario> autenticar(String username, String password) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username)
                        && u.getPassword().equals(password))
                .findFirst();
    }

    public Optional<Usuario> buscarPorId(String id) {
        return usuarios.stream()
                .filter(u -> u.getId().equalsIgnoreCase(id))
                .findFirst();
    }
}

