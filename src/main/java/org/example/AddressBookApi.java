package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AddressBookApi {

    private final AddressBookRepository abRepo;
    private final BuddyInfoRepository buddyRepo;

    public AddressBookApi(AddressBookRepository abRepo, BuddyInfoRepository buddyRepo) {
        this.abRepo = abRepo;
        this.buddyRepo = buddyRepo;
    }

    // Create a new AddressBook
    @PostMapping(value = "/addressbooks", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AddressBook> create(@RequestBody AddressBook incoming) {
        AddressBook ab = new AddressBook(incoming.getTitle() == null ? "Untitled" : incoming.getTitle());
        return new ResponseEntity<>(abRepo.save(ab), HttpStatus.CREATED);
    }

    // Get an AddressBook by ID
    @GetMapping(value = "/addressbooks/{id}", produces = "application/json")
    public ResponseEntity<AddressBook> get(@PathVariable Long id) {
        return abRepo.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add a Buddy to an AddressBook
    @PostMapping(value = "/addressbooks/{id}/buddies", consumes = "application/json", produces = "application/json")
    public ResponseEntity<AddressBook> addBuddy(@PathVariable Long id, @RequestBody BuddyInfo buddy) {
        return abRepo.findById(id).map(ab -> {
            ab.addBuddy(new BuddyInfo(buddy.getName(), buddy.getPhone()));
            return ResponseEntity.ok(abRepo.save(ab));
        }).orElse(ResponseEntity.notFound().build());
    }

    // Delete a Buddy from an AddressBook
    @DeleteMapping("/addressbooks/{id}/buddies/{buddyId}")
    public ResponseEntity<Void> removeBuddy(@PathVariable Long id, @PathVariable Long buddyId) {
        return abRepo.findById(id).map(ab -> {
            buddyRepo.findById(buddyId).ifPresent(ab::removeBuddy);
            abRepo.save(ab);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElse(ResponseEntity.notFound().build());
    }
}
