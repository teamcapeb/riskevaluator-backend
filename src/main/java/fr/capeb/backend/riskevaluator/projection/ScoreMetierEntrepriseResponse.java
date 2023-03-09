package fr.capeb.backend.riskevaluator.projection;
public class ScoreMetierEntrepriseResponse implements ScoreMetierEntreprise {
	
	private Integer idEvaluation;
	
	private String nomEntreprise;
	
	private String taille;
	
	private String nomMetier;
	
	private String thematique;
	
	private double score;
	
	public ScoreMetierEntrepriseResponse(ScoreMetierEntreprise scoreMetierEntreprise) {
		this.idEvaluation = scoreMetierEntreprise.getIdEvaluation();
		this.thematique = scoreMetierEntreprise.getThematique();
		this.score = scoreMetierEntreprise.getScoreGenerale();
		this.nomEntreprise = scoreMetierEntreprise.getNomEntreprise();
		this.taille = scoreMetierEntreprise.getTaille();
		this.nomMetier = scoreMetierEntreprise.getNomMetier();
	}
	
	@Override
	public Integer getIdEvaluation() {
		return idEvaluation;
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
	public String getNomMetier() {
		return nomMetier;
	}
	
	@Override
	public String getThematique() {
		return thematique;
	}
	
	@Override
	public double getScoreGenerale() {
		return score;
	}
}
