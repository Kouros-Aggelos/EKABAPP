package com.kerasia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;

public class hospitalfind {

    private String departmentGreeklish;
    private String dayGreeklish;
    private String shift;

    // Μετατροπή σε Greeklish θελει και αλλα
    private String convertDepartmentToGreeklish(String department) {
        switch (department) {
            case "Παθολογική": return "Pathologikh";
            case "Καρδιολογική": return "Kardiologikh";
            case "Χειρουργική": return "Cheirourgikh";
            case "Αγγειοχειρουργική": return "Aggeiocheirourgikh";
            case "Αιματολογική": return "Aimatologikh";
            case "Γαστρεντερολογική": return "Gastrenterologikh";
            case "Γναθοχειρουργική": return "Gnathocheirourgikh";
            case "Δερματολογική": return "Dermatologikh";
            case "Ενδοκρινολογική": return "Endokrinologikh";
            case "Θωρακοχειρουργική": return "Thorakocheirourgikh";
            case "Καρδιοχειρουργική": return "Kardiocheirourgikh";
            case "Νευρολογική": return "Nevrologikh";
            case "Νευροχειρουργική": return "Nevrocheirourgikh";
            case "Νεφρολογική": return "Nefrologikh";
            case "Ογκολογική": return "Ogkologikh";
            case "Οδοντιατρική": return "Odontiatrikh";
            case "Ορθοπαιδική": return "Orthopaidikh";
            case "Κλινική Χεριού": return "KlinikhXeriou";
            case "Ουρολογική": return "Orologikh";
            case "Οφθαλμολογική": return "Ofthalmologikh";
            case "Πνευμονολογική": return "Pneumonologikh";
            case "Πλαστική Χειρουργική": return "PlastikhCheirourgikh";
            case "Ρευματολογική": return "Reumatologikh";
            case "Ψυχιατρική": return "Psyxiatrikh";
            case "Ωτορινολαρυγγολογική": return "Otorinolaryngologikh";
            case "Γυναικολογική": return "Gynaikologikh";
            case "Μαιευτική": return "Maieutikh";
            case "Παιδιατρική": return "Paidiatrikh";
            case "Παιδοψυχιατρική": return "Paidopsyxiatrikh";
            case "Παιδοοδοντιατρική": return "Paidoodontiatrikh";
            case "Παιδοκαρδιοχειρουργική": return "Paidokardiocheirourgikh";
            case "Παιδοκαρδιολογική": return "Paidokardiologikh";
            default: return "Allo";
        }
    }

    // Μετατροπή ημέρας σε Greeklish
    private String convertDayToGreeklish(String dayOfWeek) {
        switch (dayOfWeek.toLowerCase(Locale.ROOT)) {
            case "δευτέρα": return "Deutera";
            case "τρίτη": return "Trith";
            case "τετάρτη": return "Tetarth";
            case "πέμπτη": return "Pempth";
            case "παρασκευή": return "Paraskeuh";
            case "σάββατο": return "Savvato";
            case "κυριακή": return "Kuriakh";
            default: return "Unknown";
        }
    }

    // Υπολογισμός βάρδιας (Πρωινή/Βραδινή)
    private String calculateShift(String time) {
        if (time.compareTo("08:00") >= 0 && time.compareTo("14:30") < 0) {
            return "Prwini";
        } else {
            return "Bradinh";
        }
    }

    // Μέθοδος για να ανατρέξει βάση δεδομένων SQLite 
    public void findHospitals(String department, String dayOfWeek, String time) {
        try {
            // Μετατροπή σε Greeklish
            departmentGreeklish = convertDepartmentToGreeklish(department);
            dayGreeklish = convertDayToGreeklish(dayOfWeek);
            shift = calculateShift(time);

            // Σύνδεση στη βάση δεδομένων SQLite
            String url = "jdbc:sqlite:hospital_system.db";
            Connection connection = DriverManager.getConnection(url);

            // Ερώτημα SQL
            String query = "SELECT h.hospital_name, h.hospital_address, h.area " +
                           "FROM hospitals h " +
                           "JOIN hospital_schedule hs ON h.hospital_id = hs.hospital_id " +
                           "JOIN schedule s ON hs.schedule_id = s.schedule_id " +
                           "WHERE s.department = ? " +
                           "AND s.day = ? " +
                           "AND s.shift = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, departmentGreeklish);
            statement.setString(2, dayGreeklish);
            statement.setString(3, shift);

            ResultSet resultSet = statement.executeQuery();

            // Εμφάνιση αποτελεσμάτων
            System.out.println("Νοσοκομεία που εφημερεύουν:");
            while (resultSet.next()) {
                String hospitalName = resultSet.getString("hospital_name");
                String hospitalAddress = resultSet.getString("hospital_address");
                String area = resultSet.getString("area");
                System.out.println(hospitalName + " - " + hospitalAddress + " (" + area + ")");
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Σφάλμα κατά την αναζήτηση νοσοκομείων: " + e.getMessage());
        }
    }
}
