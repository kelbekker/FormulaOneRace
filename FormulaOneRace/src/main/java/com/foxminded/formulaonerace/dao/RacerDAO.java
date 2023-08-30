package com.foxminded.formulaonerace.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.foxminded.formulaonerace.model.Racer;

public class RacerDAO implements RaceDAO<Racer> {

	@Override
	public Optional<Racer> get(String key) throws IOException {

		return getAll().stream().filter(racer -> racer.getKey().equals(key)).findFirst();
	}

	@Override
	public List<Racer> getAll() throws IOException {

		try (InputStream lines = getClass().getClassLoader().getResourceAsStream("abbreviations.txt");
				BufferedReader reader = new BufferedReader(new InputStreamReader(lines))) {

			return reader.lines().map(line -> {
				String[] split = line.split("_");
				String key = split[0];
				String name = split[1];
				String carName = split[2];
				return new Racer(key, name, carName);
			}).collect(Collectors.toList());

		}

	}
}
