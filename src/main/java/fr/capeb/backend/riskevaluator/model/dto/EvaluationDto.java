package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Compte;
import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.model.ScoreCategory;
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
public class EvaluationDto {
    private Integer idEvaluation;
    private Integer scoreGeneraleEvaluation;
    private Compte compte;
    private Entreprise entreprise;
    private Set<ScoreCategory> scoreCategories=new HashSet<>();

    public static EvaluationDto from(Evaluation evaluationEntity){
        EvaluationDto evaluationDto = new EvaluationDto();
        evaluationDto.setIdEvaluation(evaluationEntity.getIdEvaluation());
        evaluationDto.setCompte(evaluationEntity.getCompte());
        evaluationDto.setEntreprise(evaluationEntity.getEntreprise());
        evaluationDto.setScoreGeneraleEvaluation(evaluationEntity.getScoreGeneraleEvaluation());
        return evaluationDto;
    }


}
