package com.sistema.inventarioapp.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

}
