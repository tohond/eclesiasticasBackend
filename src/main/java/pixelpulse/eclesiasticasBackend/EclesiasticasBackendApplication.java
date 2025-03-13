package pixelpulse.eclesiasticasBackend;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pixelpulse.eclesiasticasBackend.registro.Registro;

@SpringBootApplication
@RestController
public class EclesiasticasBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EclesiasticasBackendApplication.class, args);
	}
	@GetMapping
	public List<Registro> index(){
		
		return List.of(
				
				);
	}
}
