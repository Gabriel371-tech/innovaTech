package br.com.faculdadeinovatech.inovatech.repository;

import br.com.faculdadeinovatech.inovatech.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByAluno_IdAluno(Integer idAluno);
}