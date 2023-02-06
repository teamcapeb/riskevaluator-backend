package fr.capeb.backend.riskevaluator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ScoreCategoryPK implements Serializable {
	
	@Column(name = "id_evaluation")
	private Integer idEvaluation;
	
	@Column(name = "id_categorie")
	private Integer idCategorie;
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {return true;}
		if (o == null || getClass() != o.getClass()) {return false;}
		ScoreCategoryPK that = (ScoreCategoryPK) o;
		return idEvaluation.equals(that.idEvaluation) && idCategorie.equals(that.idCategorie);
	}
	
}
