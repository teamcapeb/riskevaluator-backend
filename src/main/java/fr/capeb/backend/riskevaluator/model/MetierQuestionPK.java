package fr.capeb.backend.riskevaluator.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class MetierQuestionPK implements Serializable {
	
	@Column(name = "id_question")
	private Integer questionId;
	
	@Column(name = "id_metier")
	private Integer metierId;
	
	public MetierQuestionPK(Integer questionId, Integer metierId) {
		this.questionId = questionId;
		this.metierId = metierId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {return true;}
		if (o == null || getClass() != o.getClass()) {return false;}
		MetierQuestionPK that = (MetierQuestionPK) o;
		return questionId.equals(that.questionId) && metierId.equals(that.metierId);
	}
	
}
