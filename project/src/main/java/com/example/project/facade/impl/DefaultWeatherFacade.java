package com.example.project.facade.impl;

import com.example.project.dto.WeatherDto;
import com.example.project.facade.WeatherFacade;
import com.example.project.model.ExternalWeatherModel;
import com.example.project.service.CsvService;
import com.example.project.service.impl.DefaultExternalWeatherService;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class DefaultWeatherFacade implements WeatherFacade {
	private final DefaultExternalWeatherService externalWeatherService;
	private final Converter<ExternalWeatherModel, WeatherDto> weatherDtoConverter;
	private final CsvService csvService;

	public DefaultWeatherFacade(final DefaultExternalWeatherService externalWeatherService,
								Converter<ExternalWeatherModel, WeatherDto> weatherDtoConverter,
								CsvService csvService) {
		this.externalWeatherService = externalWeatherService;
		this.weatherDtoConverter = weatherDtoConverter;
		this.csvService = csvService;
	}

	public List<WeatherDto> getWeatherInfo(final Set<String> cities) {
		List<WeatherDto> weatherDtoList = new ArrayList<>();
		for (String city : cities) {
			ExternalWeatherModel externalWeatherModel = externalWeatherService.getWeatherInfoForCity(city);
			if (externalWeatherModel != null) {
				addInfo(weatherDtoList, city, weatherDtoConverter.convert(externalWeatherModel));
			} else {
				weatherDtoList.add(new WeatherDto(city));
			}
		}
		return weatherDtoList;
	}

	@Override
	public void writeDataToCSVFile(List<WeatherDto> weatherDtoList) {
		csvService.generateWeatherCsv(weatherDtoList);
	}

	private void addInfo(List<WeatherDto> weatherDtoList, String city, WeatherDto weatherDto) {
		weatherDto.setName(city);
		weatherDtoList.add(weatherDto);
	}


}
