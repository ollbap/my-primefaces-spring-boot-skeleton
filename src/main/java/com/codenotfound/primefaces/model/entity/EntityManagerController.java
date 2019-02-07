package com.codenotfound.primefaces.model.entity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;

@Named
public class EntityManagerController {
	
	@Getter
	private List<Entity> entities;
	
	private EntityService entityService;
	
	@Inject 
	public EntityManagerController(EntityService entityService) {
		this.entityService = entityService;
		entities = entityService.getEntities();
	}
	
}
