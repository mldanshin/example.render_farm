package com.example.testing.client.repositories;

import com.example.testing.client.models.History;
import com.example.testing.client.models.TaskHistories;
import com.example.testing.server.models.dto.TaskHistoriesDto;
import com.example.testing.server.services.TaskHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository("taskHistoryRepositoryImplClient")
public class TaskHistoryRepositoryImpl implements TaskHistoryRepository {

    private TaskHistoryService taskHistoryService;

    @Override
    public List<TaskHistories> getByUser(int userId) {
        return taskHistoryService.getByUser(userId).stream().map(this::convert).toList();
    }

    private TaskHistories convert(TaskHistoriesDto server) {
        return new TaskHistories(
                server.getTaskName(),
                server.getHistory()
                        .stream()
                        .map(item -> new History(Converter.convertTaskStatus(item.getStatus()), item.getDateTime()))
                        .toList()
        );
    }
}
