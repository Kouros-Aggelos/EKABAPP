public class Main {
    public static void main(String[] args) {
        // Δημιουργία αντικειμένων
        User user = new User();
        HospitalFinder finder = new HospitalFinder();
    
        // Λήψη δεδομένων από τον χρήστη
        user.getUserInput();
        user.displayUserInfo();
    
        // Ανάκτηση δεδομένων από την κλάση User
        String caseType = user.getIncidentType();
        String dayOfWeek = user.getDayOfWeek();
        String time = user.getTime();
    
        // Κλήση της μεθόδου για αναζήτηση νοσοκομείων με τα δεδομένα του χρήστη
        finder.findHospitals(caseType, dayOfWeek, time);
    }    
}
