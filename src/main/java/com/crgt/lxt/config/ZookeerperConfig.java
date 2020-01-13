package com.crgt.lxt.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @program: zookeeperStudy
 * @description: zookeeper 配置类
 * @author: lxt-15863171690
 * @create: 2020-01-06 18:15
 **/
@Configuration
public class ZookeerperConfig {

    @Value("${zookeeper.zkurl}")
    public String zkUrl;

    @Value("${zookeeper.sessionTimeOut}")
    public Integer sessionTimeOut;

    @Bean("watch")
    public Watcher getWatch() {
        return new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);
            }
        };
    }

    @Bean
    public ZooKeeper getZookeeper(@Qualifier("watch") Watcher watcher) throws IOException {
        return new ZooKeeper(zkUrl,sessionTimeOut,watcher);
    }


}
