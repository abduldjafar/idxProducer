package idx.kafka.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ProducerApplication {
	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(ProducerApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8083"));
		System.out.println(args);
		app.run(args);
	}

}
