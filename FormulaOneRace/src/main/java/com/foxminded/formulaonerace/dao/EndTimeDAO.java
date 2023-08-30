package com.foxminded.formulaonerace.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import com.foxminded.formulaonerace.model.EndTime;

public class EndTimeDAO implements RaceDAO<EndTime> {

	@Override
	public Optional<EndTime> get(String key) throws IOException {
		return getAll().stream().filter(endTime -> endTime.getKey().equals(key)).findFirst();
	}

	@Override
	public List<EndTime> getAll() throws IOException {
		try (InputStream lines = getClass().getClassLoader().getResourceAsStream("end.log");
				BufferedReader reader = new BufferedReader(new InputStreamReader(lines))) {

			return reader.lines().map(line -> {
				String key = line.substring(0, 3);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
				LocalDateTime endTime = LocalDateTime.parse(line.substring(3).trim(), formatter);
				return new EndTime(key, endTime);
			}).collect(Collectors.toList());
		}
	}

}
