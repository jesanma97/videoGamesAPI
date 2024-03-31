package com.mca.infrastructure.in.kafka;

import com.mca.infrastructure.adapters.kafka.KafkaMessageProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaMessageProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private KafkaMessageProducer<String, String> kafkaMessageProducer;

    @Test
    void testSendMessage_Success() {
        // Given
        String topicName = "testTopic";
        String id = "123";
        String message = "Test message";

        // Set the topic name via reflection since it's private and we cannot set it directly
        ReflectionTestUtils.setField(kafkaMessageProducer, "topicName", topicName);

        // When
        kafkaMessageProducer.sendMessage(id, message);

        // Then
        verify(kafkaTemplate).send(topicName, message);
    }
}