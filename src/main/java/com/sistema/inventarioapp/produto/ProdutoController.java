package com.sistema.inventarioapp.produto;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistema.inventarioapp.categoria.Categoria;
import com.sistema.inventarioapp.categoria.CategoriaRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ProdutoController {

    private ProdutoRepository produtoRepository;

    private CategoriaRepository categoriaRepository;

    // http://localhost:8080/produtos/novo
    @GetMapping("/produtos/novo")
    public String mostrarFormularioDeNovaProduto(Model model){
        
        List<Categoria> listaCategorias = categoriaRepository.findAll();

        model.addAttribute("produto", new Produto());
        model.addAttribute("listaCategorias", listaCategorias);

        return "produto_formulario";
    }

    @PostMapping("/produtos/guardar")
    public String criarProduto(Produto produto){
        produtoRepository.save(produto);
        return "redirect:/";
    }

    @GetMapping("/produtos")
    public String listarProdutos(Model model){

        List<Produto> listaProdutos = produtoRepository.findAll();

        model.addAttribute("listaProdutos", listaProdutos);
        return "/produtos";
    }
    
}
