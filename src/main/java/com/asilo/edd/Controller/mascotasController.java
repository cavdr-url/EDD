package com.asilo.edd.Controller;

import com.asilo.edd.Model.mascotasModel;
import com.asilo.edd.repository.mascotasRecursosRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/mascotas")
public class mascotasController {

    private final mascotasRecursosRepositorio repo;

    public mascotasController(mascotasRecursosRepositorio repo) {
        this.repo = repo;
    }

    // GET /mascotas/nuevo -> formulario de creación
    @GetMapping("/nuevo")
    public String form(Model model) {
        model.addAttribute("resource", new mascotasModel());
        return "mascotas/form";
    }

    // POST /mascotas -> crear
    @PostMapping
    public String create(@ModelAttribute mascotasModel resource) {
        repo.save(resource);
        return "redirect:/admin/panel";
    }

    // GET /mascotas/{id}/editar -> formulario con datos
    @GetMapping("/{id}/editar")
    public String edit(@PathVariable Long id, Model model) {
        mascotasModel resource = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        model.addAttribute("resource", resource);
        return "mascotas/form";
    }

    // POST /mascotas/{id} -> actualizar
    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute mascotasModel resource) {
        resource.setId(id);
        repo.save(resource);
        return "redirect:/admin/panel";
    }

    // POST /mascotas/{id}/eliminar -> borrar
    @PostMapping("/{id}/eliminar")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/admin/panel";
    }
}
