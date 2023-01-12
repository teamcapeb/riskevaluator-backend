package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.model.MetierQuestion;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlainMetierDto {
    private Integer idMetier;
    private String nomMetier;

    public static PlainMetierDto from(Metier metier){
        PlainMetierDto metierDto = new PlainMetierDto();
        metierDto.setIdMetier(metier.getIdMetier());
        metierDto.setNomMetier(metier.getNomMetier());
        return metierDto;
    }
}
