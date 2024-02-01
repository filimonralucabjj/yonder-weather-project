package com.example.project.facade;

import com.example.project.dto.WeatherDto;
import com.example.project.facade.impl.DefaultWeatherFacade;
import com.example.project.model.ExternalWeatherModel;
import com.example.project.service.CsvService;
import com.example.project.service.impl.DefaultExternalWeatherService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DefaultWeatherFacadeTest {
	private static final String CITY = "Iasi";
	private static final String WIND = "20.00";
	private static final String TEMP = "34.00";
	private DefaultWeatherFacade facade;

	@Mock
	private DefaultExternalWeatherService externalWeatherService;

	@Mock
	private Converter<ExternalWeatherModel, WeatherDto> weatherDtoConverter;

	@Mock
	private CsvService csvService;

	@Mock
	private ExternalWeatherModel externalWeatherModel;

	@Mock
	private WeatherDto weatherDto;

	@Before
	public void setUp(){
		facade = new DefaultWeatherFacade(externalWeatherService, weatherDtoConverter, csvService);
	}

	@Test
	public void shouldReturnEmptyList_whenCitySetToGetDataForIsEmpty() {
		List<WeatherDto> result = facade.getWeatherInfo(Collections.emptySet());

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void shouldReturnEmptyWindAndTempData_whenExternalServiceReturnsNotFound() {
		when(externalWeatherService.getWeatherInfoForCity(CITY)).thenReturn(null);

		List<WeatherDto> result = facade.getWeatherInfo(Collections.singleton(CITY));

		verify(weatherDtoConverter, never()).convert(any());
		Assert.assertTrue(result.get(0).getWind().isEmpty());
		Assert.assertTrue(result.get(0).getWind().isEmpty());
	}

	@Test
	public void shouldReturnWindAndTempData_whenExternalServiceReturnsWeatherInfo() {
		when(externalWeatherService.getWeatherInfoForCity(CITY)).thenReturn(externalWeatherModel);
		when(weatherDtoConverter.convert(externalWeatherModel)).thenReturn(weatherDto);
		when(weatherDto.getWind()).thenReturn(WIND);
		when(weatherDto.getTemperature()).thenReturn(TEMP);

		List<WeatherDto> result = facade.getWeatherInfo(Collections.singleton(CITY));

		verify(weatherDtoConverter).convert(externalWeatherModel);
		Assert.assertEquals(WIND, result.get(0).getWind());
		Assert.assertEquals(TEMP, result.get(0).getTemperature());
	}
}
