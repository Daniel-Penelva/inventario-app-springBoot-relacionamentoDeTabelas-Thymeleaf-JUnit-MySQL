package com.sistema.inventarioapp.categoria;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CategoriaController {

    private CategoriaRepository categoriaRepository;


    // http://localhost:8080/categorias
    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        return "categorias";
    }

    // http://localhost:8080/categorias/novo
    @GetMapping("/categorias/novo")
    public String mostrarFormularioDeNovaCategoria(Model model){
        model.addAttribute("categoria", new Categoria());
        return "categoria_formulario";
    }

    @PostMapping("/categorias/guardar")
    public String criarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
        return "redirect:/categorias";
    }

}
