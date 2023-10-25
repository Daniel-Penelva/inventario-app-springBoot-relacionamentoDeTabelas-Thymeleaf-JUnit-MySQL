package com.sistema.inventarioapp;

import java.util.List;

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

    @Test
    public void testAdicionarMuitosItens(){
        
        Usuario usuario = new Usuario(1);

        Produto produto1 = new Produto(1);
        Produto produto2 = new Produto(2);
        Produto produto3 = new Produto(3);

        ItemCarrinho itemCarrinho1 = new ItemCarrinho(1, produto1, usuario);
        ItemCarrinho itemCarrinho2 = new ItemCarrinho(3, produto2, usuario);
        ItemCarrinho itemCarrinho3 = new ItemCarrinho(1, produto3, usuario);
        
        itemCarrinhoRepository.saveAll(List.of(itemCarrinho1, itemCarrinho2, itemCarrinho3));
    }

    @Test
    public void testListarItens(){
        List<ItemCarrinho> itensCarrinho = itemCarrinhoRepository.findAll();

        itensCarrinho.forEach(System.out::println);
    }

    @Test
    public void testAtualizarItem(){
        ItemCarrinho itemCarrinho = itemCarrinhoRepository.findById(1).get();

        itemCarrinho.setQuantidade(7);
        itemCarrinho.setProduto(new Produto(2));

        itemCarrinhoRepository.save(itemCarrinho);
    }
}
