package com.example.project.facade;

import com.example.project.dto.WeatherDto;

import java.util.List;
import java.util.Set;


public interface WeatherFacade {

	List<WeatherDto> getWeatherInfo(final Set<String> cities);

	void writeDataToCSVFile(List<WeatherDto> weatherDtoList);
}
