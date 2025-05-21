package com.app.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pomodoro")
public class PomodoroController {

    @GetMapping
    public String mostrarPomodoro() {
        return "pomodoro";
    }
}

