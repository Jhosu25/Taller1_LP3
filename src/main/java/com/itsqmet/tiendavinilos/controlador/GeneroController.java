package com.itsqmet.tiendavinilos.controlador;

import com.itsqmet.tiendavinilos.modelo.Genero;
import com.itsqmet.tiendavinilos.repositorio.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/generos")
public class GeneroController {

    @Autowired
    private GeneroRepository generoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("generos", generoRepository.findAll());
        return "genero/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("genero", new Genero());
        return "genero/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Genero genero) {
        generoRepository.save(genero);
        return "redirect:/generos";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("genero", generoRepository.findById(id).orElse(null));
        return "genero/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        generoRepository.deleteById(id);
        return "redirect:/generos";
    }
}
