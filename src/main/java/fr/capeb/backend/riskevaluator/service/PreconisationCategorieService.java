package fr.capeb.backend.riskevaluator.service;

import fr.capeb.backend.riskevaluator.model.PreconisationCategorie;
import fr.capeb.backend.riskevaluator.model.dto.PreconisationCategorieDto;
import fr.capeb.backend.riskevaluator.model.exception.PreconisationCategorieNotFoundException;
import fr.capeb.backend.riskevaluator.repository.PreconisationCategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PreconisationCategorieService {
    private final PreconisationCategorieRepository preconisationCategorieRepository;

    @Autowired
    public PreconisationCategorieService(PreconisationCategorieRepository preconisationCategorieRepository) {
        this.preconisationCategorieRepository = preconisationCategorieRepository;
    }

    public PreconisationCategorie addPreconisationCategorie(PreconisationCategorie preconisationCategorie){
        return preconisationCategorieRepository.save(preconisationCategorie);
    }

    public List<PreconisationCategorie> getPreconisationCategories(){
        return new ArrayList<>(preconisationCategorieRepository.findAll());
    }

    public PreconisationCategorie getPreconisationCategorie(Integer id){
        return preconisationCategorieRepository.findById(id).orElseThrow(() ->
                new PreconisationCategorieNotFoundException(id));
    }

    public PreconisationCategorie deletePreconisationCategorie(Integer id){
        PreconisationCategorie preconisationCategorie = getPreconisationCategorie(id);
        preconisationCategorieRepository.delete(preconisationCategorie);
        return preconisationCategorie;
    }

    @Transactional
    public PreconisationCategorie editPreconisationCategorie(Integer id, PreconisationCategorie preconisationCategorie){
        PreconisationCategorie preconisationCategorieToEdit = getPreconisationCategorie(id);
        preconisationCategorieToEdit.setCategorieQuestion(preconisationCategorie.getCategorieQuestion());
        preconisationCategorieToEdit.setContenu(preconisationCategorie.getContenu());
        preconisationCategorieToEdit.setViewIfPourcentageScoreLessThan(preconisationCategorie.getViewIfPourcentageScoreLessThan());
        return preconisationCategorieRepository.save(preconisationCategorieToEdit);
    }

}
