package com.example.testing.server.services;

import com.example.testing.server.models.Task;
import com.example.testing.server.models.TaskHistory;
import com.example.testing.server.models.TaskStatus;
import com.example.testing.server.models.dto.HistoryDto;
import com.example.testing.server.models.dto.TaskHistoriesDto;
import com.example.testing.server.repositories.TaskHistoryRepository;
import com.example.testing.server.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TaskHistoryService {

    private final TaskHistoryRepository taskHistoryRepository;

    private final TaskRepository taskRepository;

    public HistoryDto create(int taskId, TaskStatus status, LocalDateTime dateTime) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        return convertToHistoryDto(
                taskHistoryRepository.save(
                        TaskHistory.builder().tasks(task).status(status).dateTime(dateTime).build()
                )
        );
    }

    public List<TaskHistoriesDto> getByUser(int userId) {

        return taskRepository.getByUser(userId)
                .stream()
                .map(this::convertToTaskHistoriesDto).toList();
    }

    private TaskHistoriesDto convertToTaskHistoriesDto(Task task) {
        return new TaskHistoriesDto(
                task.getName(),
                task.getHistory()
                        .stream()
                        .map(item -> convertToHistoryDto(item))
                        .toList()
        );
    }

    private HistoryDto convertToHistoryDto(TaskHistory history) {
        return new HistoryDto(history.getStatus(), history.getDateTime());
    }
}
