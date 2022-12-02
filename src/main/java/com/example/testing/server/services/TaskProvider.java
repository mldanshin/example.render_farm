package com.example.testing.server.services;

import com.example.testing.server.models.Task;

public interface TaskProvider {
    Task getTask();

    void setStartTask();

    void setEndTask();
}
