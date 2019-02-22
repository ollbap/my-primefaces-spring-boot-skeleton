package es.test.customer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.test.database.Customer;
import es.test.database.CustomerRepository;
import lombok.Getter;

@RestController
public class RestCustomerService {
	
	@Inject @Getter
	private CustomerRepository customerRepository;
	
	@RequestMapping("/customersRestService")
    public List<Customer> getAllCustomers(@RequestParam(value="filter", defaultValue="") String filter){
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);
		
		if (!filter.isEmpty()) {
			customers.removeIf(c -> !c.getFirstName().contains(filter));
		}
		
        return customers;
    }

}
