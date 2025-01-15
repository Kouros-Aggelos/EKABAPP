package com.kerasia;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        // Redirect system output for testing
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));

        // Δημιουργία spy αντικειμένου User
        String userInput = """
                Παναγιώτης Παπαδόπουλος
                Οδός Δημοκρατίας 15, Αθήνα
                30
                ναι
                """;
        Scanner scanner = new Scanner(userInput);
        user = spy(new User(scanner)); // Spy αντί για mock
    }

    @Test
    void testUserInputHandling() {
        // Ελέγχουμε τα δεδομένα που εισήχθησαν
        assertEquals("Παναγιώτης Παπαδόπουλος", user.getFullName());
        assertEquals("Οδός Δημοκρατίας 15, Αθήνα", user.getAddress());
        assertEquals(30, user.getAge());
        assertEquals(1, user.getSeverityLevel());
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }
}
