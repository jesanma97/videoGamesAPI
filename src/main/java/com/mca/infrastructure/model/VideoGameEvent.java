package com.mca.infrastructure.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoGameEvent {
	private Long stock_id;

	private boolean availability;

	private Timestamp time_update;


}
