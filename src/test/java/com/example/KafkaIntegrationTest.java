package com.example;

import com.example.config.KafkaTestContainersConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext
class KafkaIntegrationTest extends KafkaTestContainersConfig {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void shouldSendAndReceiveMessage() {
        String topic = "test-topic";
        String message = "test-message";

        kafkaTemplate.send(topic, message);

        ConsumerRecord<String, String> received = KafkaTestUtils.getSingleRecord(
            kafka.getBootstrapServers(),
            topic,
            0,
            5000L
        );

        assertThat(received).isNotNull();
        assertThat(received.value()).isEqualTo(message);
    }
}