package io.github.viacheslav.bondarchuk.kafkamirror.kafka.processor;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaMessageProcessor {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaMessageProcessor(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "${com.gamesys.sportsbook.transport.kafka.event-topic}", idIsGroup = false, containerFactory = "kafkaListenerContainerFactory")
    public void handleEvents(List<ConsumerRecord<String, String>> records, Acknowledgment acknowledgment) {
        records.forEach(r -> kafkaTemplate.send(new ProducerRecord<>(r.topic(), null, r.timestamp(), r.key(), r.value(), r.headers())));
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "${com.gamesys.sportsbook.transport.kafka.market-topic}", idIsGroup = false, containerFactory = "kafkaListenerContainerFactory")
    public void handleMarkets(List<ConsumerRecord<String, String>> records,  Acknowledgment acknowledgment) {
        records.forEach(r -> kafkaTemplate.send(new ProducerRecord<>(r.topic(), null, r.timestamp(), r.key(), r.value(), r.headers())));
        acknowledgment.acknowledge();
    }

    @KafkaListener(topics = "${com.gamesys.sportsbook.transport.kafka.selection-topic}", idIsGroup = false, containerFactory = "kafkaListenerContainerFactory")
    public void handleSelections(List<ConsumerRecord<String, String>> records,  Acknowledgment acknowledgment) {
        records.forEach(r -> kafkaTemplate.send(new ProducerRecord<>(r.topic(), null, r.timestamp(), r.key(), r.value(), r.headers())));
        acknowledgment.acknowledge();
    }
}
