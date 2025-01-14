package com.kerasia;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Frommap {

    public static String calculateRouteTimes(String origin, String destination, String apiKey) {
        try {
            // Δημιουργία URL για το Google Maps Directions API
            String urlString = "https://maps.googleapis.com/maps/api/directions/json?origin=" +
                    origin.replace(" ", "+") + 
                    "&destination=" + 
                    destination.replace(" ", "+") + 
                    "&mode=driving" + 
                    "&key=" + apiKey;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Ανάγνωση απόκρισης
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Ανάλυση JSON απόκρισης
            JSONObject jsonResponse = new JSONObject(response.toString());
            if (jsonResponse.getJSONArray("routes").length() > 0) {
                JSONObject leg = jsonResponse.getJSONArray("routes")
                        .getJSONObject(0)
                        .getJSONArray("legs")
                        .getJSONObject(0);

                // Επιστροφή του χρόνου διαδρομής
                return leg.getJSONObject("duration").getString("text");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Σφάλμα: " + e.getMessage());
            return null;
        }
    }
}
