package com.kerasia;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SuitableTest {

    @Test
    void testSelectDepartmentValidInput() {
        // Είσοδος: "1" (έγκυρη επιλογή)
        String input = "1\n";
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        Suitable suitable = new Suitable();
        String result = suitable.selectDepartment(scanner);

        // Αναμενόμενο: "Παθολογική"
        assertEquals("Παθολογική", result);
    }


    @Test

    void testSelectDepartmentOutOfBounds() {
   
        // Είσοδος: "33\n1\n" (εκτός ορίων, ακολουθούμενη από έγκυρη επιλογή)
   
        String input = "33\n1\n";
   
        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

    
        Suitable suitable = new Suitable();
    
        String result = suitable.selectDepartment(scanner);

   
        // Αναμενόμενο: "Παθολογική" (μετά την 1η λανθασμένη επιλογή)
    
        assertEquals("Παθολογική", result);

    }

    @Test

    void testSelectDepartmentInvalidInput() {

        // Είσοδος: "abc\n1\n" (μη αριθμητική, ακολουθούμενη από έγκυρη επιλογή)

        String input = "abc\n1\n";

        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));


    
        Suitable suitable = new Suitable();
    
        String result = suitable.selectDepartment(scanner);

    
        // Αναμενόμενο: "Παθολογική" (μετά τη λανθασμένη είσοδο)
    
        assertEquals("Παθολογική", result);

    }

    @Test

    void testSelectDepartmentMultipleInvalidInputs() {

        // Είσοδος: "abc\n-1\n33\n1\n" (διάφορες λανθασμένες τιμές, ακολουθούμενες από έγκυρη)

        String input = "abc\n-1\n33\n1\n";

        Scanner scanner = new Scanner(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));


    
        Suitable suitable = new Suitable();
    
        String result = suitable.selectDepartment(scanner);

    
        // Αναμενόμενο: "Παθολογική" (μετά από όλες τις λανθασμένες εισόδους)
    
        assertEquals("Παθολογική", result);

    }
}
