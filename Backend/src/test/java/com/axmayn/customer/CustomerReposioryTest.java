package com.axmayn.customer;

import com.axmayn.AbstractContainerTest;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerReposioryTest extends AbstractContainerTest{

    @Autowired
    private CustomerReposiory underTest;

    Faker faker = new Faker();
    Random random = new Random();

    @BeforeEach
    void setUp() {




    }

    @Test
    void existsCustomerByEmail() {
        Customer c = new Customer(faker.name().name(), faker.internet().emailAddress() + "_" + UUID.randomUUID(), random.nextInt(16, 50), "female");

        underTest.save(c);

        Optional<Customer> actual = underTest.findAll()
                .stream()
                .filter(t -> t.getEmail().equals(c.getEmail()))
                .findFirst();

        assertThat(actual).isPresent().hasValueSatisfying(t ->
        {
            assertThat(t.getEmail()).isEqualTo(c.getEmail());
        });

    }

    @Test
    void existsCustomerById() {
    }

    @Test
    void findCustomerByEmail() {
    }
}