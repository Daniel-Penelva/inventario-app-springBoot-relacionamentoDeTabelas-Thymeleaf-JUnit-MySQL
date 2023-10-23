package com.sistema.inventarioapp.usuario;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 25, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario(String email, String password, Set<Rol> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
