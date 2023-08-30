package io.github.viacheslav.bondarchuk.kafkamirror;

import io.github.viacheslav.bondarchuk.kafkamirror.kafka.properties.ConsumerProperties;
import io.github.viacheslav.bondarchuk.kafkamirror.kafka.properties.ProducerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ConsumerProperties.class, ProducerProperties.class})
public class KafkaMirrorApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaMirrorApplication.class, args);
    }

}
