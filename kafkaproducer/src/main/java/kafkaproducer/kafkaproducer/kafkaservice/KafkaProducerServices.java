package kafkaproducer.kafkaproducer.kafkaservice;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerServices implements IkafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerServices(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
