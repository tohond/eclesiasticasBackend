package pixelpulse.eclesiasticasbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EclesiasticasbackendApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(EclesiasticasbackendApplication.class, args);
	}
	
	
}
