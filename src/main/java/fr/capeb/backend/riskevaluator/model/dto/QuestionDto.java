package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.MetierQuestion;
import fr.capeb.backend.riskevaluator.model.Question;
import fr.capeb.backend.riskevaluator.model.Reponse;
import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class QuestionDto {
    private Integer idQuestion;
    private QuestionType typeQuestion;
    private Integer scoreMaxPossibleQuestion;
    private String libelleQuestion;
    private CategorieQuestion categorieQuestion;
    private Set<MetierQuestion> metiers = new LinkedHashSet<>();
    private Set<Reponse> reponses= new HashSet<>();

    public static QuestionDto from(Question question){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setIdQuestion(question.getIdQuestion());
        questionDto.setLibelleQuestion(question.getLibelleQuestion());
        questionDto.setCategorieQuestion(question.getCategorieQuestion());
        questionDto.setScoreMaxPossibleQuestion(question.getScoreMaxPossibleQuestion());
        questionDto.setTypeQuestion(question.getTypeQuestion());
        questionDto.setMetiers(question.getMetiers());
        questionDto.setReponses(question.getReponses());
        return questionDto;
    }
}
