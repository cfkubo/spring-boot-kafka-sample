package com.example.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import com.example.model.Message;
import com.example.service.KafkaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/kafka")
@Api(value = "Kafka Controller", description = "Operations pertaining to Kafka messaging")
public class KafkaController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/send")
    @ApiOperation(value = "Send a message to Kafka")
    public ResponseEntity<String> postMessage(@RequestBody Message message) {
        kafkaService.sendMessage(message.getTopic(), message.getContent());
        return ResponseEntity.ok("Message sent to Kafka: " + message.getContent());
    }

    @GetMapping("/consume")
    @ApiOperation(value = "Consume messages from Kafka")
    public ResponseEntity<String> consumeMessages(@RequestParam("topic") String topic, @RequestParam("key") String key, @RequestParam("value") String value) {
        ConsumerRecord<String, String> record = new ConsumerRecord<>(topic, 0, 0L, key, value);
        receiveMessages(record);
        // return ResponseEntity.ok(kafkaService.receiveMessages(record));
                return null;
    }

    @GetMapping("/home")
    @ApiOperation(value = "Get welcome message")
    public String home() {
        return "Welcome to Spring Boot Kafka Application! \n\n" +
               "To send a message, use: \n" +
               "curl -X POST http://localhost:8080/api/kafka/send \\\n" +
               "-H \"Content-Type: application/json\" \\\n" +
               "-d '{\n" +
               "  \"topic\": \"test-topic\",\n" +
               "  \"content\": \"This is a test message\"\n" +
               "}'";
    }

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}")
    public void listen(String message) {
        logger.info("Received Message in group '{}': {}", "${kafka.group.id}", message);
    }

    public String receiveMessages(ConsumerRecord<String, String> record) {
        // process the record
        return "Message processed";
    }
}