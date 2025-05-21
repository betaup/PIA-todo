package com.app.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controlador responsable de gestionar la visualizacion de la vista del test de mecanografia.
 *
 * Este controlador expone una unica ruta GET que permite mostrar la interfaz del test de typing.
 */
@Controller
@RequestMapping("/typing-test")
public class TypingController {

    /**
     * Maneja solicitudes GET a "/typing-test" y devuelve la vista del test de mecanografía.
     *
     * @return Nombre de la plantilla Thymeleaf (typing.html) que contiene el test.
     */
    @GetMapping
    public String mostrarTypingTest() {
        return "typing";
    }
}
