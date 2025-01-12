package kafkaproducer.kafkaproducer.controller;

import kafkaproducer.kafkaproducer.kafkaservice.KafkaProducerServices;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProducerController {
    private final KafkaProducerServices kafkaProducerServices;
    private final List<String> messages = new ArrayList<>();
    private String replyMessage;

    @Autowired
    public ProducerController(KafkaProducerServices kafkaProducerServices) {
        this.kafkaProducerServices = kafkaProducerServices;
    }

    // Abhi sends a message
    @GetMapping("/abhi/send")
    public String sendMessage(@RequestParam String message) {
        kafkaProducerServices.sendMessage("my-topic", "Abhi: " + message);
        return "Abhi sent: " + message;
    }

    // Abhi receives messages from the topic
    @KafkaListener(topics = "my-topic", groupId = "abhi-group")
    public void listenForMessages(ConsumerRecord<String, String> record) {
        String receivedMessage = record.value();
        messages.add(receivedMessage);
        System.out.println("Abhi received: " + receivedMessage);
        if (receivedMessage.startsWith("client: ")) {
            this.replyMessage = receivedMessage;
        }
    }

    // Abhi retrieves the latest reply from client
    @GetMapping("/abhi/get-reply")
    public String getReplyMessage() {
        return replyMessage != null ? replyMessage : "No reply received yet!";
    }

    // Abhi fetches all messages
    @GetMapping("/abhi/messages")
    public List<String> getMessages() {
        return messages;
    }
}