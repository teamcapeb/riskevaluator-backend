package fr.capeb.backend.riskevaluator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.capeb.backend.riskevaluator.model.dto.PlainCategorieQuestionDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainPreconisationGlobaleDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainQuestionDto;
import fr.capeb.backend.riskevaluator.model.dto.QuestionnaireDto;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "questionnaire", uniqueConstraints = {
        @UniqueConstraint(columnNames = "thematique")
})
public class Questionnaire {

    @Id
    @Column(name = "id_questionnaire")
    private Integer idQuestionnaire;

    private Date date;

    @Column(name = "thematique", nullable=false)
    private String thematique;

    @OrderBy("libelle")
    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JsonIgnore
    private Set<CategorieQuestion> categorieQuestions = new HashSet<>();

    @OrderBy("contenu")
   // @JsonIgnore
    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<PreconisationGlobale> preconisationGlobales = new HashSet<>();

    public static Questionnaire from(QuestionnaireDto questionnaireDto){
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setThematique(questionnaireDto.getThematique());
        Set<PlainCategorieQuestionDto> plainCategorieQuestionDto =questionnaireDto.getCategorieQuestions();
        Set<CategorieQuestion> categorieQuestion = new HashSet<>();



        plainCategorieQuestionDto.forEach(categorieQuestionDto1 -> {
            CategorieQuestion categorieQuestion2 = new CategorieQuestion();
            categorieQuestion2.setLibelle(categorieQuestionDto1.getLibelle());
            categorieQuestion2.setIdCategorie(categorieQuestionDto1.getIdCategorie());
            Set<Question> questions1 = new HashSet<>();
            categorieQuestionDto1.getQuestions().forEach(question -> {
                Question question1 = new Question();
                question1.setTypeQuestion(question.getTypeQuestion());
                question1.setIdQuestion(question.getIdQuestion());
                question1.setScoreMaxPossibleQuestion(question.getScoreMaxPossibleQuestion());
                question1.setLibelleQuestion(question.getLibelleQuestion());
                questions1.add(question1);
            });
            categorieQuestion2.setQuestions(questions1);
            categorieQuestion.add(categorieQuestion2);
        });
        questionnaire.setCategorieQuestions(categorieQuestion);

        Set<PlainPreconisationGlobaleDto> plainPreconisationGlobaleDtos = questionnaireDto.getPreconisationGlobales();
        Set<PreconisationGlobale> preconisationGlobale = new HashSet<>();
        plainPreconisationGlobaleDtos.forEach(preconisationGlobaleDto1 -> {
            PreconisationGlobale preconisationGlobale1 = new PreconisationGlobale();
            preconisationGlobale1.setIdPreconisationG(preconisationGlobaleDto1.getIdPreconisationG());
            preconisationGlobale1.setViewIfPourcentageScoreLessThan(preconisationGlobaleDto1.getViewIfPourcentageScoreLessThan());
            preconisationGlobale1.setContenu(preconisationGlobaleDto1.getContenu());
            preconisationGlobale.add(preconisationGlobale1);
        });
        questionnaire.setPreconisationGlobales(preconisationGlobale);
        return questionnaire;
    }

    public void addCategorieQuestion(CategorieQuestion categorieQuestion){
        this.categorieQuestions.add(categorieQuestion);
    }

}
