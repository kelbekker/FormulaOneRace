package com.foxminded.formulaonerace.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxminded.formulaonerace.dao.EndTimeDAO;
import com.foxminded.formulaonerace.dao.RacerDAO;
import com.foxminded.formulaonerace.dao.StartTimeDAO;
import com.foxminded.formulaonerace.model.EndTime;
import com.foxminded.formulaonerace.model.RaceResult;
import com.foxminded.formulaonerace.model.Racer;
import com.foxminded.formulaonerace.model.StartTime;

class RaceServiceTest {

	@Mock
	private RacerDAO racerDao;
	@Mock
	private StartTimeDAO startTimeDao;
	@Mock
	private EndTimeDAO endTimeDao;

	private RaceService raceService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		raceService = new RaceService();
	}

	@Test
	void matchRacersWithTimeWhenValidDataShoudBeSortedRaceResults() throws IOException {
		List<RaceResult> raceResults = raceService.matchRacersWithTime();

		List<Racer> racers = new ArrayList<>();
		racers.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"));
		racers.add(new Racer("SVF", "Sebastian Vettel", "FERRARI"));

		List<StartTime> startTimes = new ArrayList<>();
		startTimes.add(new StartTime("DRR", LocalDateTime.of(2018, 5, 24, 12, 14, 12, 054)));
		startTimes.add(new StartTime("SVF", LocalDateTime.of(2018, 5, 24, 12, 2, 58, 917)));

		List<EndTime> endTimes = new ArrayList<>();
		endTimes.add(new EndTime("DRR", LocalDateTime.of(2018, 5, 24, 12, 15, 24, 067)));
		endTimes.add(new EndTime("SVF", LocalDateTime.of(2018, 05, 24, 12, 4, 3, 332)));

		when(racerDao.getAll()).thenReturn(racers);
		when(startTimeDao.getAll()).thenReturn(startTimes);
		when(endTimeDao.getAll()).thenReturn(endTimes);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

		assertEquals(3, raceResults.size());

		assertEquals("SVF", raceResults.get(0).getKey());
		assertEquals(LocalDateTime.parse("2018-05-24_12:02:58.917", formatter), raceResults.get(0).getStartTime());
		assertEquals(LocalDateTime.parse("2018-05-24_12:04:03.332", formatter), raceResults.get(0).getEndTime());
		assertEquals(Duration.parse("PT1M4.415S"), raceResults.get(0).getElapsedTime());

		assertEquals("DRR", raceResults.get(1).getKey());
		assertEquals(LocalDateTime.parse("2018-05-24_12:14:12.054", formatter), raceResults.get(1).getStartTime());
		assertEquals(LocalDateTime.parse("2018-05-24_12:15:24.067", formatter), raceResults.get(1).getEndTime());
		assertEquals(Duration.parse("PT1M12.013S"), raceResults.get(1).getElapsedTime());

		assertEquals("LHM", raceResults.get(2).getKey());
		assertEquals(LocalDateTime.parse("2018-05-24_12:18:20.125", formatter), raceResults.get(2).getStartTime());
		assertEquals(LocalDateTime.parse("2018-05-24_12:19:32.585", formatter), raceResults.get(2).getEndTime());
		assertEquals(Duration.parse("PT1M12.46S"), raceResults.get(2).getElapsedTime());
	}
}
