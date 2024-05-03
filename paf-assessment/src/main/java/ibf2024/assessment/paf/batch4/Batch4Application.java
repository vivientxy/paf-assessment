package ibf2024.assessment.paf.batch4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ibf2024.assessment.paf.batch4.repositories.BeerRepository;

@SpringBootApplication
public class Batch4Application implements CommandLineRunner {

	@Autowired
	BeerRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Batch4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// System.out.println(">>>> " + repo.getBeersFromBrewery(2));
	}

}
