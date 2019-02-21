package es.test.customer;

import es.test.database.Customer;
import lombok.Getter;
import lombok.Setter;

public class CustomerEditionController {
	
	private CustomerManagerController manager;
	
	@Getter @Setter
	private Customer edited;
	
	private boolean isNew;
	
	public CustomerEditionController(CustomerManagerController manager) {
		this.manager = manager;
	}
	
	public void prepareToEdit(Customer toEdit) {
		this.edited = toEdit;
		isNew = false;
	}
	
	public void saveEdition() {
		manager.getCustomerRepository().save(edited);
		manager.refreshData();
	}

	public void createNew() {
		this.edited = new Customer("", "");
		isNew = true;
	}
	
}
