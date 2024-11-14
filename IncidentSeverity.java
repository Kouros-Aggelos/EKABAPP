public class IncidentSeverity {

    // Enum για τα επίπεδα κρισιμότητας (από 1 έως 5)
    public enum SeverityLevel {
        LEVEL_1("Πολύ Χαμηλή Σημαντικότητα"),
        LEVEL_2("Χαμηλή Σημαντικότητα"),
        LEVEL_3("Μεσαία Σημαντικότητα"),
        LEVEL_4("Υψηλή Σημαντικότητα"),
        LEVEL_5("Πολύ Υψηλή Σημαντικότητα");

        private final String description;

        SeverityLevel(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // Ιδιότητες της κλάσης
    private SeverityLevel severityLevel;
    private String incidentDescription;

    // Κατασκευαστής
    public IncidentSeverity(int severity, String incidentDescription) {
        this.severityLevel = setSeverityLevel(severity);
        this.incidentDescription = incidentDescription;
    }

    // Μέθοδος για να ορίσουμε τη σημαντικότητα
    private SeverityLevel setSeverityLevel(int severity) {
        switch (severity) {
            case 1:
                return SeverityLevel.LEVEL_1;
            case 2:
                return SeverityLevel.LEVEL_2;
            case 3:
                return SeverityLevel.LEVEL_3;
            case 4:
                return SeverityLevel.LEVEL_4;
            case 5:
                return SeverityLevel.LEVEL_5;
            default:
                throw new IllegalArgumentException("Το επίπεδο σημαντικότητας πρέπει να είναι από 1 έως 5.");
        }
    }

    // Μέθοδος για επιστροφή του επιπέδου κρισιμότητας
    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }

    // Μέθοδος για επιστροφή της περιγραφής του περιστατικού
    public String getIncidentDescription() {
        return incidentDescription;
    }

    // Μέθοδος για εμφάνιση των πληροφοριών του περιστατικού
    @Override
    public String toString() {
        return "Περιστατικό: " + incidentDescription +
               "\nΣημαντικότητα: " + severityLevel.getDescription();
    }
}
