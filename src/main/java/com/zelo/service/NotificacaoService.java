package com.zelo.service;

import com.zelo.dto.NotificacaoDTO;
import com.zelo.entity.Notificacao;
import com.zelo.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    private NotificacaoDTO transformarDTO(Notificacao n) {
        return new NotificacaoDTO(n.getId(), n.getTitulo(), n.getDescricao(), n.getDataHora(), n.getTipo(), n.getLida());
    }

    public List<NotificacaoDTO> listarPorUsuario(Long usuarioId) {
        return notificacaoRepository.findByUsuarioIdOrderByDataHoraDesc(usuarioId).stream().map(this::transformarDTO).toList();
    }
}
