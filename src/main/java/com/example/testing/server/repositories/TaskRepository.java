package com.example.testing.server.repositories;

import com.example.testing.server.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query("SELECT t FROM Task t " +
            "WHERE t.user.id = :userId")
    List<Task> getByUser(@Param("userId") int userId);

    @Query("SELECT t FROM Task t " +
            "WHERE t.user.id = :userId " +
            "AND t.status = com.example.testing.server.models.TaskStatus.RENDERING")
    List<Task> getActualByUser(@Param("userId") int userId);
}
