package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.dto.MetierQuestionDto;
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
    private Question question;


    @MapsId(value = "metierId")
    @ManyToOne
    @JoinColumn(name = "id_metier", referencedColumnName = "id_metier")
    private Metier metier;

    public static MetierQuestion from(MetierQuestionDto metierQuestionDto){
        MetierQuestion metierQuestion = new MetierQuestion();
        metierQuestion.setMetier(metierQuestionDto.getMetier());
        metierQuestion.setQuestion(metierQuestionDto.getQuestion());
        metierQuestion.setKey(metierQuestionDto.getKey());
        return metierQuestion;
    }

}
