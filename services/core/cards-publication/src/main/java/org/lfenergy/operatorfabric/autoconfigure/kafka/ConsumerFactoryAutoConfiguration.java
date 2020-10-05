package org.lfenergy.operatorfabric.autoconfigure.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lfenergy.operatorfabric.avro.CardCommand;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@ConditionalOnProperty("spring.kafka.consumer.group-id")
//@Import(KafkaListenerContainerFactoryConfiguration.class)
@Configuration
public class ConsumerFactoryAutoConfiguration {

    private final KafkaProperties kafkaProperties;

    private Map<String,Object> consumerConfig() {
        Map<String,Object> props = kafkaProperties.buildConsumerProperties();
        log.info("bootstrapServers: " + kafkaProperties.getBootstrapServers());
        return props;
    }

    @Bean
    ConsumerFactory<String, CardCommand> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

}
