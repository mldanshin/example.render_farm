package com.example.testing.server.services;

import com.example.testing.server.models.User;
import com.example.testing.server.models.dto.UserDto;
import com.example.testing.server.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository repository;

    public UserDto create(String login, String password) {
        return convertToDto(repository.save(User.builder().login(login).password(password).build()));
    }

    public UserDto identify(String login, String password) {
        User user = repository.findByLoginAndPassword(login, password);
        return (user == null) ? null : convertToDto(user);
    }

    public UserDto findById(int id) {
        return convertToDto(repository.findById(id).orElseThrow());
    }

    private UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getLogin(),
                user.getPassword()
        );
    }
}
