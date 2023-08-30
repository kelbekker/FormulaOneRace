package com.foxminded.formulaonerace;

import java.io.IOException;

import com.foxminded.formulaonerace.controller.RaceController;
import com.foxminded.formulaonerace.dao.EndTimeDAO;
import com.foxminded.formulaonerace.dao.RaceDAO;
import com.foxminded.formulaonerace.service.RaceService;

public class App {
	public static void main(String[] args) throws IOException {	
		RaceController race = new RaceController();
		System.out.println(race.takeFormattedRaceResult());
	}

}
