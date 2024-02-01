package com.example.project.service;

import com.example.project.model.ExternalWeatherModel;


public interface ExternalWeatherService {
	ExternalWeatherModel getWeatherInfoForCity(final String city);
}
