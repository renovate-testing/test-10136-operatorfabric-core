package org.lfenergy.operatorfabric.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.lfenergy.operatorfabric.utilities.json.CustomInstantSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for custom serializer for {@link Instant}
 *
 *
 * @author Alexandra Guironnet
 */
public class CustomInstantSerializerShould {

    private static final Logger log = LoggerFactory.getLogger(CustomInstantSerializer.class);

    private static ObjectMapper objectMapper;
    private static CustomInstantSerializer customInstantSerializer;

    @BeforeAll
    public static void setup(){
        objectMapper = new ObjectMapper();
        customInstantSerializer = new CustomInstantSerializer();
        objectMapper.registerModule(new SimpleModule().addSerializer(Instant.class, customInstantSerializer));
    }

    @Test
    public void shouldSerializeInstantAsMillisFromEpoch () {

        long milliFromEpoch = 123456789L;
        Instant instant = Instant.ofEpochMilli(milliFromEpoch);

        String expectedSerialization = "123456789";
        String actualSerialization = null;

        try {
            actualSerialization = objectMapper.writeValueAsString(instant);
            assertThat(actualSerialization).isEqualTo(expectedSerialization);
        } catch (Exception e) {
            log.error(String.format("Unable to serialize %s", Instant.class.getSimpleName()), e);
        }

    }

    @Test
    public void shouldSerializeNullAsMillisFromEpoch () {

        Instant instant = null;

        String expectedSerialization = "null";

        try {
            String actualSerialization = objectMapper.writeValueAsString(instant);
            assertThat(actualSerialization).isEqualTo(expectedSerialization);
        } catch (JsonProcessingException e) {
            log.error(String.format("Unable to serialize %s", Instant.class.getSimpleName()), e);
        }


    }


}
