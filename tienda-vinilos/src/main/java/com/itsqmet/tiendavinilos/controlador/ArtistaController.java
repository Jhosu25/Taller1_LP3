package com.itsqmet.tiendavinilos.controlador;

import com.itsqmet.tiendavinilos.modelo.Artista;
import com.itsqmet.tiendavinilos.repositorio.ArtistaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador CRUD para Artista.
 */
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
    public String guardar(@Valid @ModelAttribute Artista artista,
                          BindingResult resultado,
                          RedirectAttributes flash) {
        if (resultado.hasErrors()) {
            return "artista/formulario";
        }
        boolean esNuevo = (artista.getId() == null);
        artistaRepository.save(artista);
        flash.addFlashAttribute("mensaje",
                esNuevo ? "Artista creado correctamente." : "Artista actualizado correctamente.");
        return "redirect:/artistas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Artista artista = artistaRepository.findById(id).orElse(null);
        if (artista == null) {
            flash.addFlashAttribute("error", "El artista no existe.");
            return "redirect:/artistas";
        }
        model.addAttribute("artista", artista);
        return "artista/formulario";
    }

    // Vista de detalle: artista y todos sus discos
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Artista artista = artistaRepository.findById(id).orElse(null);
        if (artista == null) {
            flash.addFlashAttribute("error", "El artista no existe.");
            return "redirect:/artistas";
        }
        model.addAttribute("artista", artista);
        return "artista/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        try {
            artistaRepository.deleteById(id);
            flash.addFlashAttribute("mensaje", "Artista eliminado correctamente.");
        } catch (DataIntegrityViolationException e) {
            flash.addFlashAttribute("error",
                    "No se puede eliminar el artista porque tiene discos asociados.");
        }
        return "redirect:/artistas";
    }
}
