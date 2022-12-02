package com.example.testing.server.services;

import com.example.testing.server.models.TaskStatus;
import com.example.testing.server.models.dto.HistoryDto;
import com.example.testing.server.models.dto.TaskHistoriesDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class TaskHistoryServiceTest {
    @Autowired
    private TaskHistoryService taskHistoryService;

    @Test
    void createSuccess() {
        LocalDateTime dateTime = LocalDateTime.now();
        HistoryDto expected = new HistoryDto(TaskStatus.RENDERING, dateTime);
        HistoryDto actual = taskHistoryService.create(1, TaskStatus.RENDERING, dateTime);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void createWrong() {
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> taskHistoryService.create(99, TaskStatus.RENDERING, LocalDateTime.now())
        );
    }

    @Test
    void getByUserSuccess() {
        Assertions.assertEquals(
                List.of(
                        new TaskHistoriesDto("task4", List.of(
                                new HistoryDto(TaskStatus.RENDERING, LocalDateTime.of(2022, 11, 11, 17, 36, 00))
                        )),
                        new TaskHistoriesDto("task5", List.of())
                ),
                taskHistoryService.getByUser(4)
        );
    }

    @Test
    void getByUserWrong() {
        Assertions.assertEquals(
                List.of(),
                taskHistoryService.getByUser(99)
        );
    }
}