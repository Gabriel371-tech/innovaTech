package br.com.faculdadeinovatech.inovatech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPedido;

    @Column(nullable = false)
    private LocalDateTime dataPedido;

    @Column(nullable = false, length = 20)
    private String statusPedido; // PENDENTE, CONCLUIDO, CANCELADO

    @ManyToOne
    @JoinColumn(name = "idAluno_fk")
    private Aluno aluno;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public Double getTotalPedido() {
        return itens.stream()
                .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade())
                .sum();
    }
}