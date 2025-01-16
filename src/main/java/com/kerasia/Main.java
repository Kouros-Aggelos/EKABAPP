package com.kerasia;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /** Μήνυμα καλωσορίσματος!*/
        System.out.println("Καλώς ήρθατε στο σύστημα διαχείρισης ασθενών και εύρεσης νοσοκομείων!");

        User user = new User(scanner); // Μεταβιβάζουμε το Scanner στον κατασκευαστή
        user.displayUserInfo();

        int severityLevel = user.getSeverityLevel();

        hospitalfind hospitalFind = new hospitalfind();

        Closest closest = new Closest();

        if (severityLevel == 1 || severityLevel == 2 || severityLevel == 3) {
            handleLowSeverity(user, hospitalFind, closest);
        } else if (severityLevel == 4 || severityLevel == 5) {
            Suitable suitable = new Suitable();
            handleHighSeverity(scanner, user, hospitalFind, suitable);
        } else {
            System.out.println("Μη έγκυρο επίπεδο σοβαρότητας.");
        }

        scanner.close();

        System.out.println("Άνοιγμα του χάρτη...");
        MapLauncher mapLauncher = new MapLauncher();
        mapLauncher.launchMap();
    }

    public static void handleLowSeverity(User user, hospitalfind hospitalFind, Closest closest) {
        String department = "Παθολογική";
        List<String[]> hospitals = hospitalFind.findHospitals(department, user.getDayOfWeek(), user.getTime());

        System.out.println("Αναζητείται το κοντινότερο νοσοκομείο...");
        String result = closest.findClosestHospital(user, hospitalFind);
        System.out.println(result);
    }

    public static void handleHighSeverity(Scanner scanner, User user, hospitalfind hospitalFind, Suitable suitable) {
        
        String selectedDepartment = suitable.selectDepartment(scanner);
        System.out.println("Επιλέχθηκε τμήμα: " + selectedDepartment);

        List<String[]> hospitals = hospitalFind.findHospitals(selectedDepartment, user.getDayOfWeek(), user.getTime());

        if (hospitals.isEmpty()) {
            System.out.println("Δεν βρέθηκαν νοσοκομεία για το τμήμα: " + selectedDepartment);
        } else {
            findAndDisplayClosestHospital(user, hospitals);
        }
    }

    public static void findAndDisplayClosestHospital(User user, List<String[]> hospitals) {
        System.out.println("Υπολογισμός χρόνων διαδρομής προς τα νοσοκομεία...");

        String userAddress = user.getAddress();
        String apiKey = "AIzaSyB5L0dTX88It7Qz8JZlQw72MKcF-i0uIJo";

        String closestHospital = null;
        String shortestDuration = null;
        int shortestDurationMinutes = Integer.MAX_VALUE;

        for (String[] hospital : hospitals) {
            String hospitalName = hospital[0];
            String hospitalAddress = hospital[1];

            String duration = Frommap.calculateRouteTimes(userAddress, hospitalAddress, apiKey);

            if (duration != null) {
                System.out.println("Χρόνος διαδρομής προς το νοσοκομείο " + hospitalName + ": " + duration);

                int durationInMinutes = parseDurationToMinutes(duration);

                if (durationInMinutes < shortestDurationMinutes) {
                    shortestDurationMinutes = durationInMinutes;
                    shortestDuration = duration;
                    closestHospital = hospitalName + " (" + hospitalAddress + ")";
                }
            } else {
                System.out.println("Αποτυχία υπολογισμού διαδρομής για το νοσοκομείο " + hospitalName + ".");
            }
        }
        
        if (closestHospital != null) {
            System.out.println("Το κοντινότερο νοσοκομείο είναι: " + closestHospital + " με χρόνο διαδρομής " + shortestDuration);
        } else {
            System.out.println("Δεν βρέθηκε κοντινό νοσοκομείο.");
        }
    }

    public static int parseDurationToMinutes(String duration) {
        String[] parts = duration.split(" ");
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("min")) {
                return Integer.parseInt(parts[i - 1]);
            }
        }
        return 0; // Επιστρέφει 0 αν δεν βρεθεί διάρκεια σε λεπτά
    }
}
