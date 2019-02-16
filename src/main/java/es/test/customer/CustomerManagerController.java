package es.test.customer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import es.test.database.Customer;
import es.test.database.CustomerRepository;

@Named
public class CustomerManagerController {
	
	private CustomerRepository customerRepository;
	
	@Inject 
	public CustomerManagerController(CustomerRepository customerRepository) {
		this.customerRepository  = customerRepository;
	}
	
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(c -> customers.add(c));
		return customers;
	}

	public void delete(Customer toDelete) {
		customerRepository.deleteById(toDelete.getId());
	}
	
}
