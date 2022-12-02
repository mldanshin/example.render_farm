package com.example.testing.client.services;

import com.example.testing.client.models.User;
import com.example.testing.client.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service("userServiceClient")
public class UserService {
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.create(user);
    }

    public User identify(User user) {
        return userRepository.identify(user);
    }
}
