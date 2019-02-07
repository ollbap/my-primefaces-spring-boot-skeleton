package com.codenotfound.primefaces.model.entity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;

@Named
public class EntityManagerController {
	
	@Getter
	List<Entity> entities;
	
	@Inject
	EntityService entityService;
	
	public EntityManagerController() {
		//TODO use inject.
//		entities = entityService.getEntities();
		entities = new EntityService().getEntities();
	}
	
}
