package com.sistema.inventarioapp.usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 25, nullable = false, unique = true)
    private String nome;

    public Rol(Integer id) {
        this.id = id;
    }

    public Rol(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    } 
}
