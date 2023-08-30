package com.foxminded.formulaonerace.dao;

import java.io.IOException;
import java.util.*;

public interface RaceDAO<T> {
	Optional<T> get(String id) throws IOException;

	List<T> getAll() throws IOException;
}
