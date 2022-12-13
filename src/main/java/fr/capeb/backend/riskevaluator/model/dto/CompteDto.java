package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.Compte;
import fr.capeb.backend.riskevaluator.model.Evaluation;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CompteDto {
    private Integer idCompte;
    private Set<Evaluation> evaluations=new HashSet<>();
    private Boolean isAdmin;
    private String mail;
    private String pwd;
    private String name;
    private String firstName;
    private Boolean isValid;
    private Date creationDate;
    private Date lastLogin;
    private Boolean isCgu;
    private Boolean isForgotpass;
    private String isTempPassword;

    public static CompteDto from(Compte compte){
        CompteDto compteDto = new CompteDto();
        compteDto.setIdCompte(compte.getIdCompte());
        compteDto.setEvaluations(compte.getEvaluations());
        compteDto.setCreationDate(compte.getCreationDate());
        compteDto.setIsCgu(compte.getIsCgu());
        compteDto.setMail(compte.getMail());
        compteDto.setFirstName(compte.getFirstName());
        compteDto.setPwd(compte.getPwd());
        compteDto.setIsForgotpass(compte.getIsForgotpass());
        compteDto.setIsAdmin(compte.getIsAdmin());
        compteDto.setIsTempPassword(compte.getIsTempPassword());
        compteDto.setName(compte.getName());
        compteDto.setLastLogin(compte.getLastLogin());
        compteDto.setIsValid(compte.getIsValid());
        return compteDto;
    }


}

