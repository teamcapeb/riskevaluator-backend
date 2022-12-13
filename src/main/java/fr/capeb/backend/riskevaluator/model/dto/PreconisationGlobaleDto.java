package fr.capeb.backend.riskevaluator.model.dto;


import fr.capeb.backend.riskevaluator.model.PreconisationGlobale;
import fr.capeb.backend.riskevaluator.model.Questionnaire;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PreconisationGlobaleDto {

    private Integer idPreconisationG;
    private Questionnaire questionnaire;
    private String contenu;
    private Integer viewIfPourcentageScoreLessThan;

    public static PreconisationGlobaleDto from(PreconisationGlobale preconisationGlobale){
        PreconisationGlobaleDto preconisationGlobaleDto = new PreconisationGlobaleDto();
        preconisationGlobaleDto.setViewIfPourcentageScoreLessThan(preconisationGlobale.getViewIfPourcentageScoreLessThan());
        preconisationGlobaleDto.setIdPreconisationG(preconisationGlobale.getIdPreconisationG());
        preconisationGlobaleDto.setQuestionnaire(preconisationGlobale.getQuestionnaire());
        preconisationGlobaleDto.setContenu(preconisationGlobale.getContenu());
        return preconisationGlobaleDto;
    }
}
