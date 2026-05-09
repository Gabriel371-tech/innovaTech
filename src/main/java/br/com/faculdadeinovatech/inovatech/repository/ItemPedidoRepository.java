package br.com.faculdadeinovatech.inovatech.repository;

import br.com.faculdadeinovatech.inovatech.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}