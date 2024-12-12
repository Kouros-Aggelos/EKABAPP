import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class User {

    // Ιδιότητες που θα αποθηκεύουν τα δεδομένα του χρήστη
    private String fullName;
    private String address;
    private String incidentType;
    private int severityLevel;
    private String dayOfWeek; // Αποθηκεύει τη μέρα της εβδομάδας
    private String time; // Αποθηκεύει την ώρα

    
    private static final String[] INCIDENT_TYPES = {
        "Παθολογική", "Καρδιολογική", "Χειρουργική", "Αγγειοχειρουργική", "Αιματολογική",
        "Γαστρεντερολογική", "Γναθοχειρουργική", "Δερματολογική", "Ενδοκρινολογική",
        "Θωρακοχειρουργική", "Καρδιοχειρουργική", "Νευρολογική", "Νευροχειρουργική",
        "Νεφρολογική", "Ογκολογική", "Οδοντιατρική", "Ορθοπαιδική", "Κλινική Χεριού",
        "Ουρολογική", "Οφθαλμολογική", "Πνευμονολογική", "Πλαστική Χειρουργική",
        "Ρευματολογική", "Ψυχιατρική", "Ωτορινολαρυγγολογική", "Γυναικολογική",
        "Μαιευτική", "Παιδιατρική", "Παιδοψυχιατρική", "Παιδοοδοντιατρική",
        "Παιδοκαρδιοχειρουργική", "Παιδοκαρδιολογική"
    };

    
    public void getUserInput() {
        try {
            System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
            Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);

            System.out.print("Εισάγετε όνομα και επώνυμο: ");
            fullName = scanner.nextLine();

            System.out.print("Εισάγετε διεύθυνση και περιοχή: ");
            address = scanner.nextLine();

            System.out.println("Επιλέξτε είδος περιστατικού από την παρακάτω λίστα:");
            for (int i = 0; i < INCIDENT_TYPES.length; i++) {
                System.out.println((i + 1) + ". " + INCIDENT_TYPES[i]);
            }

            System.out.print("Εισάγετε τον αριθμό του είδους περιστατικού: ");
            int incidentChoice = scanner.nextInt();
            scanner.nextLine(); // Καθαρισμός buffer
            if (incidentChoice >= 1 && incidentChoice <= INCIDENT_TYPES.length) {
                incidentType = INCIDENT_TYPES[incidentChoice - 1];
            } else {
                System.out.println("Μη έγκυρη επιλογή! Προεπιλέγεται το πρώτο είδος.");
                incidentType = INCIDENT_TYPES[0];
            }

            System.out.print("Εισάγετε επίπεδο σοβαρότητας (1-5): ");
            severityLevel = scanner.nextInt();
            if (severityLevel < 1 || severityLevel > 5) {
                System.out.println("Μη έγκυρο επίπεδο! Προεπιλέγεται το επίπεδο 1.");
                severityLevel = 1;
            }

    
            saveDayAndTime();

            scanner.close();
        } catch (Exception e) {
            System.out.println("Σφάλμα κατά την είσοδο δεδομένων: " + e.getMessage());
        }
    }

    // Μέθοδος που αποθηκεύει τη μέρα της εβδομάδας και την ώρα
    public void saveDayAndTime() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Αποθηκεύουμε τη μέρα της εβδομάδας στα ελληνικά
        dayOfWeek = currentDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("el"));
        // Αποθηκεύουμε την ώρα με τη μορφή HH:mm
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        time = currentDateTime.format(timeFormatter);
    }

    // Μέθοδος για εμφάνιση των δεδομένων του χρήστη
    public void displayUserInfo() {
        System.out.println("\n--- Πληροφορίες Χρήστη ---");
        System.out.println("Ονοματεπώνυμο: " + fullName);
        System.out.println("Διεύθυνση: " + address);
        System.out.println("Είδος Περιστατικού: " + incidentType);
        System.out.println("Επίπεδο Σοβαρότητας: " + severityLevel);
        System.out.println("Μέρα Εισαγωγής: " + dayOfWeek);
        System.out.println("Ώρα Εισαγωγής: " + time);
    }

    
    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getIncidentType() {
        return incidentType;
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
    public static void main(String[] args) {
        // Δημιουργούμε ένα νέο αντικείμενο User
        User user = new User();

        // Καλούμε τη μέθοδο για την είσοδο των δεδομένων από τον χρήστη
        user.getUserInput();

        // Εμφανίζουμε τις πληροφορίες του χρήστη
        user.displayUserInfo();
    }
}
