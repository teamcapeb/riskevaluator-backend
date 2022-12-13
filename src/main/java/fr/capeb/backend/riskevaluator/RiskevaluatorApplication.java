package fr.capeb.backend.riskevaluator;

import fr.capeb.backend.riskevaluator.model.*;
import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import fr.capeb.backend.riskevaluator.repository.*;
import fr.capeb.backend.riskevaluator.service.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import org.json.JSONArray;
import org.json.JSONObject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
@OpenAPIDefinition
public class RiskevaluatorApplication {

	public static void main(String[] args) throws FileNotFoundException {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(RiskevaluatorApplication.class, args);

		String EntreprisePath = "C:\\Users\\eddy-\\Desktop\\Cours\\capeb\\riskevaluator-backend-master\\src\\main\\java\\fr\\capeb\\backend\\riskevaluator\\jsonData\\entreprise.json";
		String EntrepriseJson = new Scanner(new File(EntreprisePath)).useDelimiter("\\Z").next();
		JSONArray EntrepriseArray = new JSONArray(EntrepriseJson);
		EntrepriseRepository entrepriseRepository = configurableApplicationContext.getBean(EntrepriseRepository.class);
		EntrepriseService entrepriseService = new EntrepriseService(entrepriseRepository);
		for (Object ent : EntrepriseArray) {
			JSONObject entJson = (JSONObject) ent;
			Entreprise entreprise = new Entreprise();
			entreprise.setNoSiret(entJson.getLong("nosiret"));
			entreprise.setAnneeDeCreation(entJson.getInt("anneedecreation"));
			entreprise.setEffectifEntreprise(entJson.getInt("effectif"));
			entreprise.setNomEntreprise(entJson.getString("nom_entreprise"));
			entrepriseRepository.save(entreprise);
		}

		String QuestionnairePath = "C:\\Users\\eddy-\\Desktop\\Cours\\capeb\\riskevaluator-backend-master\\src\\main\\java\\fr\\capeb\\backend\\riskevaluator\\jsonData\\questionnaire.json";
		String questionnaireJson = new Scanner(new File(QuestionnairePath)).useDelimiter("\\Z").next();
		JSONArray questionnaireArray = new JSONArray(questionnaireJson);
		QuestionnaireRepository questionnaireRepository = configurableApplicationContext.getBean(QuestionnaireRepository.class);
		QuestionnaireService questionnaireService = new QuestionnaireService(questionnaireRepository);
		for (Object question : questionnaireArray) {
			JSONObject questionJson = (JSONObject) question;
			Questionnaire questionnaire = new Questionnaire();
			questionnaire.setThematique(questionJson.getString("thematique"));
			questionnaire.setIdQuestionnaire(questionJson.getInt("id_questionnaire"));
			questionnaire.setCategorieQuestions(new HashSet<CategorieQuestion>());
			questionnaireRepository.save(questionnaire);
		}


		String CategorieQuestionPath = "C:\\Users\\eddy-\\Desktop\\Cours\\capeb\\riskevaluator-backend-master\\src\\main\\java\\fr\\capeb\\backend\\riskevaluator\\jsonData\\categorieQuestion.json";
		String categorieQuestionJson = new Scanner(new File(CategorieQuestionPath)).useDelimiter("\\Z").next();
		JSONArray categorieQuestionArray = new JSONArray(categorieQuestionJson);
		CategorieQuestionRepository categorieQuestionRepository = configurableApplicationContext.getBean(CategorieQuestionRepository.class);
		CategorieQuestionService categorieQuestionService = new CategorieQuestionService(categorieQuestionRepository);
		for (Object cg : categorieQuestionArray) {
			JSONObject cgJson = (JSONObject) cg;
			CategorieQuestion categorieQuestion = new CategorieQuestion();
			categorieQuestion.setIdCategorie(cgJson.getInt("id_categorie"));
			categorieQuestion.setLibelle(cgJson.getString("libelle"));
			Questionnaire questionnaire = questionnaireService.getQuestionnaire(cgJson.getInt("id_questionnaire"));
			questionnaire.addCategorieQuestion(categorieQuestion);
			categorieQuestion.setQuestionnaire(questionnaire);
			categorieQuestionRepository.save(categorieQuestion);
		}

		String EvaluationPath = "C:\\Users\\eddy-\\Desktop\\Cours\\capeb\\riskevaluator-backend-master\\src\\main\\java\\fr\\capeb\\backend\\riskevaluator\\jsonData\\evaluation.json";
		String evaluationJson = new Scanner(new File(EvaluationPath)).useDelimiter("\\Z").next();
		JSONArray evaluationArray = new JSONArray(evaluationJson);
		EvaluationRepository evaluationRepository = configurableApplicationContext.getBean(EvaluationRepository.class);
		EvaluationService evaluationService = new EvaluationService(evaluationRepository);
		for (Object eval : evaluationArray) {
			JSONObject evalJson = (JSONObject) eval;
			Evaluation evaluation = new Evaluation();
			evaluation.setIdEvaluation(evalJson.getInt("id_evaluation"));
			evaluation.setScoreGeneraleEvaluation(evalJson.getInt("score_generale"));
			evaluation.setEntreprise(entrepriseService.getEntreprise(evalJson.getLong("nosiret")));
			evaluationRepository.save(evaluation);
		}

		String MetierPath = "C:\\Users\\eddy-\\Desktop\\Cours\\capeb\\riskevaluator-backend-master\\src\\main\\java\\fr\\capeb\\backend\\riskevaluator\\jsonData\\metier.json";
		String metierJson = new Scanner(new File(MetierPath)).useDelimiter("\\Z").next();
		JSONArray metierArray = new JSONArray(metierJson);
		MetierRepository metierRepository = configurableApplicationContext.getBean(MetierRepository.class);
		MetierService metierService = new MetierService(metierRepository);
		for (Object met : metierArray) {
			JSONObject metJson = (JSONObject) met;
			Metier metier = new Metier();
			metier.setIdMetier(metJson.getInt("id_metier"));
			metier.setNomMetier(metJson.getString("nom_metier"));
			metierRepository.save(metier);
		}

		String QuestionPath = "C:\\Users\\eddy-\\Desktop\\Cours\\capeb\\riskevaluator-backend-master\\src\\main\\java\\fr\\capeb\\backend\\riskevaluator\\jsonData\\question.json";
		String questionJson = new Scanner(new File(QuestionPath)).useDelimiter("\\Z").next();
		JSONArray questionArray = new JSONArray(questionJson);
		QuestionRepository questionRepository = configurableApplicationContext.getBean(QuestionRepository.class);
		QuestionService questionService = new QuestionService(questionRepository);
		for (Object quest : questionArray) {
			JSONObject questJson = (JSONObject) quest;
			Question question = new Question();
			question.setIdQuestion(questJson.getInt("id_question"));
			question.setLibelleQuestion(questJson.getString("libelle_question"));
			question.setScoreMaxPossibleQuestion(questJson.getInt("score_max_possible"));

			if(questJson.getString("q_type") == "QUESTION_CHOIX_MULTIPLE"){
				question.setTypeQuestion(QuestionType.QUESTION_CHOIX_MULTIPLE);
			}else{
				question.setTypeQuestion(QuestionType.QUESTION_CHOIX_UNIQUE);
			}
			question.setCategorieQuestion(categorieQuestionService.getCategorieQuestion(questJson.getInt("id_categorie")));
			questionRepository.save(question);
		}

		String PreconisationCategoriePath = "C:\\Users\\eddy-\\Desktop\\Cours\\capeb\\riskevaluator-backend-master\\src\\main\\java\\fr\\capeb\\backend\\riskevaluator\\jsonData\\preconisationCategorie.json";
		String preconisationCategorieJson = new Scanner(new File(PreconisationCategoriePath)).useDelimiter("\\Z").next();
		JSONArray preconisationCategorieArray = new JSONArray(preconisationCategorieJson);
		PreconisationCategorieRepository preconisationCategorieRepository = configurableApplicationContext.getBean(PreconisationCategorieRepository.class);
		PreconisationCategorieService preconisationCategorieService = new PreconisationCategorieService(preconisationCategorieRepository);
		for (Object preco : preconisationCategorieArray) {
			JSONObject precoJson = (JSONObject) preco;
			PreconisationCategorie preconisationCategorie = new PreconisationCategorie();
			preconisationCategorie.setCategorieQuestion(categorieQuestionService.getCategorieQuestion(precoJson.getInt("id_categorie")));
			preconisationCategorie.setIdPreconisation(precoJson.getInt("id_preconisation"));
			preconisationCategorie.setContenu(precoJson.getString("contenu"));
			preconisationCategorie.setViewIfPourcentageScoreLessThan(precoJson.getInt("viewifpourcentagescorelessthan"));
			preconisationCategorieRepository.save(preconisationCategorie);
		}

		String PreconisationGlobalPath = "C:\\Users\\eddy-\\Desktop\\Cours\\capeb\\riskevaluator-backend-master\\src\\main\\java\\fr\\capeb\\backend\\riskevaluator\\jsonData\\preconisationGlobal.json";
		String preconisationGlobalJson = new Scanner(new File(PreconisationGlobalPath)).useDelimiter("\\Z").next();
		JSONArray preconisationGlobalArray = new JSONArray(preconisationGlobalJson);
		PreconisationGlobaleRepository preconisationGlobalRepository = configurableApplicationContext.getBean(PreconisationGlobaleRepository.class);
		PreconisationGlobaleService preconisationGlobalService = new PreconisationGlobaleService(preconisationGlobalRepository);
		for (Object preco : preconisationGlobalArray) {
			JSONObject precoJson = (JSONObject) preco;
			PreconisationGlobale preconisationGlobal = new PreconisationGlobale();
			preconisationGlobal.setQuestionnaire(questionnaireService.getQuestionnaire(precoJson.getInt("id_questionnaire")));
			preconisationGlobal.setIdPreconisationG(precoJson.getInt("id_preconisation_g"));
			preconisationGlobal.setContenu(precoJson.getString("contenu"));
			preconisationGlobal.setViewIfPourcentageScoreLessThan(precoJson.getInt("viewifpourcentagescorelessthan"));
			preconisationGlobalRepository.save(preconisationGlobal);
		}


		String ReponsePath = "C:\\Users\\eddy-\\Desktop\\Cours\\capeb\\riskevaluator-backend-master\\src\\main\\java\\fr\\capeb\\backend\\riskevaluator\\jsonData\\reponse.json";
		String reponseJson = new Scanner(new File(ReponsePath)).useDelimiter("\\Z").next();
		JSONArray reponseArray = new JSONArray(reponseJson);
		ReponseRepository reponseRepository = configurableApplicationContext.getBean(ReponseRepository.class);
		ReponseService reponseService = new ReponseService(reponseRepository);
		for (Object met : reponseArray) {
			JSONObject metJson = (JSONObject) met;
			Reponse reponse = new Reponse();
			reponse.setIdReponse(metJson.getInt("id_reponse"));
			reponse.setContenu(metJson.getString("contenu"));
			reponse.setNbPoints(metJson.getInt("nb_points"));
			reponse.setQuestion(questionService.getQuestion(metJson.getInt("id_question")));
			reponseRepository.save(reponse);
		}


		String ScoreCategoriePath = "C:\\Users\\eddy-\\Desktop\\Cours\\capeb\\riskevaluator-backend-master\\src\\main\\java\\fr\\capeb\\backend\\riskevaluator\\jsonData\\scoreCategory.json";
		String scoreCategorieJson = new Scanner(new File(ScoreCategoriePath)).useDelimiter("\\Z").next();
		JSONArray scoreCategorieArray = new JSONArray(scoreCategorieJson);
		ScoreCategorieRepository scoreCategorieRepository = configurableApplicationContext.getBean(ScoreCategorieRepository.class);
		ScoreCategorieService scoreCategorieService = new ScoreCategorieService(scoreCategorieRepository);
		for (Object met : scoreCategorieArray) {
			JSONObject metJson = (JSONObject) met;
			ScoreCategory scoreCategorie = new ScoreCategory();
			scoreCategorie.setNbPoints(metJson.getInt("nb_points"));
			scoreCategorie.setEvaluation(evaluationService.getEvaluation(metJson.getInt("id_evaluation")));
			scoreCategorie.setCategorieQuestion(categorieQuestionService.getCategorieQuestion(metJson.getInt("id_categorie")));
			scoreCategorie.setNbPoints(metJson.getInt("nb_points"));
			scoreCategorieRepository.save(scoreCategorie);
		}



	}

}

