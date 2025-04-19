package giglist.giglist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import giglist.giglist.domain.Genre;
import giglist.giglist.domain.GenreRepository;
import giglist.giglist.domain.Gig;
import giglist.giglist.domain.GigRepository;
import giglist.giglist.domain.User;
import giglist.giglist.domain.UserRepository;

@Controller

public class GigContoller {
    @Autowired
    private GigRepository repository;
    @Autowired
    private GenreRepository grepository;
    @Autowired
    private UserRepository urepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Show gigs in a list
    @GetMapping("/giglist")
    public String gigList(Model model) {
        model.addAttribute("gigs", repository.findAll());
        return "giglist";
    }

    // Get all gigs
    @GetMapping("/gigs")
    public @ResponseBody List<Gig> gigListRest() {
        return (List<Gig>) repository.findAll();
    }

    // Get a gig
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

    // Add a genre
    @RequestMapping("/addgenre")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        model.addAttribute("genres", grepository.findAll());
        return "addgenre";
    }

    // Save a genre
    @PostMapping("/savegenre")
    public String saveGenre(Genre genre, Model model) {
        grepository.save(genre);
        return "redirect:/giglist";
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

    // Register
    @GetMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    // Save new user
    @PostMapping("/saveuser")
    public String saveUser(@ModelAttribute User user, Model model) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        user.setRole("USER");
        urepository.save(user);
        return "redirect:/login";
    }

}