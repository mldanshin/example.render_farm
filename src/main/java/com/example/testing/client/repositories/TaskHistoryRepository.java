package com.example.testing.client.repositories;

import com.example.testing.client.models.TaskHistories;

import java.util.List;

public interface TaskHistoryRepository {

    List<TaskHistories> getByUser(int userId);
}
