package com.example.project.service.impl;

import com.example.project.model.ExternalWeatherModel;
import com.example.project.service.ExternalWeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class DefaultExternalWeatherService implements ExternalWeatherService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultExternalWeatherService.class);
	@Value("${external.weather.api}")
	private String externalServiceEndpoint;

	private RestTemplate restTemplate;

	public DefaultExternalWeatherService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	@Override
	public ExternalWeatherModel getWeatherInfoForCity(String city) {
		try {
			return restTemplate.getForObject(externalServiceEndpoint + city,
																	  ExternalWeatherModel.class);
		} catch (HttpClientErrorException ex) {
			LOG.warn("No data found for city [{}]", city, ex);
			return null;
		}
	}
}
