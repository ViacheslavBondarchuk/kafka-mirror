package io.github.viacheslav.bondarchuk.kafkamirror.kafka.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "io.github.kafka-mirror.kafka.producer")
public class ProducerProperties {
    private final String bootstrapServers;

    @ConstructorBinding
    public ProducerProperties(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("bootstrap.servers", bootstrapServers);
        return properties;
    }
}
