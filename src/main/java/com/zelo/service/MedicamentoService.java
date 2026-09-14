package com.zelo.service;

import com.zelo.dto.MedicamentoDTO;
import com.zelo.entity.Medicamento;
import com.zelo.entity.Usuario;
import com.zelo.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void verificarEstoqueBaixo(Medicamento medicamento) {
        if (medicamento.getQuantidadeEstoque() <= LIMITE_ESTOQUE_BAIXO) {
            eventoCalendarioService.criarAlertaEstoqueBaixo(medicamento);
            notificacaoService.notificarEstoqueBaixo(medicamento.getUsuario(), medicamento.getNome(), medicamento.getQuantidadeEstoque());
        }
    }
    public Medicamento cadastrar(Long usuarioId, MedicamentoDTO dto) {
        Usuario usuario = usuarioService.buscarPorId(usuarioId);
        Medicamento medicamento = new Medicamento();
        medicamento.setNome(dto.getNome());
        medicamento.setDosagem(dto.getDosagem());
        medicamento.setFormato(dto.getFormato());
        medicamento.setViaAdministracao(dto.getViaAdministracao());
        medicamento.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        medicamento.setAtivo(true);
        medicamento.setUsuario(usuario);

        Medicamento salvo = medicamentoRepository.save(medicamento);
        verificarEstoqueBaixo(salvo);
        return salvo;
    }

    public List<Medicamento> listarPorUsuario(Long usuarioId) {
        return medicamentoRepository.findByUsuarioId(usuarioId);
    }

    public Medicamento buscarPorId(Long id) {
        return medicamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("Medicamento não encontrado"));
    }

    public Medicamento atualizar(Long id, MedicamentoDTO dto) {
        Medicamento medicamento = buscarPorId(id);
        medicamento.setNome(dto.getNome());
        medicamento.setDosagem(dto.getDosagem());
        medicamento.setFormato(dto.getFormato());
        medicamento.setViaAdministracao(dto.getViaAdministracao());
        medicamento.setQuantidadeEstoque(dto.getQuantidadeEstoque());

        Medicamento salvo = medicamentoRepository.save(medicamento);
        verificarEstoqueBaixo(salvo);
        return salvo;
    }

    public void deletar(Long id) {
        medicamentoRepository.deleteById(id);
    }

    public Medicamento alterarStatus(Long id, boolean ativo) {
        Medicamento medicamento = buscarPorId(id);
        medicamento.setAtivo(ativo);
        return medicamentoRepository.save(medicamento);
    }

    public Medicamento darBaixaEstoque(Long id, int quantidade) {
        Medicamento medicamento = buscarPorId(id);
        int quantidadeNova = medicamento.getQuantidadeEstoque() - quantidade;
        medicamento.setQuantidadeEstoque(Math.max(quantidadeNova, 0));

        Medicamento salvo = medicamentoRepository.save(medicamento);
        verificarEstoqueBaixo(salvo);
        return salvo;
    }
}
