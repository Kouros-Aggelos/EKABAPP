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

    private Scanner scanner;

    public SeverityIndex(Scanner scanner) {
        this.scanner = scanner;
    }

    public SeverityLevel severityLevel;

    public SeverityLevel determineSeverity() throws InvalidInputException {
        severityLevel = null;


            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

            while (true) {

                try {
                    System.out.println("Απαιτείται άμεση παρέμβαση; (ναι/όχι): ");
                    String immediateIntervention = scanner.next().trim().toLowerCase();
                    scanner.nextLine();
                    System.out.println("Η είσοδος για άμεση παρέμβαση: " + immediateIntervention);

                    switch (immediateIntervention) {
                        case "ναι" -> {
                            severityLevel = SeverityLevel.LEVEL_1;
                            return severityLevel;
                        }

                        case "όχι", "οχι" -> {
                            return evaluateNonImmediateCases(scanner);
                        }

                        default -> throw new InvalidInputException("Λανθασμένη εισαγωγή απάντησης. Παρακαλώ δοκιμάστε ξανα.");
                    }
                } catch (InvalidInputException e) {
                    System.out.println("Σφάλμα: " + e.getMessage());
                }
            }
        
    }

    private SeverityLevel evaluateNonImmediateCases(Scanner scanner) throws InvalidInputException {
        
        int resources;

        while (true) {
            try {
                System.out.print("Πόσοι πόροι χρειάζονται (0, 1, 2 ή περισσότεροι): ");
                resources = Integer.parseInt(scanner.nextLine().trim());

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
            } catch (NumberFormatException e) {
                System.out.println("Λανθασμένη εισαγωγή αριθμού. Παρακαλώ δοκιμάστε ξανά.");
            }

        }    
        
    }

    private SeverityLevel evaluateVitalSigns(Scanner scanner) throws InvalidInputException {
        while (true) {
            try {
                System.out.print("Είναι οι ζωτικές ενδείξεις εντός φυσιολογικών ορίων; (ναι/όχι): ");
                String vitalSigns = scanner.nextLine().trim().toLowerCase();

                switch (vitalSigns) {
                    case "ναι" -> {
                        severityLevel = SeverityLevel.LEVEL_3;
                        return severityLevel;
                    }

                    case "όχι", "οχι" -> {
                        severityLevel = SeverityLevel.LEVEL_2;
                        return severityLevel;
                    }

                    default -> throw new InvalidInputException("Λανθασμένη εισαγωγή απάντησης.");
                }
            } catch (InvalidInputException e) {
                System.out.println("Σφάλμα: " + e.getMessage() + " Παρακαλώ δοκιμάστε ξανά.");
            }
        }
    }
    @Override
    public String toString() {
        return "Level: " + severityLevel.getLevel() + ", Description: " + severityLevel.getDescription();
    }
}
