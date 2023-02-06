package fr.capeb.backend.riskevaluator.model.exception;

import java.text.MessageFormat;

public class PreconisationGlobaleNotFoundException extends RuntimeException {
	
	public PreconisationGlobaleNotFoundException(final Integer id) {
		super(MessageFormat.format("Could not find question with id: {0}", id));
	}
}
