package es.test.jsf.services;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import lombok.Getter;

@Named
@SessionScoped
public class SessionBean {
	
	@Getter
	private Date postConstructDate = new Date();
	
	@PostConstruct
	public void postConstruct() {
		postConstructDate = new Date();
	}
	
}
