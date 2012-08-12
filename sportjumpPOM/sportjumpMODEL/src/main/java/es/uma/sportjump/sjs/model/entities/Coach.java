package es.uma.sportjump.sjs.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TB_COACH")
public class Coach extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
	private String surname;

    public Coach() {
	    super();
    }


	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}
