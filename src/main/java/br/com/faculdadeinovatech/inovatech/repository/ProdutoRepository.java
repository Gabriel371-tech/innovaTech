package br.com.faculdadeinovatech.inovatech.repository;

import br.com.faculdadeinovatech.inovatech.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByNomeProdutoContainingIgnoreCase(String nome);
}