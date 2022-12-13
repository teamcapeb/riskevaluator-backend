package fr.capeb.backend.riskevaluator.model.exception;

import java.text.MessageFormat;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(final Integer id){
        super(MessageFormat.format("Could not find question with id: {0}", id));
    }
}