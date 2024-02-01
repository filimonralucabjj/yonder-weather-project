package com.example.project.converters;

import com.example.project.dto.WeatherDto;
import com.example.project.model.ExternalWeatherModel;
import com.example.project.model.WeatherModel;
import org.springframework.core.convert.converter.Converter;

import java.text.DecimalFormat;
import java.util.List;



public class ExternalWeatherModelToWeatherDtoConverter implements Converter<ExternalWeatherModel, WeatherDto> {
	private static final DecimalFormat df = new DecimalFormat("0.00");

	@Override
	public WeatherDto convert(ExternalWeatherModel source) {
		final WeatherDto target = new WeatherDto();
		target.setWind(df.format(getAverageWind(source.getForecast())));
		target.setTemperature(df.format(getAverageTemperature(source.getForecast())));
		return target;
	}

	private Double getAverageTemperature(List<WeatherModel> forecast) {
		return forecast.stream().map(WeatherModel::getTemperature).mapToDouble(d -> d).average().orElse(0.0);
	}

	private Double getAverageWind(List<WeatherModel> forecast) {
		return forecast.stream().map(WeatherModel::getWind).mapToDouble(d -> d).average().orElse(0.0);
	}

}
