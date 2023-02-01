package fr.capeb.backend.riskevaluator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.capeb.backend.riskevaluator.model.dto.MetierQuestionDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainMetierDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainQuestionDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "metier_question")
public class MetierQuestion {

    @EmbeddedId
    private MetierQuestionPK key = new MetierQuestionPK();

    @MapsId(value = "questionId")
    @ManyToOne
    @JoinColumn(name = "id_question", referencedColumnName = "id_question")
    @JsonIgnore
    private Question question;


    @MapsId(value = "metierId")
    @ManyToOne
    @JoinColumn(name = "id_metier", referencedColumnName = "id_metier")
    @JsonIgnore
    private Metier metier;

    public static MetierQuestion from(MetierQuestionDto metierQuestionDto){
        MetierQuestion metierQuestion = new MetierQuestion();
        PlainMetierDto plainMetierDto = metierQuestionDto.getMetier();
        Metier metier1 = new Metier();
        metier1.setNomMetier(plainMetierDto.getNomMetier());
        metier1.setIdMetier(plainMetierDto.getIdMetier());
        metierQuestion.setMetier(metier1);
        Question question1 = new Question();
        PlainQuestionDto plainQuestionDto = metierQuestionDto.getQuestionDto();
        question1.setLibelleQuestion(plainQuestionDto.getLibelleQuestion());
        question1.setTypeQuestion(plainQuestionDto.getTypeQuestion());
        question1.setIdQuestion(plainQuestionDto.getIdQuestion());
        question1.setScoreMaxPossibleQuestion(plainQuestionDto.getScoreMaxPossibleQuestion());
        metierQuestion.setQuestion(question1);
        metierQuestion.setKey(metierQuestionDto.getKey());
        return metierQuestion;
    }

}
