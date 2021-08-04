package com.example.testcontainec.poc.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @NotEmpty
    public String name;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @EqualsAndHashCode.Exclude
    public List<Task> taskList;
}
