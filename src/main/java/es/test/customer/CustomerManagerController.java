package es.test.customer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.test.database.Customer;
import es.test.database.CustomerRepository;

@Named
@ViewScoped
public class CustomerManagerController {
	
	private CustomerRepository customerRepository;
	private List<Customer> customers = new ArrayList<>();
	
	@PostConstruct
	public void postConstruct() {
		refreshData();
	}
	
	@Inject 
	public CustomerManagerController(CustomerRepository customerRepository) {
		this.customerRepository  = customerRepository;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public void delete(Customer toDelete) {
		customerRepository.deleteById(toDelete.getId());
		refreshData();
	}
	
	public void refreshData() {
		customers = new ArrayList<>();
		customerRepository.findAll().forEach(c -> customers.add(c));
	}
	
}
