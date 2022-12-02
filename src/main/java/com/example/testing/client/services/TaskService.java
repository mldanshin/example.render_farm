package com.example.testing.client.services;

import com.example.testing.client.models.Task;
import com.example.testing.client.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("taskServiceClient")
public class TaskService {

    private final TaskRepository taskRepository;

    public Task create(int userId, String name) {
        return taskRepository.create(userId, name);
    }

    public List<Task> getActualByUser(int userId) {
        return taskRepository.getActualByUser(userId);
    }
}
