package giglist.giglist.domain;

import java.util.Locale.Category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Gig {
    @ManyToOne
    @JoinColumn(name = "artistid")
    private Genre genre;
    // booklistin category -> genre?

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String artist;

    // LocalDate päivämäärä!
    // getterit, setterit, jne...

    public Long getId() {
        return id;
    }

}
