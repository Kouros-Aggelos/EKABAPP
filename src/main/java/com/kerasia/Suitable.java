import java.util.InputMismatchException;
import java.util.Scanner;

public class Suitable {
    private static final String[][] departments = {
            { "1", "Παθολογική" },
            { "2", "Καρδιολογική" },
            { "3", "Χειρουργική" },
            { "4", "Αγγειοχειρουργική" },
            { "5", "Αιματολογική" },
            { "6", "Γαστρεντερολογική" },
            { "7", "Γναθοχειρουργική" },
            { "8", "Δερματολογική" },
            { "9", "Ενδοκρινολογική" },
            { "10", "Θωρακοχειρουργική" },
            { "11", "Καρδιοχειρουργική" },
            { "12", "Νευρολογική" },
            { "13", "Νευροχειρουργική" },
            { "14", "Νεφρολογική" },
            { "15", "Ογκολογική" },
            { "16", "Οδοντιατρική" },
            { "17", "Ορθοπαιδική" },
            { "18", "Κλινική Χεριού" },
            { "19", "Ουρολογική" },
            { "20", "Οφθαλμολογική" },
            { "21", "Πνευμονολογική" },
            { "22", "Πλαστική Χειρουργική" },
            { "23", "Ρευματολογική" },
            { "24", "Ψυχιατρική" },
            { "25", "Ωτορινολαρυγγολογική" },
            { "26", "Γυναικολογική" },
            { "27", "Μαιευτική" },
            { "28", "Παιδιατρική" },
            { "29", "Παιδοψυχιατρική" },
            { "30", "Παιδοοδοντιατρική" },
            { "31", "Παιδοκαρδιοχειρουργική" },
            { "32", "Παιδοκαρδιολογική" }
    };

    public String selectDepartment(Scanner scanner) {
        System.out.println("Διαθέσιμα Τμήματα:");
        for (String[] department : departments) {
            System.out.println("ID: " + department[0] + ", Όνομα: " + department[1]);
        }
    
        while (true) {
            try {
            System.out.print("Εισάγετε κωδικό τμήματος (1-32): ");
            
            if (!scanner.hasNextInt()) { // Ελέγχει αν η επόμενη είσοδος είναι αριθμός
                System.out.println("Μη έγκυρη εισαγωγή. Πρέπει να εισάγετε αριθμό. Δοκιμάστε ξανά.");
                scanner.nextLine();   // consume the invalid input (e.g. text or just Enter)
                continue;  
            } 
        
                int selection = scanner.nextInt(); // Διαβάζει τον αριθμό
                scanner.nextLine(); // Καθαρίζει το υπόλοιπο της γραμμής
        
                if (selection >= 1 && selection <= departments.length) {
                    return departments[selection - 1][1]; // Επιστρέφει το όνομα του τμήματος
                } else {
                    System.out.println("Μη έγκυρος κωδικός. Παρακαλώ δοκιμάστε ξανά.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Μη έγκυρη είσοδος. Παρακαλώ εισάγετε έναν ακέραιο αριθμό.");
                scanner.nextLine(); // Clear invalid input
            }
        }       
    }    
}





