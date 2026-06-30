package com.itsqmet.tiendavinilos.controlador;

import com.itsqmet.tiendavinilos.modelo.Venta;
import com.itsqmet.tiendavinilos.repositorio.ClienteRepository;
import com.itsqmet.tiendavinilos.repositorio.DiscoRepository;
import com.itsqmet.tiendavinilos.repositorio.VentaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador CRUD para Venta.
 */
@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DiscoRepository discoRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaRepository.findAll());
        return "venta/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("discos", discoRepository.findAll());
        return "venta/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Venta venta,
                          BindingResult resultado,
                          @RequestParam(required = false) Long clienteId,
                          @RequestParam(required = false) Long discoId,
                          Model model,
                          RedirectAttributes flash) {

        // Validacion adicional: cliente y disco son obligatorios en una venta
        if (clienteId == null) {
            resultado.rejectValue("cliente", "error.venta", "Debe seleccionar un cliente");
        }
        if (discoId == null) {
            resultado.rejectValue("disco", "error.venta", "Debe seleccionar un disco");
        }

        if (resultado.hasErrors()) {
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("discos", discoRepository.findAll());
            return "venta/formulario";
        }

        venta.setCliente(clienteRepository.findById(clienteId).orElse(null));
        venta.setDisco(discoRepository.findById(discoId).orElse(null));

        boolean esNuevo = (venta.getId() == null);
        ventaRepository.save(venta);
        flash.addFlashAttribute("mensaje",
                esNuevo ? "Venta registrada correctamente." : "Venta actualizada correctamente.");
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Venta venta = ventaRepository.findById(id).orElse(null);
        if (venta == null) {
            flash.addFlashAttribute("error", "La venta no existe.");
            return "redirect:/ventas";
        }
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("discos", discoRepository.findAll());
        return "venta/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        ventaRepository.deleteById(id);
        flash.addFlashAttribute("mensaje", "Venta eliminada correctamente.");
        return "redirect:/ventas";
    }
}
