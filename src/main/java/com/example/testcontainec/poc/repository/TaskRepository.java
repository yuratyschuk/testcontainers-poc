package com.example.testcontainec.poc.repository;

import com.example.testcontainec.poc.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(value = "select * from task where task.project_id = ?", nativeQuery = true)
    Iterable<Task> getAllByProjectId(long id);

    @Query(value = "select * from task where task.project_id = ? AND task.active = ?", nativeQuery = true)
    Iterable<Task> getAllByProjectIdAndActive(long projectId, boolean active);
}
