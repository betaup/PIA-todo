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

/*
 * NOTA SOBRE WARNINGS AL EJECUTAR:
 * Los mensajes de advertencia como "Mockito is currently self-attaching..." o
 * "A Java agent has been loaded dynamically..." son solo informativos.
 * No afectan la ejecucion ni los resultados de las pruebas.
 * Se deben a cambios en futuras versiones del JDK respecto al uso de agentes y Mockito.
 * Podemos ignorarlos por ahora; el codigo funciona correctamente.
 */

/*
Refrencia de como se puede utilizar Mockito:
https://youtu.be/j9k3epjUgr8?si=PrddJYdmarPRNWlz
Es basicamente algunos conceptos o etiquetas que usa en el video replicados en mis pruebas
 */
@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task1;
    private Task task2;

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

    @Test
    void getAllTasks() {
        when(taskRepository.findAll()).thenReturn(List.of(task1, task2));

        List<Task> result = taskService.getAllTasks();

        assertEquals(2, result.size());
        verify(taskRepository).findAll();

        System.out.println("✅ Test ejecutado correctamente");
    }

    @Test
    void createTask() {
        taskService.createTask("Nueva");

        verify(taskRepository).save(argThat(t -> t.getTitle().equals("Nueva") && !t.isCompleted()));

        System.out.println("✅ Test ejecutado correctamente");
    }

    @Test
    void deleteTask() {
        taskService.deleteTask(1L);

        verify(taskRepository).deleteById(1L);

        System.out.println("✅ Test ejecutado correctamente");
    }

    @Test
    void toggleTask_CambiaEstado() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task1));

        taskService.toggleTask(1L); // pasa de false a true
        assertTrue(task1.isCompleted());

        taskService.toggleTask(1L); // pasa de true a false
        assertFalse(task1.isCompleted());

        verify(taskRepository, times(2)).save(task1);

        System.out.println("✅ Test ejecutado correctamente");
    }

    @Test
    void toggleTask_TareaNoExiste() {
        when(taskRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> taskService.toggleTask(99L));

        verify(taskRepository, never()).save(any());

        System.out.println("✅ Test ejecutado correctamente");
    }
}
