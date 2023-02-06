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

import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.model.dto.MetierDto;
import fr.capeb.backend.riskevaluator.service.MetierService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/metiers")
public class MetierController {
	
	private final MetierService metierService;
	
	@Autowired
	public MetierController(MetierService metierService) {
		this.metierService = metierService;
	}
	
	@PostMapping
	public ResponseEntity<MetierDto> addMetier(@RequestBody final MetierDto metierDto) {
		Metier metier = metierService.addMetier(Metier.from(metierDto));
		return new ResponseEntity<>(MetierDto.from(metier), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<MetierDto>> getMetiers() {
		List<Metier> metiers = metierService.getMetiers();
		List<MetierDto> metiersDto = metiers.stream().map(MetierDto::from).collect(Collectors.toList());
		return new ResponseEntity<>(metiersDto, HttpStatus.OK);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<MetierDto> getMetier(@PathVariable final Integer id) {
		Metier metier = metierService.getMetier(id);
		return new ResponseEntity<>(MetierDto.from(metier), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<MetierDto> deleteMetier(@PathVariable final Integer id) {
		Metier metier = metierService.deleteMetier(id);
		return new ResponseEntity<>(MetierDto.from(metier), HttpStatus.OK);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<MetierDto> editMetier(@PathVariable final Integer id,
			@RequestBody final MetierDto metierDto) {
		Metier editedMetier = metierService.editMetier(id, Metier.from(metierDto));
		return new ResponseEntity<>(MetierDto.from(editedMetier), HttpStatus.OK);
	}
}
