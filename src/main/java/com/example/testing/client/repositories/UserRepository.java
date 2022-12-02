package com.example.testing.client.repositories;

import com.example.testing.client.models.User;

public interface UserRepository {
    User create(User user);

    User identify(User user);
}
