package com.example.testing.client.repositories;

import com.example.testing.client.models.Task;

import java.util.List;

public interface TaskRepository {

    Task create(int userId, String name);

    List<Task> getActualByUser(int userId);
}
