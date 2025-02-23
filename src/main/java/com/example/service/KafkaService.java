package com.example.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    // public String receiveMessages(ConsumerRecord<String, String> record) {
    //     // implementation
    //     return "Received message: " + record.value();
    // }

    @KafkaListener(topics = "your_topic_name", groupId = "your_group_id")
    public void receiveMessages(ConsumerRecord<String, String> record) {
        // Process the received message
        System.out.println("Received message: " + record.value());
    }

 
    // public Object receiveMessages(ConsumerRecord<String record) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'receiveMessages'");
    // }
}

// package com.example.service;

// import org.apache.kafka.clients.producer.KafkaProducer;
// import org.apache.kafka.clients.producer.ProducerRecord;
// import org.apache.kafka.clients.consumer.ConsumerRecord;
// import org.apache.kafka.clients.consumer.KafkaConsumer;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;

// @Service
// public class KafkaService {

//     private final KafkaProducer<String, String> producer;

//     @Autowired
//     public KafkaService(KafkaProducer<String, String> producer, KafkaConsumer<String, String> consumer) {
//     public KafkaService(KafkaProducer<String, String> producer) {
//         this.producer = producer;
//     }
//     public void sendMessage(String topic, String message) {
//         ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
//         producer.send(record);
//     }

//     @KafkaListener(topics = "your_topic_name", groupId = "your_group_id")
//     public void receiveMessages(ConsumerRecord<String, String> record) {
//         // Process the received message
//         System.out.println("Received message: " + record.value());
//     }
// }