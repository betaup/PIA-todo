package com.app.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador encargado de manejar las solicitudes relacionadas con el temporizador Pomodoro.
 *
 * Este controlador responde a la ruta "/pomodoro" y muestra la vista correspondiente
 * al temporizador Pomodoro, que puede ser una herramienta de productividad para los usuarios.
 */
@Controller
@RequestMapping("/pomodoro")
public class PomodoroController {

    /**
     * Maneja las solicitudes GET a la ruta "/pomodoro".
     *
     * @return El nombre de la vista "pomodoro", que deberia coincidir con
     *         una plantilla HTML disponible en la carpeta de templates.
     */
    @GetMapping
    public String mostrarPomodoro() {
        return "pomodoro";
    }
}

