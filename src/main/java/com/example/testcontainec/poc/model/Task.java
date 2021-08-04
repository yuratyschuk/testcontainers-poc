package com.example.testcontainec.poc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty
    private String title;

    private String description;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "finish_date")
    private LocalDateTime finishDate;

    @Column(name = "active")
    private boolean isActive;

    private int priority;
}

