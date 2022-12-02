package com.example.testing.server.models.dto;

import com.example.testing.server.models.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class HistoryDto {
    private TaskStatus status;

    private LocalDateTime dateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryDto that = (HistoryDto) o;
        return status == that.status && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, dateTime);
    }

    @Override
    public String toString() {
        return "HistoryDto{" +
                "status=" + status +
                ", dateTime=" + dateTime +
                '}';
    }
}
