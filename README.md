# shield-rocketmq-client-spring
> RocketMQ薄封装，基于Spring

当前特性
1. 普通消息发送
2. 普通消息消费
3. 事务消息发送
4. 事务消息消费
5. 支持扩展，对官方原生的调用方式保持支持

## changeLog
1.1.0  消费者初始化增加指定消费指定消息的接口，通过subExpression可以指定TAG