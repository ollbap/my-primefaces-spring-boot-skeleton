package es.test.test_controllers.customer;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.eclipse.jdt.annotation.Nullable;
import org.primefaces.PrimeFaces;

import es.test.database.Customer;
import lombok.Getter;
import lombok.Setter;

public class CustomerEditionController {
	
	private CustomerManagerController manager;
	
	@Getter @Setter
	private @Nullable Customer edited = null;
	
	public CustomerEditionController(CustomerManagerController manager) {
		this.manager = manager;
	}
	
	public void prepareToEdit(Customer toEdit) {
		this.edited = toEdit;
	}
	
	public void saveEdition() {
		Customer localEdited = edited;
		if (localEdited == null) {
			return;
		}
		
		if (localEdited.getFirstName().length() > 10 || localEdited.getLastName().length() > 10) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Name is too long"));
			return;
		}
		
		manager.getCustomerRepository().save(localEdited);
		manager.refreshData();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Modification applied."));
		PrimeFaces.current().executeScript("PF('editionDialog').hide()");
	}

	public void createNew() {
		this.edited = new Customer("", "");
	}
	
}
