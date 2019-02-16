package com.codenotfound.primefaces.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jdt.annotation.Nullable;

import com.codenotfound.primefaces.Customer;
import com.codenotfound.primefaces.CustomerRepository;

@Named
public class EntityManagerController {
	
	private @Nullable SelectableListDataModel<Customer> entities = null;
	
	private CustomerRepository customerRepository;
	
	@Inject 
	public EntityManagerController(CustomerRepository customerRepository) {
		this.customerRepository  = customerRepository;
	}
	
	public SelectableListDataModel<Customer> getEntities() {
		SelectableListDataModel<Customer> lentities = entities;
		if (lentities == null) {
			lentities = prepareEntitiesModel();
			entities = lentities;
		}
		return lentities;
	}

	private SelectableListDataModel<Customer> prepareEntitiesModel() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(c -> customers.add(c));
		return new SelectableListDataModel<>(customers, c -> ""+c.getId());
	}
	
	public void deleteEntity() {
		//TODO mejorar los lios del selector
		@SuppressWarnings("unchecked")
		Customer toDelete = ((List<Customer>)entities.getWrappedData()).get(0);
		customerRepository.deleteById(toDelete.getId());
	}
	
}
