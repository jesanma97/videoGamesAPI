package com.mca.infrastructure.in.kafka;

import com.mca.application.ports.in.StockConsultPort;
import com.mca.infrastructure.adapters.in.web.StockConsultAdapter;
import com.mca.infrastructure.adapters.kafka.KafkaMessageConsumer;
import com.mca.infrastructure.model.VideoGameEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.annotation.KafkaListener;

import java.sql.Timestamp;

import static org.mockito.Mockito.*;

public class KafkaMessageConsumerTest {

    @Mock
    private StockConsultAdapter stockConsultAdapter;

    @InjectMocks
    private KafkaMessageConsumer kafkaMessageConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testListen_Success() {
        // Given
        long currentTimeMillis = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(currentTimeMillis);
        String message = "5,true,"+timestamp;

        VideoGameEvent videoGameEvent = new VideoGameEvent(5L, true, timestamp);
        doNothing().when(this.stockConsultAdapter).updateStockVideoGame(videoGameEvent);
        kafkaMessageConsumer.listen(message);
    }

    @Test
    void testListen_Exception() {
        // Given
        String message = "5,true,2023-01-31T04:49:44.832Z";
        doThrow(NullPointerException.class).when(stockConsultAdapter).updateStockVideoGame(any());
        kafkaMessageConsumer.listen(message);

    }
}