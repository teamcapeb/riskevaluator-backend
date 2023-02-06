package fr.capeb.backend.riskevaluator.model.exception;

import java.text.MessageFormat;

public class EntrepriseNotFoundException extends RuntimeException {
	
	public EntrepriseNotFoundException(final Long id) {
		super(MessageFormat.format("Could not find question with id: {0}", id));
	}
}
