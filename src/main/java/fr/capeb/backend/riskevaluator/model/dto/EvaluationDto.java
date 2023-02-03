package fr.capeb.backend.riskevaluator.model.dto;

import java.util.HashSet;
import java.util.Set;

import fr.capeb.backend.riskevaluator.model.Compte;
import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.model.ScoreCategory;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class EvaluationDto {

    private Integer idEvaluation;

    private Integer scoreGeneraleEvaluation;

    private Compte compte;

    private String date;

    private PlainEntrepriseDto entreprise;

    private Set<ScoreCategoryDto> scoreCategories = new HashSet<>();

    public static EvaluationDto from(Evaluation evaluationEntity) {
        EvaluationDto evaluationDto = new EvaluationDto();
        evaluationDto.setIdEvaluation(evaluationEntity.getIdEvaluation());
        evaluationDto.setCompte(evaluationEntity.getCompte());
        PlainEntrepriseDto plainEntrepriseDto = new PlainEntrepriseDto();
        Entreprise entreprise1 = evaluationEntity.getEntreprise();
        plainEntrepriseDto.setNomEntreprise(entreprise1.getNomEntreprise());
        plainEntrepriseDto.setNoSiret(entreprise1.getNoSiret());
        plainEntrepriseDto.setEffectifEntreprise(entreprise1.getEffectif());
        plainEntrepriseDto.setAnneeDeCreation(entreprise1.getAnneeDeCreation());
        evaluationDto.setEntreprise(plainEntrepriseDto);
        evaluationDto.setDate(evaluationEntity.getDate());
        evaluationDto.setScoreGeneraleEvaluation(evaluationEntity.getScoreGeneraleEvaluation());
        Set<ScoreCategoryDto> scoreCategoryDtos = new HashSet<>();
        Set<ScoreCategory> scoreCategories1 = evaluationEntity.getScoreCategories();
        scoreCategories1.forEach(scoreCategory -> {
            ScoreCategoryDto scoreCategoryDto = new ScoreCategoryDto();
            scoreCategoryDto.setNbPoints(scoreCategory.getNbPoints());
            scoreCategoryDto.setKey(scoreCategory.getKey());

            CategorieQuestionDto categorieQuestionDto = new CategorieQuestionDto();
            categorieQuestionDto.setIdCategorie(scoreCategory.getKey().getIdCategorie());
            categorieQuestionDto.setLibelle(scoreCategory.getCategorieQuestion().getLibelle());
            PlainQuestionnaireDto plainQuestionnaireDto = new PlainQuestionnaireDto();
            plainQuestionnaireDto.setThematique(scoreCategory.getCategorieQuestion().getQuestionnaire().getThematique());
            plainQuestionnaireDto.setIdQuestionnaire(scoreCategory.getCategorieQuestion().getQuestionnaire().getIdQuestionnaire());
            plainQuestionnaireDto.setDate(scoreCategory.getCategorieQuestion().getQuestionnaire().getDate());
            categorieQuestionDto.setQuestionnaire(plainQuestionnaireDto);
            Set<PlainPreconisationCategorieDto> preconisationsCategorie= new HashSet<>();
            scoreCategory.getCategorieQuestion().getPreconisationsCategorie().forEach(preconisationCategorie -> {
                PlainPreconisationCategorieDto plainPreconisationCategorieDto = new PlainPreconisationCategorieDto();
                plainPreconisationCategorieDto.setIdPreconisation(preconisationCategorie.getIdPreconisation());
                plainPreconisationCategorieDto.setContenu(preconisationCategorie.getContenu());
                plainPreconisationCategorieDto.setViewIfPourcentageScoreLessThan(preconisationCategorie.getViewIfPourcentageScoreLessThan());
                preconisationsCategorie.add(plainPreconisationCategorieDto);
            });

            categorieQuestionDto.setPreconisationsCategorie(preconisationsCategorie);
            scoreCategoryDto.setCategorieQuestion(categorieQuestionDto);

            PlainEvaluationDto plainEvaluationDto = new PlainEvaluationDto();
            plainEvaluationDto.setIdEvaluation(evaluationEntity.getIdEvaluation());
            plainEvaluationDto.setScoreGeneraleEvaluation(evaluationEntity.getScoreGeneraleEvaluation());
            scoreCategoryDto.setEvaluation(plainEvaluationDto);

            scoreCategoryDtos.add(scoreCategoryDto);
        });
        evaluationDto.setScoreCategories(scoreCategoryDtos);
        return evaluationDto;
    }

}
