package fr.capeb.backend.riskevaluator.service;

import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.model.exception.MetierNotFoundException;
import fr.capeb.backend.riskevaluator.repository.MetierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class MetierService {
    private final MetierRepository metierRepository;

    @Autowired
    public MetierService(MetierRepository metierRepository) {
        this.metierRepository = metierRepository;
    }

    public Metier addMetier(Metier metier){
        return metierRepository.save(metier);
    }

    public List<Metier> getMetiers(){
        return StreamSupport
                .stream(metierRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Metier getMetier(Integer id){
        return metierRepository.findById(id).orElseThrow(() ->
                new MetierNotFoundException(id));
    }

    public Metier deleteMetier(Integer id){
        Metier metier = getMetier(id);
        metierRepository.delete(metier);
        return metier;
    }

    @Transactional
    public Metier editMetier(Integer id, Metier metier){
        Metier metierToEdit = getMetier(id);
        metierToEdit.setNomMetier(metier.getNomMetier());
        metierToEdit.setQuestions(metier.getQuestions());
        return metierToEdit;
    }

}
