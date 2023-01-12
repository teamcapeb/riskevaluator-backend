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
public class PlainCategorieQuestionDto {
    public Integer idCategorie;
    public String libelle;




    public static PlainCategorieQuestionDto from(CategorieQuestion categorieQuestion){
        PlainCategorieQuestionDto categorieQuestionDto = new PlainCategorieQuestionDto();
        categorieQuestionDto.setIdCategorie(categorieQuestion.getIdCategorie());
        categorieQuestionDto.setLibelle(categorieQuestion.getLibelle());
        return categorieQuestionDto;
    }
}
