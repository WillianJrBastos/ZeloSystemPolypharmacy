package com.zelo.service;

import com.zelo.entity.Alarme;
import com.zelo.entity.RegistroMedicacao;
import com.zelo.repository.AlarmeRepository;
import com.zelo.repository.RegistroMedicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class RegistroMedicacaoService {

    @Autowired
    private RegistroMedicacaoRepository registroMedicacaoRepository;

    @Autowired
    private AlarmeRepository alarmeRepository;

    @Autowired
    private AlarmeService alarmeService;

    @Autowired
    private MedicamentoService medicamentoService;

    public RegistroMedicacao confirmarIngestao(Long alarmeId, LocalDateTime dataHoraAgendada) {
        Alarme alarme = alarmeService.buscarPorId(alarmeId);
        RegistroMedicacao registroMedicacao = new RegistroMedicacao();
        registroMedicacao.setDataHoraAgendada(dataHoraAgendada);
        registroMedicacao.setDataHoraAcao(LocalDateTime.now());
        registroMedicacao.setStatus("TOMADO");
        registroMedicacao.setAlarme(alarme);
        medicamentoService.darBaixaEstoque(alarme.getMedicamento().getId(), 1);
        return registroMedicacaoRepository.save(registroMedicacao);
    }

    public RegistroMedicacao ignorar(Long alarmeId, LocalDateTime dataHoraAgendada) {
        Alarme alarme = alarmeService.buscarPorId(alarmeId);
        RegistroMedicacao registroMedicacao = new RegistroMedicacao();
        registroMedicacao.setDataHoraAgendada(dataHoraAgendada);
        registroMedicacao.setDataHoraAcao(LocalDateTime.now());
        registroMedicacao.setStatus("IGNORADO");
        registroMedicacao.setAlarme(alarme);
        return registroMedicacaoRepository.save(registroMedicacao);
    }

    public List<RegistroMedicacao> listarPorUsuario(Long usuarioId) {
        return registroMedicacaoRepository.findByAlarmeMedicamentoUsuarioIdOrderByDataHoraAgendadaDesc(usuarioId);
    }
}
