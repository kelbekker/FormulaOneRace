package com.foxminded.formulaonerace.service;

import java.io.IOException;
import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.foxminded.formulaonerace.dao.EndTimeDAO;
import com.foxminded.formulaonerace.dao.RacerDAO;
import com.foxminded.formulaonerace.dao.StartTimeDAO;
import com.foxminded.formulaonerace.model.EndTime;
import com.foxminded.formulaonerace.model.RaceResult;
import com.foxminded.formulaonerace.model.Racer;
import com.foxminded.formulaonerace.model.StartTime;

public class RaceService {
	RacerDAO racerDao = new RacerDAO();
	StartTimeDAO startTimeDao = new StartTimeDAO();
	EndTimeDAO endTimeDao = new EndTimeDAO();

	public List<RaceResult> matchRacersWithTime() throws IOException {
		List<Racer> racers = racerDao.getAll();
		List<StartTime> startTimes = startTimeDao.getAll();
		List<EndTime> endTimes = endTimeDao.getAll();

		return racers.stream().map(r -> new RaceResult(r.getKey(), r.getName(), r.getCarName()))
				.map(rr -> setStartTime(rr, startTimes)).map(rr -> setEndTime(rr, endTimes))
				.map(this::calculateDuration).sorted(Comparator.comparing(RaceResult::getElapsedTime))
				.collect(Collectors.toList());
	}

	private RaceResult calculateDuration(RaceResult raceResult) {
		Duration duration = Duration.between(raceResult.getStartTime(), raceResult.getEndTime());
		raceResult.setElapsedTime(duration);
		return raceResult;

	}

	private RaceResult setStartTime(RaceResult raceResult, List<StartTime> startTimes) {
		startTimes.stream().filter(startTimeEntry -> startTimeEntry.getKey().equals(raceResult.getKey())).findFirst()
				.ifPresent(startTimeEntry -> raceResult.setStartTime(startTimeEntry.getStartTime()));
		return raceResult;
	}

	private RaceResult setEndTime(RaceResult raceResult, List<EndTime> endTimes) {
		endTimes.stream().filter(endTimeEntry -> endTimeEntry.getKey().equals(raceResult.getKey())).findFirst()
				.ifPresent(endTimeEntry -> raceResult.setEndTime(endTimeEntry.getEndTime()));
		return raceResult;
	}

}
