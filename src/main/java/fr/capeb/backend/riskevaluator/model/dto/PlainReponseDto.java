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
public class PlainReponseDto {
    private Integer idReponse;
    private Integer nbPoints;
    private String contenu;

    public static PlainReponseDto from(Reponse reponse){
        PlainReponseDto plainReponseDto = new PlainReponseDto();
        plainReponseDto.setIdReponse(reponse.getIdReponse());
        plainReponseDto.setContenu(reponse.getContenu());
        plainReponseDto.setNbPoints(reponse.getNbPoints());
        return plainReponseDto;
    }





}
