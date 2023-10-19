package com.sistema.inventarioapp.marca;

import java.util.List;

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


  @PostMapping("/marcas/guardar")
  public String criarMarca(Marca marca){
    
    marcaRepository.save(marca);
    return "redirect:/";
  }

  // http://localhost:8080/marcas
  @GetMapping("/marcas")
  public String listaMarcas(Model model){
    List<Marca> listaMarcas = marcaRepository.findAll();
    model.addAttribute("listaMarcas", listaMarcas);
    return "/marcas";
  }

  @GetMapping("/marcas/editar/{id}")
    public String mostrarFormularioEditarMarca(@PathVariable("id") Integer id, Model model){

        List<Categoria> listaCategorias = categoriaRepository.findAll();
        Marca marca = marcaRepository.findById(id).get();
        
        model.addAttribute("listaCategorias", listaCategorias);
        model.addAttribute("marca", marca);
    
        return "marca_formulario";
  }
    
}
