package personal.afiproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AfiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfiProjectApplication.class, args);
	}

}