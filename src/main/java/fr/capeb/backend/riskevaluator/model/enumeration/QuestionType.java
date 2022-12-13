package fr.capeb.backend.riskevaluator.model.enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum QuestionType {
    QUESTION_CHOIX_UNIQUE("QUESTION_CHOIX_UNIQUE"),
    QUESTION_CHOIX_MULTIPLE("QUESTION_CHOIX_MULTIPLE");

    @Getter
    private String value;
}
