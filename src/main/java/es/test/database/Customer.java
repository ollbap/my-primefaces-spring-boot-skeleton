package es.test.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Customer {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id = 0l;
    
    @Pattern(regexp="[a-zA-Z']+")
    private String firstName = "";
    @Pattern(regexp="[a-zA-Z']+")
    private String lastName = "";
    @Min(value=9)
    private Integer age = 20;
    
	public Customer(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

    protected Customer() {}


    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

}