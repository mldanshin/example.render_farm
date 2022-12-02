package com.example.testing.server.models.dto;

import com.example.testing.server.models.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
@Getter
public class TaskDto {

    private int id;

    private String name;

    private TaskStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDto taskDto = (TaskDto) o;
        return id == taskDto.id && Objects.equals(name, taskDto.name) && status == taskDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status);
    }
}
