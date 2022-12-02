package com.example.testing.client.repositories;

import com.example.testing.client.models.Task;
import com.example.testing.client.models.TaskStatus;
import com.example.testing.client.models.User;
import com.example.testing.server.models.dto.TaskDto;
import com.example.testing.server.models.dto.UserDto;

public class Converter {
    static User convertUser(UserDto user) {
        return new User(user.getId(), user.getLogin(), user.getPassword());
    }

    static Task convertTask(TaskDto task) {
        return new Task(
                task.getId(),
                task.getName(),
                convertTaskStatus(task.getStatus())
        );
    }

    static TaskStatus convertTaskStatus(com.example.testing.server.models.TaskStatus status) {
        if (status == com.example.testing.server.models.TaskStatus.RENDERING) {
            return TaskStatus.RENDERING;
        }
        return TaskStatus.COMPLETE;
    }
}
