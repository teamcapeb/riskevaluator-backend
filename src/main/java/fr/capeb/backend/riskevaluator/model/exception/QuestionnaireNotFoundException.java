package fr.capeb.backend.riskevaluator.model.exception;

import java.text.MessageFormat;

public class QuestionnaireNotFoundException extends RuntimeException {
	
	public QuestionnaireNotFoundException(final Integer id) {
		super(MessageFormat.format("Could not find question with id: {0}", id));
	}
}
