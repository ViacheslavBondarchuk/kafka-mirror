package io.github.viacheslav.bondarchuk.kafkamirror.kafka.properties;

import io.github.viacheslav.bondarchuk.kafkamirror.util.JKSUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ConfigurationProperties(prefix = "com.gamesys.sportsbook.transport.kafka")
public class ConsumerProperties {
    private final String bootstrapServers;
    private final String configFilesPath;
    private final String eventTopic;
    private final String marketTopic;
    private final String selectionTopic;
    private final String groupId;
    private final Security security;

    @ConstructorBinding
    public ConsumerProperties(String bootstrapServers, String configFilesPath, String eventTopic,
                              String marketTopic, String selectionTopic, String groupId, Security security) {
        this.bootstrapServers = bootstrapServers;
        this.configFilesPath = configFilesPath;
        this.eventTopic = eventTopic;
        this.marketTopic = marketTopic;
        this.selectionTopic = selectionTopic;
        this.groupId = groupId;
        this.security = security;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public String getConfigFilesPath() {
        return configFilesPath;
    }

    public String getEventTopic() {
        return eventTopic;
    }

    public String getMarketTopic() {
        return marketTopic;
    }

    public String getSelectionTopic() {
        return selectionTopic;
    }

    public String getGroupId() {
        return groupId;
    }

    public Security getSecurity() {
        return security;
    }

    public static class Security {
        private final String enabledProtocols;
        private final String interBrokerProtocol;
        private final String keystore;
        private final String keyPassword;
        private final String keystorePassword;
        private final String keystoreType;
        private final String protocol;
        private final String truststore;
        private final String truststorePassword;
        private final String truststoreType;
        private final String identificationAlgorithm;


        public Security(String enabledProtocols, String interBrokerProtocol, String keyPassword, String keystore, String keystorePassword,
                        String keystoreType, String protocol, String truststore, String truststorePassword, String truststoreType,
                        String identificationAlgorithm) {
            this.enabledProtocols = enabledProtocols;
            this.interBrokerProtocol = interBrokerProtocol;
            this.keystore = keystore;
            this.keystorePassword = keystorePassword;
            this.keystoreType = keystoreType;
            this.protocol = protocol;
            this.truststore = truststore;
            this.truststorePassword = truststorePassword;
            this.truststoreType = truststoreType;
            this.keyPassword = keyPassword;
            this.identificationAlgorithm = identificationAlgorithm;
        }

        public String getEnabledProtocols() {
            return enabledProtocols;
        }

        public String getIdentificationAlgorithm() {
            return identificationAlgorithm;
        }

        public String getInterBrokerProtocol() {
            return interBrokerProtocol;
        }

        public String getKeystore() {
            return keystore;
        }

        public String getKeystorePassword() {
            return keystorePassword;
        }

        public String getKeystoreType() {
            return keystoreType;
        }

        public String getProtocol() {
            return protocol;
        }

        public String getTruststore() {
            return truststore;
        }

        public String getTruststorePassword() {
            return truststorePassword;
        }

        public String getTruststoreType() {
            return truststoreType;
        }
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("group.id", groupId + UUID.randomUUID());
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("auto.offset.reset", "earliest");
        properties.put("security.protocol", security.protocol);
        properties.put("max.poll.records", "5000");
        properties.put("fetch.max.wait.ms", "1");
        properties.put("fetch.min.bytes", "1");
        properties.put("ssl.keystore.location", JKSUtil.load("keystore.jks", security.keystorePassword, configFilesPath, security.keystore, Base64.getMimeDecoder()));
        properties.put("ssl.truststore.location", JKSUtil.load("truststore.jks", security.truststorePassword, configFilesPath, security.truststore, Base64.getMimeDecoder()));
        properties.put("ssl.truststore.password", security.truststorePassword);
        properties.put("ssl.keystore.password", security.keystorePassword);
        properties.put("ssl.key.password", security.keyPassword);
        properties.put("ssl.truststore.type", security.truststoreType);
        properties.put("ssl.keystore.type", security.keystoreType);
        properties.put("enable.auto.commit", "false");
        properties.put("ssl.enabled.protocols", security.enabledProtocols);
        properties.put("security.inter.broker.protocol", security.interBrokerProtocol);
        properties.put("ssl.endpoint.identification.algorithm", security.identificationAlgorithm);
        return properties;
    }

}
