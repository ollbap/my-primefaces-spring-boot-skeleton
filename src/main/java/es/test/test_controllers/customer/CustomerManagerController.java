package es.test.test_controllers.customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.test.database.Customer;
import es.test.database.CustomerService;
import lombok.Getter;

@Named
@ViewScoped
public class CustomerManagerController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	private Date postConstructDate = new Date();
	
	@Inject @Getter
	private transient CustomerService customerRepository;
	
	@Getter
	private CustomerEditionController editor = new CustomerEditionController(this);
	
	private List<Customer> customers = new ArrayList<>();
	
	@PostConstruct
	public void postConstruct() {
		refreshData();
		postConstructDate = new Date();
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}

	public void delete(Customer toDelete) {
		customerRepository.deleteById(toDelete.getId());
		refreshData();
	}
	
	public void edit(Customer toEdit) {
		editor.prepareToEdit(toEdit);
	}
	
	public void createNew() {
		editor.createNew();
	}
	
	public void refreshData() {
		customers = new ArrayList<>();
		customerRepository.findAll().forEach(c -> customers.add(c));
	}
	
	@SuppressWarnings("static-method")
	public void throwException()  {
		throw new IllegalStateException("Test exception");
	}
	
}
