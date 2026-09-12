package com.zelo.service;

import com.zelo.dto.CadastroUsuarioDTO;
import com.zelo.dto.EditarPerfilDTO;
import com.zelo.dto.InformacoesSaudeDTO;
import com.zelo.dto.LoginDTO;
import com.zelo.entity.Usuario;
import com.zelo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrar(CadastroUsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setTipoUsuario(dto.getTipoUsuario() != null ? dto.getTipoUsuario() : "PACIENTE");
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> login(LoginDTO dto) {
        return usuarioRepository.findByEmailAndSenha(dto.getEmail(), dto.getSenha());
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario atualizar(Long id, CadastroUsuarioDTO dto) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        if(dto.getSenha() != null && !dto.getSenha().isBlank()) {
            usuario.setSenha(dto.getSenha());
        }
        return usuarioRepository.save(usuario);
    }

    public Usuario editarPerfil(Long id, EditarPerfilDTO dto) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(dto.getNome());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setTelefone(dto.getTelefone());
        usuario.setFotoUrl(dto.getFotoUrl());
        return usuarioRepository.save(usuario);
    }

    public Usuario editarInformacoesSaude(Long id, InformacoesSaudeDTO dto) {
        Usuario usuario = buscarPorId(id);
        usuario.setTipoSanguineo(dto.getTipoSanguineo());
        usuario.setAlergias(dto.getAlergias());
        usuario.setCondicaoSaude(dto.getCondicaoSaude());
        usuario.setObservacoesImportantes(dto.getObservacoesImportantes());
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }
}
