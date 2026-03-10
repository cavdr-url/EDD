package com.asilo.edd.Controller;

import com.asilo.edd.Model.mascotasModel;
import com.asilo.edd.repository.mascotasRecursosRepositorio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

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
    public String create(@ModelAttribute mascotasModel resource, @RequestParam("imagenFile") MultipartFile imagenFile,
                          @RequestParam(value = "tab", defaultValue = "mascotas") String tab) {
        // Convertir imagen a Base64 si se subió
        if (!imagenFile.isEmpty()) {
            try {
                // Limitar a 1MB (1048576 bytes)
                if (imagenFile.getSize() > 1048576) {
                    resource.setImagen(null);
                } else {
                    String base64 = Base64.getEncoder().encodeToString(imagenFile.getBytes());
                    String mimeType = imagenFile.getContentType();
                    resource.setImagen("data:" + mimeType + ";base64," + base64);
                }
            } catch (IOException e) {
                resource.setImagen(null);
            }
        } else {
            resource.setImagen(null);
        }
        repo.save(resource);
        return "redirect:/admin/panel#" + tab;
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
    public String update(@PathVariable Long id, @ModelAttribute mascotasModel resource, 
                         @RequestParam(value = "imagenFile", required = false) MultipartFile imagenFile,
                         @RequestParam(value = "tab", defaultValue = "mascotas") String tab) {
        mascotasModel existing = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        resource.setId(id);
        
        // Si se subió nueva imagen, convertir a Base64
        if (imagenFile != null && !imagenFile.isEmpty()) {
            try {
                if (imagenFile.getSize() > 1048576) {
                    resource.setImagen(existing.getImagen()); // Mantener la anterior
                } else {
                    String base64 = Base64.getEncoder().encodeToString(imagenFile.getBytes());
                    String mimeType = imagenFile.getContentType();
                    resource.setImagen("data:" + mimeType + ";base64," + base64);
                }
            } catch (IOException e) {
                resource.setImagen(existing.getImagen());
            }
        } else {
            // Mantener imagen existente
            resource.setImagen(existing.getImagen());
        }
        
        repo.save(resource);
        return "redirect:/admin/panel#" + tab;
    }

    // POST /mascotas/{id}/eliminar -> borrar
    @PostMapping("/{id}/eliminar")
    public String delete(@PathVariable Long id, @RequestParam(value = "tab", defaultValue = "mascotas") String tab) {
        repo.deleteById(id);
        return "redirect:/admin/panel#" + tab;
    }

    // POST /mascotas/{id}/adoptar -> registrar adopción
    @PostMapping("/{id}/adoptar")
    public String adoptar(@PathVariable Long id, @RequestParam String adoptante,
                          @RequestParam(value = "tab", defaultValue = "adopcion") String tab) {
        mascotasModel mascota = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        LocalDate fechaAdopcion = LocalDate.now();
        
        // Actualizar estado y datos de adopción
        mascota.setEstado("adoptado");
        mascota.setFechaAdopcion(fechaAdopcion);
        mascota.setAdoptante(adoptante);
        
        // Agregar nota en observaciones
        String obsActual = mascota.getObservaciones() != null ? mascota.getObservaciones() : "";
        String nuevaObservacion = obsActual + "\n[ADOPCIÓN] Adoptado por: " + adoptante + " el " + fechaAdopcion;
        mascota.setObservaciones(nuevaObservacion);
        
        repo.save(mascota);
        return "redirect:/admin/panel#" + tab;
    }
    
    // POST /mascotas/{id}/actualizarAdopcion -> actualizar info de adopción
    @PostMapping("/{id}/actualizarAdopcion")
    public String actualizarAdopcion(@PathVariable Long id, @RequestParam String adoptante,
                                    @RequestParam(value = "tab", defaultValue = "adopcion") String tab) {
        mascotasModel mascota = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        LocalDate fechaActualizacion = LocalDate.now();
        
        // Actualizar adoptante
        mascota.setAdoptante(adoptante);
        
        // Actualizar nota en observaciones
        String obsActual = mascota.getObservaciones() != null ? mascota.getObservaciones() : "";
        
        // Si ya existe [ADOPCIÓN], reemplazar; si no, añadir
        if (obsActual.contains("[ADOPCIÓN]")) {
            obsActual = obsActual.replaceAll("\\[ADOPCIÓN\\].*", 
                "[ADOPCIÓN] Adoptado por: " + adoptante + " el " + fechaActualizacion);
        } else {
            if (!obsActual.isEmpty()) obsActual += "\n";
            obsActual += "[ADOPCIÓN] Adoptado por: " + adoptante + " el " + fechaActualizacion;
        }
        mascota.setObservaciones(obsActual);
        
        repo.save(mascota);
        return "redirect:/admin/panel#" + tab;
    }
    
    // POST /mascotas/{id}/cancelarAdopcion -> cancelar adopción (regresa a disponible)
    @PostMapping("/{id}/cancelarAdopcion")
    public String cancelarAdopcion(@PathVariable Long id, @RequestParam(value = "tab", defaultValue = "adopcion") String tab) {
        mascotasModel mascota = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
        
        // Regresar a disponible
        mascota.setEstado("disponible");
        mascota.setAdoptante(null);
        mascota.setFechaAdopcion(null);
        
        // Quitar nota de adopción de observaciones
        String obsActual = mascota.getObservaciones() != null ? mascota.getObservaciones() : "";
        if (obsActual.contains("[ADOPCIÓN]")) {
            obsActual = obsActual.replaceAll("\n?\\[ADOPCIÓN\\].*", "");
        }
        // Limpiar espacios y líneas vacías
        obsActual = obsActual.replaceAll("\\n\\n+", "\n").trim();
        
        // Si queda vacío o solo tiene espacios, poner null
        if (obsActual.isEmpty()) {
            mascota.setObservaciones(null);
        } else {
            mascota.setObservaciones(obsActual);
        }
        
        repo.save(mascota);
        return "redirect:/admin/panel#" + tab;
    }
}

