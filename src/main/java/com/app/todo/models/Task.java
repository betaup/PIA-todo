package com.app.todo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Entidad que representa una tarea (Task) en el sistema To-Do.
 *
 * Cada tarea tiene un identificador unico, un titulo y un estado que indica si ha sido completada.
 */
@Entity
public class Task {
    /**
     * Identificador unico de la tarea. Se genera automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * Titulo o descripción breve de la tarea.
     */
    private String title;
    /**
     * Estado que indica si la tarea ha sido completada.
     */
    private boolean completed;
    /**
     * Fecha y hora en la que se creo la tarea.
     */
    private LocalDateTime fechaDeCreacion;

    // Constructor que inicializa la fecha de creacion automáticamente
    public Task() {
        this.fechaDeCreacion = LocalDateTime.now();
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
