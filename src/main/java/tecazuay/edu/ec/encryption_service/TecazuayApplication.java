package tecazuay.edu.ec.encryption_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "${info.project.title}", version = "${info.project.version}", description = "${info.project.description}"))
@SpringBootApplication
public class TecazuayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TecazuayApplication.class, args);
	}

}
