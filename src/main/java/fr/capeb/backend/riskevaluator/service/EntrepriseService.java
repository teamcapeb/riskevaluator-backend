package fr.capeb.backend.riskevaluator.service;

import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.model.dto.EntrepriseDto;
import fr.capeb.backend.riskevaluator.model.exception.EntrepriseNotFoundException;
import fr.capeb.backend.riskevaluator.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
public class EntrepriseService {
    private final EntrepriseRepository entrepriseRepository;

    @Autowired
    public EntrepriseService(EntrepriseRepository entrepriseRepository) {
        this.entrepriseRepository = entrepriseRepository;
    }

    public Entreprise addEntreprise(Entreprise entreprise){
        return entrepriseRepository.save(entreprise);
    }

    public List<Entreprise> getEntreprises(){
        return StreamSupport
                .stream(entrepriseRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Entreprise getEntreprise(Long id){
        return entrepriseRepository.findById(id).orElseThrow(() ->
                new EntrepriseNotFoundException(id));
    }

    public Entreprise deleteEntreprise(Long id){
        Entreprise entreprise = getEntreprise(id);
        entrepriseRepository.delete(entreprise);
        return entreprise;
    }

    @Transactional
    public Entreprise editEntreprise(Long id, Entreprise entreprise){
        Entreprise entrepriseToEdit = getEntreprise(id);
        entrepriseToEdit.setNomEntreprise(entreprise.getNomEntreprise());
        entrepriseToEdit.setEffectifEntreprise(entreprise.getEffectifEntreprise());
        entrepriseToEdit.setEvaluations(entreprise.getEvaluations());
        entrepriseToEdit.setAnneeDeCreation(entreprise.getAnneeDeCreation());
        return entrepriseToEdit;
    }

}
