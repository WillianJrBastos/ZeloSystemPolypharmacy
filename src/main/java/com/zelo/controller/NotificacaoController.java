package com.zelo.controller;

import com.zelo.dto.NotificacaoDTO;
import com.zelo.entity.Notificacao;
import com.zelo.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class NotificacaoController {

    @Autowired
    private NotificacaoService notificacaoService;

    @GetMapping("/api/usuarios/{usuarioId}/notificacoes")
    public List<NotificacaoDTO> listar(@PathVariable Long usuarioId) {
        return notificacaoService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/api/usuarios/{usuarioId}/notificacoes/contagem")
    public Map<String, Long> contarNaoLidas(@PathVariable Long usuarioId) {
        return Map.of("naoLidas", notificacaoService.contarNaoLidas(usuarioId));
    }

    @PatchMapping("/api/notificacoes/{id}/lida")
    public Notificacao marcarComoLida(@PathVariable Long id) {
        return notificacaoService.marcarComoLida(id);
    }
}
