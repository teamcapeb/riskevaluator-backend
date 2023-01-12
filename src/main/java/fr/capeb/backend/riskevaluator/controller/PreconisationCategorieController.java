package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.model.PreconisationCategorie;
import fr.capeb.backend.riskevaluator.model.dto.PreconisationCategorieDto;
import fr.capeb.backend.riskevaluator.service.PreconisationCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/preconisationCategories")
public class PreconisationCategorieController {

    private final PreconisationCategorieService preconisationCategorieService;

    @Autowired
    public PreconisationCategorieController(PreconisationCategorieService preconisationCategorieService) {
        this.preconisationCategorieService = preconisationCategorieService;
    }

    @PostMapping
    public ResponseEntity<PreconisationCategorieDto> addPreconisationCategorie(@RequestBody final PreconisationCategorieDto preconisationCategorieDto){
        PreconisationCategorie preconisationCategorie = preconisationCategorieService.addPreconisationCategorie(PreconisationCategorie.from(preconisationCategorieDto));
        return new ResponseEntity<>(PreconisationCategorieDto.from(preconisationCategorie), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PreconisationCategorieDto>> getPreconisationCategories(){
        List<PreconisationCategorie> preconisationCategories = preconisationCategorieService.getPreconisationCategories();
        List<PreconisationCategorieDto> preconisationCategoriesDto = preconisationCategories.stream().map(PreconisationCategorieDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(preconisationCategoriesDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PreconisationCategorieDto> getPreconisationCategorie(@PathVariable final Integer id){
        PreconisationCategorie preconisationCategorie = preconisationCategorieService.getPreconisationCategorie(id);
        return new ResponseEntity<>(PreconisationCategorieDto.from(preconisationCategorie), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<PreconisationCategorieDto> deletePreconisationCategorie(@PathVariable final Integer id){
        PreconisationCategorie preconisationCategorie = preconisationCategorieService.deletePreconisationCategorie(id);
        return new ResponseEntity<>(PreconisationCategorieDto.from(preconisationCategorie), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<PreconisationCategorieDto> editPreconisationCategorie(@PathVariable final Integer id,
                                                                                @RequestBody final PreconisationCategorieDto preconisationCategorieDto){
        PreconisationCategorie editedPreconisationCategorie = preconisationCategorieService.editPreconisationCategorie(id, PreconisationCategorie.from(preconisationCategorieDto));
        return new ResponseEntity<>(PreconisationCategorieDto.from(editedPreconisationCategorie), HttpStatus.OK);
    }
}