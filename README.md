# Spring Boot Kafka Application

This project is a Spring Boot application that integrates with Apache Kafka. It provides APIs to post and consume messages from Kafka.

## Project Structure

```
spring-boot-kafka-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           ├── SpringBootKafkaAppApplication.java
│   │   │           ├── config
│   │   │           │   └── KafkaConfig.java
│   │   │           ├── controller
│   │   │           │   └── KafkaController.java
│   │   │           ├── service
│   │   │           │   └── KafkaService.java
│   │   │           └── model
│   │   │               └── Message.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── SpringBootKafkaAppApplicationTests.java
├── mvnw
├── mvnw.cmd
└── pom.xml
```

## Setup Instructions

1. **Clone the repository:**
   ```
   git clone <repository-url>
   cd spring-boot-kafka-app
   ```

2. **Build the project:**
   ```
   ./mvnw clean install
   ```

3. **Run the application:**
   ```
   ./mvnw spring-boot:run
   ```

## Usage

### Post a Message

To send a message to Kafka, make a POST request to the following endpoint:

```
POST /api/messages
```

```
curl -X POST http://localhost:8080/api/kafka/send \
-H "Content-Type: application/json" \
-d '{
  "topic": "your_topic_name",
  "content": "This is a test message"
}'
```

**Request Body:**
```json
{
  "id": "1",
  "content": "Hello Kafka!"
}
```

### Consume Messages

To consume messages from Kafka, make a GET request to the following endpoint:

```
GET /api/messages
```

This will return the list of messages consumed from Kafka.

## Dependencies

- Spring Boot
- Spring Kafka
- Apache Kafka

## License

This project is licensed under the MIT License.# spring-boot-kafka-sample
