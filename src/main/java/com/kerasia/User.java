package com.kerasia;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.InputMismatchException;

public class User {


    private String fullName;
    private String address;
    private int age;
    private int severityLevel;
    private String dayOfWeek; 
    private String time; 
    private Scanner scanner; // Scanner που μεταβιβάζεται μέσω του κατασκευαστή

    // Κατασκευαστής με Scanner
    public User(Scanner scanner) {
        this.scanner = scanner;
        getUserInput();
    }

    public void getUserInput() {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            System.out.print("Εισάγετε όνομα και επώνυμο: ");
            fullName = scanner.nextLine();

            System.out.print("Εισάγετε διεύθυνση και περιοχή: ");
            address = scanner.nextLine();

            age = setAge();

            saveDayAndTime();

            SeverityIndex severityIndex = new SeverityIndex(scanner);
            SeverityIndex.SeverityLevel result = severityIndex.determineSeverity();
            System.out.println("Το επίπεδο σοβαρότητας που καθορίστηκε είναι: Level " + result.getLevel() + " : " + result.getDescription());
            severityLevel = result.getLevel();
        } catch (Exception e) {
            System.out.println("Σφάλμα κατά την είσοδο δεδομένων: " + e.getMessage());
        }
    }

    public int setAge() throws InputMismatchException {
        int a = -1;
        boolean validInput = false;
        do {
            try {
                System.out.println("Παρακαλώ εισάγετε την ηλικία του ασθενή:");
                a = scanner.nextInt();
                scanner.nextLine();
                if (a <= 0) {
                    System.out.println("Η ηλικία πρέπει να είναι θετικός αριθμός. Παρακαλώ δοκιμάστε ξανά.");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.err.println("Μη έγκυρη είσοδος. Παρακαλώ εισάγετε έναν αριθμό ακέραιο.");
                scanner.nextLine();
                if (System.getProperty("testing") != null) {
                    throw e;
                }
            }
        } while (!validInput);
        return a;
    }

    public void saveDayAndTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Αποθηκεύουμε τη μέρα της εβδομάδας στα ελληνικά
        dayOfWeek = currentDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("el"));
        // Αποθηκεύουμε την ώρα με τη μορφή HH:mm
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        time = currentDateTime.format(timeFormatter);
    }

    public void displayUserInfo() {
        System.out.println("\n--- Πληροφορίες Ασθενούς ---");
        System.out.println("Ονοματεπώνυμο: " + fullName);
        System.out.println("Διεύθυνση: " + address);
        System.out.println("Ηλικία Ασθενούς: " + age);
        System.out.println("Επίπεδο Σοβαρότητας: " + severityLevel);
        System.out.println("Μέρα Συμβάντος: " + dayOfWeek);
        System.out.println("Ώρα Συμβάντος: " + time);
    }

    // Getters
    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public int getSeverityLevel() {
        return severityLevel;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getTime() {
        return time;
    }
}
