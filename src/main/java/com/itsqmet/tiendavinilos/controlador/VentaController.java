package com.itsqmet.tiendavinilos.controlador;

import com.itsqmet.tiendavinilos.modelo.Venta;
import com.itsqmet.tiendavinilos.repositorio.ClienteRepository;
import com.itsqmet.tiendavinilos.repositorio.DiscoRepository;
import com.itsqmet.tiendavinilos.repositorio.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
    public String guardar(@ModelAttribute Venta venta) {
        ventaRepository.save(venta);
        return "redirect:/ventas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("venta", ventaRepository.findById(id).orElse(null));
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("discos", discoRepository.findAll());
        return "venta/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        ventaRepository.deleteById(id);
        return "redirect:/ventas";
    }
}
