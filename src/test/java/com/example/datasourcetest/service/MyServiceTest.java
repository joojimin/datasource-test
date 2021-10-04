package com.example.datasourcetest.service;

import com.example.datasourcetest.service.MyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyServiceTest {

    @Autowired
    private MyService myService;

    @Test
    void originalJpaTest() {
        myService.originalJpa();
    }

    @Test
    void checkTransactional() {
        myService.checkTransactional();
    }

    @Test
    void rollbackTransaction() {
        myService.checkRollback();
    }

    @Test
    void requiredNewTransaction() {
        myService.checkRequiredNew();
    }
}
