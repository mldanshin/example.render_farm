package com.example.testing.client.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class History {
    private TaskStatus status;

    private LocalDateTime dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        History history = (History) o;
        return status == history.status && Objects.equals(dateTime, history.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, dateTime);
    }

    @Override
    public String toString() {
        return "History{" +
                "status=" + status +
                ", dateTime=" + dateTime +
                '}';
    }
}
