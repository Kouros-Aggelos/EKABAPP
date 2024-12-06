import java.util.Scanner;

class InvalidInputException extends Exception {
    public InvalidInputException(String msg) {
        super(msg);
    }
}

public class SeverityIndex2 {

    public enum SeverityLevel {
        LEVEL_1("Critical, immediate life-saving intervention required"),
        LEVEL_2("Emergent, high risk of life-threatening condition"),
        LEVEL_3("Urgent, needs multiple resources but stable"),
        LEVEL_4("Less urgent, needs only one resource"),
        LEVEL_5("Non-urgent, needs no resources or minimal care");

        // δεν αλλάζει τιμή, παίρνει ένα από τα 5
        private final String description;

        SeverityLevel(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public SeverityLevel severityLevel;

    public SeverityLevel determineSeverity() throws InvalidInputException {
        Scanner scanner = new Scanner(System.in);

        /** Ερώτηση 1:Απαιτείται άμεση παρέμβαση; */
        System.out.println("Απαιτείται άμεση παρέμβαση; (ναι/όχι): ");
        String immediateIntervention = scanner.nextLine().trim().toLowerCase();

        switch (immediateIntervention) {
            case "ναι":
                severityLevel = SeverityLevel.LEVEL_1;
                System.out.println("Κατηγορία ESI: " + severityLevel.getDescription());
                return severityLevel;

            case "όχι":
                return evaluateNonImmediateCases(scanner);

            default:
                throw new InvalidInputException("Λανθασμένη εισαγωγή απάντησης.");
        }
    }

    /** ΔΕΝ ΧΡΕΙΑΖΕΤΑΙ ΑΜΕΣΗ ΠΑΡΕΜΒΑΣΗ ΑΡΑ ΥΠΟΛΟΓΙΖΟΝΤΑΙ ΕΠΙΠΕΔΑ2-5 */
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
            case 0:
                severityLevel = SeverityLevel.LEVEL_5;
                System.out.println("Κατηγορία ESI: " + severityLevel.getDescription());
                return severityLevel;

            case 1:
                severityLevel = SeverityLevel.LEVEL_4;
                System.out.println("Κατηγορία ESI: " + severityLevel.getDescription());
                return severityLevel;

            default:
                return evaluateVitalSigns(scanner);
        }
    }

    // Μέθοδος για τη διαχείριση των ζωτικών ενδείξεων
    private SeverityLevel evaluateVitalSigns(Scanner scanner) throws InvalidInputException {
        /** Ερώτηση 3: Είναι σταθερές οι ζωτικές ενδείξεις; */
        System.out.print("Είναι οι ζωτικές ενδείξεις εντός φυσιολογικών ορίων; (ναι/όχι): ");
        String vitalSigns = scanner.nextLine().trim().toLowerCase();

        switch (vitalSigns) {
            case "ναι":
                severityLevel = SeverityLevel.LEVEL_3;
                System.out.println("Κατηγορία ESI: " + severityLevel.getDescription());
                return severityLevel;

            case "όχι":
                severityLevel = SeverityLevel.LEVEL_2;
                System.out.println("Κατηγορία ESI: " + severityLevel.getDescription());
                return severityLevel;

            default:
                throw new InvalidInputException("Λανθασμένη εισαγωγή απάντησης.");
        }
    }

    public static void main(String[] args) {
        try {
            // Δημιουργία αντικειμένου της κλάσης SeverityIndex2
            SeverityIndex severityIndex = new SeverityIndex();

            // Κλήση της μη στατικής μεθόδου μέσω του αντικειμένου
            SeverityLevel result = severityIndex.determineSeverity();
            System.out.println("Το επίπεδο σοβαρότητας που καθορίστηκε είναι: " + result.getDescription());
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }
    }
}

