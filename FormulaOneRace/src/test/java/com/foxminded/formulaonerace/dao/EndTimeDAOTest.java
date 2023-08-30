package com.foxminded.formulaonerace.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.junit.jupiter.api.Test;

import com.foxminded.formulaonerace.model.EndTime;

class EndTimeDAOTest {

	private EndTimeDAO endTimeDao = new EndTimeDAO();

	@Test
	void getAllWhenNormalInputShoudBeListEndTime() throws IOException {

		List<EndTime> actualResult = endTimeDao.getAll();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

		assertEquals(3, actualResult.size());

		assertEquals("DRR", actualResult.get(0).getKey());
		assertEquals(LocalDateTime.parse("2018-05-24_12:15:24.067", formatter)
				,actualResult.get(0).getEndTime());
		assertEquals("SVF", actualResult.get(1).getKey());
		assertEquals(LocalDateTime.parse("2018-05-24_12:04:03.332", formatter)
				,actualResult.get(1).getEndTime());
	}

	@Test
	void getWhenExistingKeyShoudBeEndTime() throws IOException {
		Optional<EndTime> actual = endTimeDao.get("LHM");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

		assertTrue(actual.isPresent());
		assertEquals(LocalDateTime.parse("2018-05-24_12:19:32.585", formatter), actual.get().getEndTime());
	}
}
