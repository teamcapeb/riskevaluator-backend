package fr.capeb.backend.riskevaluator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.projection.ScoreMetierEntreprise;
import fr.capeb.backend.riskevaluator.projection.ScoreMoyenByTailleAndThematique;
import fr.capeb.backend.riskevaluator.projection.ScoreMoyenEntreprise;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
	
	@Query(value = "SELECT taille, thematique, AVG(score_moyen) as scoreMoyen " +
			"FROM ( " +
			"  SELECT nom_entreprise, taille, thematique, AVG(score_generale) as score_moyen " +
			"  FROM ( " +
			"    SELECT *, row_number() over (partition by nom_entreprise, nom_metier, thematique ORDER BY id_evaluation desc) as rn " +
			"    FROM ( " +
			"      SELECT distinct ev.id_evaluation, ent.nom_entreprise, " +
			"             CASE " +
			"                 WHEN ent.effectif BETWEEN 0 AND 5 THEN 'Petite' " +
			"                 WHEN ent.effectif BETWEEN 6 AND 10 THEN 'Moyenne' " +
			"                 WHEN ent.effectif > 10 THEN 'Grande' " +
			"             END AS taille, " +
			"             met.nom_metier, q.thematique, ev.score_generale " +
			"      FROM entreprise ent " +
			"      JOIN evaluation ev ON ent.nosiret = ev.nosiret " +
			"      JOIN evaluations_metiers em ON em.evaluation_id = ev.id_evaluation " +
			"      JOIN metier met ON met.id_metier = em.metier_id " +
			"      JOIN score_category sc ON sc.id_evaluation = ev.id_evaluation " +
			"      JOIN categorie_question cq ON cq.id_categorie = sc.id_categorie " +
			"      JOIN questionnaire q ON q.id_questionnaire = cq.id_questionnaire " +
			"    ) t1 " +
			"  ) t2 " +
			"  WHERE rn = 1 " +
			"  GROUP BY nom_entreprise, taille, thematique " +
			"  ORDER BY nom_entreprise " +
			") t3 " +
			"GROUP BY taille, thematique", nativeQuery = true)
	List<ScoreMoyenByTailleAndThematique> findScoreMoyenByTailleAndThematique();
	
	
	
	@Query(value = "select id_evaluation as idEvaluation, nom_entreprise as nomEntreprise, taille, nom_metier as nomMetier, thematique, sg as scoreGenerale from (select *, row_number() over (partition by nom_entreprise, nom_metier, thematique order by id_evaluation desc) as rn from (select distinct ev.id_evaluation, ent.nom_entreprise, ev.score_generale as sg, case when ent.effectif between 0 and 5 then 'Petite' when ent.effectif between 6 and 10 then 'Moyenne' when ent.effectif > 10 then 'Grande' end as taille, met.nom_metier, q.thematique, ev.score_generale from entreprise ent join evaluation ev on ent.nosiret = ev.nosiret join evaluations_metiers em on em.evaluation_id = ev.id_evaluation join metier met on met.id_metier = em.metier_id join score_category sc on sc.id_evaluation = ev.id_evaluation join categorie_question cq on cq.id_categorie = sc.id_categorie join questionnaire q on q.id_questionnaire = cq.id_questionnaire) t1) t2 where rn = 1 group by t2.id_evaluation, nom_entreprise, taille, thematique, nom_metier, sg order by nom_entreprise", nativeQuery = true)
	
	List<ScoreMetierEntreprise> findScoreParMetier();
	
	@Query(value =
			"  SELECT nom_entreprise as nomEntreprise, taille , thematique, AVG(score_generale) as scoreMoyen " +
					"  FROM ( " +
					"    SELECT *, row_number() over (partition by nom_entreprise, nom_metier, thematique ORDER BY id_evaluation desc) as rn " +
					"    FROM ( " +
					"      SELECT distinct ev.id_evaluation, ent.nom_entreprise, " +
					"             CASE " +
					"                 WHEN ent.effectif BETWEEN 0 AND 5 THEN 'Petite' " +
					"                 WHEN ent.effectif BETWEEN 6 AND 10 THEN 'Moyenne' " +
					"                 WHEN ent.effectif > 10 THEN 'Grande' " +
					"             END AS taille, " +
					"             met.nom_metier, q.thematique, ev.score_generale " +
					"      FROM entreprise ent " +
					"      JOIN evaluation ev ON ent.nosiret = ev.nosiret " +
					"      JOIN evaluations_metiers em ON em.evaluation_id = ev.id_evaluation " +
					"      JOIN metier met ON met.id_metier = em.metier_id " +
					"      JOIN score_category sc ON sc.id_evaluation = ev.id_evaluation " +
					"      JOIN categorie_question cq ON cq.id_categorie = sc.id_categorie " +
					"      JOIN questionnaire q ON q.id_questionnaire = cq.id_questionnaire " +
					"    ) t1 " +
					"  ) t2 " +
					"  WHERE rn = 1 " +
					"  GROUP BY nom_entreprise, taille, thematique " +
					"  ORDER BY nom_entreprise", nativeQuery = true)
	List<ScoreMoyenEntreprise> findScoreMoyen();
	
}
