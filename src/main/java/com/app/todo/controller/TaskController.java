package com.app.todo.controller;

import com.app.todo.models.Task;
import com.app.todo.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador encargado de gestionar las tareas en la seccion To-Do.
 *
 * Proporciona endpoints para visualizar, crear, eliminar y actualizar el estado de las tareas.
 */
@Controller
@RequestMapping("/todo")
public class TaskController {

    private final TaskService taskService;

    /**
     * Constructor que inyecta el servicio de tareas.
     *
     * @param taskService Servicio responsable de la logica de negocio de las tareas.
     */
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Maneja solicitudes GET a "/todo" para mostrar la lista de tareas.
     *
     * @param model Objeto de Spring usado para pasar datos a la vista.
     * @return Nombre de la vista que muestra las tareas (tasks.html).
     */
    @GetMapping
    public String getTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    /**
     * Crea una nueva tarea a partir del titulo recibido desde un formulario.
     *
     * @param title Titulo de la nueva tarea.
     * @return Redirecciona a la vista principal de tareas.
     */
    @PostMapping
    public String createTask(@RequestParam String title) {
        taskService.createTask(title);
        return "redirect:/todo";
    }

    /**
     * Elimina una tarea especifica por su ID.
     *
     * @param id ID de la tarea a eliminar.
     * @return Redirecciona a la vista principal de tareas.
     */
    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/todo";
    }

    /**
     * Cambia el estado (completado/no completado) de una tarea por su ID.
     *
     * @param id ID de la tarea a actualizar.
     * @return Redirecciona a la vista principal de tareas.
     */
    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id) {
        taskService.toggleTask(id);
        return "redirect:/todo";
    }
}
