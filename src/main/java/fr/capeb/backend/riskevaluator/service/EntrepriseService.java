package fr.capeb.backend.riskevaluator.service;

import fr.capeb.backend.riskevaluator.model.Entreprise;
import fr.capeb.backend.riskevaluator.model.exception.EntrepriseNotFoundException;
import fr.capeb.backend.riskevaluator.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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
        return new ArrayList<>(entrepriseRepository.findAll());
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

    public boolean exists(Long siret) {
        return entrepriseRepository.existsById(siret);
    }

    @Transactional
    public Entreprise editEntreprise(Long id, Entreprise entreprise){
        Entreprise entrepriseToEdit = getEntreprise(id);
        entrepriseToEdit.setNomEntreprise(entreprise.getNomEntreprise());
        entrepriseToEdit.setEffectif(entreprise.getEffectif());
        entrepriseToEdit.setEvaluations(entreprise.getEvaluations());
        entrepriseToEdit.setAnneeDeCreation(entreprise.getAnneeDeCreation());
        return entrepriseRepository.save(entrepriseToEdit);
    }

}
