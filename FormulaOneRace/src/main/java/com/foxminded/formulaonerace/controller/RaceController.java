package com.foxminded.formulaonerace.controller;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Comparator;
import java.util.Formatter;

import com.foxminded.formulaonerace.model.RaceResult;
import com.foxminded.formulaonerace.service.RaceService;

public class RaceController {

	private RaceService raceService = new RaceService();

	public String takeFormattedRaceResult() throws IOException {

		List<RaceResult> sortedResults = raceService.matchRacersWithTime();

		AtomicInteger i = new AtomicInteger(1);
		return sortedResults.stream().limit(15)
				.map(r -> String.format("%-2d %-20s |%-25s |%s%n", i.getAndIncrement(), r.getName(), r.getCarName(),
						formatElapsedTime(r.getElapsedTime())))
				.collect(Collectors.joining())
				.concat(i.get() <= 15 ? ""
						: String.format("-------------------------------------------------------------%n"))
				.concat(sortedResults
						.stream().skip(15).map(r -> String.format("%-2d %-20s |%-25s |%s%n", i.getAndIncrement(),
								r.getName(), r.getCarName(), formatElapsedTime(r.getElapsedTime())))
						.collect(Collectors.joining()));
	}

	private String formatElapsedTime(Duration elapsedTime) {
		Duration duration = elapsedTime;
		long minutes = duration.toMinutes();
		long seconds = duration.getSeconds() % 60;
		long milliseconds = duration.toMillis() % 1000;

		return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds);
	}
}
