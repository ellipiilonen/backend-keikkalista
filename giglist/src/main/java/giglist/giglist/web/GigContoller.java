package giglist.giglist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import giglist.giglist.domain.GenreRepository;
import giglist.giglist.domain.Gig;
import giglist.giglist.domain.GigRepository;

@Controller

// LocalDate-päivämäärät!!
// rek ja kirj
// JWT?
// muuta nämä bookstoresta tähän projektiin

public class GigContoller {
    @Autowired
    private GigRepository repository;
    @Autowired
    private GenreRepository grepository;

    // Get all gigs
    @GetMapping("/giglist")
    public String gigList(Model model) {
        model.addAttribute("gigs", repository.findAll());
        return "giglist";
    }

    @GetMapping("/gigs")
    public @ResponseBody List<Gig> gigListRest() {
        return (List<Gig>) repository.findAll();
    }

    // Select a gig
    @GetMapping("/gig/{id}")
    public @ResponseBody Optional<Gig> findGigRest(@PathVariable("id") Long gigId) {
        return repository.findById(gigId);
    }

    // Add a gig
    @RequestMapping("/add")
    public String addGig(Model model) {
        model.addAttribute("gig", new Gig());
        model.addAttribute("genres", grepository.findAll());
        return "addgig";
    }

    // Save a gig
    @PostMapping("/save")
    public String save(Gig gig) {
        repository.save(gig);
        return "redirect:/giglist";
    }

    // Delete a gig
    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteGig(@PathVariable("id") Long gigId, Model model) {
        repository.deleteById(gigId);
        return "redirect:/giglist";
    }

    // Edit a gig
    @RequestMapping("/edit/{id}")
    public String editGig(@PathVariable("id") Long gigId, Model model) {
        model.addAttribute("gig", repository.findById(gigId));
        model.addAttribute("genres", grepository.findAll());
        return "editgig";
    }

    // Login
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(Model model) {
        return "redirect:/login?logout";
    }

}