package com.codenotfound.primefaces.model.entity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import lombok.Getter;

@Named
public class EntityManagerController {
	
	@Getter
	private SelectableListDataModel<Entity> entities;
	
	private EntityService entityService;
	
	@Inject 
	public EntityManagerController(EntityService entityService) {
		this.entityService = entityService;
		entities = new SelectableListDataModel<>(entityService.getEntities(), Entity::getName);
	}
	
	public void deleteEntity() {
		//TODO mejorar los lios del selector
		Entity toDelete = ((List<Entity>)entities.getWrappedData()).get(0);
		entityService.delete(toDelete.getId());
	}
	
}
