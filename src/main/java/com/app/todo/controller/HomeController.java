package com.app.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador responsable de manejar la ruta raiz de la aplicacion.
 *
 * Este controlador se encarga de redirigir a la vista principal (index)
 * cuando se accede a la URL base ("/") del sitio.
 */
@Controller
public class HomeController {

    /**
     * Maneja las solicitudes GET a la raiz del sitio ("/").
     *
     * @return El nombre de la vista "index", que generalmente representa
     *         la pagina de inicio de la aplicacion.
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
