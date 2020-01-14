package com.crgt.lxt.curator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CuratorServiceTest {

    @Autowired
    CuratorService curatorService;

    @Test
    void createTest() {
        curatorService.createTest();
    }
}