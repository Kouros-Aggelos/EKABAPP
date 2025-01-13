package com.kerasia;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kerasia.SeverityIndex.InvalidInputException;

class SeverityIndexTest {

    private SeverityIndex severityIndex;

    @BeforeEach
    void setUp() {
        System.out.println("setUp is called");
        severityIndex = new SeverityIndex();
    }

    @Test
    /*
     * Σε κάθε test χρησιμοποιώ την τεχνική του ByteArrayInputStream για να
     * προσομοιώσω
     * την είσοδο του χρήστη από το πληκτρολόγιο (που θα ήταν κανονικά μέσω scanner
     * και System.in)
     */

    void testImmediateInterventionRequired() throws InvalidInputException {
        /* Simulate user input for "ναι" (Immediate intervention) */
        InputStream in = new ByteArrayInputStream("ναι\n".getBytes());
        // αναθέτει τη ροή εισόδου ναι στον Byte πίνακα ώστε η μέθοδος να κρατήσει την
        // τιμή ναι
        System.setIn(in);

        SeverityIndex.SeverityLevel result = severityIndex.determineSeverity();
        assertEquals(SeverityIndex.SeverityLevel.LEVEL_1, result);
        assertNotNull(severityIndex);

    }

    @Test
    void testNonImmediateSingleResource() throws InvalidInputException {
        // Simulate user input for "όχι" then "1" (Non-immediate, 1 resource)
        InputStream in = new ByteArrayInputStream("όχι\n1\n".getBytes());
        System.setIn(in);

        SeverityIndex.SeverityLevel result = severityIndex.determineSeverity();
        assertEquals(SeverityIndex.SeverityLevel.LEVEL_4, result);
    }

    @Test
    void testNonImmediateMultipleResourcesStableVitals() throws InvalidInputException {
        // Simulate user input for "όχι", "2", "ναι" (Non-immediate, 2 resources, stable
        // vitals)
        InputStream in = new ByteArrayInputStream("όχι\n2\nναι\n".getBytes());
        System.setIn(in);

        SeverityIndex.SeverityLevel result = severityIndex.determineSeverity();
        assertEquals(SeverityIndex.SeverityLevel.LEVEL_3, result);
    }

    @Test
    void testNonImmediateMultipleResourcesUnstableVitals() throws InvalidInputException {
        // Simulate user input for "όχι", "2", "όχι" (Non-immediate, 2 resources,
        // unstable vitals)
        InputStream in = new ByteArrayInputStream("όχι\n2\nόχι\n".getBytes());
        System.setIn(in);

        SeverityIndex.SeverityLevel result = severityIndex.determineSeverity();
        assertEquals(SeverityIndex.SeverityLevel.LEVEL_2, result);
    }

    @Test
    void testInvalidImmediateInput() {
        // Simulate invalid user input
        InputStream in = new ByteArrayInputStream("invalid\n".getBytes());
        System.setIn(in);

        Exception exception = assertThrows(InvalidInputException.class, () -> {
            severityIndex.determineSeverity();
        });

        assertEquals("Λανθασμένη εισαγωγή απάντησης. Παρακαλώ δοκιμάστε ξανά", exception.getMessage());
    }

    @Test
    void testInvalidResourcesInput() {
        InputStream in = new ByteArrayInputStream("όχι\ninvalid\n".getBytes());
        System.setIn(in);

        Exception exception = assertThrows(NumberFormatException.class, () -> {
            severityIndex.determineSeverity();
        });

        assertEquals("For input string: \"invalid\"", exception.getMessage());
    }
}
