package io.github.viacheslav.bondarchuk.kafkamirror.kafka.configuration;

import io.github.viacheslav.bondarchuk.kafkamirror.kafka.properties.ConsumerProperties;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;

import java.time.Duration;

import static org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL;

@Configuration
public class KafkaConsumerAutoConfiguration {


    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory(
            ConsumerProperties consumerProperties) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerProperties.toMap(), new StringDeserializer(), new StringDeserializer()));
        factory.setBatchListener(true);
        factory.setConcurrency(1);
        factory.setAutoStartup(true);

        ContainerProperties containerProperties = factory.getContainerProperties();
        containerProperties.setAckMode(MANUAL);
        containerProperties.setAuthExceptionRetryInterval(Duration.ofSeconds(60));

        factory.setCommonErrorHandler(new DefaultErrorHandler());
        return factory;
    }

}
