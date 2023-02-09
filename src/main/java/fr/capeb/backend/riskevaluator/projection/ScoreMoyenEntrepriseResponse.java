package fr.capeb.backend.riskevaluator.projection;
public class ScoreMoyenEntrepriseResponse {
	
	private String nomEntreprise;
	
	private String thematique;
	
	private double scoreMoyen;
	
	public ScoreMoyenEntrepriseResponse(ScoreMoyenEntreprise scoreMoyenEntreprise) {
		this.thematique = scoreMoyenEntreprise.getThematique();
		this.scoreMoyen = scoreMoyenEntreprise.getScoreMoyen();
		this.nomEntreprise = scoreMoyenEntreprise.getNomEntreprise();
	}
}
