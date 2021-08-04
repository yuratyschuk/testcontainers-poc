package com.example.testcontainec.poc.service;


import com.example.testcontainec.poc.TestContainerConfiguration;
import com.example.testcontainec.poc.model.Task;
import com.example.testcontainec.poc.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TaskServiceTest extends TestContainerConfiguration {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void testTaskListIsFindByProjectIdAndActive() {
        List<Task> result = (List<Task>) taskRepository.getAllByProjectIdAndActive(1, false);

        assertEquals(result.size(), 1);
    }

    @Test
    public void testTaskListIsFindByProjectId() {
        List<Task> result = (List<Task>) taskRepository.getAllByProjectId(1);

        assertEquals(result.size(), 3);
    }

    @Test
    @Transactional
    public void testTaskEntityWithIdIsDeletedById() {
        List<Task> beforeDelete = taskRepository.findAll();

        taskRepository.deleteById(1L);

        List<Task> afterDelete = taskRepository.findAll();

        assertNotEquals(afterDelete.size(), beforeDelete.size());
    }

    @Test
    @Transactional
    public void testTaskEntityWithId1IsUpdated() {
        Task task = taskRepository.getById(1L);

        task.setDescription("Updated description");
        taskRepository.save(task);

        Task result = taskRepository.getById(1L);

        assertEquals(result.getDescription(), "Updated description");
    }

    @Test
    @Transactional
    public void testTaskEntityWillBeInserted() {
        Task task = new Task();
        task.setTitle("inserted title");
        task.setDescription("inserted description");

        taskRepository.save(task);

        assertNotEquals(task.getId(), 0);
    }

    @Test
    public void testTaskEntityWillNotBeInserted() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            Task task = new Task();
            task.setDescription("inserted description");

            taskRepository.save(task);
        });
    }
}
