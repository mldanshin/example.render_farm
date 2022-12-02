package com.example.testing.client.repositories;

import com.example.testing.client.exceptions.ServerException;
import com.example.testing.client.exceptions.UserIdentifyException;
import com.example.testing.client.models.User;
import com.example.testing.server.models.dto.UserDto;
import com.example.testing.server.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository("UserRepositoryImplClient")
public class UserRepositoryImpl implements UserRepository {
    private final UserService userService;

    @Override
    public User create(User user) {
        UserDto userServer = userService.create(user.getLogin(), user.getPassword());
        if (userServer == null) {
            throw new ServerException();
        }
        return Converter.convertUser(userServer);
    }

    @Override
    public User identify(User user) {
        UserDto userServer = userService.identify(user.getLogin(), user.getPassword());
        if (userServer == null) {
            throw new UserIdentifyException();
        }
        return Converter.convertUser(userServer);
    }
}
