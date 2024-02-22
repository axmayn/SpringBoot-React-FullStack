package com.axmayn;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;

@Testcontainers
public class AbstractContainerTest {

    @BeforeAll
    static void setUp(){
        try {

            // Provide database connection details
            String url = postgreSQLContainer.getJdbcUrl();
            String username = postgreSQLContainer.getUsername();
            String password = postgreSQLContainer.getPassword();

            // Establish a database connection
            try (Connection connection = DriverManager.getConnection(url, username, password)) {

                // Create a Liquibase instance
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                Liquibase liquibase = new Liquibase("/db/changelog/changelog-master.xml", new ClassLoaderResourceAccessor(), database);

                // Perform Liquibase update
                liquibase.update("");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Container
    protected static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>("postgres:latest")
                    .withDatabaseName("axmayn-dao-unit-test")
                    .withUsername("axmayn")
                    .withPassword("asdf");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("sprint.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    private DataSource getDatasource(){
        DataSourceBuilder builder = DataSourceBuilder.create()
                .driverClassName(postgreSQLContainer.getDriverClassName())
                .url(postgreSQLContainer.getJdbcUrl())
                .username(postgreSQLContainer.getUsername())
                .password(postgreSQLContainer.getPassword());

        return builder.build();
    }

    protected JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(getDatasource());
    }

}
