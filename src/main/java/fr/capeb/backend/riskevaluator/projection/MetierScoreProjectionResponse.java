package fr.capeb.backend.riskevaluator.projection;
public class MetierScoreProjectionResponse implements MetierScoreProjection {
	
	private final String nomMetier;
	
	private final double scoreMoyen;
	
	public MetierScoreProjectionResponse(MetierScoreProjection metierScoreProjection) {
		this.nomMetier = metierScoreProjection.getNomMetier();
		this.scoreMoyen = metierScoreProjection.getScoreMoyen();
	}
	
	@Override
	public String getNomMetier() {
		return nomMetier;
	}
	
	@Override
	public double getScoreMoyen() {
		return scoreMoyen;
	}
}