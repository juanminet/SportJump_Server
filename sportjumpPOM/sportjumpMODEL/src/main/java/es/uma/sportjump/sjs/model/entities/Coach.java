package es.uma.sportjump.sjs.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TB_COACH")
public class Coach extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
    public Coach() {
	    super();
    }


	
	

}
