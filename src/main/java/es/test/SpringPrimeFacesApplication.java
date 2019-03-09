package es.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import es.test.database.Customer;
import es.test.database.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@ServletComponentScan
public class SpringPrimeFacesApplication {
	private static final Logger log = LoggerFactory.getLogger(SpringPrimeFacesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringPrimeFacesApplication.class, args);
	}

	@SuppressWarnings("static-method")
	@Bean
	public CommandLineRunner loadDummyTestData(CustomerRepository repository) {
		return (args) -> {
			if (repository.count() > 0) {
				return;
			}
			
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer", 20));
			repository.save(new Customer("Chloe", "O'Brian", 21));
			repository.save(new Customer("Kim", "Bauer", 45));
			repository.save(new Customer("David", "Palmer", 61));
			repository.save(new Customer("Michelle", "Dessler", 54));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info("Customer: {}", customer);
			}
			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L).ifPresent(customer -> {
				log.info("Customer found with findById(1L):");
				log.info("--------------------------------");
				log.info(customer.toString());
				log.info("");
			});

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
	}

}
