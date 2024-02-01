package com.example.project.service.impl;

import com.example.project.service.ParseWeatherRequestService;

import java.util.Arrays;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class DefaultParseWeatherRequestService implements ParseWeatherRequestService {
	final static String SEPARATOR = ",";
	private final List<String> citiesToShow;

	public DefaultParseWeatherRequestService(List<String> citiesToShow) {
		this.citiesToShow = citiesToShow;
	}

	public Set<String> getValidCityList(final String cities) {
		return Arrays.stream(cities.trim().split(SEPARATOR))
					 .filter(citiesToShow::contains)
					 .collect(Collectors.toCollection(TreeSet::new));

	}
}
