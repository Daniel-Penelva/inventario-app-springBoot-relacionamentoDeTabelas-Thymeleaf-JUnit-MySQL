package com.sistema.inventarioapp.marca;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.sistema.inventarioapp.categoria.Categoria;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter  // geram automaticamente métodos getters
@Setter  // geram automaticamente métodos setters 
@AllArgsConstructor  // Um construtor que aceita todos os atributos da classe
@NoArgsConstructor   // permite criar uma instância da classe sem fornecer nenhum argumento
@EqualsAndHashCode(of="id")  // gerar automaticamente os métodos equals() e hashCode()
public class Marca {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String nome;

    // Uma marca pode ter várias categorias
    @OneToMany
    @JoinColumn(name = "marca_id")
    List<Categoria> categorias = new ArrayList<>();

    public Marca(Integer id) {
        this.id = id;
    }

    public Marca(String nome, List<Categoria> categorias) {
        this.nome = nome;
        this.categorias = categorias;
    }
}
