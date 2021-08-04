package com.example.testcontainec.poc.service;

import com.example.testcontainec.poc.TestContainerConfiguration;
import com.example.testcontainec.poc.model.Project;
import com.example.testcontainec.poc.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectServiceTest extends TestContainerConfiguration {

    @Autowired
    private ProjectRepository projectRepository;


    @Test
    public void testGetByIdAndActiveMethod() {
        List<Project> result = projectRepository.findAll();

        assertEquals(result.size(), 3);
    }

    @Test
    @Transactional
    public void testProjectEntityWithId2IsDeletedById() {
        List<Project> beforeDelete = projectRepository.findAll();
        System.out.println(beforeDelete);
        projectRepository.deleteById(2L);

        List<Project> afterDelete = projectRepository.findAll();

        assertNotEquals(afterDelete.size(), beforeDelete.size());
    }

    @Test
    @Transactional
    public void testProjectEntityWithId1IsUpdated() {
        Project project = projectRepository.getById(1L);

        project.setName("Updated name");
        projectRepository.save(project);

        Project result = projectRepository.getById(1L);

        assertEquals(result.getName(), "Updated name");
    }

    @Test
    @Transactional
    public void testProjectEntityWillBeInserted() {
        Project project = new Project();
        project.setName("inserted name");

        projectRepository.save(project);

        assertNotEquals(project.getId(), 0);
    }

    @Test
    @Transactional
    public void testProjectEntityWillNotBeInserted() {
        assertThrows(DataIntegrityViolationException.class, () -> {
            Project project = new Project();

            projectRepository.save(project);

        });
    }
}
