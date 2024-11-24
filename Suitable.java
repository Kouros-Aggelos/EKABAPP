import java.util.Scanner;

public class Suitable extends Hospital {
    public static void main(String[] args) {
        String[][] departments = {
            {"1", "Παθολογική"},
            {"2", "Καρδιολογική"},
            {"3", "Χειρουργική"},
            {"4", "Αγγειοχειρουργική"},
            {"5", "Αιματολογική"},
            {"6", "Γαστρεντερολογική"},
            {"7", "Γναθοχειρουργική"},
            {"8", "Δερματολογική"},
            {"9", "Ενδοκρινολογική"},
            {"10", "Θωρακοχειρουργική"},
            {"11", "Καρδιοχειρουργική"},
            {"12", "Νευρολογική"},
            {"13", "Νευροχειρουργική"},
            {"14", "Νεφρολογική"},
            {"15", "Ογκολογική"},
            {"16", "Οδοντιατρική"},
            {"17", "Ορθοπαιδική"},
            {"18", "Κλινική Χεριού"},
            {"19", "Ουρολογική"},
            {"20", "Οφθαλμολογική"},
            {"21", "Πνευμονολογική"},
            {"22", "Πλαστική Χειρουργική"},
            {"23", "Ρευματολογική"},
            {"24", "Ψυχιατρική"},
            {"25", "Ωτορινολαρυγγολογική"},
            {"26", "Γυναικολογική"},
            {"27", "Μαιευτική"},
            {"28", "Παιδιατρική"},
            {"29", "Παιδοψυχιατρική"},
            {"30", "Παιδοοδοντιατρική"},
            {"31", "Παιδοκαρδιοχειρουργική"},
            {"32", "Παιδοκαρδιολογική"}
        };

        // Display all departments
        for (String[] department : departments) {
            System.out.println("ID: " + department[0] + ", Name: " + department[1]);
        }
        System.out.println("Εισάγετε κωδικό τμήματος :");
        Scanner scanner = new Scanner(System.in);


        int accidentDepartment = scanner.nextInt(); // Correct usage of the scanner instance

        // Close the scanner
        scanner.close();
    }
    System.out.print(departments[accidentDepartment - 1][1] );
}
