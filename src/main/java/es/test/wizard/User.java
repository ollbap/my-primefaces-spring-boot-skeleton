package es.test.wizard;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String firstname;
     
    private String lastname;
     
    private Integer age;
     
    private String street;
     
    private String city;
     
    private String postalCode;
     
    private String info;
     
    private String email;
     
    private String phone;
}