package com.kerasia;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

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
    private Suitable suitable;

    @Mock
    private Closest closest;

    @InjectMocks
    private Main main;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Προκαθορισμένη συμπεριφορά για τα mocks
        when(user.getSeverityLevel()).thenReturn(1);
        when(suitable.selectDepartment(any(Scanner.class))).thenReturn("Παθολογική");
        when(hospitalfind.findHospitals(eq("Παθολογική"), eq("TestLocation"), eq("TestType")))
                .thenReturn(List.of(
                        new String[]{"Hospital A", "Address A"},
                        new String[]{"Hospital B", "Address B"}
                ));
    }

    
    @Test
    void testHandleLowSeverity() {
        // Mock δεδομένα
        when(user.getSeverityLevel()).thenReturn(1); // Ρύθμιση χαμηλής σοβαρότητας
        when(user.getDayOfWeek()).thenReturn("TestLocation");
        when(user.getTime()).thenReturn("TestType");
        
        String department = "Παθολογική";
        
    
        // Mock για τη μέθοδο findHospitals
        when(hospitalfind.findHospitals(eq(department), eq("TestLocation"), eq("TestType")))
                .thenReturn(List.of(
                        new String[]{"Hospital A", "Address A"},
                        new String[]{"Hospital B", "Address B"}));
    
        // Mock για τη findClosestHospital
        when(closest.findClosestHospital(eq(user), eq(hospitalfind)))
                .thenReturn("Hospital A");
    
        // Κλήση της μεθόδου
        Main.handleLowSeverity(user, hospitalfind, closest);
    
        // Επαλήθευση ότι έγινε η σωστή κλήση
        verify(hospitalfind).findHospitals(eq(department), eq("TestLocation"), eq("TestType"));
        verify(closest).findClosestHospital(eq(user), eq(hospitalfind));
    }
    
    @Test
    void testHandleHighSeverity() {
        // Mock δεδομένα
        Scanner scanner = new Scanner("3\n"); // Mock είσοδος από τον χρήστη
        when(user.getSeverityLevel()).thenReturn(5); // Ρύθμιση υψηλής σοβαρότητας
        when(user.getDayOfWeek()).thenReturn("TestLocation");
        when(user.getTime()).thenReturn("TestType");

        String department = "Χειρουργική";
    
        // Mock για τη μέθοδο selectDepartment
        when(suitable.selectDepartment(any(Scanner.class))).thenReturn(department);
    

        // Mock για τη μέθοδο findHospitals
        when(hospitalfind.findHospitals(eq(department), eq("TestLocation"), eq("TestType")))
                .thenReturn(List.of(
                        new String[]{"Hospital C", "Address C"},
                        new String[]{"Hospital D", "Address D"}));
    
        // Κλήση της μεθόδου
        Main.handleHighSeverity(scanner, user, hospitalfind, suitable);
    
        // Επαλήθευση ότι έγινε η σωστή κλήση
        verify(suitable).selectDepartment(any(Scanner.class));
        verify(hospitalfind).findHospitals(eq(department), eq("TestLocation"), eq("TestType"));
    }
}    
