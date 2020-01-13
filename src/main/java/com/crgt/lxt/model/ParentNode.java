package com.crgt.lxt.model;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: zookeeperStudy
 * @description: 父节点
 * @author: lxt-15863171690
 * @create: 2020-01-09 17:13
 **/
@Slf4j
public class ParentNode {

    @Autowired
    private ZooKeeper zooKeeper;

    AsyncCallback.StringCallback stringCallback = new AsyncCallback.StringCallback() {
        @SneakyThrows
        @Override
        public void processResult(int i, String s, Object o, String s1) {
            switch (KeeperException.Code.get(i)) {
                case CONNECTIONLOSS:
                    Thread.sleep(1000L);
                    createParent(s, (byte[]) o);
                    break;
                case OK:
                    log.info("Parent created");
                    break;
                case NODEEXISTS:
                    log.warn("Parent already registered:" + s);
                    break;
                default:
                    log.error("Something went wrong:{}",s);
            }
        }
    };

    public void bootstrap() throws KeeperException, InterruptedException {
        createParent("/workers",new byte[0]);
        createParent("/assign",new byte[0]);
        createParent("/tasks",new byte[0]);
        createParent("/status",new byte[0]);
    }

    public void createParent(String path,byte[] data) throws KeeperException, InterruptedException {
        zooKeeper.create(path,data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT,stringCallback,data);

    }


}
