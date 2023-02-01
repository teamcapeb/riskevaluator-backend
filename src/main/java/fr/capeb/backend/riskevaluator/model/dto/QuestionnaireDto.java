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
public class QuestionnaireDto {

    private Integer idQuestionnaire;
    private String thematique;
    private Date date;
    private Set<PlainCategorieQuestionDto> categorieQuestions = new HashSet<>();
    private Set<PlainPreconisationGlobaleDto> preconisationGlobales = new HashSet<>();

    public static QuestionnaireDto from(Questionnaire questionnaire){
        QuestionnaireDto questionnaireDto = new QuestionnaireDto();
        questionnaireDto.setIdQuestionnaire(questionnaire.getIdQuestionnaire());
        questionnaireDto.setThematique(questionnaire.getThematique());
        Set<PlainCategorieQuestionDto> plainCategorieQuestionDto = new HashSet<>();
        Set<CategorieQuestion> categorieQuestion = questionnaire.getCategorieQuestions();
        categorieQuestion.forEach(categorieQuestion1 -> {
            PlainCategorieQuestionDto plainCategorieQuestionDto1 = new PlainCategorieQuestionDto();
            plainCategorieQuestionDto1.setLibelle(categorieQuestion1.getLibelle());
            plainCategorieQuestionDto1.setIdCategorie(categorieQuestion1.getIdCategorie());
            Set<PlainQuestionDto> plainQuestionDtos = new HashSet<>();
            categorieQuestion1.getQuestions().forEach(question -> {
                PlainQuestionDto plainQuestionDto = new PlainQuestionDto();
                plainQuestionDto.setTypeQuestion(question.getTypeQuestion());
                plainQuestionDto.setIdQuestion(question.getIdQuestion());
                plainQuestionDto.setScoreMaxPossibleQuestion(question.getScoreMaxPossibleQuestion());
                plainQuestionDto.setLibelleQuestion(question.getLibelleQuestion());
                plainQuestionDtos.add(plainQuestionDto);
            });
            plainCategorieQuestionDto1.setQuestions(plainQuestionDtos);
            plainCategorieQuestionDto.add(plainCategorieQuestionDto1);
        });
        questionnaireDto.setCategorieQuestions(plainCategorieQuestionDto);

        Set<PlainPreconisationGlobaleDto> plainPreconisationGlobaleDtos = new HashSet<>();
        Set<PreconisationGlobale> preconisationGlobale = questionnaire.getPreconisationGlobales();
        preconisationGlobale.forEach(preconisationGlobale1 -> {
            PlainPreconisationGlobaleDto plainPreconisationGlobaleDto = new PlainPreconisationGlobaleDto();
            plainPreconisationGlobaleDto.setIdPreconisationG(preconisationGlobale1.getIdPreconisationG());
            plainPreconisationGlobaleDto.setViewIfPourcentageScoreLessThan(preconisationGlobale1.getViewIfPourcentageScoreLessThan());
            plainPreconisationGlobaleDto.setContenu(preconisationGlobale1.getContenu());
            plainPreconisationGlobaleDtos.add(plainPreconisationGlobaleDto);
        });
        questionnaireDto.setPreconisationGlobales(plainPreconisationGlobaleDtos);
        return questionnaireDto;
    }

}
