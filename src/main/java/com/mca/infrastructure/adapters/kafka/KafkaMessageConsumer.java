package com.mca.infrastructure.adapters.kafka;

import com.google.gson.Gson;
import com.mca.application.ports.in.StockConsultPort;
import com.mca.infrastructure.adapters.in.web.StockConsultAdapter;
import com.mca.infrastructure.adapters.out.persistence.StockPersistenceAdapter;
import com.mca.infrastructure.model.VideoGameEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageConsumer {
    private final StockConsultPort stockConsultPort;
    private static final Logger log = LoggerFactory.getLogger(KafkaMessageConsumer.class);
    @Autowired
    public KafkaMessageConsumer(StockConsultAdapter stockConsultAdapter){
        this.stockConsultPort = stockConsultAdapter;
    }

    /**
     * Listen to the message received of specified topic
     *
     * @param  message
     */
    @KafkaListener(topics = "${topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(String message){
        try{
            Gson gson = new Gson();
            VideoGameEvent videoGameEvent = gson.fromJson(message, VideoGameEvent.class);
            stockConsultPort.updateStockVideoGame(videoGameEvent);
        }catch (Exception e){
            log.error(String.format("An error occurred while trying to consume the content of the producer. " +
                    "The content is: %s", message), e);
        }

    }


}
