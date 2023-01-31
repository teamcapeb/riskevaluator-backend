package fr.capeb.backend.riskevaluator;

import java.io.IOException;
import java.util.HashSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.model.PreconisationCategorie;
import fr.capeb.backend.riskevaluator.model.PreconisationGlobale;
import fr.capeb.backend.riskevaluator.model.Question;
import fr.capeb.backend.riskevaluator.model.Questionnaire;
import fr.capeb.backend.riskevaluator.model.Reponse;
import fr.capeb.backend.riskevaluator.model.ScoreCategory;
import fr.capeb.backend.riskevaluator.model.enumeration.QuestionType;
import fr.capeb.backend.riskevaluator.repository.CategorieQuestionRepository;
import fr.capeb.backend.riskevaluator.repository.EntrepriseRepository;
import fr.capeb.backend.riskevaluator.repository.EvaluationRepository;
import fr.capeb.backend.riskevaluator.repository.MetierRepository;
import fr.capeb.backend.riskevaluator.repository.PreconisationCategorieRepository;
import fr.capeb.backend.riskevaluator.repository.PreconisationGlobaleRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionRepository;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import fr.capeb.backend.riskevaluator.repository.ReponseRepository;
import fr.capeb.backend.riskevaluator.repository.ScoreCategorieRepository;
import fr.capeb.backend.riskevaluator.service.CategorieQuestionService;
import fr.capeb.backend.riskevaluator.service.EntrepriseService;
import fr.capeb.backend.riskevaluator.service.EvaluationService;
import fr.capeb.backend.riskevaluator.service.MetierService;
import fr.capeb.backend.riskevaluator.service.PreconisationCategorieService;
import fr.capeb.backend.riskevaluator.service.PreconisationGlobaleService;
import fr.capeb.backend.riskevaluator.service.QuestionService;
import fr.capeb.backend.riskevaluator.service.QuestionnaireService;
import fr.capeb.backend.riskevaluator.service.ReponseService;
import fr.capeb.backend.riskevaluator.service.ScoreCategorieService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@SpringBootApplication
@OpenAPIDefinition
public class RiskevaluatorApplication {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(RiskevaluatorApplication.class, args);
		ObjectMapper mapper = new ObjectMapper();

		ClassPathResource entrepriseJson = new ClassPathResource("data/entreprise.json");
		String entreprisePath = mapper.readTree(entrepriseJson.getInputStream()).toString();
		JSONArray entrepriseArray = new JSONArray(entreprisePath);

		EntrepriseRepository entrepriseRepository = configurableApplicationContext.getBean(EntrepriseRepository.class);
		EntrepriseService entrepriseService = new EntrepriseService(entrepriseRepository);
		for (Object ent : entrepriseArray) {
			JSONObject entJson = (JSONObject) ent;
			Entreprise entreprise = new Entreprise();
			entreprise.setNoSiret(entJson.getLong("nosiret"));
			entreprise.setAnneeDeCreation(entJson.getInt("anneedecreation"));
			entreprise.setEffectifEntreprise(entJson.getInt("effectif"));
			entreprise.setNomEntreprise(entJson.getString("nom_entreprise"));
			entrepriseRepository.save(entreprise);
		}

		ClassPathResource questionnaireJson = new ClassPathResource("data/questionnaire.json");
		String questionnairePath = mapper.readTree(questionnaireJson.getInputStream()).toString();
		JSONArray questionnaireArray = new JSONArray(questionnairePath);

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

		ClassPathResource categorieQuestionJson = new ClassPathResource("data/categorieQuestion.json");
		String categorieQuestionPath = mapper.readTree(categorieQuestionJson.getInputStream()).toString();
		JSONArray categorieQuestionArray = new JSONArray(categorieQuestionPath);

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

		ClassPathResource evaluationJson = new ClassPathResource("data/evaluation.json");
		String evaluationPath = mapper.readTree(evaluationJson.getInputStream()).toString();
		JSONArray evaluationArray = new JSONArray(evaluationPath);

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

		ClassPathResource metierJson = new ClassPathResource("data/metier.json");
		String metierPath = mapper.readTree(metierJson.getInputStream()).toString();
		JSONArray metierArray = new JSONArray(metierPath);

		MetierRepository metierRepository = configurableApplicationContext.getBean(MetierRepository.class);
		MetierService metierService = new MetierService(metierRepository);
		for (Object met : metierArray) {
			JSONObject metJson = (JSONObject) met;
			Metier metier = new Metier();
			metier.setIdMetier(metJson.getInt("id_metier"));
			metier.setNomMetier(metJson.getString("nom_metier"));
			metierRepository.save(metier);
		}

		ClassPathResource questionJson = new ClassPathResource("data/question.json");
		String questionPath = mapper.readTree(questionJson.getInputStream()).toString();
		JSONArray questionArray = new JSONArray(questionPath);

		QuestionRepository questionRepository = configurableApplicationContext.getBean(QuestionRepository.class);
		QuestionService questionService = new QuestionService(questionRepository);
		for (Object quest : questionArray) {
			JSONObject questJson = (JSONObject) quest;
			Question question = new Question();
			question.setIdQuestion(questJson.getInt("id_question"));
			question.setLibelleQuestion(questJson.getString("libelle_question"));
			question.setScoreMaxPossibleQuestion(questJson.getInt("score_max_possible"));

			if (questJson.getString("q_type") == "QUESTION_CHOIX_MULTIPLE") {
				question.setTypeQuestion(QuestionType.QUESTION_CHOIX_MULTIPLE);
			} else {
				question.setTypeQuestion(QuestionType.QUESTION_CHOIX_UNIQUE);
			}
			question.setCategorieQuestion(categorieQuestionService.getCategorieQuestion(questJson.getInt("id_categorie")));
			questionRepository.save(question);
		}

		ClassPathResource preconisationCategorieJson = new ClassPathResource("data/preconisationCategorie.json");
		String preconisationCategoriePath = mapper.readTree(preconisationCategorieJson.getInputStream()).toString();
		JSONArray preconisationCategorieArray = new JSONArray(preconisationCategoriePath);

		PreconisationCategorieRepository preconisationCategorieRepository = configurableApplicationContext.getBean(
				PreconisationCategorieRepository.class);
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

		ClassPathResource preconisationGlobalJson = new ClassPathResource("data/preconisationGlobal.json");
		String preconisationGlobalPath = mapper.readTree(preconisationGlobalJson.getInputStream()).toString();
		JSONArray preconisationGlobalArray = new JSONArray(preconisationGlobalPath);

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

		ClassPathResource reponseJson = new ClassPathResource("data/reponse.json");
		String reponsePath = mapper.readTree(reponseJson.getInputStream()).toString();
		JSONArray reponseArray = new JSONArray(reponsePath);

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

		ClassPathResource scoreCategoryJson = new ClassPathResource("data/scoreCategory.json");
		String scoreCategoryPath = mapper.readTree(scoreCategoryJson.getInputStream()).toString();
		JSONArray scoreCategorieArray = new JSONArray(scoreCategoryPath);

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

