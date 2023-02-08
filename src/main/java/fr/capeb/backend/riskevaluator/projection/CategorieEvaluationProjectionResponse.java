package fr.capeb.backend.riskevaluator.projection;
public class CategorieEvaluationProjectionResponse implements CategorieEvaluationProjection {
	
	private final String libelle;
	
	private final String thematique;
	private final int count;
	
	@Override
	public String getLibelle() {
		return libelle;
	}
	
	@Override
	public String getThematique() {
		return thematique;
	}
	
	@Override
	public int getCount() {
		return count;
	}
	
	public CategorieEvaluationProjectionResponse(CategorieEvaluationProjection categorieEvaluationProjection) {
		this.count = categorieEvaluationProjection.getCount();
		this.libelle = categorieEvaluationProjection.getLibelle();
		this.thematique = categorieEvaluationProjection.getThematique();
	}
}
