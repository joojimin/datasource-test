package com.example.datasourcetest;

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
}
