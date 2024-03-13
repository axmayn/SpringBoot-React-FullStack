package com.axmayn.customer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository("JDBCTempl")
public class CustomerJDBCDaoService implements CustomerDao{

    private final JdbcTemplate jdbcTemplate;
    private final CustomerRowMapper customerRowMapper;

    public CustomerJDBCDaoService(JdbcTemplate jdbcTemplate, CustomerRowMapper customerRowMapper){
        this.jdbcTemplate = jdbcTemplate;
        this.customerRowMapper = customerRowMapper;
    }


    @Override
    public List<Customer> findAllCustomer() {
        var sql = """
                SELECT * FROM public.customer
                """;


        List<Customer> results = jdbcTemplate.query(sql, customerRowMapper);

        return results;

    }

    @Override
    public Optional<Customer> findCustomerById(Integer id) {

        var sql = """
                select * from public.customer
                where id = ?
                """;

        Customer result = jdbcTemplate.queryForObject(sql, new Object[]{id}, customerRowMapper);

        return Optional.of(result);
    }

    @Override
    public void insertCustomer(Customer customer) {

        var sql = """
                INSERT INTO public.customer
                (name, email, age, gender)
                values (?, ?, ?)
                """;

        jdbcTemplate.update(sql, new Object[]{customer.getName(), customer.getEmail(), customer.getAge(), customer.getGender()});

    }

    @Override
    public boolean existByEmail(String email) {
        return false;
    }

    @Override
    public boolean existById(Integer id) {
        return false;
    }

    @Override
    public void deleteCustomer(Integer id) {

    }

    @Override
    public Customer findByEmail(String email) {
        return null;
    }
}
