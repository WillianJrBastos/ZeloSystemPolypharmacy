package com.zelo.repository;

import com.zelo.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByUsuarioIdOrderByDataHoraDesc(Long usuarioId);

    long countByUsuarioIdAndLidaFalse(Long usuarioId);

    boolean existsByUsuarioIdAndTipoAndTituloAndLidaFalse(Long usuarioId, String tipo, String titulo);
}
