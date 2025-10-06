package org.example;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    // Unidirectional one-to-many; FK lives in BuddyInfo
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "addressbook_id")
    private List<BuddyInfo> buddies = new ArrayList<>();

    public AddressBook() { }
    public AddressBook(String title) { this.title = title; }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<BuddyInfo> getBuddies() { return buddies; }
    public void addBuddy(BuddyInfo buddy) { if (buddy != null) buddies.add(buddy); }
    public boolean removeBuddy(BuddyInfo buddy) { return buddies.remove(buddy); }

    @Override
    public String toString() {
        return "AddressBook{id=" + id + ", title='" + title + "', buddies=" + buddies + "}";
    }
}
