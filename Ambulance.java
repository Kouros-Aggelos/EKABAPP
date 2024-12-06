public class Ambulance {
    private int ambId;
    private String status;
    private double latitude;
    private double longitude;
    double shortestDistance;
    Ambulance nearestAmbulance;

    //Κατασκευαστής
    public Ambulance(int ambId) {
        this.ambId = ambId;
        this.status = "available";
        this.latitude = latitude;
        this.longitude = longitude;
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
    Ambulance nearestAmbulance = null;
    //Υπολογισμός απόστασης ασθενή και ασθενοφόρου
    for (Ambulance ambulance : ambulances) {
        if status = "available" {
            double distancePatToAmb = ambulance.calculateDistance(patient.currentLat, patient.currentLon, latitude, longitude);
            if (distancePatToAmb < shortestDistance) {
                shortestDistance = distance;
                nearestAmbulance = ambulance;
            }
        }
    }
    public void displayAmbulanceInfo() {
        System.out.println("Ambulance ID: " + ambId + ", Location: (" + latitude + ", " + longitude + ")");
}
