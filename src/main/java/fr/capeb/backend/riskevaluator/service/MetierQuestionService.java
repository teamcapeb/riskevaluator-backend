package fr.capeb.backend.riskevaluator.service;

import fr.capeb.backend.riskevaluator.model.MetierQuestion;
import fr.capeb.backend.riskevaluator.model.MetierQuestionPK;
import fr.capeb.backend.riskevaluator.model.dto.MetierQuestionDto;
import fr.capeb.backend.riskevaluator.model.exception.MetierQuestionNotFoundException;
import fr.capeb.backend.riskevaluator.repository.MetierQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MetierQuestionService {
    private final MetierQuestionRepository metierMetierQuestionRepository;

    @Autowired
    public MetierQuestionService(MetierQuestionRepository metierMetierQuestionRepository) {
        this.metierMetierQuestionRepository = metierMetierQuestionRepository;
    }

    public MetierQuestion addMetierQuestion(MetierQuestion metierMetierQuestion){
        return metierMetierQuestionRepository.save(metierMetierQuestion);
    }

    public List<MetierQuestion> getMetierQuestions(){
        return new ArrayList<>(metierMetierQuestionRepository.findAll());
    }

    public MetierQuestion getMetierQuestion(MetierQuestionPK id){
        return metierMetierQuestionRepository.findById(id).orElseThrow(() ->
                new MetierQuestionNotFoundException(id));
    }

    public MetierQuestion deleteMetierQuestion(MetierQuestionPK id){
        MetierQuestion metierMetierQuestion = getMetierQuestion(id);
        metierMetierQuestionRepository.delete(metierMetierQuestion);
        return metierMetierQuestion;
    }

    @Transactional
    public MetierQuestion editMetierQuestion(MetierQuestionPK id, MetierQuestion metierMetierQuestion){
        MetierQuestion metierMetierQuestionToEdit = getMetierQuestion(id);
        metierMetierQuestionToEdit.setMetier(metierMetierQuestion.getMetier());
        metierMetierQuestionToEdit.setQuestion(metierMetierQuestion.getQuestion());
        return metierMetierQuestionToEdit;
    }

}
