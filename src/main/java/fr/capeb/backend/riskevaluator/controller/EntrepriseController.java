package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.model.MetierQuestionPK;
import fr.capeb.backend.riskevaluator.model.dto.EntrepriseDto;
import fr.capeb.backend.riskevaluator.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/entreprises")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    @PostMapping
    public ResponseEntity<EntrepriseDto> addEntreprise(@RequestBody final EntrepriseDto entrepriseDto){
        Entreprise entreprise = entrepriseService.addEntreprise(Entreprise.from(entrepriseDto));
        return new ResponseEntity<>(EntrepriseDto.from(entreprise), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EntrepriseDto>> getEntreprises(){
        List<Entreprise> entreprises = entrepriseService.getEntreprises();
        List<EntrepriseDto> entreprisesDto = entreprises.stream().map(EntrepriseDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(entreprisesDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<EntrepriseDto> getEntreprise(@PathVariable final Long id){
        Entreprise entreprise = entrepriseService.getEntreprise(id);
        return new ResponseEntity<>(EntrepriseDto.from(entreprise), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<EntrepriseDto> deleteEntreprise(@PathVariable final Long id){
        Entreprise entreprise = entrepriseService.deleteEntreprise(id);
        return new ResponseEntity<>(EntrepriseDto.from(entreprise), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<EntrepriseDto> editEntreprise(@PathVariable final Long id,
                                                        @RequestBody final EntrepriseDto entrepriseDto){
        Entreprise editedEntreprise = entrepriseService.editEntreprise(id, Entreprise.from(entrepriseDto));
        return new ResponseEntity<>(EntrepriseDto.from(editedEntreprise), HttpStatus.OK);
    }
}