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
    private PlainEntrepriseDto entreprise;
    private Set<PlainScoreCategoryDto> scoreCategories=new HashSet<>();

    public static EvaluationDto from(Evaluation evaluationEntity){
        EvaluationDto evaluationDto = new EvaluationDto();
        evaluationDto.setIdEvaluation(evaluationEntity.getIdEvaluation());
        evaluationDto.setCompte(evaluationEntity.getCompte());
        PlainEntrepriseDto plainEntrepriseDto = new PlainEntrepriseDto();
        Entreprise entreprise1 = evaluationEntity.getEntreprise();
        plainEntrepriseDto.setNomEntreprise(entreprise1.getNomEntreprise());
        plainEntrepriseDto.setNoSiret(entreprise1.getNoSiret());
        plainEntrepriseDto.setEffectifEntreprise(entreprise1.getEffectifEntreprise());
        plainEntrepriseDto.setAnneeDeCreation(entreprise1.getAnneeDeCreation());
        evaluationDto.setEntreprise(plainEntrepriseDto);
        evaluationDto.setScoreGeneraleEvaluation(evaluationEntity.getScoreGeneraleEvaluation());
        Set<PlainScoreCategoryDto> scoreCategoryDtos = new HashSet<>();
        Set<ScoreCategory> scoreCategories1 = evaluationEntity.getScoreCategories();
        scoreCategories1.forEach(scoreCategory -> {
            PlainScoreCategoryDto plainScoreCategoryDto = new PlainScoreCategoryDto();
            plainScoreCategoryDto.setNbPoints(scoreCategory.getNbPoints());
            plainScoreCategoryDto.setKey(scoreCategory.getKey());
            scoreCategoryDtos.add(plainScoreCategoryDto);
        });
        evaluationDto.setScoreCategories(scoreCategoryDtos);
        return evaluationDto;
    }


}
