package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.dto.PreconisationGlobaleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "preconisation_globale")
public class PreconisationGlobale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preconisation_g")
    private Integer idPreconisationG;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_questionnaire", nullable=false)
    private Questionnaire questionnaire;

    @Column(name = "contenu", columnDefinition="TEXT")
    private String contenu;
    @NotNull
    @Column(name = "viewifpourcentagescorelessthan")
    private Integer viewIfPourcentageScoreLessThan;

    public static PreconisationGlobale from(PreconisationGlobaleDto preconisationGlobaleDto){
        PreconisationGlobale preconisationGlobale = new PreconisationGlobale();
        preconisationGlobale.setViewIfPourcentageScoreLessThan(preconisationGlobaleDto.getViewIfPourcentageScoreLessThan());
        preconisationGlobale.setQuestionnaire(preconisationGlobaleDto.getQuestionnaire());
        preconisationGlobale.setContenu(preconisationGlobaleDto.getContenu());
        return preconisationGlobale;
    }

}
