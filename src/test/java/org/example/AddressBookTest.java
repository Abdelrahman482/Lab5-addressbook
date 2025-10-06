package org.example;
import org.junit.jupiter.api.Test;                    // JUnit 5
import static org.junit.jupiter.api.Assertions.*;     // assertions

public class AddressBookTest {

    @Test
    public void testAddBuddy() {
        AddressBook book = new AddressBook();
        BuddyInfo buddy = new BuddyInfo("Alice", "555-1234");
        book.addBuddy(buddy);
        assertTrue(book.getBuddies().contains(buddy));
    }

    @Test
    public void testRemoveBuddy() {
        AddressBook book = new AddressBook();
        BuddyInfo buddy = new BuddyInfo("Bob", "555-9876");
        book.addBuddy(buddy);
        assertTrue(book.removeBuddy(buddy));
        assertFalse(book.getBuddies().contains(buddy));
    }
}
