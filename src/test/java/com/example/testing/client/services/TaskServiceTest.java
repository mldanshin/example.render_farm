package com.example.testing.client.services;

import com.example.testing.client.exceptions.ServerException;
import com.example.testing.client.models.Task;
import com.example.testing.client.models.TaskStatus;
import com.example.testing.server.models.dto.TaskDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class TaskServiceTest {

    @MockBean
    private com.example.testing.server.services.TaskService taskRepositoryServer;

    @Autowired
    private TaskService taskService;

    @Test
    void createSuccess() {
        int userId = 1;
        String taskName = "taskFake";
        Mockito.when(taskRepositoryServer.create(userId, taskName))
                .thenReturn(new TaskDto(userId, taskName, com.example.testing.server.models.TaskStatus.RENDERING));

        Assertions.assertEquals(
                new Task(userId, taskName, TaskStatus.RENDERING),
                taskService.create(userId, taskName)
        );
    }

    @Test
    void createWrong() {
        int userId = 1;
        String taskName = "taskFake";
        Mockito.when(taskRepositoryServer.create(userId, taskName))
                .thenReturn(null);

        Assertions.assertThrows(
                ServerException.class,
                () -> taskService.create(userId, taskName)
        );
    }

    @ParameterizedTest
    @MethodSource("getActualByUserProvider")
    void getActualByUser(int userId, List<TaskDto> tasksServer, List<Task> tasksExpected) {
        Mockito.when(taskRepositoryServer.getActualByUser(userId))
                .thenReturn(tasksServer);

        Assertions.assertEquals(
                tasksExpected,
                taskService.getActualByUser(userId)
        );
    }

    private static Stream<Arguments> getActualByUserProvider() {
        return Stream.of(
                Arguments.of(1, List.of(), List.of()),
                Arguments.of(
                        2,
                        List.of(
                                new TaskDto(1, "task1", com.example.testing.server.models.TaskStatus.RENDERING),
                                new TaskDto(2, "task2", com.example.testing.server.models.TaskStatus.RENDERING)
                        ),
                        List.of(
                                new Task(1, "task1", TaskStatus.RENDERING),
                                new Task(2, "task2", TaskStatus.RENDERING)
                        )
                )
        );
    }

}