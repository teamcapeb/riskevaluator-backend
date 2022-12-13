package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.dto.EvaluationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "evaluation")
public class Evaluation {

    @Id
    @Column(name = "id_evaluation")
    private Integer idEvaluation;

    @Column(name = "score_generale")
    private Integer scoreGeneraleEvaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_compte")
    private Compte compte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="nosiret", nullable=false)
    private Entreprise entreprise;

    @OneToMany(mappedBy = "evaluation",cascade = CascadeType.ALL)
    private Set<ScoreCategory> scoreCategories=new HashSet<>();

    public static Evaluation from(EvaluationDto evaluationDto){
        Evaluation evaluationEntity = new Evaluation();
        evaluationEntity.setCompte(evaluationDto.getCompte());
        evaluationEntity.setEntreprise(evaluationDto.getEntreprise());
        evaluationEntity.setScoreGeneraleEvaluation(evaluationDto.getScoreGeneraleEvaluation());
        return evaluationEntity;
    }


}
