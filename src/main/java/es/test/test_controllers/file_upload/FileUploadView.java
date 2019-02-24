package es.test.test_controllers.file_upload;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;

@Named
@ViewScoped
public class FileUploadView {
    
    @SuppressWarnings("static-method")
	public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded. Size="+event.getFile().getSize());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}