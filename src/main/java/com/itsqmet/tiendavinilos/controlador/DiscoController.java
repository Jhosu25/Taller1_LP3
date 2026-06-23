package com.itsqmet.tiendavinilos.controlador;

import com.itsqmet.tiendavinilos.modelo.Disco;
import com.itsqmet.tiendavinilos.repositorio.ArtistaRepository;
import com.itsqmet.tiendavinilos.repositorio.DiscoRepository;
import com.itsqmet.tiendavinilos.repositorio.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/discos")
public class DiscoController {

    @Autowired
    private DiscoRepository discoRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Autowired
    private GeneroRepository generoRepository;

    // READ: listar todos los discos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("discos", discoRepository.findAll());
        return "disco/lista";
    }

    // Mostrar formulario para un disco nuevo
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("disco", new Disco());
        model.addAttribute("artistas", artistaRepository.findAll());
        model.addAttribute("generos", generoRepository.findAll());
        return "disco/formulario";
    }

    // CREATE / UPDATE: guardar el disco (si tiene id, actualiza; si no, crea)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Disco disco) {
        discoRepository.save(disco);
        return "redirect:/discos";
    }

    // Mostrar formulario con los datos de un disco existente para editar
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        Disco disco = discoRepository.findById(id).orElse(null);
        model.addAttribute("disco", disco);
        model.addAttribute("artistas", artistaRepository.findAll());
        model.addAttribute("generos", generoRepository.findAll());
        return "disco/formulario";
    }

    // DELETE: eliminar un disco por su id
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        discoRepository.deleteById(id);
        return "redirect:/discos";
    }
}
