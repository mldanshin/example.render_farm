package com.example.testing.client.services;

import com.example.testing.client.models.TaskHistories;
import com.example.testing.client.repositories.TaskHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service("taskHistoryServiceClient")
public class TaskHistoryService {

    private TaskHistoryRepository taskHistoryRepository;

    public List<TaskHistories> getByUser(int userId) {
        return taskHistoryRepository.getByUser(userId);
    }
}
