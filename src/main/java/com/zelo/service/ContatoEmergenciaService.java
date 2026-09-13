package com.zelo.service;

import com.zelo.dto.ContatoEmergenciaDTO;
import com.zelo.entity.ContatoEmergencia;
import com.zelo.entity.Usuario;
import com.zelo.repository.ContatoEmergenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContatoEmergenciaService {

    @Autowired
    private ContatoEmergenciaRepository contatoEmergenciaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public ContatoEmergencia cadastrar(Long usuarioId, ContatoEmergenciaDTO dto) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        ContatoEmergencia contatoEmergencia = new ContatoEmergencia();
        contatoEmergencia.setNome(dto.getNome());
        contatoEmergencia.setTelefone(dto.getTelefone());
        contatoEmergencia.setParentesco(dto.getParentesco());
        contatoEmergencia.setUsuario(usuario);
        return contatoEmergenciaRepository.save(contatoEmergencia);
    }
}
