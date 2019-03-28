package com.snowalker.shield.rocketmq.client;

import com.snowalker.shield.rocketmq.client.config.RocketMQConsumerConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/1/21 9:25
 * @className RocketMQConsumerAgent
 * @desc RocketMQ消费者客户端
 */
@Scope("prototype")
@Component
public class RocketMQPushConsumerAgent {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQPushConsumerAgent.class);

    private MessageListenerConcurrently messageListener;

    private DefaultMQPushConsumer defaultMQPushConsumer;

    /**
     * 手动方式初始化，便于参数注入
     * @param messageListener
     * @throws Exception
     */
    public RocketMQPushConsumerAgent init(RocketMQConsumerConfig consumerConfig, MessageListenerConcurrently messageListener)  throws MQClientException  {
        buildDefaultMQPushConsumer(consumerConfig, messageListener);
        defaultMQPushConsumer.subscribe(consumerConfig.getTopic(), "*");
        LOGGER.debug("com.shield.job.message.rocketmq.RocketMQConsumerAgent消费者客户端组装完成");
        return this;
    }

    /**
     * 手动方式初始化，便于参数注入
     * @param consumerConfig
     * @param messageListener
     * @param subExpression      子表达式，如："tag1 || tag2 || tag3" <br>
     *                           null 或者 * 表示订阅所有
     * @return
     * @throws MQClientException
     */
    public RocketMQPushConsumerAgent init(RocketMQConsumerConfig consumerConfig,
                                          MessageListenerConcurrently messageListener,
                                          String subExpression)  throws MQClientException  {
        buildDefaultMQPushConsumer(consumerConfig, messageListener);
        defaultMQPushConsumer.subscribe(consumerConfig.getTopic(), subExpression);
        LOGGER.debug("com.shield.job.message.rocketmq.RocketMQConsumerAgent消费者客户端组装完成");
        return this;
    }

    private void buildDefaultMQPushConsumer(RocketMQConsumerConfig consumerConfig, MessageListenerConcurrently messageListener) {
        defaultMQPushConsumer = new DefaultMQPushConsumer(consumerConfig.getConsumerGroup());
        defaultMQPushConsumer.setNamesrvAddr(consumerConfig.getNameSrvAddr());
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        // 消费模式
        if (consumerConfig.getMessageModel() != null) {
            defaultMQPushConsumer.setMessageModel(consumerConfig.getMessageModel());
        }
        // 注册监听器
        this.messageListener = messageListener;
        defaultMQPushConsumer.registerMessageListener(this.messageListener);
    }

    /**
     * 启动消费者服务
     */
    public void start() throws MQClientException {
        this.defaultMQPushConsumer.start();
    }

    /**
     * 返回消费者实例
     * @return
     */
    public DefaultMQPushConsumer getConsumer() {
        return defaultMQPushConsumer;
    }

    public void destroy() {
        defaultMQPushConsumer.shutdown();
        LOGGER.debug("com.shield.job.message.rocketmq.RocketMQConsumerAgent消费者客户端[已关闭]");
    }

}
