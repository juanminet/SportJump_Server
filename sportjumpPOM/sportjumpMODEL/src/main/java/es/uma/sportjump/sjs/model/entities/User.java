package es.uma.sportjump.sjs.model.entities;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(uniqueConstraints=@UniqueConstraint(columnNames="USER_NAME"),   name="TB_USER" )


public abstract class User {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID_USER")
	private Long idUser;	
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
	private String surname;
	
	@Column(name="USER_NAME", unique= true , nullable = false)	
	private String userName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="DNI", unique= true , nullable = false)
	private String dni;
	
	@Column(name="TYPE")
	private String type;
	
	@Column(name="DATE_BIRTH")
	private Date dateBirth;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="TELEPHONE")
	private String telephone;
	
	@Column(name="COMMENTS")
	private String comments;
	
	public User() {
		super();		
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
		
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCompleteName(){
		return (this.name + " " + this.surname);
	}
	
	public int getAge() {
	    int age = 0;
	    Calendar born = Calendar.getInstance();
	    Calendar now = Calendar.getInstance();
	    if(dateBirth!= null) {
	        now.setTime(new Date());
	        born.setTime(dateBirth);  
	        if(born.after(now)) {
	            throw new IllegalArgumentException("Can't be born in the future");
	        }
	        age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);             
	        if(now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR))  {
	            age-=1;
	        }
	    }  
	    return age;
	}
	
	
}
