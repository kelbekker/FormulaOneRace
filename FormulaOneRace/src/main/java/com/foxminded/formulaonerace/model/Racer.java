package com.foxminded.formulaonerace.model;

public class Racer {
	private String name;
	private String carName;
	private String key;

	public Racer(String key, String name, String carName) {
		this.setName(name);
		this.setCarName(carName);
		this.setKay(key);
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKay(String key) {
		this.key = key;
	}
}
