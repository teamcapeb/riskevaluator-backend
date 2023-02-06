package fr.capeb.backend.riskevaluator.model.exception;

import java.text.MessageFormat;

public class PreconisationCategorieNotFoundException extends RuntimeException {
	
	public PreconisationCategorieNotFoundException(final Integer id) {
		super(MessageFormat.format("Could not find question with id: {0}", id));
	}
}
