package Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import Security.JwtFilter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = {"Controller", "Service", "Security", "Repository" })
@EntityScan("domain")
@EnableJpaRepositories("Repository")
public class Microservice1Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger("DB TEST");

	public static void main(String[] args) {
		SpringApplication.run(Microservice1Application.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns(); // "/customer/*", "/product/add", "/product/update", "/purchase/process/*");

		return registrationBean;
	}

	@Override
	public void run(String... strings) {
	}
}
