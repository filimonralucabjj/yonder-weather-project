package com.example.project.service.impl;

import com.example.project.dto.WeatherDto;
import com.example.project.service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;


public class DefaultCsvService implements CsvService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultCsvService.class);
	private static String csvHeader;
	private static String fileName;

	@Override
	public void generateWeatherCsv(List<WeatherDto> weatherDtoList) {
		File csvFile = new File(fileName);
		try {
			PrintWriter out = new PrintWriter(csvFile);
			out.println(csvHeader);
			for (WeatherDto weatherDto : weatherDtoList) {
				out.printf("%s, %s, %s\n", weatherDto.getName(), weatherDto.getTemperature(), weatherDto.getWind());
			}
			out.close();
		} catch (FileNotFoundException e) {
			LOG.warn("There was an issue while generating csv file", e);
		}
	}

	public static void setCsvHeader(String csvHeader) {
		DefaultCsvService.csvHeader = csvHeader;
	}

	public static void setFileName(String fileName) {
		DefaultCsvService.fileName = fileName;
	}
}
