package com.crgt.lxt.config;

import com.crgt.lxt.service.ZookeeperService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.nio.file.WatchEvent;
import java.nio.file.Watchable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: zookeeperStudy
 * @description:
 * @author: lxt-15863171690
 * @create: 2020-01-13 15:13
 **/
@Slf4j
@Component
public class ApplicationImpl implements ApplicationRunner {

    @Autowired
    ZooKeeper zooKeeper;

    Watcher watch = new Watcher() {
        @Override
        public void process(WatchedEvent watchedEvent) {
            if (watchedEvent.getType().equals(Event.EventType.NodeChildrenChanged)) {
                log.info("子节点发生变化：{}", LocalDateTime.now());
                log.info(watchedEvent.getPath());
                log.info(watchedEvent.getState().name());
                log.info(watchedEvent.getType().name());
                log.info(watchedEvent.getWrapper().getPath());
            }
            if (watchedEvent.getType().equals(Event.EventType.NodeCreated)) {
                log.info("节点产生：{}", LocalDateTime.now());
                log.info(watchedEvent.getPath());
                log.info(watchedEvent.getState().name());
                log.info(watchedEvent.getType().name());
                log.info(watchedEvent.getWrapper().getPath());
            }
            if (watchedEvent.getType().equals(Event.EventType.NodeDataChanged)) {
                log.info("节点发生变化：{}", LocalDateTime.now());
                log.info(watchedEvent.getPath());
                log.info(watchedEvent.getState().name());
                log.info(watchedEvent.getType().name());
                log.info(watchedEvent.getWrapper().getPath());
            }
            if (watchedEvent.getType().equals(Event.EventType.NodeDeleted)) {
                log.info("节点被删除：{}", LocalDateTime.now());
                log.info(watchedEvent.getPath());
                log.info(watchedEvent.getState().name());
                log.info(watchedEvent.getType().name());
                log.info(watchedEvent.getWrapper().getPath());
            }
            if (watchedEvent.getType().equals(Event.EventType.None)) {
                log.info("这个是什么意思呢：{}", LocalDateTime.now());
                log.info(watchedEvent.getPath());
                log.info(watchedEvent.getState().name());
                log.info(watchedEvent.getType().name());
                log.info(watchedEvent.getWrapper().getPath());
            }
            //每次监控只能监控一次，这样可以实现持续监控
            getChildren();
        }
    };

    AsyncCallback.ChildrenCallback childrenCallBack = new AsyncCallback.ChildrenCallback() {
        @SneakyThrows
        @Override
        public void processResult(int i, String s, Object o, List<String> list) {
            log.info("第一次回调函数");
            switch (KeeperException.Code.get(i)) {
                case CONNECTIONLOSS:
                    Thread.sleep(100);
                    getChildren();
                    break;
                case OK:
                    log.info(i + "");
                    log.info(s);
                    log.info(o.toString());
                    log.info(list.toString());
                    break;
                default:
                    log.info("watch's wrong!");
            }

        }
    };



    @Override
    public void run(ApplicationArguments args) throws Exception {
        getChildren();
    }

    public void test() {

    }


    public void  getChildren() {
        zooKeeper.getChildren("/test",watch,childrenCallBack,"和尚不吃肉");
    }
}
