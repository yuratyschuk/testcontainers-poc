package com.example.testcontainec.poc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * We can use this annotation with properties to start our container.
 */
//@SpringBootTest(properties = {
//        "spring.datasource.url=jdbc:tc:postgresql:10-alpine:///integration_tests",
//        "spring.datasource.driver-class-name=org.testcontainers.jdbc.ContainerDatabaseDriver"
//})
@SpringBootTest
public abstract class TestContainerConfiguration {

    private static final String POSTGRES_VERSION = "postgres:12.7";

    private static final String IMPORT_DATA_FILE = "import.sql";
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(POSTGRES_VERSION)
            .withInitScript(IMPORT_DATA_FILE);

    static {
        postgresContainer.start();
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }


}
