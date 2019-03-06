package es.test.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles special redirects on specific reques paths and errors.
 */
@Controller
public class MyRedirectsController implements ErrorController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MyRedirectsController.class);
	
	private static final String INDEX_REDIRECT = "redirect:/index.xhtml";
	private static final String ERROR_MAPPING = "/error";

	@RequestMapping(value = "/")
	public static String rootRedirect() {
		LOGGER.info("Redirecting from root to index page");
		return INDEX_REDIRECT;
	}

	@RequestMapping(value = ERROR_MAPPING)
	public static String error() {
		LOGGER.warn("Redirecting internal error");
		return INDEX_REDIRECT;
	}

	@Override
	public String getErrorPath() {
		return ERROR_MAPPING;
	}

}
