package com.crgt.lxt.service;

import org.apache.zookeeper.KeeperException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ZookeeperServiceTest {

    @Autowired
    private ZookeeperService zookeeperService;

    @Test
    void testTimeOut() throws KeeperException, InterruptedException {
        System.out.println(zookeeperService.testTimeOut());
    }

    @Test
    void createTest() throws KeeperException, InterruptedException {
        zookeeperService.createJava();
    }

    @Test
    void deleteTest() throws KeeperException, InterruptedException {
        zookeeperService.deleteJava();
    }

    @Test
    void getJavaTest() throws KeeperException, InterruptedException {
        zookeeperService.getJava();

    }

    @Test
    void createJava2Test() throws KeeperException, InterruptedException {
        zookeeperService.createJava2();

    }


}