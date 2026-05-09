package br.com.faculdadeinovatech.inovatech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idItemPedido;

    @ManyToOne
    @JoinColumn(name = "idPedido_fk", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "idProduto_fk", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double precoUnitario;
}