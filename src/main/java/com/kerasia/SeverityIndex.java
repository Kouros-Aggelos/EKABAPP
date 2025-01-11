package com.kerasia;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SeverityIndex {

    public class InvalidInputException extends Exception {
        public InvalidInputException(String msg) {
            super(msg);
        }
    }

    public enum SeverityLevel {
        LEVEL_1(1, "Critical, immediate life-saving intervention required"),
        LEVEL_2(2, "Emergent, high risk of life-threatening condition"),
        LEVEL_3(3, "Urgent, needs multiple resources but stable"),
        LEVEL_4(4, "Less urgent, needs only one resource"),
        LEVEL_5(5, "Non-urgent, needs no resources or minimal care");

        // δεν αλλάζει τιμή, παίρνει ένα από τα 5
        private final String description;
        private final int level;

        SeverityLevel(int level, String description) {
            this.level = level;
            this.description = description;
        }

        public int getLevel() {
            return level;
        }

        public String getDescription() {
            return description;
        }
    }

    public SeverityLevel severityLevel;

    public SeverityLevel determineSeverity() throws InvalidInputException {
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {

            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

            /** Ερώτηση 1:Απαιτείται άμεση παρέμβαση; */
            System.out.println("Απαιτείται άμεση παρέμβαση; (ναι/όχι): ");
            String immediateIntervention = scanner.next().trim().toLowerCase();
            scanner.nextLine();
            System.out.println("Η είσοδος για άμεση παρέμβαση: " + immediateIntervention);

            switch (immediateIntervention) {
                case "ναι" -> {
                    severityLevel = SeverityLevel.LEVEL_1;
                    return severityLevel;
                }

                case "όχι" -> {
                    return evaluateNonImmediateCases(scanner);
                }

                default -> throw new InvalidInputException("Λανθασμένη εισαγωγή απάντησης.");
            }
        }
    }

    /** ΔΕΝ ΧΡΕΙΑΖΕΤΑΙ ΑΜΕΣΗ ΠΑΡΕΜΒΑΣΗ ΑΡΑ ΥΠΟΛΟΓΙΖΟΝΤΑΙ ΕΠΙΠΕΔΑ 4νόχι-5 */
    private SeverityLevel evaluateNonImmediateCases(Scanner scanner) throws InvalidInputException {
        // Ερώτηση 2: Πόσοι πόροι χρειάζονται;
        System.out.print("Πόσοι πόροι χρειάζονται (0, 1, 2 ή περισσότεροι): ");
        int resources;
        try {
            resources = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Λανθασμένη εισαγωγή αριθμού. Τερματισμός.");
        }

        switch (resources) {
            case 0 -> {
                severityLevel = SeverityLevel.LEVEL_5;
                return severityLevel;
            }

            case 1 -> {
                severityLevel = SeverityLevel.LEVEL_4;
                return severityLevel;
            }

            default -> {
                return evaluateVitalSigns(scanner);
            }
        }
    }

    // Μέθοδος για τη διαχείριση των ζωτικών ενδείξεων
    private SeverityLevel evaluateVitalSigns(Scanner scanner) throws InvalidInputException {
        /** Ερώτηση 3: Είναι σταθερές οι ζωτικές ενδείξεις; */
        System.out.print("Είναι οι ζωτικές ενδείξεις εντός φυσιολογικών ορίων; (ναι/όχι): ");
        String vitalSigns = scanner.nextLine().trim().toLowerCase();

        switch (vitalSigns) {
            case "ναι" -> {
                severityLevel = SeverityLevel.LEVEL_3;
                return severityLevel;
            }

            case "όχι" -> {
                severityLevel = SeverityLevel.LEVEL_2;
                return severityLevel;
            }

            default -> throw new InvalidInputException("Λανθασμένη εισαγωγή απάντησης.");
        }
    }

    @Override
    public String toString() {
        return "Level: " + severityLevel.getLevel() + ", Description: " + severityLevel.getDescription();
    }


    public static void main(String[] args) {
        try {
            // Δημιουργία αντικειμένου της κλάσης SeverityIndex
            SeverityIndex severityIndex = new SeverityIndex();

            // Κλήση της μη στατικής μεθόδου μέσω του αντικειμένου
            SeverityLevel result = severityIndex.determineSeverity();
            System.out.println("Το επίπεδο σοβαρότητας που καθορίστηκε είναι: Level " + result.getLevel() + " : " + result.getDescription());
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}
