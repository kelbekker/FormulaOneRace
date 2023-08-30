package com.foxminded.formulaonerace.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.foxminded.formulaonerace.model.StartTime;

class StartTimeDAOTest {

	private StartTimeDAO startTimeDao = new StartTimeDAO();

	@Test
	void getAllWhenNormalInputShoudBeListStartTime() throws IOException {
		List<StartTime> actualResult = startTimeDao.getAll();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

		assertEquals(3, actualResult.size());
		assertEquals("SVF", actualResult.get(0).getKey());
		assertEquals(LocalDateTime.parse("2018-05-24_12:02:58.917", formatter)
				, actualResult.get(0).getStartTime());
		assertEquals("DRR", actualResult.get(1).getKey());
		assertEquals(LocalDateTime.parse("2018-05-24_12:14:12.054", formatter)
				, actualResult.get(1).getStartTime());
	}

	@Test
	void getWhenExistingKeyShoudBeStartTime() throws IOException {
		Optional<StartTime> actual = startTimeDao.get("LHM");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

		assertTrue(actual.isPresent());
		assertEquals(LocalDateTime.parse("2018-05-24_12:18:20.125", formatter), actual.get().getStartTime());
	}
}
