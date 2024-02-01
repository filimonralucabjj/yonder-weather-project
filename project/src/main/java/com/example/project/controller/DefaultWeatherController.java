package com.example.project.controller;

import com.example.project.dto.WeatherDto;
import com.example.project.facade.WeatherFacade;
import com.example.project.service.ParseWeatherRequestService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/weather")
public class DefaultWeatherController {
	@Resource
	private final WeatherFacade weatherFacade;

	@Resource
	private final ParseWeatherRequestService parseWeatherRequestService;

	public DefaultWeatherController(WeatherFacade weatherFacade, ParseWeatherRequestService parseWeatherRequestService) {
		this.weatherFacade = weatherFacade;
		this.parseWeatherRequestService = parseWeatherRequestService;
	}


	@GetMapping
	public ResponseEntity<List<WeatherDto>> getWeather(@RequestParam String city) {
		List<WeatherDto> weatherDtoSet = weatherFacade.getWeatherInfo(parseWeatherRequestService.getValidCityList(city));
		weatherFacade.writeDataToCSVFile(weatherDtoSet);
		return ResponseEntity.ok(weatherDtoSet);
	}
}
