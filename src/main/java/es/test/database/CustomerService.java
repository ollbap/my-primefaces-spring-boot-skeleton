package es.test.database;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.access.annotation.Secured;

@Named
public class CustomerService {

	@Inject
	public CustomerRepository repository;

	@Secured("ROLE_CUSTOMER_MODIFY")
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Secured("ROLE_CUSTOMER_MODIFY")
	public void save(Customer localEdited) {
		repository.save(localEdited);
	}

	public List<Customer> findAll() {
		List<Customer> customers = new ArrayList<>();
		repository.findAll().forEach(c -> customers.add(c));
		return customers;
	}

}
