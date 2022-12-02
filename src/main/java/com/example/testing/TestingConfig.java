package com.example.testing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class TestingConfig {

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
