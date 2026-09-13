package com.zelo.service;

import com.zelo.dto.EventoCalendarioDTO;
import com.zelo.entity.EventoCalendario;
import com.zelo.entity.Usuario;
import com.zelo.repository.EventoCalendarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoCalendarioService {

    @Autowired
    private EventoCalendarioRepository eventoCalendarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private NotificacaoService notificacaoService;

    public EventoCalendario cadastrar(Long usuarioId, EventoCalendarioDTO dto) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        EventoCalendario eventoCalendario = new EventoCalendario();
        eventoCalendario.setTitulo(dto.getTitulo());
        eventoCalendario.setDescricao(dto.getDescricao());
        eventoCalendario.setDataHora(dto.getDataHora());
        eventoCalendario.setTipo(dto.getTipo());
        eventoCalendario.setUsuario(usuario);

        EventoCalendario salvo = eventoCalendarioRepository.save(eventoCalendario);
        notificacaoService.notificarEvento(usuario, dto.getTitulo(), dto.getTipo(), dto.getDataHora());
        return salvo;
    }
}
