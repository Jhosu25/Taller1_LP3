package com.itsqmet.tiendavinilos.controlador;

import com.itsqmet.tiendavinilos.modelo.Artista;
import com.itsqmet.tiendavinilos.repositorio.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("artistas", artistaRepository.findAll());
        return "artista/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("artista", new Artista());
        return "artista/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Artista artista) {
        artistaRepository.save(artista);
        return "redirect:/artistas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("artista", artistaRepository.findById(id).orElse(null));
        return "artista/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        artistaRepository.deleteById(id);
        return "redirect:/artistas";
    }
}
