package idx.kafka.producer;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class ProducerApplication {
	public static void main(String[] args) {
		if (args.length < 2){

			System.out.println("please run : java -jar app.jar --config=kafka_config.config listener_port ");
			System.exit(0);
		}
		String port = args[1];

		SpringApplication app = new SpringApplication(ProducerApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", port));

		app.run(args);
	}
}
