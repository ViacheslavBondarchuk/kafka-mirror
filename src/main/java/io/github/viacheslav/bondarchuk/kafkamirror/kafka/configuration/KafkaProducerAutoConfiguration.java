package io.github.viacheslav.bondarchuk.kafkamirror.kafka.configuration;

import io.github.viacheslav.bondarchuk.kafkamirror.kafka.properties.ProducerProperties;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerAutoConfiguration {

    @Bean
    public ProducerFactory<String, String> producerFactory(ProducerProperties producerProperties) {
        return new DefaultKafkaProducerFactory<>(producerProperties.toMap(), new StringSerializer(), new StringSerializer());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
