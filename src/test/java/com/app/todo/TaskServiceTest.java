package com.app.todo;

import com.app.todo.models.Task;
import com.app.todo.repository.TaskRepository;
import com.app.todo.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas unitarias para la clase TaskService.
 *
 * Utiliza Mockito para simular el comportamiento del repositorio TaskRepository y
 * verificar que la logica del servicio funcione correctamente de forma aislada.
 *
 * NOTA SOBRE WARNINGS AL EJECUTAR:
 * Los mensajes como "Mockito is currently self-attaching..." o
 * "A Java agent has been loaded dynamically..." son informativos y no afectan las pruebas.
 *
 * Video de referencia para entender Mockito:
 * https://youtu.be/j9k3epjUgr8?si=PrddJYdmarPRNWlz
 */

/**
 * Refrencia de como se puede utilizar Mockito:
 * https://youtu.be/j9k3epjUgr8?si=PrddJYdmarPRNWlz
 * Es basicamente algunos conceptos o etiquetas que usa en el video replicados en mis pruebas
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    /**
     * Simulacion (mock) del repositorio que interactua con la base de datos.
     */
    @Mock
    private TaskRepository taskRepository;

    /**
     * Instancia del servicio donde se inyecta el mock del repositorio.
     */
    @InjectMocks
    private TaskService taskService;

    private Task task1;
    private Task task2;

    /**
     * Metodo que se ejecuta antes de cada prueba.
     * Inicializa dos tareas de ejemplo para usar en los tests.
     */
    @BeforeEach
    void setUp() {
        task1 = new Task();
        task1.setId(1L);
        task1.setTitle("Tarea 1");
        task1.setCompleted(false);

        task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Tarea 2");
        task2.setCompleted(true);
    }

    /**
     * Verifica que el metodo getAllTasks() retorne todas las tareas correctamente.
     */
    @Test
    void getAllTasks() {
        // Simula que el repositorio retorna dos tareas
        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size()); // Verifica que se obtienen dos tareas
        verify(taskRepository).findAll(); // Verifica que se llamo al repositorio

        System.out.println("✅ Test ejecutado correctamente");
    }

    /**
     * Verifica que createTask() cree una nueva tarea con el titulo proporcionado.
     */
    @Test
    void createTask() {
        taskService.createTask("Nueva");
        // Verifica que se guarde una tarea con el titulo "Nueva" y estado 'no completado'
        verify(taskRepository).save(argThat(t -> t.getTitle().equals("Nueva") && !t.isCompleted()));

        System.out.println("✅ Test ejecutado correctamente");
    }

    /**
     * Verifica que deleteTask() invoque correctamente la eliminacion en el repositorio.
     */
    @Test
    void deleteTask() {
        taskService.deleteTask(1L);

        verify(taskRepository).deleteById(1L);

        System.out.println("✅ Test ejecutado correctamente");
    }

    /**
     * Verifica que toggleTask() cambie correctamente el estado de la tarea
     * de completado a no completado y viceversa.
     */
    @Test
    void toggleTask_CambiaEstado() {
        // Simula que se encuentra la tarea con ID 1
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        taskService.toggleTask(1L); // Pasa de false a true
        assertTrue(task1.isCompleted());

        taskService.toggleTask(1L); // Pasa de true a false
        assertFalse(task1.isCompleted());

        // Verifica que se guardo dos veces (una por cada cambio)
        verify(taskRepository, times(2)).save(task1);

        System.out.println("✅ Test ejecutado correctamente");
    }

    /**
     * Verifica que toggleTask() lance una excepcion si la tarea no existe.
     */
    @Test
    void toggleTask_TareaNoExiste() {
        // Simula que no se encuentra la tarea con ID 99
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());
        // Espera que se lance una excepcion cuando la tarea no existe
        assertThrows(IllegalArgumentException.class, () -> taskService.toggleTask(99L));
        // Verifica que nunca se haya intentado guardar una tarea inexistente
        verify(taskRepository, never()).save(any());

        System.out.println("✅ Test ejecutado correctamente");
    }
}
