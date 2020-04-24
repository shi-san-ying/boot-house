package com.etoak.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.DeliveryMode;
import javax.jms.Session;

@Configuration
public class ActiveMQConfig {
    //1.activeMQConnectionFactroy
    //2.CachiConnectionFactroy
    //3.JmsTemplate

    @Bean
    public ActiveMQConnectionFactory mqConnectionFactory(){
        //1.username 2.password 3.网址
        return new ActiveMQConnectionFactory(null,null,"tcp://localhost:61616");
    }

    @Bean
    public CachingConnectionFactory connectionFactory(){
        CachingConnectionFactory factory =new CachingConnectionFactory();
        factory.setTargetConnectionFactory(this.mqConnectionFactory());
        factory.setSessionCacheSize(10);
        return factory;
    }

    //发送jms消息
    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate =new JmsTemplate();
        jmsTemplate.setConnectionFactory(this.connectionFactory());
        //启动优先级 持久化的设置
        jmsTemplate.setExplicitQosEnabled(true);
        //设置持久化
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);
        //设置客户端手动签收消息
        jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        return jmsTemplate;
    }


}
