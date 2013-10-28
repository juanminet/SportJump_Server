package es.uma.sportjump.sjs.service.services.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import es.uma.sportjump.sjs.service.services.AuthService;


@Service("authService")
public class AuthServiceImpl implements AuthService{

	@Autowired
	private UserDetailsManager userDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SaltSource saltSource;

	public void addUser(String username, String password, String role) {
		List<GrantedAuthority> authorites = new ArrayList<GrantedAuthority>();
		authorites.add(new SimpleGrantedAuthority(role));
		
		
		User user = new User(username, password, true, false, false, false, authorites);
		User saltedUser = new User(username, passwordEncoder.encodePassword(password, saltSource.getSalt(user)), 
				true, false, false, false, authorites);
		
		userDetailsManager.createUser(saltedUser);
	}

	
	public void removeUser(String username) {
		userDetailsManager.deleteUser(username);		
	}

	
	public boolean existUser(String user) {
		return userDetailsManager.userExists(user);		
	}


	
	public void updateUser(String userNameOld, String userNameNew, String password) {
		UserDetails userDetails = userDetailsManager.loadUserByUsername(userNameOld);
		
		Collection<? extends GrantedAuthority> authorites =userDetails.getAuthorities();
		GrantedAuthority authority = (GrantedAuthority) authorites.toArray()[0];
		String role = authority.getAuthority();
		
		removeUser(userNameOld);
		addUser(userNameNew, password, role);		
	}

}
