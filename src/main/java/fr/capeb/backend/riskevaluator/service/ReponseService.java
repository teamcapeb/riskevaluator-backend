package fr.capeb.backend.riskevaluator.service;

import fr.capeb.backend.riskevaluator.model.Reponse;
import fr.capeb.backend.riskevaluator.model.dto.ReponseDto;
import fr.capeb.backend.riskevaluator.model.exception.ReponseNotFoundException;
import fr.capeb.backend.riskevaluator.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class ReponseService {
    private final ReponseRepository reponseRepository;

    @Autowired
    public ReponseService(ReponseRepository reponseRepository) {
        this.reponseRepository = reponseRepository;
    }

    public Reponse addReponse(Reponse reponse){
        return reponseRepository.save(reponse);
    }

    public List<Reponse> getReponses(){
        return new ArrayList<>(reponseRepository.findAll());
    }

    public Reponse getReponse(Integer id){
        return reponseRepository.findById(id).orElseThrow(() ->
                new ReponseNotFoundException(id));
    }

    public Reponse deleteReponse(Integer id){
        Reponse reponse = getReponse(id);
        reponseRepository.delete(reponse);
        return reponse;
    }

    @Transactional
    public Reponse editReponse(Integer id, Reponse reponse){
        Reponse reponseToEdit = getReponse(id);
        reponseToEdit.setContenu(reponse.getContenu());
        reponseToEdit.setQuestion(reponse.getQuestion());
        reponseToEdit.setNbPoints(reponse.getNbPoints());
        return reponseToEdit;
    }

}
