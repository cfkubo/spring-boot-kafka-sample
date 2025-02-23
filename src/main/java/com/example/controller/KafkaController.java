package com.example.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

import com.example.model.Message;
import com.example.service.KafkaService;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/send")
    public ResponseEntity<String> postMessage(@RequestBody Message message) {
        kafkaService.sendMessage(message.getTopic(), message.getContent());
        return ResponseEntity.ok("Message sent to Kafka: " + message.getContent());
    }

    @GetMapping("/consume")
    public ResponseEntity<String> consumeMessages(@RequestParam("topic") String topic, @RequestParam("key") String key, @RequestParam("value") String value) {
        ConsumerRecord<String, String> record = new ConsumerRecord<>(topic, 0, 0L, key, value);
        return ResponseEntity.ok(kafkaService.receiveMessages(record));
    }

    @KafkaListener(topics = "your_topic_name", groupId = "your_group_id")
    public void listen(String message) {
        System.out.println("Received Message: " + message);
    }
}