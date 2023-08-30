package com.foxminded.formulaonerace.model;

import java.time.LocalDateTime;

public class EndTime {

	private LocalDateTime endTime;
	private String key;

	public EndTime(String key, LocalDateTime endTime) {
		this.setKey(key);
		this.setEndTime(endTime);
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}