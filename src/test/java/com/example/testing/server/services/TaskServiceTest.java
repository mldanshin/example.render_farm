package com.example.testing.server.services;

import com.example.testing.server.models.TaskStatus;
import com.example.testing.server.models.dto.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Test
    void createSuccess() {
        Assertions.assertEquals(
                new TaskDto(1, "task1", TaskStatus.RENDERING),
                taskService.create(1, "task1")
        );

        Assertions.assertEquals(
                new TaskDto(2, "task23", TaskStatus.RENDERING),
                taskService.create(2, "task23")
        );
    }

    @Test
    void createWrong() {
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> taskService.create(99, "task1")
        );
    }

    @Test
    void getActualByUserSuccess() {
        Assertions.assertEquals(
                List.of(
                        new TaskDto(1, "task1", TaskStatus.RENDERING),
                        new TaskDto(3, "task3", TaskStatus.RENDERING)
                ),
                taskService.getActualByUser(3)
        );
    }

    @Test
    void getActualByUserWrong() {
        Assertions.assertEquals(
                List.of(),
                taskService.getActualByUser(99)
        );
    }

    @Test
    void setStatusSuccess() {
        Assertions.assertEquals(
                new TaskDto(1, "task1", TaskStatus.COMPLETE),
                taskService.setStatus(1, TaskStatus.COMPLETE)
        );
    }

    @Test
    void setStatusWrong() {
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> taskService.setStatus(99, TaskStatus.COMPLETE)
        );
    }

    @Test
    void findByIdSuccess() {
        Assertions.assertEquals(
                new TaskDto(1, "task1", TaskStatus.RENDERING),
                taskService.findById(1)
        );

        TaskDto taskActual = taskService.findById(4);
        Assertions.assertEquals(
                new TaskDto(4, "task4", TaskStatus.RENDERING),
                taskActual
        );
    }

    @Test
    void findByIdWrong() {
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> taskService.findById(99)
        );
    }
}
