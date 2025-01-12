package kafkaproducer.kafkaproducer.kafkaservice;

public interface IkafkaProducerService {
    void sendMessage(String topic, String message);
}
