package com.example.testing.client.repositories;

import com.example.testing.client.exceptions.ServerException;
import com.example.testing.client.models.Task;
import com.example.testing.server.models.dto.TaskDto;
import com.example.testing.server.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository("taskRepositoryImplClient")
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskService taskService;

    @Override
    public Task create(int userId, String name) {
        TaskDto taskServer = taskService.create(userId, name);
        if (taskServer == null) {
            throw new ServerException();
        }
        return Converter.convertTask(taskServer);
    }

    @Override
    public List<Task> getActualByUser(int userId) {
        return taskService.getActualByUser(userId).
                stream().
                map(task -> new Task(
                        task.getId(),
                        task.getName(),
                        Converter.convertTaskStatus(task.getStatus())
                ))
                .toList();
    }
}
