package kafkaconsumer.kafkaconsumer.kafkacontroller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ConsumerServiceController {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final List<String> messages = new ArrayList<>();
    private String replyMessage;

    public ConsumerServiceController (KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // client sends a message
    @GetMapping("/client/send")
    public String sendMessage(@RequestParam String message) {
        kafkaTemplate.send("my-topic", "client: " + message);
        return "client sent: " + message;
    }

    // client receives messages from the topic
    @KafkaListener(topics = "my-topic", groupId = "client-group")
    public void listenForMessages(ConsumerRecord<String, String> record) {
        String receivedMessage = record.value();
        messages.add(receivedMessage);
        System.out.println("client received: " + receivedMessage);
        if (receivedMessage.startsWith("Abhi: ")) {
            this.replyMessage = receivedMessage;
        }
    }

    // client retrieves the latest reply from Abhi
    @GetMapping("/client/get-reply")
    public String getReplyMessage() {
        return replyMessage != null ? replyMessage : "No reply received yet!";
    }

    // client fetches all messages
    @GetMapping("/client/messages")
    public List<String> getMessages() {
        return messages;
    }
}
