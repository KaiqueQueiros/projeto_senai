package trevo.agro.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import trevo.agro.api.culture.cultureRepository;

@SpringBootApplication
public class ApiApplication implements CommandLineRunner {
	private cultureRepository repository;
	public ApiApplication(cultureRepository repository) {
		this.repository = repository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*Scanner scanner = new Scanner(System.in);
		Culture culture = new Culture();
		culture.setDescription("CEREAIS");
		culture.setDescription("HORTALIÇAS");
		culture.setDescription("PECUÁRIA");
		culture.setDescription("CANA");
		culture.setDescription("FRUTAS");
		culture.setDescription("CAFÉ");
		culture.setDescription("FLORES");


		repository.save(culture);*/

	}
}
