import java.util.ArrayList;

public class Hospital {

    private String title;
    private double latitude;
    private double longitude;

    // Στατική λίστα για αποθήκευση των νοσοκομείων
    private static ArrayList<Hospital> hospitals = new ArrayList<>();

    // Κατασκευαστής
    public Hospital(String title, double latitude, double longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter για τον τίτλο του νοσοκομείου
    public String getTitle() {
        return title;
    }

    // Getter για το γεωγραφικό πλάτος
    public double getLatitude() {
        return latitude;
    }

    // Getter για το γεωγραφικό μήκος
    public double getLongitude() {
        return longitude;
    }

    // Μέθοδος υπολογισμού απόστασης μεταξύ νοσοκομείου και εκ' άστοτε περιστατικού
    public static double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    // Μέθοδος για την εμφάνιση των εφημερευόντων νοσοκομείων
    public static void displayHospitals() {
        if (hospitals.isEmpty()) {
            System.out.println("Δεν υπάρχουν νοσοκομεία στη λίστα.");
        } else {
            for (Hospital hospital : hospitals) {
                System.out.println(hospital.getTitle() + " - Συντεταγμένες: (" + hospital.getLatitude() + ", " + hospital.getLongitude() + ")");
            }
        }
    }

    // Μέθοδος για να εισάγουμε τα νοσοκομεία στην static λίστα
    public static void addHospital(Hospital hospital) {
        hospitals.add(hospital);
    }

    // Μέθοδος για να ανακτήσουμε όλα τα νοσοκομεία από τη λίστα
    public static ArrayList<Hospital> getHospitals() {
        return hospitals;
    }

    // Μέθοδος για να αρχικοποιήσουμε τα νοσοκομεία (π.χ. από δεδομένα ή βάση δεδομένων)
    public static void initializeHospitals() {
        // Όλα τα νοσοκομεία
        addHospital(new Hospital("Γ.Ν.Ν.ΙΩΝΙΑΣ ΚΩΝ/ΠΟΥΛΕΙΟ", 38.037999023010904, 23.757447111046247));
        addHospital(new Hospital("Γ.Ν.Α. Γ. Γεννηματάς", 37.99726391243294, 23.782734111040604));
        addHospital(new Hospital("Γ.Ν.Α. Αλεξάνδρα", 37.98053270577266, 23.75491176686225));
        addHospital(new Hospital("Γ.Ν.Α. Σωτηρία", 37.99524844183412, 23.77951561104386));
        addHospital(new Hospital("Γ.Ν.Π. Τζάνειο", 37.934485573858964, 23.645577453368304));
        addHospital(new Hospital("Π.Γ.Ν. Αττικόν", 38.01596528716577, 23.665232439881265));
        addHospital(new Hospital("Γ.Ν.Α. Σισμανόγλειο", 38.046362110183004, 23.82830821104667));
        addHospital(new Hospital("Γ.Ν.Α. Ευαγγελισμός", 37.97765258351495, 23.74666925151744));
        addHospital(new Hospital("Γ.Ν.Α. ΚΑΤ", 38.0660559147875, 23.808382924539217));
        addHospital(new Hospital("Γ.Ν. Ασκληπιείο Βούλας", 37.84846152543729, 23.754899626380826));
        addHospital(new Hospital("Γ.Ν.Α. Λαϊκό", 37.9834127354887, 23.765280838026303));
        addHospital(new Hospital("Γ.Ο.Ν.Κ. Άγιοι Ανάργυροι", 38.079905336267686, 223.770074595703807)); // Διόρθωσε το μήκος
        addHospital(new Hospital("Α.Ο.Ν.Α. Άγιος Σάββας", 37.988135008135764, 23.75558193591389));
        addHospital(new Hospital("Ν.Δ.Ν.Α. Α. Σύγγρος", 37.97405040166575, 23.75180382453426));
        addHospital(new Hospital("Γ.Ν.Μ. Ελ. Βενιζέλου", 37.985131896555146, 23.755248798329937));
        addHospital(new Hospital("Π.Γ.Ν.Α. Αρεταίειο", 37.97990823689677, 23.754277380353845));
        addHospital(new Hospital("Γ.Ν.Π.Α. Αγλαΐα Κυριακού", 37.98539189457194, 23.767847295698754));
        addHospital(new Hospital("Γ.Ν. Παίδων Πεντέλης", 38.05106509737491, 23.875143795702282));
        addHospital(new Hospital("Ν.Α. Οφθαλμιατρείο Αθηνών", 37.99085507245576, 23.755025135905996));
        addHospital(new Hospital("Ψ.Ν.Α. Δαφνί", 38.013336918462144, 23.640769124536387));
        addHospital(new Hospital("Ψ.Ν.Α. Δρομοκαΐτειο", 38.000719379180765, 23.662581894364857));
        addHospital(new Hospital("Ν.Π. Αιγινήτειο", 37.97941350885422, 23.7537402597085));
        addHospital(new Hospital("Ε.Α.Ν.Π. Μεταξά", 37.92996959741673, 23.64350316685954));
        addHospital(new Hospital("Ιπποκράτειο", 37.98372296250003, 23.759771156604263));
        addHospital(new Hospital("Ελπίς", 37.99001331758793, 23.755381353371344));
        addHospital(new Hospital("Παμμακάριστος", 38.01448759410462, 23.73161742453642));
        addHospital(new Hospital("Αγ. Σοφία", 37.98494513940935, 23.768890366862518));
        addHospital(new Hospital("Αρεταίειο", 37.97984903959627, 23.754288109189886));
        addHospital(new Hospital("Αγ. Παντελεήμονας", 37.97214084045193, 23.661937695698047));
    }

    // Μέθοδος για να πάρουμε τα εφημερεύοντα νοσοκομεία από την HospitalFinder και να τα αποθηκεύσουμε στη λίστα της Hospital
    public static void loadOnCallHospitals() {
        // Εδώ καλούμε τη μέθοδο από την HospitalFinder για να πάρουμε τα εφημερεύοντα νοσοκομεία
        ArrayList<Hospital> onCallHospitals = HospitalFinder.getOnCallHospitals();
        for (Hospital hospital : onCallHospitals) {
            addHospital(hospital);
        }
    }
}


