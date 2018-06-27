package Application;

import Security.JwtFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"Controller", "Service", "Security", "Repository" })
@EntityScan("Domain")
@EnableJpaRepositories("Repository")
public class Microservice3Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Microservice3Application.class, args);
	}

	@Override
	public void run(String... strings) {
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/users/*", "/messages/*");

		return registrationBean;
	}
}
