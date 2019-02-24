package es.test;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import es.test.database.Customer;
import es.test.database.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
	@Inject
	private CustomerRepository customerRepository;

    @Test
    public void customerRepositoy() throws Exception {
    	Iterable<Customer> all = customerRepository.findAll();
    	final AtomicInteger count = new AtomicInteger(0);
    	all.forEach(c -> count.incrementAndGet());
    	assert count.get() == 5;
    }

}