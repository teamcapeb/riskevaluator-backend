package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.model.PreconisationGlobale;
import fr.capeb.backend.riskevaluator.model.dto.PreconisationGlobaleDto;
import fr.capeb.backend.riskevaluator.service.PreconisationGlobaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/preconisationGlobales")
public class PreconisationGlobaleController {

    private final PreconisationGlobaleService preconisationGlobaleService;

    @Autowired
    public PreconisationGlobaleController(PreconisationGlobaleService preconisationGlobaleService) {
        this.preconisationGlobaleService = preconisationGlobaleService;
    }

    @PostMapping
    public ResponseEntity<PreconisationGlobaleDto> addPreconisationGlobale(@RequestBody final PreconisationGlobaleDto preconisationGlobaleDto){
        PreconisationGlobale preconisationGlobale = preconisationGlobaleService.addPreconisationGlobale(PreconisationGlobale.from(preconisationGlobaleDto));
        return new ResponseEntity<>(PreconisationGlobaleDto.from(preconisationGlobale), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PreconisationGlobaleDto>> getPreconisationGlobales(){
        List<PreconisationGlobale> preconisationGlobales = preconisationGlobaleService.getPreconisationGlobales();
        List<PreconisationGlobaleDto> preconisationGlobalesDto = preconisationGlobales.stream().map(PreconisationGlobaleDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(preconisationGlobalesDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<PreconisationGlobaleDto> getPreconisationGlobale(@PathVariable final Integer id){
        PreconisationGlobale preconisationGlobale = preconisationGlobaleService.getPreconisationGlobale(id);
        return new ResponseEntity<>(PreconisationGlobaleDto.from(preconisationGlobale), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<PreconisationGlobaleDto> deletePreconisationGlobale(@PathVariable final Integer id){
        PreconisationGlobale preconisationGlobale = preconisationGlobaleService.deletePreconisationGlobale(id);
        return new ResponseEntity<>(PreconisationGlobaleDto.from(preconisationGlobale), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<PreconisationGlobaleDto> editPreconisationGlobale(@PathVariable final Integer id,
                                                                            @RequestBody final PreconisationGlobaleDto preconisationGlobaleDto){
        PreconisationGlobale editedPreconisationGlobale = preconisationGlobaleService.editPreconisationGlobale(id, PreconisationGlobale.from(preconisationGlobaleDto));
        return new ResponseEntity<>(PreconisationGlobaleDto.from(editedPreconisationGlobale), HttpStatus.OK);
    }
}