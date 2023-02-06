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

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.dto.CategorieQuestionDto;
import fr.capeb.backend.riskevaluator.service.CategorieQuestionService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/categorieQuestions")
public class CategorieQuestionController {
	
	private final CategorieQuestionService categorieQuestionService;
	
	@Autowired
	public CategorieQuestionController(CategorieQuestionService categorieCategorieQuestionService) {
		this.categorieQuestionService = categorieCategorieQuestionService;
	}
	
	@PostMapping
	public ResponseEntity<CategorieQuestionDto> addCategorieQuestion(@RequestBody final CategorieQuestionDto categorieCategorieQuestionDto) {
		CategorieQuestion categorieCategorieQuestion = categorieQuestionService.addCategorieQuestion(
				CategorieQuestion.from(categorieCategorieQuestionDto));
		return new ResponseEntity<>(CategorieQuestionDto.from(categorieCategorieQuestion), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CategorieQuestionDto>> getCategorieQuestions() {
		List<CategorieQuestion> categorieCategorieQuestions = categorieQuestionService.getCategorieQuestions();
		List<CategorieQuestionDto> categorieCategorieQuestionsDto = categorieCategorieQuestions.stream().map(CategorieQuestionDto::from).collect(
				Collectors.toList());
		return new ResponseEntity<>(categorieCategorieQuestionsDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<CategorieQuestionDto> getCategorieQuestion(@PathVariable final Integer id) {
		CategorieQuestion categorieCategorieQuestion = categorieQuestionService.getCategorieQuestion(id);
		return new ResponseEntity<>(CategorieQuestionDto.from(categorieCategorieQuestion), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<CategorieQuestionDto> deleteCategorieQuestion(@PathVariable final Integer id) {
		CategorieQuestion categorieCategorieQuestion = categorieQuestionService.deleteCategorieQuestion(id);
		return new ResponseEntity<>(CategorieQuestionDto.from(categorieCategorieQuestion), HttpStatus.OK);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<CategorieQuestionDto> editCategorieQuestion(@PathVariable final Integer id,
			@RequestBody final CategorieQuestionDto categorieCategorieQuestionDto) {
		CategorieQuestion editedCategorieQuestion = categorieQuestionService.editCategorieQuestion(id,
				CategorieQuestion.from(categorieCategorieQuestionDto));
		return new ResponseEntity<>(CategorieQuestionDto.from(editedCategorieQuestion), HttpStatus.OK);
	}
}
