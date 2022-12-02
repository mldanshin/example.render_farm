package com.example.testing.client.services;

import com.example.testing.client.exceptions.ServerException;
import com.example.testing.client.exceptions.UserIdentifyException;
import com.example.testing.client.models.User;
import com.example.testing.server.models.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private com.example.testing.server.services.UserService userServiceServer;

    @Autowired
    private UserService userService;

    @Test
    void createSuccess() {
        String login = "loginFake";
        String password = "passwordFake";
        Mockito.when(userServiceServer.create(login, password))
                .thenReturn(new UserDto(1, login, password));

        Assertions.assertEquals(
                new User(1, login, password),
                userService.create(new User(1, login, password))
        );
    }

    @Test
    void createWrong() {
        String login = "loginFake";
        String password = "passwordFake";
        Mockito.when(userServiceServer.create(login, password))
                .thenReturn(null);

        Assertions.assertThrows(
                ServerException.class,
                () -> userService.create(new User(1, login, password))
        );
    }

    @Test
    void identifySuccess() {
        String login = "loginFake";
        String password = "passwordFake";
        Mockito.when(userServiceServer.identify(login, password))
                .thenReturn(new UserDto(1, login, password));

        Assertions.assertEquals(
                new User(1, login, password),
                userService.identify(new User(1, login, password))
        );
    }

    @Test
    void identifyWrong() {
        String login = "loginFake";
        String password = "passwordFake";
        Mockito.when(userServiceServer.identify(login, password))
                .thenReturn(null);

        Assertions.assertThrows(
                UserIdentifyException.class,
                () -> userService.identify(new User(1, login, password))
        );
    }
}