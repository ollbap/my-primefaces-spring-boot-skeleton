package es.test.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.annotation.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@WebServlet("/authentication")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(@Nullable HttpServletRequest req, @Nullable HttpServletResponse response) throws IOException {
		if (response == null || req == null) {
			return;
		}
		
		String securityToken = req.getParameter("securityToken");
		if (securityToken != null && securityToken.equals("1234_secure")) {
			Object principal = "InMemoryUser";
			Object credentials = "";
			Collection<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("CUSTOMERS_ROLE"), new SimpleGrantedAuthority("ADMINISTRATOR_ROLE"));
			Authentication auth = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(auth);
		}
		
		response.sendRedirect(req.getContextPath() + "/index.xhtml");
	}

}