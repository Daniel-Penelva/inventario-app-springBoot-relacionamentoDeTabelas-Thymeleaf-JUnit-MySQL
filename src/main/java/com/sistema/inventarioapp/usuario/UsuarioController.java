package com.sistema.inventarioapp.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioReository;

    @Autowired
    RolRepository rolReository;


    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {

        List<Usuario> listaUsuarios = usuarioReository.findAll();

        model.addAttribute("listaUsuarios", listaUsuarios);

        return "usuarios";
    }

    @GetMapping("/usuarios/novo")
    public String mostarFormularioRegistroDeUsuarios(Model model) {

        List<Rol> listaRoles = rolReository.findAll();
        model.addAttribute("listaRoles", listaRoles);
        model.addAttribute("usuario", new Usuario());
        
        return "usuario_formulario";
    }

    @PostMapping("/usuarios/guardar")
    public String criarUsuario(Usuario usuario){
        usuarioReository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable("id") Integer id, Model model){
        
        Usuario usuario = usuarioReository.findById(id).get();
        model.addAttribute("usuario", usuario);

        // Recupera a lista de usuario
        List<Rol> listaRoles = rolReository.findAll();
        model.addAttribute("listaRoles", listaRoles);

        return "usuario_formulario";
    }
}
