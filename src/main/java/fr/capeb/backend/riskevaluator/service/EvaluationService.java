package fr.capeb.backend.riskevaluator.service;

import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.model.dto.EvaluationDto;
import fr.capeb.backend.riskevaluator.model.exception.EvaluationNotFoundException;
import fr.capeb.backend.riskevaluator.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    public Evaluation addEvaluation(Evaluation evaluation){
        return evaluationRepository.save(evaluation);
    }

    public List<Evaluation> getEvaluations(){
        return StreamSupport
                .stream(evaluationRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Evaluation getEvaluation(Integer id){
        return evaluationRepository.findById(id).orElseThrow(() ->
                new EvaluationNotFoundException(id));
    }

    public Evaluation deleteEvaluation(Integer id){
        Evaluation evaluation = getEvaluation(id);
        evaluationRepository.delete(evaluation);
        return evaluation;
    }

    @Transactional
    public Evaluation editEvaluation(Integer id, Evaluation evaluation){
        Evaluation evaluationToEdit = getEvaluation(id);
        evaluationToEdit.setCompte(evaluation.getCompte());
        evaluationToEdit.setEntreprise(evaluation.getEntreprise());
        evaluationToEdit.setScoreGeneraleEvaluation(evaluation.getScoreGeneraleEvaluation());
        return evaluationToEdit;
    }

}
