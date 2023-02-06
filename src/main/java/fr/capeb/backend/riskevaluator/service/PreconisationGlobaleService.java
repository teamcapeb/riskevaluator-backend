package fr.capeb.backend.riskevaluator.service;

import fr.capeb.backend.riskevaluator.model.PreconisationGlobale;
import fr.capeb.backend.riskevaluator.model.dto.PreconisationGlobaleDto;
import fr.capeb.backend.riskevaluator.model.exception.PreconisationGlobaleNotFoundException;
import fr.capeb.backend.riskevaluator.repository.PreconisationGlobaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PreconisationGlobaleService {
    private final PreconisationGlobaleRepository preconisationGlobaleRepository;

    @Autowired
    public PreconisationGlobaleService(PreconisationGlobaleRepository preconisationGlobaleRepository) {
        this.preconisationGlobaleRepository = preconisationGlobaleRepository;
    }

    public PreconisationGlobale addPreconisationGlobale(PreconisationGlobale preconisationGlobale){
        return preconisationGlobaleRepository.save(preconisationGlobale);
    }

    public List<PreconisationGlobale> getPreconisationGlobales(){
        return new ArrayList<>(preconisationGlobaleRepository.findAll());
    }

    public PreconisationGlobale getPreconisationGlobale(Integer id){
        return preconisationGlobaleRepository.findById(id).orElseThrow(() ->
                new PreconisationGlobaleNotFoundException(id));
    }

    public PreconisationGlobale deletePreconisationGlobale(Integer id){
        PreconisationGlobale preconisationGlobale = getPreconisationGlobale(id);
        preconisationGlobaleRepository.delete(preconisationGlobale);
        return preconisationGlobale;
    }

    @Transactional
    public PreconisationGlobale editPreconisationGlobale(Integer id, PreconisationGlobale preconisationGlobale){
        PreconisationGlobale preconisationGlobaleToEdit = getPreconisationGlobale(id);
        preconisationGlobaleToEdit.setViewIfPourcentageScoreLessThan(preconisationGlobale.getViewIfPourcentageScoreLessThan());
        preconisationGlobaleToEdit.setQuestionnaire(preconisationGlobale.getQuestionnaire());
        preconisationGlobaleToEdit.setContenu(preconisationGlobale.getContenu());
        return preconisationGlobaleRepository.save(preconisationGlobaleToEdit);
    }

}
