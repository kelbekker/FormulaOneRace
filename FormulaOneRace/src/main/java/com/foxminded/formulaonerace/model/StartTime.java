package com.foxminded.formulaonerace.model;

import java.time.LocalDateTime;

public class StartTime {
	private LocalDateTime startTime;
	private String key;

	public StartTime(String key, LocalDateTime startTime) {
		this.setStartTime(startTime);
		this.setKey(key);
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
