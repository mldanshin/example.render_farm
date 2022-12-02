package com.example.testing.server.services;

import com.example.testing.server.models.Task;
import com.example.testing.server.models.TaskStatus;
import com.example.testing.server.models.User;
import com.example.testing.server.models.dto.TaskDto;
import com.example.testing.server.repositories.TaskRepository;
import com.example.testing.server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskDto create(int userId, String name) {
        User user = userRepository.findById(userId).orElseThrow();
        Task task = Task.builder().name(name).status(TaskStatus.RENDERING).user(user).build();
        return convertToDto(taskRepository.save(task));
    }

    public List<TaskDto> getActualByUser(int userId) {
        return taskRepository.getActualByUser(userId).stream().map(this::convertToDto).toList();
    }

    public TaskDto setStatus(int id, TaskStatus status) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setStatus(status);
        return convertToDto(taskRepository.save(task));
    }

    public TaskDto findById(int id) {
        return convertToDto(taskRepository.findById(id).orElseThrow());
    }

    private TaskDto convertToDto(Task task) {
        return new TaskDto(task.getId(), task.getName(), task.getStatus());
    }
}
