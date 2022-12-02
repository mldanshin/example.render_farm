package com.example.testing.server.services;

import lombok.AllArgsConstructor;

import java.util.ArrayDeque;
import java.util.Queue;

@AllArgsConstructor
public class TaskExecutor {
    private final Queue<TaskProvider> queue = new ArrayDeque<>();
    private final Thread thread = new Thread();

    public void add(TaskProvider provider) {
        queue.add(provider);
    }

    private void run() {
        while (true) {
            TaskProvider provider = queue.poll();
            if (provider != null) {
                provider.setStartTask();
                //sleep thread
                provider.setEndTask();
            }
        }
    }
}
