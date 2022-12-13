package fr.capeb.backend.riskevaluator.model.exception;

import fr.capeb.backend.riskevaluator.model.MetierQuestionPK;

import java.text.MessageFormat;

public class MetierQuestionNotFoundException extends RuntimeException {

    public MetierQuestionNotFoundException(final MetierQuestionPK id){
        super(MessageFormat.format("Could not find question with id: {0} {1}", id.getMetierId(),id.getQuestionId()));
    }
}