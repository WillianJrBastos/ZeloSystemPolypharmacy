package com.zelo.service;

import com.zelo.dto.NotificacaoDTO;
import com.zelo.entity.Notificacao;
import com.zelo.entity.Usuario;
import com.zelo.repository.NotificacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    private NotificacaoDTO transformarDTO(Notificacao n) {
        return new NotificacaoDTO(n.getId(), n.getTitulo(), n.getDescricao(), n.getDataHora(), n.getTipo(), n.getLida());
    }

    private void criar(Usuario usuario, String titulo, String descricao, String tipo, LocalDateTime dataHora) {
        Notificacao notificacao = new Notificacao();
        notificacao.setTitulo(titulo);
        notificacao.setDescricao(descricao);
        notificacao.setDataHora(dataHora);
        notificacao.setTipo(tipo);
        notificacao.setLida(false);
        notificacao.setUsuario(usuario);
        notificacaoRepository.save(notificacao);
    }

    public List<NotificacaoDTO> listarPorUsuario(Long usuarioId) {
        return notificacaoRepository.findByUsuarioIdOrderByDataHoraDesc(usuarioId).stream().map(this::transformarDTO).toList();
    }

    public long contarNaoLidas(Long usuarioId) {
        return notificacaoRepository.countByUsuarioIdAndLidaFalse(usuarioId);
    }

    public Notificacao marcarComoLida(Long id) {
        Notificacao notificacao = notificacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Notificação não encontrada"));
        notificacao.setLida(true);
        return notificacaoRepository.save(notificacao);
    }
}
