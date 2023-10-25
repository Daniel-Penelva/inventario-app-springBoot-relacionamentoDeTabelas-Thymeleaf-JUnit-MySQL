package com.sistema.inventarioapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventarioapp.carrinhocompras.ItemCarrinho;
import com.sistema.inventarioapp.carrinhocompras.ItemCarrinhoRepository;
import com.sistema.inventarioapp.produto.Produto;
import com.sistema.inventarioapp.usuario.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ItemCarrinhoRepositoryTest {
    
    @Autowired
    private ItemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void testAdicionarItem(){
        Produto produto = testEntityManager.find(Produto.class, 5);

        Usuario usuario = testEntityManager.find(Usuario.class, 5);

        ItemCarrinho itemCarrinho = new ItemCarrinho(1, produto, usuario);
        itemCarrinhoRepository.save(itemCarrinho);
    }
}
