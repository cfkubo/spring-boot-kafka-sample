package com.example.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.kafka.core.KafkaAdmin;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;

@Service
public class KafkaService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaAdmin kafkaAdmin;

    public void sendMessage(String topic, String message) {
        ensureTopicExists(topic);
        
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Successfully sent message='{}' to topic='{}', partition={}, offset={}", 
                    message, topic, result.getRecordMetadata().partition(), 
                    result.getRecordMetadata().offset());
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Failed to send message='{}' to topic='{}': {}", 
                    message, topic, ex.getMessage());
            }
        });
    }

    private void ensureTopicExists(String topicName) {
        try {
            NewTopic newTopic = TopicBuilder.name(topicName)
                    .partitions(1)
                    .replicas(1)
                    .build();
            kafkaAdmin.createOrModifyTopics(newTopic);
            logger.info("Topic '{}' created or verified", topicName);
        } catch (Exception e) {
            logger.warn("Error creating topic '{}': {}", topicName, e.getMessage());
        }
    }

    @KafkaListener(topics = "your_topic_name", groupId = "your_group_id")
    public void receiveMessages(ConsumerRecord<String, String> record) {
        // Process the received message
        System.out.println("Received message: " + record.value());
    }
}