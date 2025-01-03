package com.kerasia;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kerasia.User;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final String userInput = """
            Παναγιώτης Παπαδόπουλος
            30
            Οδός Δημοκρατίας 15
            Δήμος Αθηναίων
            """;

    @BeforeEach
    void setUp() {
        // Redirect system input to the userInput for testing purposes
        ByteArrayInputStream in = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(in);
    }

    @Test
    void testUserInputHandling() {
        // Instantiate User to process the input
        User user = new User();

        // Assert fields based on the input provided
        assertEquals("Παναγιώτης Παπαδόπουλος", user.getFullName());
        assertEquals(30, user.getAge());
        assertEquals("Οδός Δημοκρατίας 15", user.getAddress());
        assertEquals("Δήμος Αθηναίων", user.getMunicipality());
    }

    @Test
    void testGetAge() {
        // Instantiate User
        User user = new User();

        // Assert the returned age matches the input
        assertEquals(30, user.getAge(), "The getAge method should return the correct age.");
    }

    @Test
    void testInputMismatchExceptionInGetAge() {
        // Simulate invalid input
        String invalidInput = """
                Παναγιώτης Παπαδόπουλος
                abc
                Οδός Δημοκρατίας 15
                Δήμος Αθηναίων
                """;
        ByteArrayInputStream in = new ByteArrayInputStream(invalidInput.getBytes());
        System.setIn(in);

        // Instantiate User and ensure InputMismatchException is handled in the flow
        Exception exception = assertThrows(Exception.class, User::new);

        // Verify the exception message if needed
        assertTrue(exception.getMessage().contains("Messagepart here"));
    }

    @Test
    void testNegativeAgeHandling() {
        // Simulate negative age input, followed by a valid age
        String negativeInput = """
                Παναγιώτης Παπαδόπουλος
                -10
                25
                Οδός Δημοκρατίας 15
                Δήμος Αθηναίων
                """;
        ByteArrayInputStream in = new ByteArrayInputStream(negativeInput.getBytes());
        System.setIn(in);

        // Create User and verify correct handling of valid input
        User user = new User();

        // Assert that the age is correctly set to the valid value (25)
        assertEquals(25, user.getAge(), "The negative input should be ignored, and valid input should set the age.");
    }


    @Test
    void testSaveDayAndTime() {
        // Instantiate User to save the day and time
        User user = new User();

        // Get the current date and time for validation
        LocalDateTime now = LocalDateTime.now();
        String expectedDayOfWeek = now.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("el"));
        String expectedTime = now.format(DateTimeFormatter.ofPattern("HH:mm"));

        // Assert the day of the week and time
        assertEquals(expectedDayOfWeek, user.getDayOfWeek());
        assertEquals(expectedTime, user.getTime());
    }

    @Test
    void testToString() {
        // Instantiate User to test the toString method
        User user = new User();

        // Build the expected string
        String expected = "Πληροφορίες χρήστη: [Όνοματεπώνυμο=Παναγιώτης Παπαδόπουλος, Ηλικία=30, Διεύθυνση=Οδός Δημοκρατίας 15, Δήμος=Δήμος Αθηναίων]";

        // Assert the string representation
        assertEquals(expected, user.toString());
    }
}
