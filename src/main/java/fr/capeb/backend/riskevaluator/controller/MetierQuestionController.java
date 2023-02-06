package fr.capeb.backend.riskevaluator.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.capeb.backend.riskevaluator.model.MetierQuestion;
import fr.capeb.backend.riskevaluator.model.MetierQuestionPK;
import fr.capeb.backend.riskevaluator.model.dto.MetierQuestionDto;
import fr.capeb.backend.riskevaluator.service.MetierQuestionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/metierQuestions")
public class MetierQuestionController {
	
	private final MetierQuestionService metierQuestionService;
	
	@Autowired
	public MetierQuestionController(MetierQuestionService metierQuestionService) {
		this.metierQuestionService = metierQuestionService;
	}
	
	@PostMapping
	public ResponseEntity<MetierQuestionDto> addMetierQuestion(@RequestBody final MetierQuestionDto metierQuestionDto) {
		MetierQuestion metierQuestion = metierQuestionService.addMetierQuestion(MetierQuestion.from(metierQuestionDto));
		return new ResponseEntity<>(MetierQuestionDto.from(metierQuestion), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<MetierQuestionDto>> getMetierQuestions() {
		List<MetierQuestion> metierQuestions = metierQuestionService.getMetierQuestions();
		List<MetierQuestionDto> metierQuestionsDto = metierQuestions.stream().map(MetierQuestionDto::from).collect(Collectors.toList());
		return new ResponseEntity<>(metierQuestionsDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<MetierQuestionDto> getMetierQuestion(@PathVariable final MetierQuestionPK id) {
		MetierQuestion metierQuestion = metierQuestionService.getMetierQuestion(id);
		return new ResponseEntity<>(MetierQuestionDto.from(metierQuestion), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<MetierQuestionDto> deleteMetierQuestion(@PathVariable final MetierQuestionPK id) {
		MetierQuestion metierQuestion = metierQuestionService.deleteMetierQuestion(id);
		return new ResponseEntity<>(MetierQuestionDto.from(metierQuestion), HttpStatus.OK);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<MetierQuestionDto> editMetierQuestion(@PathVariable final MetierQuestionPK id,
			@RequestBody final MetierQuestionDto metierQuestionDto) {
		MetierQuestion editedMetierQuestion = metierQuestionService.editMetierQuestion(id, MetierQuestion.from(metierQuestionDto));
		return new ResponseEntity<>(MetierQuestionDto.from(editedMetierQuestion), HttpStatus.OK);
	}
}
