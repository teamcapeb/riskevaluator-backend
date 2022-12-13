package fr.capeb.backend.riskevaluator.controller;

import fr.capeb.backend.riskevaluator.model.Reponse;
import fr.capeb.backend.riskevaluator.model.dto.ReponseDto;
import fr.capeb.backend.riskevaluator.service.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reponses")
public class ReponseController {

    private final ReponseService reponseService;

    @Autowired
    public ReponseController(ReponseService reponseService) {
        this.reponseService = reponseService;
    }

    @PostMapping
    public ResponseEntity<ReponseDto> addReponse(@RequestBody final ReponseDto reponseDto){
        Reponse reponse = reponseService.addReponse(Reponse.from(reponseDto));
        return new ResponseEntity<>(ReponseDto.from(reponse), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReponseDto>> getReponses(){
        List<Reponse> reponses = reponseService.getReponses();
        List<ReponseDto> reponsesDto = reponses.stream().map(ReponseDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(reponsesDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ReponseDto> getReponse(@PathVariable final Integer id){
        Reponse reponse = reponseService.getReponse(id);
        return new ResponseEntity<>(ReponseDto.from(reponse), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ReponseDto> deleteReponse(@PathVariable final Integer id){
        Reponse reponse = reponseService.deleteReponse(id);
        return new ResponseEntity<>(ReponseDto.from(reponse), HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ReponseDto> editReponse(@PathVariable final Integer id,
                                                  @RequestBody final ReponseDto reponseDto){
        Reponse editedReponse = reponseService.editReponse(id, Reponse.from(reponseDto));
        return new ResponseEntity<>(ReponseDto.from(editedReponse), HttpStatus.OK);
    }
}