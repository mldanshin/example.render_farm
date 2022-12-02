package com.example.testing.server.repositories;

import com.example.testing.server.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.login = :login AND u.password = :password")
    User findByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
