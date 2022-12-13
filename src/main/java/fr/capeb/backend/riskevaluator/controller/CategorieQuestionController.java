package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.dto.CategorieQuestionDto;
import fr.capeb.backend.riskevaluator.service.CategorieQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorieCategorieQuestions")
public class CategorieQuestionController {

    private final CategorieQuestionService categorieCategorieQuestionService;

    @Autowired
    public CategorieQuestionController(CategorieQuestionService categorieCategorieQuestionService) {
        this.categorieCategorieQuestionService = categorieCategorieQuestionService;
    }

    @PostMapping
    public ResponseEntity<CategorieQuestionDto> addCategorieQuestion(@RequestBody final CategorieQuestionDto categorieCategorieQuestionDto){
        CategorieQuestion categorieCategorieQuestion = categorieCategorieQuestionService.addCategorieQuestion(CategorieQuestion.from(categorieCategorieQuestionDto));
        return new ResponseEntity<>(CategorieQuestionDto.from(categorieCategorieQuestion), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategorieQuestionDto>> getCategorieQuestions(){
        List<CategorieQuestion> categorieCategorieQuestions = categorieCategorieQuestionService.getCategorieQuestions();
        List<CategorieQuestionDto> categorieCategorieQuestionsDto = categorieCategorieQuestions.stream().map(CategorieQuestionDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(categorieCategorieQuestionsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CategorieQuestionDto> getCategorieQuestion(@PathVariable final Integer id){
        CategorieQuestion categorieCategorieQuestion = categorieCategorieQuestionService.getCategorieQuestion(id);
        return new ResponseEntity<>(CategorieQuestionDto.from(categorieCategorieQuestion), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<CategorieQuestionDto> deleteCategorieQuestion(@PathVariable final Integer id){
        CategorieQuestion categorieCategorieQuestion = categorieCategorieQuestionService.deleteCategorieQuestion(id);
        return new ResponseEntity<>(CategorieQuestionDto.from(categorieCategorieQuestion), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CategorieQuestionDto> editCategorieQuestion(@PathVariable final Integer id,
                                                                      @RequestBody final CategorieQuestionDto categorieCategorieQuestionDto){
        CategorieQuestion editedCategorieQuestion = categorieCategorieQuestionService.editCategorieQuestion(id, CategorieQuestion.from(categorieCategorieQuestionDto));
        return new ResponseEntity<>(CategorieQuestionDto.from(editedCategorieQuestion), HttpStatus.OK);
    }
}