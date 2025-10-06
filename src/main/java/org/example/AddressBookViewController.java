package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddressBookViewController {

    private final AddressBookRepository abRepo;

    public AddressBookViewController(AddressBookRepository abRepo) {
        this.abRepo = abRepo;
    }

    // Show an address book and its buddies
    @GetMapping("/addressbooks/{id}")
    public String show(@PathVariable Long id, Model model) {
        AddressBook ab = abRepo.findById(id).orElse(null);
        model.addAttribute("ab", ab);
        return "addressbook"; // This will load templates/addressbook.html
    }
}
