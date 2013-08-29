package es.uma.sportjump.sjs.service.services;

public interface AuthService {
	
	public void addUser(String username, String password, String role);
	
	public void removeUser(String username);
	
	public boolean existUser(String user);

	public void updateUser(String userNameOld, String userNameNew, String password);

}
