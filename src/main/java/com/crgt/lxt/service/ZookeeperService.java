package com.crgt.lxt.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: zookeeperStudy
 * @description: zookeeper业务逻辑层
 * @author: lxt-15863171690
 * @create: 2020-01-07 17:59
 **/
@Service
@Slf4j
public class ZookeeperService {
    @Autowired
    private ZooKeeper zookeeper;

    public Stat testTimeOut() throws KeeperException, InterruptedException {
        return zookeeper.exists("/test",true);
    }
}
