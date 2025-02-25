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

## Local Development Setup

1. Start Kafka using Docker Compose:
```bash
docker-compose up -d
```

2. Create the Kafka topic:
```bash
docker exec kafka kafka-topics --create --topic test-topic --bootstrap-server localhost:29092 --partitions 1 --replication-factor 1
```

3. Run the application:
```bash
./mvnw spring-boot:run
```

4. Test the application:
```bash
# Send a message
curl -X POST http://localhost:8080/api/kafka/send \
-H "Content-Type: application/json" \
-d '{
  "topic": "test-topic",
  "content": "This is a test message"
}'

# Verify Kafka topic
docker exec kafka kafka-console-consumer --topic test-topic --from-beginning --bootstrap-server localhost:29092
```

## Docker Setup

### Build and Run with Docker Compose

1. Build and start all services:
```bash
docker-compose up -d --build
```

2. Check service status:
```bash
docker-compose ps
```

3. View application logs:
```bash
docker-compose logs -f app
```

4. Create Kafka topic:
```bash
docker exec kafka kafka-topics --create \
    --topic test-topic \
    --bootstrap-server localhost:29092 \
    --partitions 1 \
    --replication-factor 1
```

5. Test the application:
```bash
curl -X POST http://localhost:9898/api/kafka/send \
-H "Content-Type: application/json" \
-d '{
  "topic": "test-topic",
  "content": "Test message from Docker"
}'
```

6. Stop all services:
```bash
docker-compose down
```

## Building Docker Image with Maven

### Build the image
```bash
./mvnw spring-boot:build-image
```

### Run the image
```bash
docker run -p 9898:9898 \
    -e KAFKA_BOOTSTRAP_SERVERS=kafka:9092 \
    -e KAFKA_TOPIC=test-topic \
    -e KAFKA_GROUP_ID=test-group \
    --network kafka-network \
    spring-boot-kafka-app:0.0.1-SNAPSHOT
```

### Alternative Build Commands
```bash
# Build without tests
./mvnw spring-boot:build-image -DskipTests

# Build with custom name and tag
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=myorg/myapp:latest
```

### Docker Compose Integration
Update your docker-compose.yml to use the Maven-built image:

```yaml
services:
  app:
    image: spring-boot-kafka-app:0.0.1-SNAPSHOT
    # ... rest of your service configuration
```

## Monitoring

### Metrics
Access metrics endpoints:
- Prometheus metrics: `http://localhost:9898/actuator/prometheus`
- Metrics endpoint: `http://localhost:9898/actuator/metrics`
- Health endpoint: `http://localhost:9898/actuator/health`

### Logging
Logs are available in:
- Console output
- File: `logs/application.log`
- JSON format for easy parsing

### Tracing
Distributed tracing is available through Zipkin:
- Zipkin UI: `http://localhost:9411`

### Dashboards
- Grafana: `http://localhost:3000` (admin/admin)
- Prometheus: `http://localhost:9090`

### Example Commands

Check metrics:
```bash
# Get all available metrics
curl http://localhost:9898/actuator/metrics

# Get specific metric
curl http://localhost:9898/actuator/metrics/kafka.producer.record.send.total
```

View logs:
```bash
# View application logs
tail -f logs/application.log

# View Docker container logs
docker-compose logs -f app
```

## License

This project is licensed under the MIT License.# spring-boot-kafka-sample
# spring-boot-kafka-sample
