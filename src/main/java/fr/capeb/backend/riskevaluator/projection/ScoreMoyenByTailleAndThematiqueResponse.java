package fr.capeb.backend.riskevaluator.projection;
public class ScoreMoyenByTailleAndThematiqueResponse implements ScoreMoyenByTailleAndThematique {
	
	private final String taille;
	
	private final String thematique;
	
	private final Double scoreMoyen;
	
	public ScoreMoyenByTailleAndThematiqueResponse(ScoreMoyenByTailleAndThematique scoreMoyenByTailleAndThematique) {
		this.taille = scoreMoyenByTailleAndThematique.getTaille();
		this.thematique = scoreMoyenByTailleAndThematique.getThematique();
		this.scoreMoyen = scoreMoyenByTailleAndThematique.getScoreMoyen();
	}
	
	@Override
	public String getTaille() {
		return taille;
	}
	
	@Override
	public String getThematique() {
		return thematique;
	}
	
	@Override
	public Double getScoreMoyen() {
		return scoreMoyen;
	}
}
