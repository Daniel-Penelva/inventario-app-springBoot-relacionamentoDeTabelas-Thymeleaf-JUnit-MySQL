package com.sistema.inventarioapp.marca;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sistema.inventarioapp.categoria.Categoria;
import com.sistema.inventarioapp.categoria.CategoriaRepository;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MarcaController {

    private MarcaRepository marcaRepository;

    private CategoriaRepository categoriaRepository;

    // http://localhost:8080/marcas/novo
    @GetMapping("/marcas/novo")
    public String mostrarFormularioDeNovaMarca(Model model){

        List<Categoria> listaCategorias = categoriaRepository.findAll();

        model.addAttribute("listaCategorias", listaCategorias);
        model.addAttribute("marca", new Marca());

        return "marca_formulario";
  }
    
}
