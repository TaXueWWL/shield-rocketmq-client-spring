package com.snowalker.shield.rocketmq.client;

import com.snowalker.shield.rocketmq.client.config.RocketMQTransactionProducerConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author snowalker
 * @version 1.0
 * @date 2019/1/21 21:05
 * @className RocketMQTransactionProducerAgent
 * @desc RocketMQ事务消息生产者
 */
@Component
@Scope("prototype")
public class RocketMQTransactionProducerAgent {

    private TransactionMQProducer producer;

    private TransactionListener transactionListener;

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQTransactionProducerAgent.class);

    public RocketMQTransactionProducerAgent init(RocketMQTransactionProducerConfig transactionProducerConfig,
                                                 TransactionListener transactionListener) throws Exception {
        producer = new TransactionMQProducer(transactionProducerConfig.getProducerGroup());
        producer.setNamesrvAddr(transactionProducerConfig.getNameSrvAddr());
        // 设置本地事务执行线程池
        producer.setExecutorService(transactionProducerConfig.getExecutorService());
        this.transactionListener = transactionListener;
        // 设置本地事务执行监听器
        producer.setTransactionListener(this.transactionListener);
        LOGGER.debug("com.shield.job.message.rocketmq.RocketMQTransactionProducerAgent[初始化完成]");
        return this;
    }

    /**
     * 启动消费者服务
     */
    public void start() throws MQClientException {
        this.producer.start();
    }

    public void destroy() {
        this.producer.shutdown();
        LOGGER.debug("com.shield.job.message.rocketmq.RocketMQTransactionProducerAgent[已关闭]");
    }

    public TransactionMQProducer getProducer() {
        return this.producer;
    }
}
