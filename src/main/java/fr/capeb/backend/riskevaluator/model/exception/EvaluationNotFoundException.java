package fr.capeb.backend.riskevaluator.model.exception;

import java.text.MessageFormat;

public class EvaluationNotFoundException extends RuntimeException {
	
	public EvaluationNotFoundException(final Integer id) {
		super(MessageFormat.format("Could not find evaluation with id: {0}", id));
	}
}
