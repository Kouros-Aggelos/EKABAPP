package com.kerasia;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

    class ClosestTest {

    private Closest closest;
    private hospitalfind mockFinder;
    private User mockUser;

    @BeforeEach
    void setUp() {
        closest = new Closest();
        mockFinder = Mockito.mock(hospitalfind.class);
        mockUser = Mockito.mock(User.class);
    }

    @Test
    void testCalculateDistance() {
        double lat1 = 37.9838; // Αθήνα
        double lon1 = 23.7275;
        double lat2 = 40.6401; // Θεσσαλονίκη
        double lon2 = 22.9444;

        double distance = closest.calculateDistance(lat1, lon1, lat2, lon2);

        assertEquals(303.0, distance, 1.0, "Η απόσταση Αθήνας-Θεσσαλονίκης πρέπει να είναι περίπου 303 χλμ.");
    }

    @Test
    void testGetCoordinates() {
        // Δεδομένου ότι το API καλείται, αυτό το τεστ πρέπει να επικεντρωθεί στη διαχείριση λαθών
        double[] coordinates = closest.getCoordinates("Invalid Address");

        assertEquals(0.0, coordinates[0], "Η συντεταγμένη lat για άκυρη διεύθυνση πρέπει να είναι 0.0.");
        assertEquals(0.0, coordinates[1], "Η συντεταγμένη lon για άκυρη διεύθυνση πρέπει να είναι 0.0.");
    }

    @Test
    void testFindClosestHospital() {
        // Mock δεδομένα χρήστη
        Mockito.when(mockUser.getAddress()).thenReturn("Athens, Greece");
        Mockito.when(mockUser.getDayOfWeek()).thenReturn("Monday");
        Mockito.when(mockUser.getTime()).thenReturn("Morning");

        // Mock δεδομένα νοσοκομείων
        List<String[]> hospitals = new ArrayList<>();
        hospitals.add(new String[]{"Hospital A", "Thessaloniki, Greece"});
        hospitals.add(new String[]{"Hospital B", "Patras, Greece"});
        Mockito.when(mockFinder.findHospitals("Παθολογική", "Monday", "Morning"))
                .thenReturn(hospitals);

        // Εκτέλεση της μεθόδου
        String result = closest.findClosestHospital(mockUser, mockFinder);

        assertTrue(result.contains("Το κοντινότερο νοσοκομείο είναι"), "Το αποτέλεσμα πρέπει να περιέχει πληροφορίες για το κοντινότερο νοσοκομείο.");
    }

    @Test
    void testFindClosestHospital_NoHospitalsFound() {
        Mockito.when(mockUser.getAddress()).thenReturn("Athens, Greece");
        Mockito.when(mockUser.getDayOfWeek()).thenReturn("Monday");
        Mockito.when(mockUser.getTime()).thenReturn("Morning");

        Mockito.when(mockFinder.findHospitals("Παθολογική", "Monday", "Morning"))
                .thenReturn(new ArrayList<>());

        String result = closest.findClosestHospital(mockUser, mockFinder);

        assertEquals("Δε βρέθηκαν νοσοκομεία με το τμήμα Παθολογική.", result, "Πρέπει να εμφανίζει μήνυμα ότι δεν βρέθηκαν νοσοκομεία.");
    }
}
