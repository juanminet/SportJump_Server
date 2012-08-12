package es.uma.sportjump.sjs.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="TB_USER")
public abstract class User {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_USER")
	private Long idUser;	
	

	public User() {
		super();		
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}
	
	
}
