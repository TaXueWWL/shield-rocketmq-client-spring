package com.snowalker.shield.rocketmq.client.config;

import com.google.common.base.Preconditions;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/1/21 10:39
 * @className RocketMQConsumerConfig
 * @desc RocketMQ消费者配置
 */
public class RocketMQConsumerConfig {

    /**消费者组*/
    private String consumerGroup;

    /**nameServer地址*/
    private String nameSrvAddr;

    /**消息消费主题*/
    private String topic;

    private MessageModel messageModel;

    public RocketMQConsumerConfig(String consumerGroup, String nameSrvAddr, String topic) {
        Preconditions.checkNotNull(consumerGroup);
        Preconditions.checkNotNull(nameSrvAddr);
        Preconditions.checkNotNull(topic);
        this.consumerGroup = consumerGroup;
        this.nameSrvAddr = nameSrvAddr;
        this.topic = topic;
    }

    public RocketMQConsumerConfig(String consumerGroup, String nameSrvAddr, String topic, MessageModel messageModel) {
        Preconditions.checkNotNull(consumerGroup);
        Preconditions.checkNotNull(nameSrvAddr);
        Preconditions.checkNotNull(topic);
        Preconditions.checkNotNull(messageModel);
        this.consumerGroup = consumerGroup;
        this.nameSrvAddr = nameSrvAddr;
        this.topic = topic;
        this.messageModel = messageModel;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public String getNameSrvAddr() {
        return nameSrvAddr;
    }

    public String getTopic() {
        return topic;
    }

    public MessageModel getMessageModel() {
        return messageModel;
    }
}
