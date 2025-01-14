package com.kerasia;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class HospitalFindTest {

    private hospitalfind hospitalFinder;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() {
        hospitalFinder = new hospitalfind();

        // Mocking database objects
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
    }

    @Test
    void testFindHospitals() throws Exception {
        // Mock data
        String department = "Παθολογική";
        String dayOfWeek = "Δευτέρα";
        String time = "10:00";

        // Mock database behavior
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);

        // Mock ResultSet data
        when(mockResultSet.next()).thenReturn(true, true, false); // 2 rows
        when(mockResultSet.getString("hospital_name")).thenReturn("Hospital A", "Hospital B");
        when(mockResultSet.getString("hospital_address")).thenReturn("Address A", "Address B");
        when(mockResultSet.getString("area")).thenReturn("Area A", "Area B");

        try (MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString()))
                               .thenReturn(mockConnection);

            hospitalFinder.findHospitals("Παθολογική", "Δευτέρα", "10:00");

            verify(mockConnection).prepareStatement(anyString());
            verify(mockStatement).setString(1, "Pathologikh");
            verify(mockStatement).setString(2, "Deutera");
            verify(mockStatement).setString(3, "Prwini");
            verify(mockResultSet, times(2)).getString("hospital_name");
        }
    }

       

    @Test
    void testFindHospitals_NoResults() throws Exception {
        // Mock data
        String department = "Καρδιολογική";
        String dayOfWeek = "Τρίτη";
        String time = "15:00";

        when(mockResultSet.next()).thenReturn(false);

        try (MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString()))
                               .thenReturn(mockConnection);

            // Εκτέλεση της μεθόδου
            hospitalFinder.findHospitals("Καρδιολογική", "Τρίτη", "15:00");

            // Επαλήθευση ότι το ResultSet δεν έχει δεδομένα
            verify(mockResultSet, never()).getString("hospital_name");

            verify(mockStatement).close();
            verify(mockResultSet).close();
            verify(mockConnection).close();
        }
    }
}



