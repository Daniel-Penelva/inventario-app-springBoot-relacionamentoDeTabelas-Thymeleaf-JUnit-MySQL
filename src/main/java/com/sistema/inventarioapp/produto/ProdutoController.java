package com.sistema.inventarioapp.produto;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String criarProduto(Produto produto, HttpServletRequest request){

        String[] detalhesIds = request.getParameterValues("detalhesIds");
        String[] detalhesNomes = request.getParameterValues("detalhesNomes");
        String[] detalhesValores = request.getParameterValues("detalhesValores");

        for(int i=0; i< detalhesNomes.length; i++){
            if(detalhesIds != null && detalhesIds.length > 0){
                produto.carregarDetalhes(Integer.valueOf(detalhesIds[i]), detalhesNomes[i], detalhesValores[i]);
            }else{
                produto.adicionarDetalhes(detalhesNomes[i], detalhesValores[i]);
            }
        }
        
        produtoRepository.save(produto); 
        return "redirect:/";
    }

    @GetMapping("/produtos")
    public String listarProdutos(Model model){

        List<Produto> listaProdutos = produtoRepository.findAll();

        model.addAttribute("listaProdutos", listaProdutos);
        return "/produtos";
    }

    @GetMapping("/produtos/editar/{id}")
    public String mostrarFormularioEditarProduto(@PathVariable("id") Integer id, Model model){

        // .get() é usado para obter o objeto Produto do Optional<Produto> retornado pelo findById.
        
        Produto produto = produtoRepository.findById(id).get();
        model.addAttribute("produto", produto);
        
      /*  Ou pode fazer assim:

        Optional<Produto> produto = produtoRepository.findById(id);
        model.addAttribute("produto", produto.get());
        */ 

        // Recupera a lista de categoria
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);

        return "produto_formulario";
    }

    @GetMapping("/produtos/deletar/{id}")
    public String deletarProduto(@PathVariable("id") Integer id, Model model){
        
        produtoRepository.deleteById(id);
        return "redirect:/produtos";
    }
    
}
