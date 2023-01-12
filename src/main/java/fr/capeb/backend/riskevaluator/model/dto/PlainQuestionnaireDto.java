package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.PreconisationGlobale;
import fr.capeb.backend.riskevaluator.model.Questionnaire;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlainQuestionnaireDto {

    private Integer idQuestionnaire;
    private String thematique;
    private Date date;

    public static PlainQuestionnaireDto from(Questionnaire questionnaire){
        PlainQuestionnaireDto plainQuestionnaireDto = new PlainQuestionnaireDto();
        plainQuestionnaireDto.setIdQuestionnaire(questionnaire.getIdQuestionnaire());
        plainQuestionnaireDto.setThematique(questionnaire.getThematique());
        return plainQuestionnaireDto;
    }

}
