package giglist.giglist;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import giglist.giglist.domain.Genre;
import giglist.giglist.domain.GenreRepository;
import giglist.giglist.domain.Gig;
import giglist.giglist.domain.GigRepository;
import giglist.giglist.domain.User;
import giglist.giglist.domain.UserRepository;

@SpringBootApplication
public class GiglistApplication {

	// TODO: päivitä tähän projektiin!

	private static final Logger log = LoggerFactory.getLogger(GiglistApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GiglistApplication.class, args);
	}

	@Bean
	public CommandLineRunner gigs(GenreRepository grepository, GigRepository repository,
			UserRepository urepository) {
		return (args) -> {

			// Adding genre examples
			log.info("Saving genre examples");
			Genre rock = new Genre("Rock");
			Genre pop = new Genre("Pop");
			Genre rap = new Genre("Rap");

			grepository.save(rock);
			grepository.save(pop);
			grepository.save(rap);

			// Adding gig examples
			log.info("Saving gig examples");
			Gig g1 = new Gig("Hevisaurus", rock, LocalDate.of(2025, 1, 2), "Helsinki", "Finland");
			Gig g2 = new Gig("Robin", pop, LocalDate.of(2024, 5, 9), "Jyväskylä", "Finland");
			Gig g3 = new Gig("Elastinen", rap, LocalDate.of(2023, 4, 8), "Paris", "France");

			repository.save(g1);
			repository.save(g2);
			repository.save(g3);

			// Adding user examples
			// user password user
			// admin password admin

			User user1 = new User("user", "$2a$10$0a.GgYCpzDRNvGeNwN0UkuTC94SUmGVkJhEETwg6t6ysc1/XBFW2O", "ROLE_USER");
			User user2 = new User("admin", "$2a$10$kcrXn7eN.H1a..WumjqWLevc71PTF3uZrAOepksGmHkyau2ABMBi2",
					"ROLE_ADMIN");

			urepository.save(user1);
			urepository.save(user2);

		};
	}
}
