package udemy.kafkaforbeginners;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        try (var producer = new KafkaProducer<String, String>(setProperties())) {
            var producerRecord = new ProducerRecord<String, String>("demo_java", "Hello World!");
            log.info("sending message: {}", producerRecord);
            producer.send(producerRecord);
        }
    }

    private static Properties setProperties() {
        var properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:29092"); // exposed container port
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        return properties;
    }

}

// kafka-topics --bootstrap-server localhost:9092 --topic demo_java --describe