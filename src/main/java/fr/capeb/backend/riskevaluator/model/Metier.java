package fr.capeb.backend.riskevaluator.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.capeb.backend.riskevaluator.model.dto.MetierDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	@ManyToMany(mappedBy = "metiers")
	private Set<Evaluation> evaluations = new HashSet<>();
	

	@ManyToMany(mappedBy = "metiers")
	private Set<Entreprise> entreprises = new HashSet<>();
	
	public static Metier from(MetierDto metierDto) {
		Metier metier = new Metier();
		metier.setIdMetier(metierDto.getIdMetier());
		metier.setNomMetier(metierDto.getNomMetier());
		metier.setQuestions(metierDto.getQuestions());
		return metier;
	}
	
	
	
}
