package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Question;
import fr.capeb.backend.riskevaluator.model.Reponse;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ReponseDto {
    private Integer idReponse;
    private Question question;
    private Integer nbPoints;
    private String contenu;

    public static ReponseDto from(Reponse reponse){
        ReponseDto reponseDto = new ReponseDto();
        reponseDto.setIdReponse(reponse.getIdReponse());
        reponseDto.setContenu(reponse.getContenu());
        reponseDto.setQuestion(reponse.getQuestion());
        reponseDto.setNbPoints(reponse.getNbPoints());
        return reponseDto;
    }





}
