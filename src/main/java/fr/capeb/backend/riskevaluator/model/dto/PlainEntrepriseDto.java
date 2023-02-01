package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.model.Evaluation;
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
public class PlainEntrepriseDto {
    public Long noSiret;
    public String nomEntreprise;
    public Integer effectifEntreprise;
    public Integer anneeDeCreation;
    private Set<PlainMetierDto> metiers  = new HashSet<>();

    public static PlainEntrepriseDto from(Entreprise entrepriseEntity){
        PlainEntrepriseDto entrepriseDto = new PlainEntrepriseDto();
        entrepriseDto.setNomEntreprise(entrepriseEntity.getNomEntreprise());
        entrepriseDto.setEffectifEntreprise(entrepriseEntity.getEffectifEntreprise());
        entrepriseDto.setNoSiret(entrepriseEntity.getNoSiret());
        entrepriseDto.setAnneeDeCreation(entrepriseEntity.getAnneeDeCreation());

        Set<PlainMetierDto> plainMetiers  = new HashSet<>();
        entrepriseEntity.getMetiers().forEach(metier -> {
            PlainMetierDto plainMetierDto = new PlainMetierDto();
            plainMetierDto.setNomMetier(metier.getNomMetier());
            plainMetierDto.setIdMetier(metier.getIdMetier());
            plainMetiers.add(plainMetierDto);
        });


        entrepriseDto.setMetiers(plainMetiers);
        return entrepriseDto;
    }


}
