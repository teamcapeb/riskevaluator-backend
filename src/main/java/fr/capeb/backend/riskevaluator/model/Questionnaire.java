package fr.capeb.backend.riskevaluator.model;

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

    @Column(name = "thematique", nullable=false)
    private String thematique;

    @OrderBy("libelle")
    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CategorieQuestion> categorieQuestions = new HashSet<>();

    @OrderBy("contenu")
    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<PreconisationGlobale> preconisationGlobales = new HashSet<>();

    public static Questionnaire from(QuestionnaireDto questionnaireDto){
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setThematique(questionnaireDto.getThematique());
        questionnaire.setCategorieQuestions(questionnaireDto.getCategorieQuestions());
        questionnaire.setPreconisationGlobales(questionnaireDto.getPreconisationGlobales());
        return questionnaire;
    }

    public void addCategorieQuestion(CategorieQuestion categorieQuestion){
        this.categorieQuestions.add(categorieQuestion);
    }

}
