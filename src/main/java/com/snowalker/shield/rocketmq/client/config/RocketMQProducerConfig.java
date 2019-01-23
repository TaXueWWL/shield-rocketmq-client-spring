package com.snowalker.shield.rocketmq.client.config;

import com.google.common.base.Preconditions;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/1/21 10:38
 * @className RocketMQProducerConfig
 * @desc RocketMQ生产者配置
 */
public class RocketMQProducerConfig {

    /**生产者组*/
    private String producerGroup;

    /**指定NameServer名称*/
    private String nameSrvAddr;

    public RocketMQProducerConfig(String producerGroup, String nameSrvAddr) {
        Preconditions.checkNotNull(producerGroup);
        Preconditions.checkNotNull(nameSrvAddr);
        this.producerGroup = producerGroup;
        this.nameSrvAddr = nameSrvAddr;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public String getNameSrvAddr() {
        return nameSrvAddr;
    }
}
