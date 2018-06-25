package Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"Controller", "Service", "Security", "Repository" })
@EntityScan("Domain")
@EnableJpaRepositories("Repository")
public class Microservice2Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Microservice2Application.class, args);
	}

	@Override
	public void run(String... strings) {
	}
}