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


    @Bean
    CommandLineRunner demo(AddressBookRepository abRepo, BuddyInfoRepository buddyRepo) {
        return args -> {
            AddressBook ab = new AddressBook("All Contacts");
            ab.addBuddy(new BuddyInfo("Abdelrahman", "873-353-1485"));
            ab.addBuddy(new BuddyInfo("Elsayed", "613-112-7402"));
            abRepo.save(ab);

            System.out.println("Saved AddressBook id=" + ab.getId());
            System.out.println("All buddies:");
            buddyRepo.findAll().forEach(System.out::println);
        };
    }
}
