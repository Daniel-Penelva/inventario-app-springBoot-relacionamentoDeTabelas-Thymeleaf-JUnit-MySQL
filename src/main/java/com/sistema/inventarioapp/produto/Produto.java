package com.sistema.inventarioapp.produto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sistema.inventarioapp.categoria.Categoria;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter // geram automaticamente métodos getters
@Setter // geram automaticamente métodos setters
@AllArgsConstructor // Um construtor que aceita todos os atributos da classe
@NoArgsConstructor // permite criar uma instância da classe sem fornecer nenhum argumento
@EqualsAndHashCode(of = "id") // gerar automaticamente os métodos equals() e hashCode()
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String nome; 

    private float preco;

    // Muitos produtos (uma categoria pode ter muitos produtos)
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private List<ProdutoDetalhes> produtoDetalhes = new ArrayList<>();
 

    public Produto(String nome, float preco, Categoria categoria) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
    }


    public Produto(String nome) {
        this.nome = nome;
    }

    // Adicionar produtos detalhes para um array
    public void adicionarDetalhes(String nome, String valor){
        this.produtoDetalhes.add(new ProdutoDetalhes(nome, valor, this));

        // OBS. O 'this' é o produto. Como está definido no Construtor ProdutoDetalhes().
    }

    // Carregar produtos detalhes para um array
    public void carregarDetalhes(Integer id, String nome, String valor){
        this.produtoDetalhes.add(new ProdutoDetalhes(id, nome, valor, this));
    }


}
