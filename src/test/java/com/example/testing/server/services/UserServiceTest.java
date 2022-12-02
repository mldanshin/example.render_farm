package com.example.testing.server.services;

import com.example.testing.server.models.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@SpringBootTest
@AutoConfigureTestDatabase
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void create() {
        Assertions.assertEquals(
                new UserDto(1, "login", "password"),
                userService.create("login", "password")
        );

        Assertions.assertEquals(
                new UserDto(2, "login45", "password45"),
                userService.create("login45", "password45")
        );
    }

    @Test
    void identifySuccess() {
        Assertions.assertEquals(
                new UserDto(1, "login1", "password1"),
                userService.identify("login1", "password1")
        );
    }

    @Test
    void identifyWrong() {
        Assertions.assertNull(userService.identify("loginFake", "passwordFake"));
        Assertions.assertNull(userService.identify("login1", "passwordFake"));
        Assertions.assertNull(userService.identify("loginFake", "password1"));
    }

    @Test
    void findByIdSuccess() {
        Assertions.assertEquals(
                new UserDto(1, "login1", "password1"),
                userService.findById(1)
        );
    }

    @Test
    void findByIdWrong() {
        Assertions.assertThrows(
                NoSuchElementException.class,
                () -> userService.findById(99)
        );
    }
}