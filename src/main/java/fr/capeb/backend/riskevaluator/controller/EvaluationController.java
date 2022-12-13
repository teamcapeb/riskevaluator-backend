package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.model.dto.EvaluationDto;
import fr.capeb.backend.riskevaluator.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping
    public ResponseEntity<EvaluationDto> addEvaluation(@RequestBody final EvaluationDto evaluationDto){
        Evaluation evaluation = evaluationService.addEvaluation(Evaluation.from(evaluationDto));
        return new ResponseEntity<>(EvaluationDto.from(evaluation), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EvaluationDto>> getEvaluations(){
        List<Evaluation> evaluations = evaluationService.getEvaluations();
        List<EvaluationDto> evaluationsDto = evaluations.stream().map(EvaluationDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(evaluationsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EvaluationDto> getEvaluation(@PathVariable final Integer id){
        Evaluation evaluation = evaluationService.getEvaluation(id);
        return new ResponseEntity<>(EvaluationDto.from(evaluation), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<EvaluationDto> deleteEvaluation(@PathVariable final Integer id){
        Evaluation evaluation = evaluationService.deleteEvaluation(id);
        return new ResponseEntity<>(EvaluationDto.from(evaluation), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<EvaluationDto> editEvaluation(@PathVariable final Integer id,
                                                        @RequestBody final EvaluationDto evaluationDto){
        Evaluation editedEvaluation = evaluationService.editEvaluation(id, Evaluation.from(evaluationDto));
        return new ResponseEntity<>(EvaluationDto.from(editedEvaluation), HttpStatus.OK);
    }
}