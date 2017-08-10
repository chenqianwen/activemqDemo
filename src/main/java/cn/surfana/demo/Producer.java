package cn.surfana.demo;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Producer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        // 启动
        connection.start();
        Session session = connection.createSession(Boolean.TRUE,
                Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("FirstQueue");
        MessageProducer producer = session.createProducer(destination);
        for (int i = 1; i <= 10; i++) {
            TextMessage message = session.createTextMessage("ActiveMq 发送的消息"
                    + i);
            // 发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
        session.commit();
        connection.close();
    }
}
