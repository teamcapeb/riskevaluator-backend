package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.dto.CategorieQuestionDto;
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
@Table(name = "categorie_question")
public class CategorieQuestion {
    @Id
    @Column(name = "id_categorie")
    public Integer idCategorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_questionnaire", nullable=false)
    public Questionnaire questionnaire;

    @Column(name = "libelle", nullable=false)
    public String libelle;


    @OneToMany(mappedBy = "categorieQuestion",cascade = CascadeType.ALL)
    private Set<ScoreCategory> scoreEvaluations=new HashSet<>();

    @OrderBy("contenu")
    @OneToMany(mappedBy = "categorieQuestion",cascade = CascadeType.ALL)
    private Set<PreconisationCategorie> preconisationsCategorie= new HashSet<>();

    @OrderBy("libelleQuestion")
    @OneToMany(mappedBy = "categorieQuestion", cascade = CascadeType.ALL)
    private Set<Question> questions= new HashSet<>();

    public static CategorieQuestion from(CategorieQuestionDto categorieQuestionDto){
        CategorieQuestion categorieQuestion = new CategorieQuestion();
        categorieQuestion.setQuestions(categorieQuestionDto.getQuestions());
        categorieQuestion.setPreconisationsCategorie(categorieQuestionDto.getPreconisationsCategorie());
        categorieQuestion.setLibelle(categorieQuestionDto.getLibelle());
        categorieQuestion.setQuestionnaire(categorieQuestionDto.getQuestionnaire());
        categorieQuestion.setScoreEvaluations(categorieQuestion.getScoreEvaluations());
        return categorieQuestion;
    }


}
