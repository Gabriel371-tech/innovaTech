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
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProduto;

    @Column(nullable = false, length = 100)
    private String nomeProduto;

    @Column(nullable = false)
    private Double precoProduto;

    @Column(nullable = false)
    private Integer estoqueProduto;
}