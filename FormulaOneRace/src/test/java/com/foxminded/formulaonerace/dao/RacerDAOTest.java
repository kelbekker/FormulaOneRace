package com.foxminded.formulaonerace.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.foxminded.formulaonerace.model.Racer;

class RacerDAOTest {

	private RacerDAO racerDao = new RacerDAO();

	@Test
	void getAllWhenNormalInputShoudBeListRacer() throws IOException {
		List<Racer> actualResult = racerDao.getAll();

		assertEquals(3, actualResult.size());
		assertEquals("DRR", actualResult.get(0).getKey());
		assertEquals("Daniel Ricciardo", actualResult.get(0).getName());
		assertEquals("RED BULL RACING TAG HEUER", actualResult.get(0).getCarName());
		assertEquals("SVF", actualResult.get(1).getKey());
		assertEquals("Sebastian Vettel", actualResult.get(1).getName());
		assertEquals("FERRARI", actualResult.get(1).getCarName());

	}

	@Test
	void getWhenExistingKeyShoudBeRacerName() throws IOException {
		Optional<Racer> actual = racerDao.get("LHM");
		String expectedNameRacer = "Lewis Hamilton";

		assertTrue(actual.isPresent());
		assertEquals(expectedNameRacer, actual.get().getName());
	}
	
	@Test
	void getWhenExistingKeyShoudBeRacerCarName() throws IOException {
		Optional<Racer> actual = racerDao.get("LHM");
		String expectedCarRacer = "MERCEDES";
		
		assertTrue(actual.isPresent());
		assertEquals(expectedCarRacer, actual.get().getCarName());
	}

}
