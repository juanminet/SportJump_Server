package es.uma.sportjump.sjs.service.services.test.mock;

import org.springframework.stereotype.Service;

import es.uma.sportjump.sjs.service.services.AuthService;


@Service("authService")
public class AuthServiceImpl implements AuthService{

	@Override
	public void addUser(String username, String password, String role) {
		// Mock
		
	}

	@Override
	public void removeUser(String username) {
		// Mock
		
	}

	@Override
	public boolean existUser(String user) {
		// Mock
		return false;
	}

	@Override
	public void updateUser(String userNameOld, String userNameNew,	String password) {
		// Mock
	}

}

