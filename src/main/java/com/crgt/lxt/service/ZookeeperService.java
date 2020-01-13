package com.crgt.lxt.service;

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

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

    public void createJava() throws KeeperException, InterruptedException {
        zookeeper.create("/java","java".getBytes(),OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public void createJava2() throws KeeperException, InterruptedException {
        System.out.println(LocalDateTime.now());
        AsyncCallback.StringCallback cb = new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int i, String s, Object o, String s1) {
                System.out.println(LocalDateTime.now());
                switch (KeeperException.Code.get(i)) {
                    case CONNECTIONLOSS:
                        System.out.println("连接丢失");
                        break;
                    case OK:
                        System.out.println("创建临时节点成功");
                        System.out.println(i);
                        System.out.println(s);
                        System.out.println(o);
                        System.out.println(s1);
                        break;
                    default:
                        System.out.println("发生了什么");
                }
            }
        };
        zookeeper.create("/java2","java2".getBytes(),OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL,cb,"sss");
        System.out.println(LocalDateTime.now());
    }

    public void getTest() {
        //zookeeper.geta
    }

    public void deleteJava() throws KeeperException, InterruptedException {
        zookeeper.delete("/java",1);
    }

    public void getJava() throws KeeperException, InterruptedException {
        Stat stat = new Stat();
        System.out.println(new String(zookeeper.getData("/java",true,stat)));
    }

}
