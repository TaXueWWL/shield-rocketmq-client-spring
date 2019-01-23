package com.snowalker.shield.rocketmq.client;

import com.snowalker.shield.rocketmq.client.config.RocketMQProducerConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/1/21 9:24
 * @className RocketMQProducerAgent
 * @desc RocketMQ生产者客户端
 */
@Component
@Scope("prototype")
public class RocketMQSimpleProducerAgent {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQSimpleProducerAgent.class);

    private DefaultMQProducer defaultMQProducer;

    public RocketMQSimpleProducerAgent init(RocketMQProducerConfig producerConfig) throws Exception {
        defaultMQProducer = new DefaultMQProducer(producerConfig.getProducerGroup());
        defaultMQProducer.setNamesrvAddr(producerConfig.getNameSrvAddr());
        LOGGER.debug("com.shield.job.message.rocketmq.RocketMQProducerAgent[初始化完成]");
        return this;
    }


    /**
     * 启动消费者服务
     */
    public void start() throws MQClientException {
        this.defaultMQProducer.start();
    }


    public void destroy() {
        this.defaultMQProducer.shutdown();
        LOGGER.debug("com.shield.job.message.rocketmq.RocketMQProducerAgent[已关闭]");
    }

    public DefaultMQProducer getProducer() {
        return this.defaultMQProducer;
    }

}
