package com.asilo.edd.Controller;

import com.asilo.edd.Model.aplicacionVacunaModel;
import com.asilo.edd.Model.mascotasModel;
import com.asilo.edd.Model.vacunasModel;
import com.asilo.edd.repository.aplicacionVacunaRepositorio;
import com.asilo.edd.repository.mascotasRecursosRepositorio;
import com.asilo.edd.repository.vacunasRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/aplicaciones")
public class aplicacionVacunaController {

    private final aplicacionVacunaRepositorio repoAplicacion;
    private final mascotasRecursosRepositorio repoMascota;
    private final vacunasRepositorio repoVacuna;

    public aplicacionVacunaController(
            aplicacionVacunaRepositorio repoAplicacion,
            mascotasRecursosRepositorio repoMascota,
            vacunasRepositorio repoVacuna) {
        this.repoAplicacion = repoAplicacion;
        this.repoMascota = repoMascota;
        this.repoVacuna = repoVacuna;
    }

    // GET /aplicaciones/nuevo -> formulario de creación
    @GetMapping("/nuevo")
    public String form(Model model) {
        model.addAttribute("aplicacion", new aplicacionVacunaModel());
        
        // Cargar listas para los select
        List<mascotasModel> mascotas = repoMascota.findAll();
        List<vacunasModel> vacunas = repoVacuna.findAll();
        
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("vacunas", vacunas);
        
        return "admin/aplicacion/form";
    }

    // POST /aplicaciones -> crear
    @PostMapping
    public String create(@ModelAttribute aplicacionVacunaModel aplicacion) {
        repoAplicacion.save(aplicacion);
        return "redirect:/admin/panel";
    }

    // GET /aplicaciones/{id}/editar -> formulario con datos
    @GetMapping("/{id}/editar")
    public String edit(@PathVariable Long id, Model model) {
        aplicacionVacunaModel aplicacion = repoAplicacion.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("aplicacion", aplicacion);
        
        // Cargar listas para los select
        List<mascotasModel> mascotas = repoMascota.findAll();
        List<vacunasModel> vacunas = repoVacuna.findAll();
        
        model.addAttribute("mascotas", mascotas);
        model.addAttribute("vacunas", vacunas);
        
        return "admin/aplicacion/form";
    }

    // POST /aplicaciones/{id} -> actualizar
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute aplicacionVacunaModel aplicacion) {
        aplicacion.setId(id);
        repoAplicacion.save(aplicacion);
        return "redirect:/admin/panel";
    }

    // POST /aplicaciones/{id}/eliminar -> borrar
    @PostMapping("/{id}/eliminar")
    public String delete(@PathVariable Long id) {
        repoAplicacion.deleteById(id);
        return "redirect:/admin/panel";
    }
}
