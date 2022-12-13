package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.PreconisationCategorie;
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
public class PreconisationCategorieDto {
    private Integer idPreconisation;
    private CategorieQuestion categorieQuestion;
    private String contenu;
    private Integer viewIfPourcentageScoreLessThan;

    public static PreconisationCategorieDto from(PreconisationCategorie preconisationCategorie){
        PreconisationCategorieDto preconisationCategorieDto = new PreconisationCategorieDto();
        preconisationCategorieDto.setIdPreconisation(preconisationCategorie.getIdPreconisation());
        preconisationCategorieDto.setCategorieQuestion(preconisationCategorie.getCategorieQuestion());
        preconisationCategorieDto.setContenu(preconisationCategorie.getContenu());
        preconisationCategorieDto.setViewIfPourcentageScoreLessThan(preconisationCategorie.getViewIfPourcentageScoreLessThan());
        return preconisationCategorieDto;
    }
}
