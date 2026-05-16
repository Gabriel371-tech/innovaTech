package br.com.faculdadeinovatech.inovatech.service;

import br.com.faculdadeinovatech.inovatech.entity.Pedido;
import br.com.faculdadeinovatech.inovatech.entity.Produto;
import br.com.faculdadeinovatech.inovatech.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Integer id) {
        return pedidoRepository.findById(id);
    }

    @Transactional
    public Pedido save(Pedido pedido) {
        if (pedido.getDataPedido() == null) {
            pedido.setDataPedido(LocalDateTime.now());
        }
        if (pedido.getStatusPedido() == null) {
            pedido.setStatusPedido("PENDENTE");
        }

        // Vincular os itens ao pedido e atualizar estoque
        if (pedido.getItens() != null) {
            pedido.getItens().forEach(item -> {
                item.setPedido(pedido);
                
                // Diminuir estoque do produto
                if (item.getProduto() != null && item.getProduto().getIdProduto() != null) {
                    Produto produto = produtoService.findById(item.getProduto().getIdProduto())
                            .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduto().getIdProduto()));
                    
                    if (produto.getEstoqueProduto() < item.getQuantidade()) {
                        throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNomeProduto());
                    }
                    
                    produto.setEstoqueProduto(produto.getEstoqueProduto() - item.getQuantidade());
                    produtoService.save(produto);
                }
            });
        }

        return pedidoRepository.save(pedido);
    }

    public void deleteById(Integer id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido updateStatus(Integer id, String status) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        pedido.setStatusPedido(status);
        return pedidoRepository.save(pedido);
    }
}