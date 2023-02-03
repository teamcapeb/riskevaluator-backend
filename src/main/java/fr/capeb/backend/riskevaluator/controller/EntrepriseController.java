package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.model.MetierQuestionPK;
import fr.capeb.backend.riskevaluator.model.dto.EntrepriseDto;
import fr.capeb.backend.riskevaluator.service.EntrepriseService;
import fr.capeb.backend.riskevaluator.service.MetierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/entreprises")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;
    private final MetierService metierService;

    @Autowired
    public EntrepriseController(EntrepriseService entrepriseService,MetierService metierService) {
        this.entrepriseService = entrepriseService;
        this.metierService = metierService;
    }

    @PostMapping("/")
    public ResponseEntity<EntrepriseDto> addEntreprise(@RequestBody final EntrepriseDto entrepriseDto){
        Entreprise entreprise = entrepriseService.addEntreprise(Entreprise.from(entrepriseDto));
        return new ResponseEntity<>(EntrepriseDto.from(entreprise), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EntrepriseDto>> getEntreprises(){
        List<Entreprise> entreprises = entrepriseService.getEntreprises();
        List<EntrepriseDto> entreprisesDto = entreprises.stream().map(EntrepriseDto::from).collect(Collectors.toList());
        return ResponseEntity.ok(entreprisesDto);
    }


    @GetMapping("/{id}/exists")
    public boolean exists(@PathVariable String id) {
        return entrepriseService.exists( Long.parseLong(id));
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
