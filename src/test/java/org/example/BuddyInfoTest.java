package org.example;

import org.junit.jupiter.api.Test;                    // JUnit 5
import static org.junit.jupiter.api.Assertions.*;     // assertions

public class BuddyInfoTest {

    @Test
    public void testGetters() {
        BuddyInfo b = new BuddyInfo("Alice", "555-1234");
        assertEquals("Alice", b.getName());
        assertEquals("555-1234", b.getPhone());
    }

    @Test
    public void testToString() {
        BuddyInfo b = new BuddyInfo("Bob", "555-9876");
        assertEquals("Bob (555-9876)", b.toString());
    }
}
