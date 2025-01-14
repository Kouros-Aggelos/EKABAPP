package com.kerasia;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Scanner;

public class MainTest {

    @Mock
    private User user;

    @Mock
    private hospitalfind hospitalfind;

    @Mock
    private Scanner scanner;

    @Mock
    private Suitable suitable;

    @Mock
    private Closest closest;

    @Mock
    private hospitalfind finder; // Mocking του finder

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Αρχικοποίηση των mocks
    }

    @Test
    void testHandleLowSeverity() {
        // Setup mock user and behavior
        when(user.getSeverityLevel()).thenReturn(1); // Επίπεδο σοβαρότητας 1
        when(hospitalfind.findHospitals(anyString(), anyString(), anyString()))
                .thenReturn(List.of(new String[] { "Hospital A", "Address A" },
                        new String[] { "Hospital B", "Address B" }));

        // Κλήση της μεθόδου
        Main.handleLowSeverity(user, hospitalfind);

        // Επαλήθευση των αλληλεπιδράσεων με τα mocks
        verify(hospitalfind).findHospitals(eq("Παθολογική"), anyString(), anyString());
        verify(closest).findClosestHospital(eq(user), eq(finder));

    }

    @Test
    void testHandleHighSeverity() {
        // Setup mock user and behavior
        when(user.getSeverityLevel()).thenReturn(5); // Επίπεδο σοβαρότητας 5
        when(suitable.selectDepartment(scanner)).thenReturn("Χειρουργική");
        when(hospitalfind.findHospitals(eq("Χειρουργική"), anyString(), anyString()));

        // Κλήση της μεθόδου
        Main.handleHighSeverity(scanner, user, hospitalfind);

        // Επαλήθευση των αλληλεπιδράσεων με τα mocks
        verify(suitable).selectDepartment(scanner);
        verify(hospitalfind).findHospitals(eq("Χειρουργική"), anyString(), anyString());
    }
}
