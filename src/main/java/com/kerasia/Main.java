import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1) Δημιουργία Scanner
        Scanner scanner = new Scanner(System.in);

        // 2) Μήνυμα καλωσορίσματος
        System.out.println("Καλώς ήρθατε στο σύστημα διαχείρισης ασθενών και εύρεσης νοσοκομείων!");

        // 3) Δημιουργία χρήστη και συλλογή δεδομένων
        User user = new User(scanner); // Μεταβιβάζουμε το Scanner στον κατασκευαστή
        user.displayUserInfo();

        // 4) Έλεγχος επιπέδου σοβαρότητας
        int severityLevel = user.getSeverityLevel();

        // 5) Δημιουργία αντικειμένου hospitalfind
        hospitalfind hospitalFind = new hospitalfind();

        // 6) Διαχείριση ανάλογα με το επίπεδο σοβαρότητας
        if (severityLevel == 1 || severityLevel == 2 || severityLevel == 3) {
            handleLowSeverity(user, hospitalFind);
        } else if (severityLevel == 4 || severityLevel == 5) {
            handleHighSeverity(scanner, user, hospitalFind);
        } else {
            System.out.println("Μη έγκυρο επίπεδο σοβαρότητας.");
        }

        // 7) Κλείσιμο του Scanner
        scanner.close();
    }

    private static void handleLowSeverity(User user, hospitalfind hospitalFind) {
        // Παράδειγμα: Παίρνουμε νοσοκομεία για την Παθολογική
        String department = "Παθολογική";
        List<String[]> hospitals = hospitalFind.findHospitals(department, user.getDayOfWeek(), user.getTime());

        System.out.println("Αναζητείται το κοντινότερο νοσοκομείο...");
        Closest closest = new Closest();
        String result = closest.findClosestHospital(user, hospitalFind);
        System.out.println(result);
    }

    private static void handleHighSeverity(Scanner scanner, User user, hospitalfind hospitalFind) {
        // Χρήση Suitable για επιλογή τμήματος
        Suitable suitable = new Suitable();

        // Επιλογή τμήματος από τον χρήστη
        String selectedDepartment = suitable.selectDepartment(scanner);
        System.out.println("Επιλέχθηκε τμήμα: " + selectedDepartment);

        // Αναζήτηση νοσοκομείων για το επιλεγμένο τμήμα
        List<String[]> hospitals = hospitalFind.findHospitals(selectedDepartment, user.getDayOfWeek(), user.getTime());

        if (hospitals.isEmpty()) {
            System.out.println("Δεν βρέθηκαν νοσοκομεία για το τμήμα: " + selectedDepartment);
        } else {
            findAndDisplayClosestHospital(user, hospitals);
        }
    }

    private static void findAndDisplayClosestHospital(User user, List<String[]> hospitals) {
        System.out.println("Υπολογισμός χρόνων διαδρομής προς τα νοσοκομεία...");

        // Διεύθυνση χρήστη
        String userAddress = user.getAddress();
        String apiKey = "AIzaSyB5L0dTX88It7Qz8JZlQw72MKcF-i0uIJo";

        // Δήλωση μεταβλητών για το κοντινότερο νοσοκομείο
        String closestHospital = null;
        String shortestDuration = null;
        int shortestDurationMinutes = Integer.MAX_VALUE;

        // Υπολογισμός χρόνου διαδρομής για κάθε νοσοκομείο
        for (String[] hospital : hospitals) {
            String hospitalName = hospital[0];
            String hospitalAddress = hospital[1];

            // Κλήση της μεθόδου calculateRouteTimes
            String duration = Frommap.calculateRouteTimes(userAddress, hospitalAddress, apiKey);

            if (duration != null) {
                System.out.println("Χρόνος διαδρομής προς το νοσοκομείο " + hospitalName + ": " + duration);

                // Μετατροπή της διάρκειας σε λεπτά
                int durationInMinutes = parseDurationToMinutes(duration);

                // Ενημέρωση για το κοντινότερο νοσοκομείο
                if (durationInMinutes < shortestDurationMinutes) {
                    shortestDurationMinutes = durationInMinutes;
                    shortestDuration = duration;
                    closestHospital = hospitalName + " (" + hospitalAddress + ")";
                }
            } else {
                System.out.println("Αποτυχία υπολογισμού διαδρομής για το νοσοκομείο " + hospitalName + ".");
            }
        }

        // Εύρεση του κοντινότερου νοσοκομείου
        if (closestHospital != null) {
            System.out.println("Το κοντινότερο νοσοκομείο είναι: " + closestHospital + " με χρόνο διαδρομής " + shortestDuration);
        } else {
            System.out.println("Δεν βρέθηκε κοντινό νοσοκομείο.");
        }
    }

    private static int parseDurationToMinutes(String duration) {
        String[] parts = duration.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("min")) {
                return Integer.parseInt(parts[i - 1]);
            }
        }
        return 0; // Επιστρέφει 0 αν δεν βρεθεί διάρκεια σε λεπτά
    }
}
