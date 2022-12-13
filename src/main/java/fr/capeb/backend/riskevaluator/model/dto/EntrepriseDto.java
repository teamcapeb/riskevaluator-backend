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
public class EntrepriseDto {
    public Long noSiret;
    public String nomEntreprise;
    public Integer effectifEntreprise;
    public Integer anneeDeCreation;
    private Set<Evaluation> evaluations=new HashSet<>();

    public static EntrepriseDto from(Entreprise entrepriseEntity){
        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setNomEntreprise(entrepriseEntity.getNomEntreprise());
        entrepriseDto.setEffectifEntreprise(entrepriseEntity.getEffectifEntreprise());
        entrepriseDto.setNoSiret(entrepriseEntity.getNoSiret());
        entrepriseDto.setEvaluations(entrepriseEntity.getEvaluations());
        entrepriseDto.setAnneeDeCreation(entrepriseEntity.getAnneeDeCreation());
        return entrepriseDto;
    }


}
