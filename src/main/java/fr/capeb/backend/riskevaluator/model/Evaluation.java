package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.dto.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @Column(name = "id_evaluation")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvaluation;

    @Column(name = "score_generale")
    private Integer scoreGeneraleEvaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_compte")
    private Compte compte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nosiret", nullable=false)
    private Entreprise entreprise;

    private String date;

    @OneToMany(mappedBy = "evaluation",cascade = CascadeType.ALL)
    private Set<ScoreCategory> scoreCategories=new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "evaluation_id")
    private Set<Metier> metiers = new HashSet<>();

    public static Evaluation from(EvaluationDto evaluationDto){
        Evaluation evaluationEntity = new Evaluation();
        evaluationEntity.setIdEvaluation(evaluationDto.getIdEvaluation());
        evaluationEntity.setCompte(evaluationDto.getCompte());
        Entreprise entreprise1 = new Entreprise();
        PlainEntrepriseDto entrepriseDto = evaluationDto.getEntreprise();
        entreprise1.setNoSiret(entrepriseDto.getNoSiret());
        evaluationEntity.setDate(evaluationDto.getDate());
        entreprise1.setNomEntreprise(entrepriseDto.getNomEntreprise());
        entreprise1.setAnneeDeCreation(entrepriseDto.getAnneeDeCreation());
        entreprise1.setEffectif(entrepriseDto.getEffectifEntreprise());



        evaluationEntity.setEntreprise(entreprise1);
        evaluationEntity.setScoreGeneraleEvaluation(evaluationDto.getScoreGeneraleEvaluation());
        Set<ScoreCategory> scoreCategories1 = new HashSet<>();
        Set<ScoreCategoryDto> scoreCategoryDtos = evaluationDto.getScoreCategories();

        scoreCategoryDtos.forEach(plainScoreCategoryDto -> {
            ScoreCategory scoreCategory = new ScoreCategory();
            scoreCategory.setKey(plainScoreCategoryDto.getKey());
            CategorieQuestion categorieQuestion = new CategorieQuestion();
           categorieQuestion.setIdCategorie(plainScoreCategoryDto.getCategorieQuestion().getIdCategorie());
            categorieQuestion.setLibelle(plainScoreCategoryDto.getCategorieQuestion().getLibelle());

            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setIdQuestionnaire(plainScoreCategoryDto.getCategorieQuestion().getQuestionnaire().getIdQuestionnaire());
            questionnaire.setThematique(plainScoreCategoryDto.getCategorieQuestion().getQuestionnaire().getThematique());
            questionnaire.setDate(plainScoreCategoryDto.getCategorieQuestion().getQuestionnaire().getDate());


            categorieQuestion.setQuestionnaire(questionnaire);
            Set<Question> questions = new HashSet<>();
            plainScoreCategoryDto.getCategorieQuestion().getQuestions().forEach((plainQuestionDto -> {
                Question question = new Question();
                question.setIdQuestion(plainQuestionDto.getIdQuestion());
                question.setLibelleQuestion(plainQuestionDto.getLibelleQuestion());
                question.setTypeQuestion(plainQuestionDto.getTypeQuestion());
                question.setScoreMaxPossibleQuestion(plainQuestionDto.getScoreMaxPossibleQuestion());
                Set<Reponse> reponses = new HashSet<>();
                plainQuestionDto.getReponses().forEach(plainReponseDto -> {
                    Reponse reponse = new Reponse();
                    reponse.setIdReponse(plainReponseDto.getIdReponse());
                    reponse.setContenu(plainReponseDto.getContenu());
                    reponse.setNbPoints(plainReponseDto.getNbPoints());
                    reponses.add(reponse);
                });

                question.setReponses(reponses);
                questions.add(question);



            }));

            categorieQuestion.setQuestions(questions);
            scoreCategory.setCategorieQuestion(categorieQuestion);

            Evaluation evaluation = new Evaluation();
            evaluation.setIdEvaluation(evaluationDto.getIdEvaluation());
            evaluation.setDate(evaluationDto.getDate());
            evaluation.setScoreGeneraleEvaluation(evaluationDto.getScoreGeneraleEvaluation());

            scoreCategory.setEvaluation(evaluation);
            scoreCategory.setNbPoints(plainScoreCategoryDto.getNbPoints());
            scoreCategories1.add(scoreCategory);
        });
        evaluationEntity.setScoreCategories(scoreCategories1);

        Set<Metier> metiers1=new HashSet<>();
        entrepriseDto.getMetiers().forEach(plainMetierDto -> {
            Metier metier = new Metier();
            metier.setIdMetier(plainMetierDto.getIdMetier());
            metier.setNomMetier(plainMetierDto.getNomMetier());
            metiers1.add(metier);
        });
        evaluationEntity.setMetiers(metiers1);

        return evaluationEntity;
    }


}
