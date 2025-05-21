package com.app.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/typing-test")
public class TypingController {

    @GetMapping
    public String mostrarTypingTest() {
        return "typing";
    }
}
