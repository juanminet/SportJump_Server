package es.uma.sportjump.sjs.web.controller.commands;

import java.util.Date;


public class ProfileCommand {
	
	private Long idUser;
	
	private String userName;
	
	private String password;
	
	private String repeatPassword;
	
	
	private String name;
	private String surname;
	
	//@Pattern(regexp="\\d{8}([A-Z]|[a-z])")
	private String dni;
	private String type;
	
	private String dateBirthDay;	
	private String dateBirthMonth;
	private String dateBirthYear;
	
	private Date dateBirth;
	
	

	private String address;
	private String telephone;
	private String email;
	private String comments;
	
	

	
	
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
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

	public String getDateBirthDay() {
		return dateBirthDay;
	}

	public void setDateBirthDay(String dateBirthDay) {
		this.dateBirthDay = dateBirthDay;
	}

	public String getDateBirthMonth() {
		return dateBirthMonth;
	}

	public void setDateBirthMonth(String dateBirthMonth) {
		this.dateBirthMonth = dateBirthMonth;
	}

	public String getDateBirthYear() {
		return dateBirthYear;
	}

	public void setDateBirthYear(String dateBirthYear) {
		this.dateBirthYear = dateBirthYear;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
