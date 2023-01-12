
package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.dto.PlainCategorieQuestionDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainMetierDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainReponseDto;
import fr.capeb.backend.riskevaluator.model.dto.QuestionDto;
import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "question")
public class Question implements Serializable {
    @Id
    @Column(name = "id_question")
    private Integer idQuestion;

    @Enumerated(EnumType.STRING)
    @Column(name = "q_type")
    private QuestionType typeQuestion;

    @Column(name = "score_max_possible", columnDefinition = "integer default 0")
    private Integer scoreMaxPossibleQuestion;

    @NotNull
    @Column(name = "libelle_question",  columnDefinition="TEXT")
    private String libelleQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_categorie", nullable=false)
    private CategorieQuestion categorieQuestion;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MetierQuestion> metiers = new LinkedHashSet<>();

    @OrderBy("contenu")
    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Reponse> reponses= new HashSet<>();

    public static Question from(QuestionDto questionDto){
        Question question = new Question();
        question.setLibelleQuestion(questionDto.getLibelleQuestion());
        CategorieQuestion categorieQuestion1 = new CategorieQuestion();
        PlainCategorieQuestionDto plainCategorieQuestionDto = questionDto.getCategorieQuestion();
        categorieQuestion1.setLibelle(plainCategorieQuestionDto.getLibelle());
        categorieQuestion1.setIdCategorie(plainCategorieQuestionDto.getIdCategorie());
        question.setCategorieQuestion(categorieQuestion1);
        question.setScoreMaxPossibleQuestion(questionDto.getScoreMaxPossibleQuestion());
        question.setTypeQuestion(questionDto.getTypeQuestion());
        question.setMetiers(questionDto.getMetiers());
        Set<Reponse> reponses1 = new HashSet<>();
        Set<PlainReponseDto> plainReponseDto = questionDto.getReponses();
        plainReponseDto.forEach(plainReponseDto1 -> {
            Reponse reponse1 = new Reponse();
            reponse1.setIdReponse(plainReponseDto1.getIdReponse());
            reponse1.setContenu(plainReponseDto1.getContenu());
            reponse1.setNbPoints(plainReponseDto1.getNbPoints());
            reponses1.add(reponse1);
        });

        question.setReponses(reponses1);
        return question;
    }


}
