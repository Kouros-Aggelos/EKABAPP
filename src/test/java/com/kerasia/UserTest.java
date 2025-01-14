package com.kerasia;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;
import java.lang.reflect.Field;

import org.mockito.MockedStatic;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private ByteArrayInputStream inContent;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        // Redirect system input to the userInput for testing purposes
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent, true, StandardCharsets.UTF_8));

    }

    @Test
    void testUserInputHandling() {
        String userInput = """
                Παναγιώτης Παπαδόπουλος
                Οδός Δημοκρατίας 15, Αθήνα
                25
                ναι
                """;
        
        inContent = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inContent);

        // Instantiate User to process the input
        User user = new User(scanner);

        // Assert fields based on the input provided
        assertEquals("Παναγιώτης Παπαδόπουλος", user.getFullName());
        assertEquals("Οδός Δημοκρατίας 15, Αθήνα", user.getAddress());
        assertEquals(25, user.getAge());
        assertEquals(1, user.getSeverityLevel());
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
        inContent = new ByteArrayInputStream(invalidInput.getBytes());
        System.setIn(inContent);

        try {
            InputMismatchException exception = assertThrows(InputMismatchException.class, () -> {
                new User(new Scanner(System.in));
            });

            assertTrue(exception.getMessage().contains("Μη έγκυρη είσοδος"),
                   "The exception message should indicate invalid input.");
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
                ναι
                """;

        inContent = new ByteArrayInputStream(negativeInput.getBytes());
        Scanner scanner = new Scanner(inContent);

        // Create User and verify correct handling of valid input
        User user = new User(scanner);

        // Assert that the age is correctly set to the valid value (25)
        assertEquals(25, user.getAge(), "The negative input should be ignored, and valid input should set the age.");
    }

    @Test
    void testSaveDayAndTime() {
        LocalDateTime fixedDateTime = LocalDateTime.of(2025, 1, 12, 10, 30);
        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(fixedDateTime);

            String userInput = """
                    Παναγιώτης Παπαδόπουλος
                    Οδός Δημοκρατίας 15, Αθήνα
                    25
                    ναι
                    """;

            inContent = new ByteArrayInputStream(userInput.getBytes());
            Scanner scanner = new Scanner(inContent);

            // Instantiate User to save the day and time
            User user = new User(scanner);

            // Get the current date and time for validation
            String expectedDayOfWeek = fixedDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL,
                    Locale.forLanguageTag("el"));
            String expectedTime = fixedDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));

            // Assert the day of the week and time
            assertEquals(expectedDayOfWeek, user.getDayOfWeek());
            assertEquals(expectedTime, user.getTime());
        }
    }

    @Test
    void testDisplayUserInfo() {

        String userInput = """
            Παναγιώτης Παπαδόπουλος
            Οδός Δημοκρατίας 15, Αθήνα
            25
            ναι
            """;

        inContent = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inContent);

        // Instantiate User to verify correct display of user info
        User user = new User(scanner);
        user.displayUserInfo();

        
        // Expected outcome
        String output = outContent.toString(StandardCharsets.UTF_8);
                
        // Συγκρίνουμε την έξοδο (χρειάζεται να τροποποιηθεί αν χρησιμοποιηθεί
        // πραγματική έξοδος)
        assertTrue(output.contains("Πληροφορίες Ασθενούς"));
        assertTrue(output.contains("Παναγιώτης Παπαδόπουλος"));
        assertTrue(output.contains("Οδός Δημοκρατίας 15, Αθήνα"));
        assertTrue(output.contains("25"));
        assertTrue(output.contains(user.getDayOfWeek()));
        assertTrue(output.contains(user.getTime()));
    }

    @Test
    void testSeverityLevelSetting() throws Exception {
        // Mock της SeverityIndex
        SeverityIndex mockSeverityIndex = mock(SeverityIndex.class);
        when(mockSeverityIndex.determineSeverity()).thenReturn(SeverityIndex.SeverityLevel.LEVEL_3);
        
        //Simulate scanner input
        String simulatedInput = """
            Παναγιώτης Παπαδόπουλος
            Οδός Δημοκρατίας 15, Αθήνα
            25
            """;
        Scanner scanner = new Scanner(new ByteArrayInputStream(simulatedInput.getBytes()));
        

        // Δημιουργία χρήστη
        User user = new User(scanner);

        // Αντικατάσταση του SeverityIndex με το mock χρησιμοποιώντας Reflection
        Field severityIndexField = User.class.getDeclaredField("severityIndex");
        severityIndexField.setAccessible(true);
        severityIndexField.set(user, mockSeverityIndex);

        user.getUserInput();

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
