package com.codenotfound.primefaces.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

@Named
public class EntityService {
	
	private List<Entity> entities = new ArrayList<>();
	
	public EntityService() {
		for (int i=0; i<10; i++) {
			entities.add(new Entity(i, "NAME"+i));
		}
	}
	
	public List<Entity> getEntities() {
		return entities;
	}

}
