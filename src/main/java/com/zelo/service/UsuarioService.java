package com.zelo.service;

import com.zelo.dto.CadastroUsuarioDTO;
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
}
