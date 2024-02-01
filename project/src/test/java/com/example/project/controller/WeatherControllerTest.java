package com.example.project.controller;


import com.example.project.dto.WeatherDto;
import com.example.project.facade.WeatherFacade;
import com.example.project.service.ParseWeatherRequestService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {
	private static final String CITY_LIST_VALID = "Bucuresti,Arad";
	private static final String CITY_LIST_INVALID = "Iasi,Barlad";
	private static final String VALID_CITY = "Bucuresti";

	@InjectMocks
	private WeatherController controller;
	@Mock
	private WeatherFacade weatherFacade;
	@Mock
	private ParseWeatherRequestService parseWeatherRequestService;

	@Test
	public void shouldReturnWeatherInfoList_whenAtLeastOneCityIsValid() {
		WeatherDto weatherDto = new WeatherDto(VALID_CITY);
		when(parseWeatherRequestService.getValidCityList(CITY_LIST_VALID)).thenReturn(Collections.singleton(VALID_CITY));
		when(weatherFacade.getWeatherInfo(Collections.singleton(VALID_CITY))).thenReturn(Collections.singletonList(weatherDto));

		ResponseEntity<List<WeatherDto>> result = controller.getWeather(CITY_LIST_VALID);

		Assertions.assertFalse(result.getBody().isEmpty());
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	public void shouldGetEmptyList_whenNoCityIsValid() {
		when(parseWeatherRequestService.getValidCityList(CITY_LIST_INVALID)).thenReturn(Collections.emptySet());
		when(weatherFacade.getWeatherInfo(Collections.emptySet())).thenReturn(Collections.emptyList());

		ResponseEntity<List<WeatherDto>> result = controller.getWeather(CITY_LIST_INVALID);

		Assertions.assertEquals(Collections.emptyList(), result.getBody());
		Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}
