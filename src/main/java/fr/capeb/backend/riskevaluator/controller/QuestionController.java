package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.model.Question;
import fr.capeb.backend.riskevaluator.model.dto.QuestionDto;
import fr.capeb.backend.riskevaluator.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody final QuestionDto questionDto){
        Question question = questionService.addQuestion(Question.from(questionDto));
        return new ResponseEntity<>(QuestionDto.from(question), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<QuestionDto>> getQuestions(){
        List<Question> questions = questionService.getQuestions();
        List<QuestionDto> questionsDto = questions.stream().map(QuestionDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(questionsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<QuestionDto> getQuestion(@PathVariable final Integer id){
        Question question = questionService.getQuestion(id);
        return new ResponseEntity<>(QuestionDto.from(question), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<QuestionDto> deleteQuestion(@PathVariable final Integer id){
        Question question = questionService.deleteQuestion(id);
        return new ResponseEntity<>(QuestionDto.from(question), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<QuestionDto> editQuestion(@PathVariable final Integer id,
                                                    @RequestBody final QuestionDto questionDto){
        Question editedQuestion = questionService.editQuestion(id, Question.from(questionDto));
        return new ResponseEntity<>(QuestionDto.from(editedQuestion), HttpStatus.OK);
    }
}