package com.example.testing.client.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class TaskHistories {
    private String taskName;

    private List<History> history;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskHistories that = (TaskHistories) o;
        return Objects.equals(taskName, that.taskName) && Objects.equals(history, that.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, history);
    }

    @Override
    public String toString() {
        return "TaskHistories{" +
                "taskName='" + taskName + '\'' +
                ", history=" + history +
                '}';
    }
}
