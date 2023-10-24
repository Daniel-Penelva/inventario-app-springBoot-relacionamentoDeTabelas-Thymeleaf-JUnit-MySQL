package com.sistema.inventarioapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventarioapp.usuario.Rol;
import com.sistema.inventarioapp.usuario.Usuario;
import com.sistema.inventarioapp.usuario.UsuarioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCriarRoles() {
        Rol rolAdmin = new Rol("Administrador");
        Rol rolEditor = new Rol("Editor");
        Rol rolVisitante = new Rol("Visitante");

        entityManager.persist(rolAdmin);
        entityManager.persist(rolEditor);
        entityManager.persist(rolVisitante);
    }

    @Test
    public void testCriarNovoUsuarioComUmRol() {
        Rol rolAdmin = entityManager.find(Rol.class, 1);
        Usuario usuario = new Usuario("d4n.andrade@gmail.com", "123");

        usuario.adicionarRol(rolAdmin);

        usuarioRepository.save(usuario);
    }

    @Test
    public void testCriarNovoUsuarioComDoisRoles() {
        Rol rolEditor = entityManager.find(Rol.class, 2);
        Rol rolVisitante = entityManager.find(Rol.class, 3);

        Usuario usuario = new Usuario("biana@gmail.com", "123");

        usuario.adicionarRol(rolEditor);
        usuario.adicionarRol(rolVisitante);

        usuarioRepository.save(usuario);
    }

    @Test
    public void testAtribuirRolUsuarioExistente() {

        Usuario usuario = usuarioRepository.findById(1).get();

        Rol rolEditor = entityManager.find(Rol.class, 2);

        usuario.adicionarRol(rolEditor);
    }

    @Test
    public void testDeletarRolUsuarioExistente() {

        Usuario usuario = usuarioRepository.findById(1).get();

        Rol rol = new Rol(2);

        usuario.deletarRol(rol);
    }

    @Test
    public void testCriarNovoUsuarioComNovoRol() {

        Rol rolVendedor = new Rol("Vendedor");
        Usuario usuario = new Usuario("paula@gmail.com", "123");

        usuario.adicionarRol(rolVendedor);

        usuarioRepository.save(usuario);
    }

    @Test
    public void testBuscarUsuario() {

        Usuario usuario = usuarioRepository.findById(1).get();

        entityManager.detach(usuario);

        System.out.println("Usuario: " + usuario.getEmail() + " - Rol: " + usuario.getRoles());
    }


    // OBS.  Para testar esse método 'testDeletarUsuario' comenta o CascadeType e o FetchType na classe Usuário
    @Test
    public void testDeletarUsuario() {

        usuarioRepository.deleteById(2);
    }
}
