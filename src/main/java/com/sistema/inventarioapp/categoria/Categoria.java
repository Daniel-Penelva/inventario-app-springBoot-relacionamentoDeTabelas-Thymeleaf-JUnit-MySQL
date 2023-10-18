package com.sistema.inventarioapp.categoria;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sistema.inventarioapp.marca.Marca;

import lombok.AllArgsConstructor;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter  // geram automaticamente métodos getters
@Setter  // geram automaticamente métodos setters 
@AllArgsConstructor  // Um construtor que aceita todos os atributos da classe
@NoArgsConstructor   // permite criar uma instância da classe sem fornecer nenhum argumento
@EqualsAndHashCode(of="id")  // gerar automaticamente os métodos equals() e hashCode()
public class Categoria {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String nome;

    // Várias categorias para uma marca
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    public Categoria(String nome, Marca marca) {
        this.nome = nome;
        this.marca = marca;
    }

    @Override
    public String toString() {
        return nome;
    }

}
