package com.kerasia;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class FrommapTest {

    @Test
    void testCalculateRouteTimes() throws Exception {
        // Mock δεδομένα για το API
        String mockApiResponse = """
            {
              \"routes\": [
                {
                  \"legs\": [
                    {
                      \"duration\": {
                        \"text\": \"15 mins\"
                      }
                    }
                  ]
                }
              ]
            }
        """;

        // Δημιουργία mock για το HttpURLConnection
        HttpURLConnection mockConnection = Mockito.mock(HttpURLConnection.class);
        Mockito.when(mockConnection.getInputStream()).thenReturn(
                new java.io.ByteArrayInputStream(mockApiResponse.getBytes()));

        // Δημιουργία mock για τη URL
        MockedStatic<URL> mockedURL = Mockito.mockStatic(URL.class);
        URL mockUrl = Mockito.mock(URL.class);
        mockedURL.when(() -> new URL(Mockito.anyString())).thenReturn(mockUrl);
        Mockito.when(mockUrl.openConnection()).thenReturn(mockConnection);

        // Κλήση της μεθόδου προς τεστ
        String origin = "Athens";
        String destination = "Piraeus";
        String apiKey = "mockApiKey";

        String result = Frommap.calculateRouteTimes(origin, destination, apiKey);

        // Έλεγχος του αποτελέσματος
        assertEquals("15 mins", result);

        // Κλείσιμο του mock
        mockedURL.close();
    }
}
