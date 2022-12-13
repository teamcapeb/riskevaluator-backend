package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.model.Questionnaire;
import fr.capeb.backend.riskevaluator.model.dto.QuestionnaireDto;
import fr.capeb.backend.riskevaluator.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questionnaires")
public class QuestionnaireController {

    private final QuestionnaireService questionnaireService;

    @Autowired
    public QuestionnaireController(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }

    @PostMapping
    public ResponseEntity<QuestionnaireDto> addQuestionnaire(@RequestBody final QuestionnaireDto questionnaireDto){
        Questionnaire questionnaire = questionnaireService.addQuestionnaire(Questionnaire.from(questionnaireDto));
        return new ResponseEntity<>(QuestionnaireDto.from(questionnaire), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<QuestionnaireDto>> getQuestionnaires(){
        List<Questionnaire> questionnaires = questionnaireService.getQuestionnaires();
        List<QuestionnaireDto> questionnairesDto = questionnaires.stream().map(QuestionnaireDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(questionnairesDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<QuestionnaireDto> getQuestionnaire(@PathVariable final Integer id){
        Questionnaire questionnaire = questionnaireService.getQuestionnaire(id);
        return new ResponseEntity<>(QuestionnaireDto.from(questionnaire), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<QuestionnaireDto> deleteQuestionnaire(@PathVariable final Integer id){
        Questionnaire questionnaire = questionnaireService.deleteQuestionnaire(id);
        return new ResponseEntity<>(QuestionnaireDto.from(questionnaire), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<QuestionnaireDto> editQuestionnaire(@PathVariable final Integer id,
                                                              @RequestBody final QuestionnaireDto questionnaireDto){
        Questionnaire editedQuestionnaire = questionnaireService.editQuestionnaire(id, Questionnaire.from(questionnaireDto));
        return new ResponseEntity<>(QuestionnaireDto.from(editedQuestionnaire), HttpStatus.OK);
    }
}