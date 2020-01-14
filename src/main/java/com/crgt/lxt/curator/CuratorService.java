package com.crgt.lxt.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @program: zookeeperStudy
 * @description: curator测试
 * @author: lxt-15863171690
 * @create: 2020-01-14 15:14
 **/
@Service
public class CuratorService {

    @Autowired
    private CuratorFramework curatorFramework;

    public void createTest() {
        try {
            //开始连接ZK集群
            curatorFramework.start();
            //阻塞等待连接ZK集群
            boolean success = false;
            success = curatorFramework.blockUntilConnected(5, TimeUnit.SECONDS);
            if (success) {
                curatorFramework.create().withMode(CreateMode.PERSISTENT).inBackground().forPath("/test", "kkkkk".getBytes());
                curatorFramework.getData().watched().inBackground().forPath("/test");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            curatorFramework.close();
        }
    }




}
