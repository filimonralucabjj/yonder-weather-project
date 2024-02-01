package com.example.project.service;

import com.example.project.dto.WeatherDto;

import java.util.List;


public interface CsvService {
	void generateWeatherCsv(final List<WeatherDto> weatherDtoList);
}
