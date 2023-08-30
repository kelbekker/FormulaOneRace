package com.foxminded.formulaonerace.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foxminded.formulaonerace.model.StartTime;

public class StartTimeDAO implements RaceDAO<StartTime> {

	@Override
	public Optional<StartTime> get(String key) throws IOException {
		return getAll().stream().filter(startTime -> startTime.getKey().equals(key)).findFirst();
	}

	@Override
	public List<StartTime> getAll() throws IOException {

		try (InputStream lines = getClass().getClassLoader().getResourceAsStream("start.log");
				BufferedReader reader = new BufferedReader(new InputStreamReader(lines))) {

			return reader.lines().map(line -> {
				String key = line.substring(0, 3);
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
				LocalDateTime startTime = LocalDateTime.parse(line.substring(3).trim(), formatter);
				return new StartTime(key, startTime);
			}).collect(Collectors.toList());
		}
	}

}
