package com.itsqmet.tiendavinilos.controlador;

import com.itsqmet.tiendavinilos.modelo.Cliente;
import com.itsqmet.tiendavinilos.modelo.Direccion;
import com.itsqmet.tiendavinilos.repositorio.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controlador CRUD para Cliente.
 */
@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "cliente/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        Cliente cliente = new Cliente();
        cliente.setDireccion(new Direccion()); // necesario para enlazar los campos de direccion
        model.addAttribute("cliente", cliente);
        return "cliente/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Cliente cliente,
                          BindingResult resultado,
                          RedirectAttributes flash) {
        if (resultado.hasErrors()) {
            return "cliente/formulario";
        }
        boolean esNuevo = (cliente.getId() == null);
        clienteRepository.save(cliente);
        flash.addFlashAttribute("mensaje",
                esNuevo ? "Cliente creado correctamente." : "Cliente actualizado correctamente.");
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe.");
            return "redirect:/clientes";
        }
        if (cliente.getDireccion() == null) {
            cliente.setDireccion(new Direccion());
        }
        model.addAttribute("cliente", cliente);
        return "cliente/formulario";
    }

    // Vista de detalle: muestra el cliente y todas sus ventas
    @GetMapping("/ver/{id}")
    public String ver(@PathVariable Long id, Model model, RedirectAttributes flash) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) {
            flash.addFlashAttribute("error", "El cliente no existe.");
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", cliente);
        return "cliente/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        try {
            clienteRepository.deleteById(id);
            flash.addFlashAttribute("mensaje", "Cliente eliminado correctamente.");
        } catch (DataIntegrityViolationException e) {
            flash.addFlashAttribute("error",
                    "No se puede eliminar el cliente porque tiene ventas asociadas.");
        }
        return "redirect:/clientes";
    }
}
