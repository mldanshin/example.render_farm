package com.example.testing.server.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private TaskStatus status;

    @OneToMany(mappedBy = "tasks")
    private List<TaskHistory> history;

    @ManyToOne
    private User user;

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
