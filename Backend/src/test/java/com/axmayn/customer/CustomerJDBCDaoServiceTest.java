package com.axmayn.customer;

import com.axmayn.AbstractContainerTest;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;


class CustomerJDBCDaoServiceTest extends AbstractContainerTest {


    private CustomerJDBCDaoService underTest;
    private final CustomerRowMapper customerRowMapper = new CustomerRowMapper();

    Faker faker = new Faker();
    Random random = new Random();

    Customer c1 = new Customer(faker.name().name(), faker.internet().emailAddress() + "_" + UUID.randomUUID(), random.nextInt(16, 50));
    Customer c2 = new Customer(faker.name().name(), faker.internet().emailAddress()+ "_" + UUID.randomUUID(), random.nextInt(16, 50));
    Customer c3 = new Customer(faker.name().name(), faker.internet().emailAddress()+ "_" + UUID.randomUUID(), random.nextInt(16, 50));


    @BeforeEach
    void setUp() {
        underTest = new CustomerJDBCDaoService(
                getJdbcTemplate(),
                customerRowMapper);
    }

    @Test
    void findAllCustomer() {

        underTest.insertCustomer(c1);
        underTest.insertCustomer(c2);
        underTest.insertCustomer(c3);

        List<Customer> result = underTest.findAllCustomer();

        assertThat(result.size() == 3).isTrue();
    }

    @Test
    void findCustomerById() {

        Customer c4 = new Customer(faker.name().name(), faker.internet().emailAddress()+ "_" + UUID.randomUUID(), random.nextInt(16, 50));
        underTest.insertCustomer(c4);

        Integer id = underTest.findAllCustomer()
                .stream()
                .filter(c -> c.getEmail().equals(c4.getEmail()))
                .map(Customer::getId)
                        .findFirst()
                .orElseThrow();

        Optional<Customer> actual = underTest.findCustomerById(id);

        assertThat(actual).isPresent().hasValueSatisfying(c -> {
            assertThat(c.getName()).isEqualTo(c4.getName());
            assertThat(c.getEmail()).isEqualTo(c4.getEmail());
        });
        
    }

    @Test
    void insertCustomer() {

        Customer c5 = new Customer(faker.name().name(), faker.internet().emailAddress()+ "_" + UUID.randomUUID(), random.nextInt(16, 50));

        underTest.insertCustomer(c5);

        Customer actual = underTest.findAllCustomer()
                .stream()
                .filter(c -> c.getEmail().equals(c5.getEmail()))
                .findFirst()
                .orElseThrow();

        assertThat(actual.getEmail()).isEqualTo(c5.getEmail());
    }

    @Test
    void existByEmail() {
    }

    @Test
    void existById() {
    }

    @Test
    void deleteCustomer() {
    }

    @Test
    void findByEmail() {
    }
}