package com.example.project.dto;

public class WeatherDto {
	private String name;
	private String wind;
	private String temperature;

	public WeatherDto() {
	}

	public WeatherDto(String name) {
		this.name = name;
		this.wind = "";
		this.temperature = "";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
}
