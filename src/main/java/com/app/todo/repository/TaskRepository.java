package com.app.todo.repository;

import com.app.todo.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

// Me ayuda al CRUD poder comunicarme con mi db
public interface TaskRepository extends JpaRepository<Task, Long> {
}
