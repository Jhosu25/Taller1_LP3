package com.itsqmet.tiendavinilos.controlador;

import com.itsqmet.tiendavinilos.modelo.Disco;
import com.itsqmet.tiendavinilos.modelo.Genero;
import com.itsqmet.tiendavinilos.repositorio.ArtistaRepository;
import com.itsqmet.tiendavinilos.repositorio.DiscoRepository;
import com.itsqmet.tiendavinilos.repositorio.GeneroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controlador CRUD para Disco
 */
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

    // Formulario para un disco nuevo
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("disco", new Disco());
        model.addAttribute("artistas", artistaRepository.findAll());
        model.addAttribute("generos", generoRepository.findAll());
        return "disco/formulario";
    }

    // CREATE / UPDATE: guardar el disco con validaciones y sus relaciones
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Disco disco,
                          BindingResult resultado,
                          @RequestParam(required = false) Long artistaId,
                          @RequestParam(required = false) List<Long> generoIds,
                          Model model,
                          RedirectAttributes flash) {

        // Si hay errores de validacion, se regresa al formulario
        if (resultado.hasErrors()) {
            model.addAttribute("artistas", artistaRepository.findAll());
            model.addAttribute("generos", generoRepository.findAll());
            return "disco/formulario";
        }

        // Asignar el artista seleccionado (relacion muchos a uno)
        if (artistaId != null) {
            disco.setArtista(artistaRepository.findById(artistaId).orElse(null));
        } else {
            disco.setArtista(null);
        }

        // Asignar los generos seleccionados (relacion muchos a muchos)
        Set<Genero> generos = new HashSet<>();
        if (generoIds != null) {
            for (Long gid : generoIds) {
                generoRepository.findById(gid).ifPresent(generos::add);
            }
        }
        disco.setGeneros(generos);

        boolean esNuevo = (disco.getId() == null);
        discoRepository.save(disco);
        flash.addFlashAttribute("mensaje",
                esNuevo ? "Disco creado correctamente." : "Disco actualizado correctamente.");
        return "redirect:/discos";
    }

    // Formulario para editar un disco existente
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Disco disco = discoRepository.findById(id).orElse(null);
        if (disco == null) {
            flash.addFlashAttribute("error", "El disco no existe.");
            return "redirect:/discos";
        }
        model.addAttribute("disco", disco);
        model.addAttribute("artistas", artistaRepository.findAll());
        model.addAttribute("generos", generoRepository.findAll());
        return "disco/formulario";
    }

    // DELETE: eliminar un disco (controla error de integridad si tiene ventas)
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        try {
            discoRepository.deleteById(id);
            flash.addFlashAttribute("mensaje", "Disco eliminado correctamente.");
        } catch (DataIntegrityViolationException e) {
            flash.addFlashAttribute("error",
                    "No se puede eliminar el disco porque tiene ventas asociadas.");
        }
        return "redirect:/discos";
    }
}
