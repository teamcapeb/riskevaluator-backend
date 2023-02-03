package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Evaluation;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlainEvaluationDto {
    private Integer idEvaluation;
    private Integer scoreGeneraleEvaluation;
    private String date;

    private Set<PlainMetierDto> metiers  = new HashSet<>();


    public static PlainEvaluationDto from(Evaluation evaluationEntity){
        PlainEvaluationDto evaluationDto = new PlainEvaluationDto();
        evaluationDto.setIdEvaluation(evaluationEntity.getIdEvaluation());
        evaluationDto.setScoreGeneraleEvaluation(evaluationEntity.getScoreGeneraleEvaluation());
        evaluationDto.setDate(evaluationDto.getDate());

        Set<PlainMetierDto> plainMetiers  = new HashSet<>();
        evaluationEntity.getMetiers().forEach(metier -> {
            PlainMetierDto plainMetierDto = new PlainMetierDto();
            plainMetierDto.setNomMetier(metier.getNomMetier());
            plainMetierDto.setIdMetier(metier.getIdMetier());
            plainMetiers.add(plainMetierDto);
        });


        evaluationDto.setMetiers(plainMetiers);
        return evaluationDto;
    }


}
