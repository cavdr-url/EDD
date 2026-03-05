package com.asilo.edd.Controller;

import com.asilo.edd.Model.vacunasModel;
import com.asilo.edd.repository.vacunasRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vacunas")
public class vacunasController {

    private final vacunasRepositorio repo;

    public vacunasController(vacunasRepositorio repo) {
        this.repo = repo;
    }

    // GET /vacunas/nuevo -> formulario de creación
    @GetMapping("/nuevo")
    public String form(Model model) {
        model.addAttribute("vacuna", new vacunasModel());
        return "admin/vacunas/form";
    }

    // POST /vacunas -> crear
    @PostMapping
    public String create(@ModelAttribute vacunasModel vacuna) {
        repo.save(vacuna);
        return "redirect:/admin/panel";
    }

    // GET /vacunas/{id}/editar -> formulario con datos
    @GetMapping("/{id}/editar")
    public String edit(@PathVariable Long id, Model model) {
        vacunasModel vacuna = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("vacuna", vacuna);
        return "admin/vacunas/form";
    }

    // POST /vacunas/{id} -> actualizar
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute vacunasModel vacuna) {
        vacuna.setId(id);
        repo.save(vacuna);
        return "redirect:/admin/panel";
    }

    // POST /vacunas/{id}/eliminar -> borrar
    @PostMapping("/{id}/eliminar")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/admin/panel";
    }
}
