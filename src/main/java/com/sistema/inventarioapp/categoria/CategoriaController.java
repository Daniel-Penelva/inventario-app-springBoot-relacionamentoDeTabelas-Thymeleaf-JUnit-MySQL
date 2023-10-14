package com.sistema.inventarioapp.categoria;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        return "categorias";
    }

}
