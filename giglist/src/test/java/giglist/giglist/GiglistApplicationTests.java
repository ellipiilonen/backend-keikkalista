package giglist.giglist;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import giglist.giglist.domain.Genre;
import giglist.giglist.domain.GenreRepository;
import giglist.giglist.domain.Gig;
import giglist.giglist.domain.GigRepository;
import giglist.giglist.web.GigContoller;

@SpringBootTest
class GiglistApplicationTests {

	@Autowired
	private GigContoller controller;

	@Autowired
	private GigRepository repository;

	@Autowired
	private GenreRepository grepository;

	@Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	@Test
	public void createNewGig() {
		Genre genre = new Genre("TEST");
		grepository.save(genre);
		Gig gig = new Gig("Tester", genre, LocalDate.of(2012, 12, 12), "Helsinki", "Finland");
		repository.save(gig);

		assertThat(gig.getId()).isNotNull();
	}

	@Test
	public void createNewGenre() {
		Genre genre = new Genre("TEST");
		grepository.save(genre);

		List<Genre> genres = grepository.findByName("TEST");
		assertThat(genres).isNotEmpty();
		assertThat(genres.get(0).getName()).isEqualTo("TEST");
	}

	@Test
	public void findByArtistShouldReturnGenre() {
		List<Gig> gigs = repository.findByArtist("Elastinen");
		assertThat(gigs.get(0).getGenre().getName()).isEqualTo("Rap");
	}

	@Test
	public void updateGigCity() {
		Genre genre = new Genre("TEST");
		grepository.save(genre);
		Gig gig = new Gig("Tester", genre, LocalDate.of(2025, 3, 10), "Helsinki", "Finland");
		repository.save(gig);

		gig.setCity("Jyväskylä");
		repository.save(gig);

		Gig updatedGig = repository.findById(gig.getId()).orElse(null);
		assertThat(updatedGig.getCity()).isEqualTo("Jyväskylä");
	}
}
