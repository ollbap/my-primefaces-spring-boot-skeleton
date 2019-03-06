package es.test.config;


import org.eclipse.jdt.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configures spring security
 */
@EnableWebSecurity(debug=false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(@Nullable HttpSecurity http) throws Exception {
		if (http == null) {
			return;
		}
		http
			//CSRF needs to be disabled for JSF or else, you need some tunning in your JSF forms.
			.csrf().disable()
			
			.authorizeRequests()
				.antMatchers("/css/**", "*.xhtml").permitAll()		
				.antMatchers("/secured/**").hasRole("USER")			
				.and()
				
			.logout()
				.permitAll();	
	}

	@SuppressWarnings("static-method")
	@Autowired
	public void configureGlobal(@Nullable AuthenticationManagerBuilder auth) throws Exception {
		if (auth == null) {
			return;
		}
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
	}
	
}