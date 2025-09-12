package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class CustomerRepositoryClient {
    @Autowired
    CustomerRepository custRepo;

    @Test
    @Transactional
    @Commit
    public void insert() {
        Customer cust = new Customer();
        cust.setName("홍길동");
        cust.setPhone("0101111");
        Customer result = custRepo.save(cust);
        assertEquals(result.getName(), "홍길동");
    }
}
