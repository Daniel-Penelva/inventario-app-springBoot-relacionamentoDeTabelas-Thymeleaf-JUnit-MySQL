package com.sistema.inventarioapp.carrinhocompras;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sistema.inventarioapp.produto.Produto;
import com.sistema.inventarioapp.usuario.Usuario;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "item_carrinho")
@Getter // geram automaticamente métodos getters
@Setter // geram automaticamente métodos setters
@AllArgsConstructor // Um construtor que aceita todos os atributos da classe
@NoArgsConstructor // permite criar uma instância da classe sem fornecer nenhum argumento
@EqualsAndHashCode(of = "id") // gerar automaticamente os métodos equals() e hashCode()
public class ItemCarrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    Produto produto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario usuario;

    public ItemCarrinho(int quantidade, Produto produto, Usuario usuario) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.usuario = usuario;
    }
}
