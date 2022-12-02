package com.example.testing.client;

import com.example.testing.client.exceptions.ServerException;
import com.example.testing.client.exceptions.UserIdentifyException;
import com.example.testing.client.models.Task;
import com.example.testing.client.models.TaskHistories;
import com.example.testing.client.models.User;
import com.example.testing.client.services.TaskHistoryService;
import com.example.testing.client.services.TaskService;
import com.example.testing.client.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@Profile("dev")
public class Console {
    private final UserService userService;

    private final TaskService taskService;

    private final TaskHistoryService taskHistoryService;

    private User userCurrent;

    private final Scanner scanner;

    public Console(Scanner scanner, UserService userService, TaskService taskService, TaskHistoryService taskHistoryService) {
        this.scanner = scanner;
        this.userService = userService;
        this.taskService = taskService;
        this.taskHistoryService = taskHistoryService;
        createUser();
        createDashboard();
    }

    private void createDashboard() {
        while (true) {
            String userName = (userCurrent == null) ? "" : userCurrent.getLogin();

            System.out.println(
                    "Пользователь: "
                            + userName
                            + ". Команды: q-выход, c-создать юзера, i-вход, t-создать задачу, " +
                            "a-текущие задачи, h-история"
            );

            if (scanner.hasNext()) {
                if (userCurrent == null) {
                    System.out.println("Создайте пользователя или аутентифицируйтесь.");
                    continue;
                }

                switch (scanner.next()) {
                    case "q":
                        return;
                    case "c":
                        createUser();
                        break;
                    case "i":
                        identifyUser();
                        break;
                    case "t":
                        createTask();
                        break;
                    case "a":
                        getActualTasks();
                        break;
                    case "h":
                        getHistoryTasks();
                        break;
                }
            }
        }
    }

    private void createUser() {
        System.out.println("Пройдите процедуру создания юзера.");
        User user = new User();

        System.out.print("Логин: ");
        if (scanner.hasNext()) {
            user.setLogin(scanner.next());
        }

        System.out.print("Пароль: ");
        if (scanner.hasNext()) {
            user.setPassword(scanner.next());
        }

        try {
            userCurrent = userService.create(user);
            System.out.println("Пользователь успешно создан.");
        } catch (ServerException e) {
            System.out.println("Не удалось создать - ошибка на сервере.");
        }
    }

    private void identifyUser() {
        System.out.println("Пройдите процедуру аутентификации юзера.");
        User user = new User();

        System.out.print("Логин: ");
        if (scanner.hasNext()) {
            user.setLogin(scanner.next());
        }

        System.out.print("Пароль: ");
        if (scanner.hasNext()) {
            user.setPassword(scanner.next());
        }

        try {
            userCurrent = userService.identify(user);
            System.out.println("Пользователь " + userCurrent.getLogin() + " успешно аутентифицирован.");
        } catch (UserIdentifyException e) {
            System.out.println("Неверный логин или пароль.");
        }
    }

    private void createTask() {
        System.out.println("Создание задачи.");

        System.out.print("Наименование: ");
        String name = "";
        if (scanner.hasNext()) {
            name = scanner.next();
        }

        try {
            taskService.create(userCurrent.getId(), name);
            System.out.println("Задача создана.");
        } catch (ServerException e) {
            System.out.println("Ошибка при создании задачи.");
        }
    }

    private void getActualTasks() {
        List<Task> tasks = taskService.getActualByUser(userCurrent.getId());
        if (tasks.isEmpty()) {
            System.out.println("Задач нет.");
        } else {
            System.out.println("Список актуальных задач: ");
            tasks.forEach(this::printTask);
        }
    }

    private void printTask(Task task) {
        System.out.println("id: " +
                task.getId() +
                ", " +
                "наименование: " +
                task.getName() +
                ", " +
                "статус: " +
                task.getStatus().name() +
                "."
        );
    }

    private void getHistoryTasks() {
        List<TaskHistories> tasks = taskHistoryService.getByUser(userCurrent.getId());
        if (tasks.isEmpty()) {
            System.out.println("Задач нет.");
        } else {
            System.out.println("История статуса задач: ");
            tasks.forEach(this::printHistoryTask);
        }
    }

    private void printHistoryTask(TaskHistories histories) {
        System.out.println("Задача: " +
                histories.getTaskName() +
                "."
        );
        histories.getHistory().forEach(
                item -> System.out.println(item.getStatus() + " " + item.getDateTime().toString())
        );
    }
}
