package giglist.giglist.domain;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "d.M.yyyy")
    private LocalDate pvm;
    private String city;
    private String country;

    // LocalDate päivämäärä!
    // getterit, setterit, jne...

    public Gig(String artist, Genre genre, LocalDate pvm, String city, String country) {
        this.genre = genre;
        this.artist = artist;
        this.pvm = pvm;
        this.city = city;
        this.country = country;
    }

    public Gig() {

    }

    public Long getId() {
        return id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public LocalDate getPvm() {
        return pvm;
    }

    public void setPvm(LocalDate pvm) {
        this.pvm = pvm;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Gig [artist=" + artist + ", pvm=" + pvm + ", city=" + city + ", country=" + country + "]";
    }

    public void setId(Long id) {
        this.id = id;
    }

}
