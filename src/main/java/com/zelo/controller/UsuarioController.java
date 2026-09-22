package com.zelo.controller;

import com.zelo.dto.CadastroUsuarioDTO;
import com.zelo.dto.EditarPerfilDTO;
import com.zelo.dto.InformacoesSaudeDTO;
import com.zelo.entity.Usuario;
import com.zelo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody CadastroUsuarioDTO dto) {
        return usuarioService.atualizar(id, dto);
    }

    @PutMapping("/{id}/perfil")
    public Usuario editarPerfil(@PathVariable Long id, @RequestBody EditarPerfilDTO dto) {
        return usuarioService.editarPerfil(id, dto);
    }

    @PutMapping("/{id}/saude")
    public Usuario editarInformacoesSaude(@PathVariable Long id, @RequestBody InformacoesSaudeDTO dto) {
        return usuarioService.editarInformacoesSaude(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        usuarioService.deletar(id);
    }
}
