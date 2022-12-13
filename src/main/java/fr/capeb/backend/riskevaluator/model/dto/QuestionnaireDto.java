package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.PreconisationGlobale;
import fr.capeb.backend.riskevaluator.model.Questionnaire;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class QuestionnaireDto {

    private Integer idQuestionnaire;
    private String thematique;
    private Set<CategorieQuestion> categorieQuestions = new HashSet<>();
    private Set<PreconisationGlobale> preconisationGlobales = new HashSet<>();

    public static QuestionnaireDto from(Questionnaire questionnaire){
        QuestionnaireDto questionnaireDto = new QuestionnaireDto();
        questionnaireDto.setIdQuestionnaire(questionnaire.getIdQuestionnaire());
        questionnaireDto.setThematique(questionnaire.getThematique());
        questionnaireDto.setCategorieQuestions(questionnaire.getCategorieQuestions());
        questionnaireDto.setPreconisationGlobales(questionnaire.getPreconisationGlobales());
        return questionnaireDto;
    }

}
