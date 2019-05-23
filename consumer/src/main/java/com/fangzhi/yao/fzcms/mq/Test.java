package com.fangzhi.yao.fzcms.mq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author yaoping
 * @date 2018/11/21 PM5:46
 */
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args){

//        sendMsg();
        receiveMsg();
    }

    public static void sendMsg(){

        // 获取消息生产者
        DefaultMQProducer producer = Producer.getDefaultMQProducer();

        try {
            for(int i=0;i<10;i++){
                Message msg = new Message(
                        "TopicTest1",                   // topic
                        "TagA",                         // tag
                        "OrderID00"+i,                  // key
                        ("Hello MetaQ"+i).getBytes());  // body
                SendResult sendResult = producer.send(msg);
                //logger.info("sendResult:{}", sendResult);
            }
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemotingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MQBrokerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        producer.shutdown();
    }

    public static void receiveMsg(){

        // 获取消息生产者
        DefaultMQPushConsumer consumer = Consumer.getDefaultMQPushConsumer();

        // 订阅主体
        try {
            consumer.subscribe("TopicTest1", "*");

            consumer.registerMessageListener(new MessageListenerConcurrently() {

                /**
                 * * 默认msgs里唯独一条消息，能够通过设置consumeMessageBatchMaxSize參数来批量接收消息
                 */
                public ConsumeConcurrentlyStatus consumeMessage(
                        List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

                    logger.info("currentThreadName:{} and Receive New Messages:{}",Thread.currentThread().getName(),msgs);

                    MessageExt msg = msgs.get(0);

                    if (msg.getTopic().equals("TopicTest1")) {
                        // 运行TopicTest1的消费逻辑
                        if (msg.getTags() != null && msg.getTags().equals("TagA")) {
                            // 运行TagA的消费
                            logger.info("MsgBody:{}",new String(msg.getBody()));
                        } else if (msg.getTags() != null
                                && msg.getTags().equals("TagC")) {
                            // 运行TagC的消费
                        } else if (msg.getTags() != null
                                && msg.getTags().equals("TagD")) {
                            // 运行TagD的消费
                        }
                    } else if (msg.getTopic().equals("TopicTest2")) {
                        // 运行TopicTest2的消费逻辑
                    }

                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            /**
             * Consumer对象在使用之前必须要调用start初始化。初始化一次就可以<br>
             */
            consumer.start();

            logger.info("Consumer Started.");
        } catch (MQClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}