package com.sistema.inventarioapp.usuario;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @ManyToMany //(cascade =  CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario(Integer id) {
        this.id = id;
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Usuario(String email, String password, Set<Rol> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    // Método para adicionar Rol
    public void adicionarRol(Rol rol){
        this.roles.add(rol);
    }

    // Método para remover Rol
    public void deletarRol(Rol rol){
        this.roles.remove(rol);
    }
}

/**
 * cascade = CascadeType.PERSIST: O argumento cascade especifica como as operações de persistência devem ser tratadas para o relacionamento. 
 * Nesse caso, quando você persiste (ou seja, insere) um objeto da classe proprietária no banco de dados, o CascadeType.PERSIST garante que as 
 * operações de persistência sejam propagadas para as entidades associadas. Isso significa que se você criar um objeto da classe proprietária e 
 * associar entidades a ele, todas as entidades associadas também serão persistidas no banco de dados quando você persistir a entidade 
 * proprietária.
 * 
 * No contexto de um relacionamento muitos-para-muitos, a utilização de CascadeType.PERSIST pode ser apropriada quando deseja que as entidades 
 * associadas sejam persistidas automaticamente quando você persistir a entidade proprietária. No entanto, é importante ter cuidado ao usar o 
 * cascade, pois ele pode afetar o comportamento das operações de persistência e exige uma compreensão cuidadosa das implicações.
 * 
*/

/**
 *  A propriedade `fetch` é usada em mapeamento objeto-relacional (ORM) com frameworks como o Java Persistence API (JPA) para definir como as 
 * associações entre entidades (por exemplo, relacionamentos entre tabelas em um banco de dados) devem ser recuperadas (buscadas) do banco de 
 * dados quando você carrega uma entidade principal. A propriedade `fetch` define o comportamento de busca da associação. No contexto de 
 * `FetchType.EAGER`, aqui está o que isso significa:
 * 
 *      - `FetchType.EAGER`: Quando uma associação entre entidades é configurada como `EAGER`, significa que os dados relacionados (entidades 
 *         associadas) são carregados automaticamente junto com a entidade principal quando a entidade principal é recuperada do banco de dados. 
 *         Em outras palavras, as entidades associadas são carregadas imediatamente, na mesma consulta ao banco de dados que recupera a entidade 
 *         principal.
 * 
 * Isso é útil quando você deseja recuperar todas as informações relacionadas de uma vez, economizando consultas adicionais ao banco de dados. 
 * No entanto, o uso de `EAGER` pode resultar em consultas SQL mais complexas e potencialmente carregar um grande volume de dados desnecessários 
 * se as entidades associadas forem grandes ou se você não precisar desses dados em uma determinada operação.
 * 
 * A alternativa ao `EAGER` é o `LAZY`. Quando uma associação é configurada como `LAZY`, as entidades associadas são carregadas somente quando 
 * você acessa explicitamente a propriedade relacionada, evitando o carregamento desnecessário de dados em operações que não exigem essas 
 * informações.
 * 
 * A escolha entre `EAGER` e `LAZY` depende das necessidades do seu aplicativo e do desempenho. Se você frequentemente precisar dos dados 
 * relacionados, o `EAGER` pode ser apropriado. No entanto, se você raramente precisar desses dados ou se eles forem volumosos, o `LAZY` pode 
 * ser uma escolha melhor para minimizar a carga de dados e melhorar o desempenho de leitura das entidades principais.
*/
