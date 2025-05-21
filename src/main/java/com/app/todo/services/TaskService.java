package com.app.todo.services;

import com.app.todo.models.Task;
import com.app.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que encapsula la logica de negocio relacionada con las tareas ({@link Task}).
 *
 * Esta clase actua como intermediaria entre el controlador y el repositorio,
 * manteniendo la logica de negocio separada del acceso a datos y de la capa de presentacion.
 */
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Obtiene todas las tareas almacenadas en la base de datos.
     *
     * @return Lista de todas las tareas.
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Crea una nueva tarea con el titulo especificado.
     * La tarea se guarda con el estado 'no completada' por defecto.
     *
     * @param title Titulo de la nueva tarea.
     */
    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);
        taskRepository.save(task);
    }

    /**
     * Elimina una tarea existente por su ID.
     *
     * @param id Identificador de la tarea a eliminar.
     */
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    /**
     * Cambia el estado de una tarea (completada/no completada).
     * Si la tarea no se encuentra, lanza una excepcion.
     *
     * @param id Identificador de la tarea a modificar.
     */
    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarea no encontrada!"));
        task.setCompleted(!task.isCompleted());
        taskRepository.save(task);
    }
}
