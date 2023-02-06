package fr.capeb.backend.riskevaluator.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import fr.capeb.backend.riskevaluator.model.dto.CompteDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "compte")
public class Compte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_compte")
	private Integer idCompte;
	
	@OrderBy("idEvaluation")
	@OneToMany(mappedBy = "compte", cascade = CascadeType.ALL)
	private Set<Evaluation> evaluations = new HashSet<>();
	
	@Column(name = "is_admin")
	private Boolean isAdmin;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "pwd")
	private String pwd;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "is_valid")
	private Boolean isValid;
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	@Column(name = "last_login")
	private Date lastLogin;
	
	@Column(name = "is_cgu")
	private Boolean isCgu;
	
	@Column(name = "is_forgotpass")
	private Boolean isForgotpass;
	
	@Column(name = "is_temp_password")
	private String isTempPassword;
	
	public static Compte from(CompteDto compteDto) {
		Compte compte = new Compte();
		compte.setEvaluations(compteDto.getEvaluations());
		compte.setCreationDate(compteDto.getCreationDate());
		compte.setIsCgu(compteDto.getIsCgu());
		compte.setMail(compteDto.getMail());
		compte.setFirstName(compteDto.getFirstName());
		compte.setPwd(compteDto.getPwd());
		compte.setIsForgotpass(compteDto.getIsForgotpass());
		compte.setIsAdmin(compteDto.getIsAdmin());
		compte.setIsTempPassword(compteDto.getIsTempPassword());
		compte.setName(compteDto.getName());
		compte.setLastLogin(compteDto.getLastLogin());
		compte.setIsValid(compteDto.getIsValid());
		return compte;
	}
	
}
