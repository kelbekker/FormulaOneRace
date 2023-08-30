package com.foxminded.formulaonerace.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.foxminded.formulaonerace.model.RaceResult;
import com.foxminded.formulaonerace.service.RaceService;

class RaceControllerTest {

	@Mock
	private RaceService raceService;

	private RaceController raceController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		raceController = new RaceController();
	}

	@Test
	void takeFormattedRaceResultWhenValidDataShoudBFormattedRaceResults() throws IOException {
		List<RaceResult> sortedResults = new ArrayList<>();
		sortedResults.add(new RaceResult("SVF", "Sebastian Vettel", "FERRARI"));
		sortedResults.add(new RaceResult("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"));
		sortedResults.add(new RaceResult("LHM", "Lewis Hamilton", "MERCEDES"));

		when(raceService.matchRacersWithTime()).thenReturn(sortedResults);

		String expected = "1  Sebastian Vettel     |FERRARI                   |01:04.415\r\n"
				+ "2  Daniel Ricciardo     |RED BULL RACING TAG HEUER |01:12.013\r\n"
				+ "3  Lewis Hamilton       |MERCEDES                  |01:12.460\r\n" + "";

		String formattedRaceResult = raceController.takeFormattedRaceResult();

		Assertions.assertEquals(expected, formattedRaceResult);
	}
}
