package es.test.config;

import javax.inject.Named;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Return the current authenticated credentials
 */
@Named
public class AuthenticationService {

	@SuppressWarnings("static-method")
	public String getAuthenticationDetails() {
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication authentication = sc.getAuthentication();
		if (authentication instanceof UsernamePasswordAuthenticationToken) {
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
			StringBuilder details = new StringBuilder();
			details.append(token.getName()+": ");
			details.append(token.getAuthorities().toString());
			return details.toString();
		}
		
		if (authentication instanceof AnonymousAuthenticationToken) {
			AnonymousAuthenticationToken token = (AnonymousAuthenticationToken) authentication;
			StringBuilder details = new StringBuilder();
			details.append(token.getName()+": ");
			details.append(token.getAuthorities().toString());
			return details.toString();
		}
		
		throw new IllegalStateException("Unknown authentication type "+authentication);
	}
	
}
