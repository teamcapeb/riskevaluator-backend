package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.model.MetierQuestion;
import fr.capeb.backend.riskevaluator.model.MetierQuestionPK;
import fr.capeb.backend.riskevaluator.model.Question;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Data
@Getter
@Setter
@NoArgsConstructor
public class MetierQuestionDto {

    private MetierQuestionPK key = new MetierQuestionPK();
    private Question question;
    private Metier metier;

    public static MetierQuestionDto from(MetierQuestion metierQuestion){
        MetierQuestionDto metierQuestionDto = new MetierQuestionDto();
        metierQuestionDto.setMetier(metierQuestion.getMetier());
        metierQuestionDto.setQuestion(metierQuestion.getQuestion());
        metierQuestionDto.setKey(metierQuestion.getKey());
        return metierQuestionDto;
    }

}
