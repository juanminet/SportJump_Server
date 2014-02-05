package es.uma.sportjump.sjs.model.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="TB_COACH")
@NamedQueries({
    @NamedQuery(name="findAllCoaches",
                query="SELECT c from Coach c"),
    @NamedQuery(name="findCoachByUserName",
                query="SELECT c from Coach c where c.userName = :userName"),
    @NamedQuery(name="findCoachByDni",
                query="SELECT c from Coach c where c.dni = :dni"),
}) 
public class Coach extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
    public Coach() {
	    super();
    }
}

