package giglist.giglist;

import java.util.Locale.Category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import giglist.giglist.domain.GenreRepository;
import giglist.giglist.domain.UserRepository;

@SpringBootApplication
public class GiglistApplication {

	// TODO: päivitä tähän projektiin!
	public static void main(String[] args) {
		SpringApplication.run(GiglistApplication.class, args);
	}

	@Bean
	public CommandLineRunner books(GenreRepository, grepository, GigRepository repository,
			UserRepository urepository) {
		return (args) -> {

			Category novel = new Category("Novel");
			Category autobio = new Category("Autobiography");
			Category fantasy = new Category("Fantasy");

			crepository.save(novel);
			crepository.save(autobio);
			crepository.save(fantasy);

			Book b1 = new Book("Testikirja", "Testi Testaaja", 2025, "123456-7", 29.9, novel);
			Book b2 = new Book("Ellin elämänkerta", "Elli Piilonen", 2003, "535252-7", 88.0, autobio);
			Book b3 = new Book("Ollin oravanpyörä", "Olli Oravainen", 1996, "48229-97", 32.9, fantasy);

			repository.save(b1);
			repository.save(b2);
			repository.save(b3);

			// USER1password password123
			// USER2 password kissa123

			User user1 = new User("user", "$2a$12$nZeyF8xDd6xjuQiXRco5o.XWqFgD0tGzhuehbdJ2qVngz4BphgN3a", "USER");
			User user2 = new User("admin", "$2a$12$xOuwfiF75nNldZUidThzi.fUrv6gdD4nyTg6EXuS1YocnqSHWv5/i", "ADMIN");

			urepository.save(user1);
			urepository.save(user2);

		};
	}
}
