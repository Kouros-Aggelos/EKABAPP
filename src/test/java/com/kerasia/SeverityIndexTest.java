package com.kerasia;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kerasia.SeverityIndex.InvalidInputException;

class SeverityIndexTest {

    private SeverityIndex severityIndex;

    @BeforeEach
    void setUp() {
        System.out.println("setUp is called");
        //severityIndex = new SeverityIndex();
    }

    @Test
    /*
     * Σε κάθε test χρησιμοποιώ την τεχνική του ByteArrayInputStream για να
     * προσομοιώσω
     * την είσοδο του χρήστη από το πληκτρολόγιο 
     */ 
     

    void testImmediateInterventionRequired() throws InvalidInputException {
        /* Simulate user input for "ναι" (Immediate intervention) */
        String in = "ναι\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(in.getBytes()));

        severityIndex = new SeverityIndex(scanner);
        // αναθέτει τη ροή εισόδου ναι στον Byte πίνακα ώστε η μέθοδος να κρατήσει την
        // τιμή ναι

        SeverityIndex.SeverityLevel result = severityIndex.determineSeverity();
        assertEquals(SeverityIndex.SeverityLevel.LEVEL_1, result);
        assertNotNull(severityIndex);

    }

    @Test
    void testNonImmediateSingleResource() throws InvalidInputException {
        // Simulate user input for "όχι" then "1" (Non-immediate, 1 resource)
        String in = "όχι\n1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(in.getBytes()));
        severityIndex = new SeverityIndex(scanner);

        SeverityIndex.SeverityLevel result = severityIndex.determineSeverity();
        assertEquals(SeverityIndex.SeverityLevel.LEVEL_4, result);
    }

    @Test
    void testNonImmediateMultipleResourcesStableVitals() throws InvalidInputException {
        // Simulate user input for "όχι", "2", "ναι" (Non-immediate, 2 resources, stable
        // vitals)
        String in = "όχι\n2\nναι\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(in.getBytes()));
        severityIndex = new SeverityIndex(scanner);

        SeverityIndex.SeverityLevel result = severityIndex.determineSeverity();
        assertEquals(SeverityIndex.SeverityLevel.LEVEL_3, result);
    }

    @Test
    void testNonImmediateMultipleResourcesUnstableVitals() throws InvalidInputException {
        // Simulate user input for "όχι", "2", "όχι" (Non-immediate, 2 resources,
        // unstable vitals)
        String in = "όχι\n2\nόχι\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(in.getBytes()));
        severityIndex = new SeverityIndex(scanner);


        SeverityIndex.SeverityLevel result = severityIndex.determineSeverity();
        assertEquals(SeverityIndex.SeverityLevel.LEVEL_2, result);
    }

    @Test
    void testInvalidImmediateInput() throws InvalidInputException {
        // Simulate invalid user input

        //With the invalid input, the InvalidINputException is thrown
        //then caught and after the correct input LEVEL_1 is returned
        String in = "invalid\nναι\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(in.getBytes()));
        severityIndex = new SeverityIndex(scanner);

        SeverityIndex.SeverityLevel level = severityIndex.determineSeverity();
        assertEquals(SeverityIndex.SeverityLevel.LEVEL_1, level);
    }

    @Test
    void testInvalidResourcesInput() throws InvalidInputException {
        // Simulate invalid resource input and then valid
        String in = "όχι\ninvalid\n2\nναι\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(in.getBytes()));
        severityIndex = new SeverityIndex(scanner);


        SeverityIndex.SeverityLevel level = severityIndex.determineSeverity();
        assertEquals(SeverityIndex.SeverityLevel.LEVEL_3, level);
    }

    @Test
    void testToStringOutput() throws SeverityIndex.InvalidInputException {
        // Simulate input and verify the toString() method
        String in = "ναι\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(in.getBytes()));
        severityIndex = new SeverityIndex(scanner);

        severityIndex.determineSeverity();
        String expectedOutput = "Level: 1, Description: Critical, immediate life-saving intervention required";
        assertEquals(expectedOutput, severityIndex.toString());
    }
}

