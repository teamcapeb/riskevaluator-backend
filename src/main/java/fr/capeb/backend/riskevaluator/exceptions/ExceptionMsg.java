package fr.capeb.backend.riskevaluator.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionMsg {
	ID_NOT_FOUND("the id was not found, make sure your It's ID existe in database"),
	ID_CONFLICT("the id already existe in database, please use PUT METHOD to update the data");
	
	public String value;
	
}
