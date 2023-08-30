package com.foxminded.formulaonerace.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class RaceResult {
	private String key;
	private String name;
	private String carName;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private Duration elapsedTime;

	public RaceResult(String key, String name, String carName) {
		this.setKey(key);
		this.setName(name);
		this.setCarName(carName);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Duration getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Duration duration) {
		this.elapsedTime = duration;
	}

}
