package com.sistema.inventarioapp.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produto_detalhes")
@Getter // geram automaticamente métodos getters
@Setter // geram automaticamente métodos setters
@AllArgsConstructor // Um construtor que aceita todos os atributos da classe
@NoArgsConstructor // permite criar uma instância da classe sem fornecer nenhum argumento
@EqualsAndHashCode(of = "id") // gerar automaticamente os métodos equals() e hashCode()
public class ProdutoDetalhes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String nome; 

    @Column(length = 45, nullable = false)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public ProdutoDetalhes(String nome, String valor, Produto produto) {
        this.nome = nome;
        this.valor = valor;
        this.produto = produto;
    }
}
