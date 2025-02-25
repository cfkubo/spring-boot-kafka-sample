package com.example;

import com.example.config.KafkaTestContainersConfig;
import com.example.controller.KafkaController;
import com.example.model.Message;
import com.example.service.KafkaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@DirtiesContext
class SpringBootKafkaAppApplicationTests extends KafkaTestContainersConfig {

    @Autowired
    private KafkaController kafkaController;

    @Autowired
    private KafkaService kafkaService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void contextLoads() {
        assertThat(kafkaController).isNotNull();
    }

    @Test
    void shouldSendMessageToKafka() {
        Message message = new Message();
        message.setTopic("test-topic");
        message.setContent("test-message");

        assertDoesNotThrow(() -> 
            kafkaController.postMessage(message)
        );
    }

    @Test
    void shouldConsumeMessageFromKafka() {
        String topic = "test-topic";
        String content = "test-message";

        kafkaTemplate.send(topic, content);

        assertDoesNotThrow(() -> 
            kafkaController.consumeMessages(topic, "test-key", content)
        );
    }
}