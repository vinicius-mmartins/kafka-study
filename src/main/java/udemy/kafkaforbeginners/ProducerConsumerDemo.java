package udemy.kafkaforbeginners;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class ProducerConsumerDemo {

    private static final String TOPIC = "demo_java";
    private static final String MESSAGE = "Hello World!";
    private static final Logger log = LoggerFactory.getLogger(ProducerConsumerDemo.class.getSimpleName());

    public static void main(String[] args) {

        // producer sending a message
        try (var producer = new KafkaProducer<String, String>(producerProperties())) {
            var producerRecord = new ProducerRecord<String, String>(TOPIC, MESSAGE);
            log.info("sending message: {}", producerRecord);
            producer.send(producerRecord);
        }

        // consumer receiving it
        try (var consumer = new KafkaConsumer<String, String>(consumerProperties())) {
            consumer.subscribe(List.of(TOPIC));
            while(true) {
                log.info("Polling...");
                ConsumerRecords<String, String> messages = consumer.poll(Duration.ofSeconds(1));
                for (var message : messages) {
                    log.info("Result value: {}", message.value());
                }
            }
        }
    }

    private static Properties producerProperties() {
        var properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:29092"); // exposed container port
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        return properties;
    }

    private static Properties consumerProperties() {
        var properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:29092"); // exposed container port
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", "my-java-app");
        properties.setProperty("auto.offset.reset", "latest");
        return properties;
    }

}

// pra iniciar o container kafka e criar o topico desse teste
// docker compose up -d
// docker exec -it kafka-study-kafka-1 /bin/bash
// kafka-topics --bootstrap-server localhost:9092 --topic demo_java --create

// consumer pra conferir as mensagens enviadas
// kafka-console-consumer --bootstrap-server localhost:9092 --topic demo_java --from-beginning