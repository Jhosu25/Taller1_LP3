package com.itsqmet.tiendavinilos.controlador;

import com.itsqmet.tiendavinilos.modelo.Genero;
import com.itsqmet.tiendavinilos.repositorio.GeneroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador CRUD para Genero.
 */
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
    public String guardar(@Valid @ModelAttribute Genero genero,
                          BindingResult resultado,
                          RedirectAttributes flash) {
        if (resultado.hasErrors()) {
            return "genero/formulario";
        }
        boolean esNuevo = (genero.getId() == null);
        generoRepository.save(genero);
        flash.addFlashAttribute("mensaje",
                esNuevo ? "Genero creado correctamente." : "Genero actualizado correctamente.");
        return "redirect:/generos";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Genero genero = generoRepository.findById(id).orElse(null);
        if (genero == null) {
            flash.addFlashAttribute("error", "El genero no existe.");
            return "redirect:/generos";
        }
        model.addAttribute("genero", genero);
        return "genero/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        try {
            generoRepository.deleteById(id);
            flash.addFlashAttribute("mensaje", "Genero eliminado correctamente.");
        } catch (DataIntegrityViolationException e) {
            flash.addFlashAttribute("error",
                    "No se puede eliminar el genero porque esta asignado a uno o mas discos.");
        }
        return "redirect:/generos";
    }
}
