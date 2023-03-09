package fr.capeb.backend.riskevaluator.projection;
public interface ScoreMetierEntreprise {
	Integer getIdEvaluation();
	
	String getNomEntreprise();
	
	String getTaille();
	
	String getNomMetier();
	
	String getThematique();
	
	double getScoreGenerale();
}
