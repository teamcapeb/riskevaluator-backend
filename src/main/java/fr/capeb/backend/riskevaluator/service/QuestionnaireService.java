package fr.capeb.backend.riskevaluator.service;

import fr.capeb.backend.riskevaluator.model.Questionnaire;
import fr.capeb.backend.riskevaluator.model.dto.QuestionnaireDto;
import fr.capeb.backend.riskevaluator.model.exception.QuestionnaireNotFoundException;
import fr.capeb.backend.riskevaluator.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class QuestionnaireService {
    private final QuestionnaireRepository questionnaireRepository;

    @Autowired
    public QuestionnaireService(QuestionnaireRepository questionnaireRepository) {
        this.questionnaireRepository = questionnaireRepository;
    }

    public Questionnaire addQuestionnaire(Questionnaire questionnaire){
        return questionnaireRepository.save(questionnaire);
    }

    public List<Questionnaire> getQuestionnaires(){
        return new ArrayList<>(questionnaireRepository.findAll());
    }

    public Questionnaire getQuestionnaire(Integer id){
        return questionnaireRepository.findById(id).orElseThrow(() ->
                new QuestionnaireNotFoundException(id));
    }

    public Questionnaire deleteQuestionnaire(Integer id){
        Questionnaire questionnaire = getQuestionnaire(id);
        questionnaireRepository.delete(questionnaire);
        return questionnaire;
    }

    public List<Questionnaire> byMetierIds(Set<Integer> metierId ) {
        return questionnaireRepository.getQuestionnaireByMetiersIds(metierId);
    }
    
    @Transactional
    public Questionnaire editQuestionnaire(Integer id, Questionnaire questionnaire){
        Questionnaire questionnaireToEdit = getQuestionnaire(id);
        questionnaireToEdit.setThematique(questionnaire.getThematique());
        questionnaireToEdit.setCategorieQuestions(questionnaire.getCategorieQuestions());
        questionnaireToEdit.setPreconisationGlobales(questionnaire.getPreconisationGlobales());
        return questionnaireToEdit;
    }

}
