package fr.capeb.backend.riskevaluator.projection;
public class MetierScoreResponse implements MetierScoreProjection {
	
	private final String nomMetier;
	
	private final String thematique;
	
	private final double scoreMoyen;
	
	public MetierScoreResponse(MetierScoreProjection metierScoreProjection) {
		this.nomMetier = metierScoreProjection.getNomMetier();
		this.scoreMoyen = metierScoreProjection.getScoreMoyen();
		this.thematique = metierScoreProjection.getThematique();
	}
	
	@Override
	public String getNomMetier() {
		return nomMetier;
	}
	
	@Override
	public String getThematique() {
		return thematique;
	}
	
	@Override
	public double getScoreMoyen() {
		return scoreMoyen;
	}
}
