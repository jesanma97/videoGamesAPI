package com.mca.config;

import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.mca.infrastructure.adapters.kafka.KafkaMessageProducer;
import com.mca.infrastructure.adapters.out.persistence.StockPersistenceAdapter;
import com.mca.infrastructure.model.VideoGameEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.util.ResourceUtils;

@EnableKafka
@Configuration
public class KafkaConfig implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(KafkaConfig.class);

	@Autowired
	KafkaMessageProducer kafkaMessageProducer;

	@Value("classpath:events.csv")
	Resource resourceFile;

	Gson gson = new Gson();
	@Override
	public void run(String... args) throws Exception {
		try {
			List<VideoGameEvent> stocks = Files
					.readAllLines(ResourceUtils.getFile(resourceFile.getURL()).toPath()).stream()
					.map(line -> convertStock(Arrays.asList(line.trim().split(",")))).collect(Collectors.toList());
			stocks.forEach(stock -> kafkaMessageProducer.sendMessage(String.valueOf(stock.getStock_id()),gson.toJson(stock).toString()));
		} catch (Exception e) {
			log.error("An error in producer has ocurred: ", e);
		}
	}

	public VideoGameEvent convertStock(List<String> stock) {
		return VideoGameEvent.builder().stock_id(Long.parseLong(stock.get(0)))
				.availability(Boolean.parseBoolean(stock.get(1)))
				.time_update(Timestamp.valueOf(
						LocalDateTime.parse(stock.get(2), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX"))))
				.build();
	}
}
