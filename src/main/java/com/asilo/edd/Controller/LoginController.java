package com.asilo.edd.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asilo.edd.repository.mascotasRecursosRepositorio;
import com.asilo.edd.repository.vacunasRepositorio;
import com.asilo.edd.repository.aplicacionVacunaRepositorio;

@Controller
public class LoginController {

    private final mascotasRecursosRepositorio repoMascotas;
    private final vacunasRepositorio repoVacunas;
    private final aplicacionVacunaRepositorio repoAplicaciones;

    public LoginController(
            mascotasRecursosRepositorio repoMascotas,
            vacunasRepositorio repoVacunas,
            aplicacionVacunaRepositorio repoAplicaciones) {
        this.repoMascotas = repoMascotas;
        this.repoVacunas = repoVacunas;
        this.repoAplicaciones = repoAplicaciones;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @RequestMapping("/admin/panel")
    public String adminPanel(Model model) {
        model.addAttribute("resources", repoMascotas.findAll());
        model.addAttribute("total", repoMascotas.count());
        model.addAttribute("vacunas", repoVacunas.findAll());
        model.addAttribute("totalVacunas", repoVacunas.count());
        model.addAttribute("aplicaciones", repoAplicaciones.findAll());
        model.addAttribute("totalAplicaciones", repoAplicaciones.count());
        
        // Agregar mascotas para adopción
        model.addAttribute("disponibles", repoMascotas.findByEstado("disponible"));
        model.addAttribute("adoptadas", repoMascotas.findByEstado("adoptado"));
        
        return "admin/panel";
    }

    @RequestMapping("/usuario/inicio")
    public String usuarioInicio(Model model) {
        model.addAttribute("resources", repoMascotas.findAll());
        model.addAttribute("total", repoMascotas.count());
        model.addAttribute("vacunas", repoVacunas.findAll());
        model.addAttribute("aplicaciones", repoAplicaciones.findAll());
        model.addAttribute("adoptadas", repoMascotas.findByEstado("adoptado"));
        return "usuario/list";
    }

    @RequestMapping("/admin/derechos")
    public String derechos() {
        return "admin/derechos";
    }

    @RequestMapping("/usuario/derechos")
    public String derechosU() {
        return "usuario/derechos";
    }

    @RequestMapping("/derechos")
    public String derechosPublico() {
        return "derechos";
    }
    
}
