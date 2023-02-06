package fr.capeb.backend.riskevaluator.service;

import fr.capeb.backend.riskevaluator.model.MetierQuestion;
import fr.capeb.backend.riskevaluator.model.MetierQuestionPK;
import fr.capeb.backend.riskevaluator.model.exception.MetierQuestionNotFoundException;
import fr.capeb.backend.riskevaluator.repository.MetierQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetierQuestionService {
    private final MetierQuestionRepository metierQuestionRepository;

    @Autowired
    public MetierQuestionService(MetierQuestionRepository metierQuestionRepository) {
        this.metierQuestionRepository = metierQuestionRepository;
    }

    public MetierQuestion addMetierQuestion(MetierQuestion metierMetierQuestion){
        return metierQuestionRepository.save(metierMetierQuestion);
    }

    public List<MetierQuestion> getMetierQuestions(){
        return new ArrayList<>(metierQuestionRepository.findAll());
    }

    public MetierQuestion getMetierQuestion(MetierQuestionPK id){
        return metierQuestionRepository.findById(id).orElseThrow(() ->
                new MetierQuestionNotFoundException(id));
    }

    public MetierQuestion deleteMetierQuestion(MetierQuestionPK id){
        MetierQuestion metierMetierQuestion = getMetierQuestion(id);
        metierQuestionRepository.delete(metierMetierQuestion);
        return metierMetierQuestion;
    }

    @Transactional
    public MetierQuestion editMetierQuestion(MetierQuestionPK id, MetierQuestion metierQuestion){
        MetierQuestion metierQuestionToEdit = getMetierQuestion(id);
        metierQuestionToEdit.setMetier(metierQuestion.getMetier());
        metierQuestionToEdit.setQuestion(metierQuestion.getQuestion());
        return metierQuestionRepository.save(metierQuestion);
    }

}
