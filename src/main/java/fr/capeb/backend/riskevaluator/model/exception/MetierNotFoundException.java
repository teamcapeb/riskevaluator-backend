package fr.capeb.backend.riskevaluator.model.exception;

import java.text.MessageFormat;

public class MetierNotFoundException extends RuntimeException {
	
	public MetierNotFoundException(final Integer id) {
		super(MessageFormat.format("Could not find question with id: {0}", id));
	}
}
