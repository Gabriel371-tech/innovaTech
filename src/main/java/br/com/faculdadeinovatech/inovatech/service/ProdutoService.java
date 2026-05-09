package br.com.faculdadeinovatech.inovatech.service;

import br.com.faculdadeinovatech.inovatech.entity.Produto;
import br.com.faculdadeinovatech.inovatech.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Integer id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deleteById(Integer id) {
        produtoRepository.deleteById(id);
    }

    public Produto update(Integer id, Produto produtoAtualizado) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setNomeProduto(produtoAtualizado.getNomeProduto());
        produto.setPrecoProduto(produtoAtualizado.getPrecoProduto());
        produto.setEstoqueProduto(produtoAtualizado.getEstoqueProduto());
        return produtoRepository.save(produto);
    }
}