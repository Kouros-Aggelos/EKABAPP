package com.kerasia;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class Closest {

    private static final String API_KEY = "AIzaSyCkgsJjyrx_oXY2WyjCagwEDCmXMO0f1Qo";

    // Υπολογισμός απόστασης με βάση τις συντεταγμένες
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371; // Ακτίνα Γης σε χλμ
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Απόσταση σε χλμ
    }

    // Λήψη συντεταγμένων από τη διεύθυνση μέσω API Google
    public double[] getCoordinates(String address) {
        try {
            String url = "https://maps.googleapis.com/maps/api/geocode/json?address="
                       + address.replace(" ", "+") + "&key=" + API_KEY;

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            JSONObject jsonResponse = new JSONObject(response.toString());
            if ("OK".equals(jsonResponse.getString("status"))) {
                JSONObject location = jsonResponse
                        .getJSONArray("results")
                        .getJSONObject(0)
                        .getJSONObject("geometry")
                        .getJSONObject("location");
                return new double[]{location.getDouble("lat"), location.getDouble("lng")};
            }
        } catch (Exception e) {
            System.out.println("Error fetching coordinates: " + e.getMessage());
        }
        return new double[]{0.0, 0.0};
    }

    // Εύρεση κοντινότερου νοσοκομείου
    public String findClosestHospital(User user, hospitalfind finder) {
        // Παίρνουμε τη διεύθυνση από το User
        String userAddress = user.getAddress();

        // Παίρνουμε την ημέρα της εβδομάδας (dayOfWeek) από τον User
        String dayOfWeek = user.getDayOfWeek();

        // Παίρνουμε τη βάρδια (time) από τον User
        String time = user.getTime();

        // Ορίζουμε “χειροκίνητα” (hard-coded) το τμήμα στην "Παθολογική"
        String department = "Παθολογική";

        // Λήψη συντεταγμένων χρήστη
        double[] userCoordinates = getCoordinates(userAddress);
        if (userCoordinates[0] == 0.0 || userCoordinates[1] == 0.0) {
            return "Αποτυχία λήψης συντεταγμένων για τη διεύθυνση χρήστη.";
        }

        // Βρες τα νοσοκομεία με βάση το τμήμα, την ημέρα και τη βάρδια
        List<String[]> hospitals = finder.findHospitals(department, dayOfWeek, time);
        if (hospitals.isEmpty()) {
            return "Δε βρέθηκαν νοσοκομεία με το τμήμα " + department + ".";
        }

        String closestHospital = null;
        double closestDistance = Double.MAX_VALUE;

        // Υπολογισμός απόστασης για κάθε νοσοκομείο
        for (String[] hospital : hospitals) {
            String hospitalName = hospital[0];
            String hospitalAddress = hospital[1];
            double[] hospitalCoordinates = getCoordinates(hospitalAddress);

            if (hospitalCoordinates[0] == 0.0 || hospitalCoordinates[1] == 0.0) {
                System.out.println("Αποτυχία λήψης συντεταγμένων για το νοσοκομείο: " + hospitalName);
                continue;
            }

            double distance = calculateDistance(
                    userCoordinates[0], userCoordinates[1],
                    hospitalCoordinates[0], hospitalCoordinates[1]
            );

            if (distance < closestDistance) {
                closestDistance = distance;
                closestHospital = hospitalName + " (" + hospitalAddress + ")";
            }
        }

        return closestHospital != null
                ? "Το κοντινότερο νοσοκομείο είναι: " + closestHospital
                  + " με απόσταση " + closestDistance + " χλμ."
                : "Δε βρέθηκαν κοντινά νοσοκομεία.";
    }
}


