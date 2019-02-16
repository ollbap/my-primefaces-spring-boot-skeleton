package es.test.jsf.services;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;

import lombok.Getter;

@Named
@FlowScoped(value = "mainFlow")
public class FlowBean {
	
	@Getter
	private Date postConstructDate = new Date();
	
	@PostConstruct
	public void postConstruct() {
		postConstructDate = new Date();
	}
	
}
