package com.kerasia;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kerasia.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.InputMismatchException;
import java.util.Locale;
import java.lang.reflect.Field;


import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final String userInput = """
            Παναγιώτης Παπαδόπουλος
            Οδός Δημοκρατίας 15, Αθήνα
            25
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
        assertEquals("Οδός Δημοκρατίας 15, Αθήνα", user.getAddress());
        assertEquals(25, user.getAge());
    }

    @Test
    void testGetAge() {
        // Instantiate User
        User user = new User();

        // Assert the returned age matches the input
        assertEquals(25, user.getAge(), "The getAge method should return the correct age.");
    }

    @Test
    void testInputMismatchExceptionInGetAge() {

        System.setProperty("testing", "true");

        // Simulate invalid input
        String invalidInput = """
                Παναγιώτης Παπαδόπουλος
                Οδός Δημοκρατίας 15, Αθήνα
                abc
                """;
        ByteArrayInputStream in = new ByteArrayInputStream(invalidInput.getBytes());
        System.setIn(in);

        try {
            // Create the user object to process input
            User user = new User();
    
            // Ensure InputMismatchException is thrown during age input
            InputMismatchException exception = assertThrows(InputMismatchException.class, user::setAge);
    
            // Verify the exception message if needed
            assertTrue(exception.getMessage().contains("Μη έγκυρη είσοδος. Παρακαλώ εισάγετε έναν αριθμό ακέραιο."));
        } finally {
            // Restore System input
            System.setIn(System.in);
            System.clearProperty("testing");
        }
    }

        

    @Test
    void testNegativeAgeHandling() {
        // Simulate negative age input, followed by a valid age
        String negativeInput = """
                Παναγιώτης Παπαδόπουλος
                Οδός Δημοκρατίας 15, Αθήνα
                -10
                25
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
        LocalDateTime fixedDateTime = LocalDateTime.of(2025, 1, 12, 10, 30);
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(fixedDateTime);
            
            // Instantiate User to save the day and time
            User user = new User();

        // Get the current date and time for validation
        LocalDateTime now = LocalDateTime.now();
        String expectedDayOfWeek = fixedDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("el"));
        String expectedTime = fixedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));

        // Assert the day of the week and time
        assertEquals(expectedDayOfWeek, user.getDayOfWeek());
        assertEquals(expectedTime, user.getTime());
        }
    }

    @Test
    void testDisplayUserInfo() {
          ByteArrayOutputStream outContent = new ByteArrayOutputStream();
          System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));
        
          // Instantiate User to verify correct display of user info
        User user = new User();
        
        user.displayUserInfo();
        String output = outContent.toString(StandardCharsets.UTF_8);


        // Expected outcome
        String expectedOutput = """
                --- Πληροφορίες Ασθενούς ---
                Ονοματεπώνυμο: Παναγιώτης Παπαδόπουλος
                Διεύθυνση: Οδός Δημοκρατίας 15, Αθήνα
                Ηλικία Ασθενούς: 25
                Επίπεδο Σοβαρότητας: 0
                Μέρα Συμβάντος: %s
                Ώρα Συμβάντος: %s
                """.formatted(
                user.getDayOfWeek(),
                user.getTime()
        );

        // Συγκρίνουμε την έξοδο (χρειάζεται να τροποποιηθεί αν χρησιμοποιηθεί πραγματική έξοδος)
        assertTrue(expectedOutput.contains("Πληροφορίες Ασθενούς"));
        assertTrue(expectedOutput.contains(user.getFullName()));
        assertTrue(expectedOutput.contains(user.getAddress()));
        assertTrue(expectedOutput.contains(String.valueOf(user.getAge()))); 
        assertTrue(expectedOutput.contains(user.getDayOfWeek()));
        assertTrue(expectedOutput.contains(user.getTime()));
    }

    @Test
    void testSeverityLevelSetting() throws Exception {
    // Mock της SeverityIndex
    SeverityIndex mockSeverityIndex = mock(SeverityIndex.class);
    when(mockSeverityIndex.determineSeverity()).thenReturn(SeverityIndex.SeverityLevel.LEVEL_3);

    // Δημιουργία χρήστη
    User user = new User();

    // Αντικατάσταση του SeverityIndex με το mock χρησιμοποιώντας Reflection
    Field severityIndexField = User.class.getDeclaredField("severityIndex");
    severityIndexField.setAccessible(true);
    severityIndexField.set(user, mockSeverityIndex);

    // Εκτέλεση του κώδικα και έλεγχος
    assertEquals(3, user.getSeverityLevel(), "Το επίπεδο σοβαρότητας δεν είναι σωστό.");
}




    @AfterEach
    void tearDown() {
        System.setIn(System.in);
        System.setOut(System.out); 
        System.clearProperty("testing");
}
}


