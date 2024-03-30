package com.mca.infrastructure.adapters.kafka;

import com.mca.application.ports.in.StockConsultPort;
import com.mca.infrastructure.adapters.in.web.StockConsultAdapter;
import com.mca.infrastructure.model.VideoGameEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumer {
    private StockConsultPort stockConsultPort;
    @Autowired
    public KafkaMessageConsumer(StockConsultAdapter stockConsultAdapter){
        this.stockConsultPort = stockConsultAdapter;
    }

    @KafkaListener(topics = "${topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(VideoGameEvent videoGameEvent){
        stockConsultPort.updateStockVideoGame(videoGameEvent);
    }


}
