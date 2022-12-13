package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.*;
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
public class CategorieQuestionDto {
    public Integer idCategorie;
    public Questionnaire questionnaire;
    public String libelle;
    private Set<ScoreCategory> scoreEvaluations=new HashSet<>();
    private Set<PreconisationCategorie> preconisationsCategorie= new HashSet<>();
    private Set<Question> questions= new HashSet<>();



    public static CategorieQuestionDto from(CategorieQuestion categorieQuestion){
        CategorieQuestionDto categorieQuestionDto = new CategorieQuestionDto();
        categorieQuestionDto.setIdCategorie(categorieQuestion.getIdCategorie());
        categorieQuestionDto.setQuestions(categorieQuestion.getQuestions());
        categorieQuestionDto.setPreconisationsCategorie(categorieQuestion.getPreconisationsCategorie());
        categorieQuestionDto.setLibelle(categorieQuestion.getLibelle());
        categorieQuestionDto.setQuestionnaire(categorieQuestion.getQuestionnaire());
        categorieQuestionDto.setScoreEvaluations(categorieQuestion.getScoreEvaluations());
        return categorieQuestionDto;
    }
}
