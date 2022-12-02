package com.example.testing.client.services;

import com.example.testing.client.models.History;
import com.example.testing.client.models.TaskHistories;
import com.example.testing.client.models.TaskStatus;
import com.example.testing.server.models.dto.HistoryDto;
import com.example.testing.server.models.dto.TaskHistoriesDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
class TaskHistoryServiceTest {

    @MockBean
    private com.example.testing.server.services.TaskHistoryService taskHistoryServiceServer;

    @Autowired
    private TaskHistoryService taskHistoryService;

    private static Stream<Arguments> getByUserProvider() {
        return Stream.of(
                Arguments.of(1, List.of(), List.of()),
                Arguments.of(
                        2,
                        List.of(new TaskHistoriesDto("task1", List.of(
                                new HistoryDto(
                                        com.example.testing.server.models.TaskStatus.RENDERING,
                                        LocalDateTime.of(2022, 11, 11, 12, 12, 12)
                                ),
                                new HistoryDto(
                                        com.example.testing.server.models.TaskStatus.COMPLETE,
                                        LocalDateTime.of(2022, 10, 10, 14, 18, 22)
                                )
                        ))),
                        List.of(new TaskHistories("task1", List.of(
                                new History(
                                        TaskStatus.RENDERING,
                                        LocalDateTime.of(2022, 11, 11, 12, 12, 12)
                                ),
                                new History(
                                        TaskStatus.COMPLETE,
                                        LocalDateTime.of(2022, 10, 10, 14, 18, 22)
                                )
                        )))
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getByUserProvider")
    void getByUser(int userId, List<TaskHistoriesDto> tasksServer, List<TaskHistories> tasksExpected) {
        Mockito.when(taskHistoryServiceServer.getByUser(userId)).thenReturn(tasksServer);

        Assertions.assertEquals(tasksExpected, taskHistoryService.getByUser(userId));
    }


}