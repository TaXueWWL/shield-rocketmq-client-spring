package com.snowalker.shield.rocketmq.client.config;

import com.google.common.base.Preconditions;

import java.util.concurrent.ExecutorService;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/1/21 21:43
 * @className RocketMQTransactionProducerConfig
 * @desc RocketMQ事务消息消费者配置类
 */
public class RocketMQTransactionProducerConfig {

    /**生产者组*/
    private String producerGroup;

    /**指定NameServer名称*/
    private String nameSrvAddr;

    /**本地事务执行线程池*/
    ExecutorService executorService;

    public RocketMQTransactionProducerConfig(String producerGroup,
                                             String nameSrvAddr,
                                             ExecutorService executorService) {
        Preconditions.checkNotNull(producerGroup);
        Preconditions.checkNotNull(nameSrvAddr);
        Preconditions.checkNotNull(executorService);
        this.producerGroup = producerGroup;
        this.nameSrvAddr = nameSrvAddr;
        this.executorService = executorService;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public String getNameSrvAddr() {
        return nameSrvAddr;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }
}
