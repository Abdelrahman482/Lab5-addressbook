package org.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Optional: seeds data so you can see rows immediately
    @Bean
    CommandLineRunner demo(AddressBookRepository abRepo, BuddyInfoRepository buddyRepo) {
        return args -> {
            AddressBook ab = new AddressBook("My Contacts");
            ab.addBuddy(new BuddyInfo("Alice", "613-111-1111"));
            ab.addBuddy(new BuddyInfo("Bob", "613-222-2222"));
            abRepo.save(ab);

            System.out.println("Saved AddressBook id=" + ab.getId());
            System.out.println("All buddies:");
            buddyRepo.findAll().forEach(System.out::println);
        };
    }
}
