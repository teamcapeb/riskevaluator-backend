package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.dto.MetierDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "metier")
public class Metier {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_metier")
    private Integer idMetier;


    @Column(name = "nom_metier", nullable = false, unique = true)
    private String nomMetier;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "metier", cascade = CascadeType.ALL)
    private Set<MetierQuestion> questions = new HashSet<>();


    public static Metier from(MetierDto metierDto){
        Metier metier = new Metier();
        metier.setIdMetier(metierDto.getIdMetier());
        metier.setNomMetier(metierDto.getNomMetier());
        metier.setQuestions(metierDto.getQuestions());
        return metier;
    }
}
