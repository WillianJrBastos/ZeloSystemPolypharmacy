package com.zelo.service;

import com.zelo.entity.Medicamento;
import com.zelo.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoCalendarioService eventoCalendarioService;

    @Autowired
    private NotificacaoService notificacaoService;

    private static final int LIMITE_ESTOQUE_BAIXO = 5;

    public Medicamento cadastrar(Long usuarioId)
}
