package es.test.config;


import org.eclipse.jdt.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

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
				
			.formLogin()
				.loginPage("/index.xhtml")
				.permitAll()
				.failureUrl("/login-error")
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
	
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}