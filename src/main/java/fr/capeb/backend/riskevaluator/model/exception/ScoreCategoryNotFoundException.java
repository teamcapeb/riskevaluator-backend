package fr.capeb.backend.riskevaluator.model.exception;

import java.text.MessageFormat;

public class ScoreCategoryNotFoundException extends RuntimeException {
	
	public ScoreCategoryNotFoundException(final Integer id) {
		super(MessageFormat.format("Could not find question with id: {0}", id));
	}
}
