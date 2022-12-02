package com.example.testing.server.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class TaskHistoriesDto {
    private String taskName;

    private List<HistoryDto> history;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskHistoriesDto that = (TaskHistoriesDto) o;
        return Objects.equals(taskName, that.taskName) && Objects.equals(history, that.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, history);
    }

    @Override
    public String toString() {
        return "TaskHistoriesDto{" +
                "taskName='" + taskName + '\'' +
                ", history=" + history +
                '}';
    }
}
