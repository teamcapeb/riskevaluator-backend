package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Evaluation;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlainEvaluationDto {
    private Integer idEvaluation;
    private Integer scoreGeneraleEvaluation;

    public static PlainEvaluationDto from(Evaluation evaluationEntity){
        PlainEvaluationDto evaluationDto = new PlainEvaluationDto();
        evaluationDto.setIdEvaluation(evaluationEntity.getIdEvaluation());
        evaluationDto.setScoreGeneraleEvaluation(evaluationEntity.getScoreGeneraleEvaluation());
        return evaluationDto;
    }


}
