package com.example.project.service;

import java.util.Set;


public interface ParseWeatherRequestService {
	Set<String> getValidCityList(final String cities);
}
