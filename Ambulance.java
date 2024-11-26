public class Ambulance {
    private int ambId;
    private String status;
    private double latitude;
    private double longitude;

    //Κατασκευαστής
    public Ambulance(int ambId) {
        this.ambId = ambId;
        this.status = "available";
    }
    //Διαθεσιμότητα
    public boolean isAvailable() {
        return "available".equals(status);
    }
    //Ανανέωση κατάστασης ασθενοφόρου
    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }
    Closest patient = new Closest();
    //Υπολογισμός απόστασης ασθενή και ασθενοφόρου
    for (Ambulance ambulance : ambulances) {
        if status = "available" {
            double distancePatToAmb = calculateDistance(patient.currentLat, patient.currentLon, latitude, longitude);
        }
    }
}

