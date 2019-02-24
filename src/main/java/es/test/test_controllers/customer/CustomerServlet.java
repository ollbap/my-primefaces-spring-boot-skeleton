package es.test.test_controllers.customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.annotation.Nullable;

import es.test.database.Customer;
import es.test.database.CustomerRepository;
import lombok.Getter;

@WebServlet("/customersServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	@Getter
	private CustomerRepository customerRepository;

	@Override
	protected void doGet(@Nullable HttpServletRequest req, @Nullable HttpServletResponse response) throws IOException {
		if (response == null || req == null) {
			return;
		}

		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll().forEach(customers::add);

		response.getOutputStream().write(customers.toString().getBytes());
	}

}
