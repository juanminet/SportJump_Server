package es.uma.sportjump.sjs.web.handler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import es.uma.sportjump.sjs.web.constants.AuthConstants;

public class SecurityCustomHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
         throws IOException, ServletException {
    	if(hasAuthorization(authentication)){
    		response.sendRedirect("login/success");
    	}    else{
    		response.sendRedirect("login?login_error=2");
    	}
     }

	private boolean hasAuthorization(Authentication authentication) {
		boolean res = false;
		Collection<? extends GrantedAuthority> authorites = authentication.getAuthorities();
		Object[] authories =  authorites.toArray();
		
		String role = null;
		for (Object authority : authories){
			role = ((GrantedAuthority) authority).getAuthority();
			if (AuthConstants.ROLE_COACH.equals(role) ||
					AuthConstants.ROLE_ADMIN.equals(role)){
				res = true;
				break;
			}
		}
				
		return res;
	}
 }