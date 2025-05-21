package com.app.todo.repository;

import com.app.todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repositorio de acceso a datos para la entidad {@link Task}.
 *
 * Extiende de {@link JpaRepository}, lo que proporciona operaciones CRUD basicas,
 * asi como funcionalidades adicionales como paginacion y ordenamiento sin necesidad de implementar metodos manualmente.
 *
 * Al extender de JpaRepository, esta interfaz hereda metodos como:
 * - findAll()
 * - findById(Long id)
 * - save(Task task)
 * - deleteById(Long id)
 * - existsById(Long id)
 * - count()
 */
// Me ayuda al CRUD poder comunicarme con mi db
public interface TaskRepository extends JpaRepository<Task, Long> {
}
