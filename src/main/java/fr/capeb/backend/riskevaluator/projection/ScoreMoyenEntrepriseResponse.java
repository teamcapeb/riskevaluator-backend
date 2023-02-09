package fr.capeb.backend.riskevaluator.projection;
public class ScoreMoyenEntrepriseResponse implements ScoreMoyenEntreprise {
	
	private String nomEntreprise;
	
	private String taille;
	
	private String thematique;
	
	private double scoreMoyen;
	
	public ScoreMoyenEntrepriseResponse(ScoreMoyenEntreprise scoreMoyenEntreprise) {
		this.thematique = scoreMoyenEntreprise.getThematique();
		this.scoreMoyen = scoreMoyenEntreprise.getScoreMoyen();
		this.nomEntreprise = scoreMoyenEntreprise.getNomEntreprise();
		this.taille = scoreMoyenEntreprise.getTaille();
	}
	
	@Override
	public String getNomEntreprise() {
		return nomEntreprise;
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
	public double getScoreMoyen() {
		return scoreMoyen;
	}
}
