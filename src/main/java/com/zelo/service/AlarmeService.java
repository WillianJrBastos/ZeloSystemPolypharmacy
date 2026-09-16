package com.zelo.service;

import com.zelo.dto.AlarmeDTO;
import com.zelo.entity.Alarme;
import com.zelo.entity.Medicamento;
import com.zelo.repository.AlarmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmeService {

    @Autowired
    private AlarmeRepository alarmeRepository;

    @Autowired
    private MedicamentoService medicamentoService;

    public Alarme cadastrar(AlarmeDTO dto) {
        Medicamento medicamento = medicamentoService.buscarPorId(dto.getMedicamentoId());
        Alarme alarme = new Alarme();
        alarme.setHora(dto.getHora());
        alarme.setAtivo(true);
        alarme.setAdiarMinutos(dto.getAdiarMinutos() != null ? dto.getAdiarMinutos() : 10);
        alarme.setMedicamento(medicamento);
        return alarmeRepository.save(alarme);
    }

    public List<Alarme> listarPorMedicamento(Long medicamentoId) {
        return alarmeRepository.findByMedicamentoId(medicamentoId);
    }

    public List<Alarme> listarAtivosPorUsuario(Long usuarioId) {
        return alarmeRepository.findByMedicamentoUsuarioIdAndAtivoTrue(usuarioId);
    }

    public Alarme buscarPorId(Long id) {
        return alarmeRepository.findById(id).orElseThrow(() -> new RuntimeException("Alarme não encontrado"));
    }

    public void deletar(Long id) {
        alarmeRepository.deleteById(id);
    }

    public Alarme atualizar(Long id, AlarmeDTO dto) {
        Alarme alarme = buscarPorId(id);
        alarme.setHora(dto.getHora());
        if (dto.getAdiarMinutos() != null) {
            alarme.setAdiarMinutos((dto.getAdiarMinutos()));
        }
        return alarmeRepository.save(alarme);
    }
}
