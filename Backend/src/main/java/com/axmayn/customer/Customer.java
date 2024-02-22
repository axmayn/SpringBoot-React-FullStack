package com.axmayn.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer
{
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "customer_id_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Integer id;

    @Column(
            nullable = false
    )
    private String name;

    @Column(
            nullable = false
    )
    private String email;

    @Column(
            nullable = false
    )
    private Integer age;

    public Customer(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
