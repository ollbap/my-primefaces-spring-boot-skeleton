package es.test.customer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

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
		if (edited.getFirstName().length() > 10 || edited.getLastName().length() > 10) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Name is too long"));
			return;
		}
		
		manager.getCustomerRepository().save(edited);
		manager.refreshData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Modification applied."));
		PrimeFaces.current().executeScript("PF('editionDialog').hide()");
	}

	public void createNew() {
		this.edited = new Customer("", "");
		isNew = true;
	}
	
}
