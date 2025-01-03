import java.util.ArrayList;

public class Ambulance {
    private int ambId;
    private String status;
    private double latitude;
    private double longitude;
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
    //Εύρεση κοντινότερου ασθενοφόρου
    public static Ambulance findNearestAmbulance(ArrayList<Ambulance> ambulances, double currentLat, double currentLon) {
        double shortestDistance = Double.MAX_VALUE;
        Ambulance nearestAmbulance = null;
    //Υπολογισμός απόστασης ασθενή και ασθενοφόρου
    for (Ambulance ambulance : ambulances) {
        if ("available".equals(status)) {
            double distance = Hospital.calculateDistance(currentLat, currentLon, latitude, longitude);
            if (distance < shortestDistance) {
                shortestDistance = distance;
                nearestAmbulance = ambulance;
            }
        }
    }
    public void displayAmbulanceInfo() {
        System.out.println("Ambulance ID: " + ambId + ", Location: (" + latitude + ", " + longitude + ")");
    public static void main(String[] args) {
        ArrayList<Ambulance> ambulances = new ArrayList<>();
        //Τυχαίες αρχικές τοποθεσίες για τα ασθενοφόρα
        for (int i = 0; i < 5; i++) {
        double latitude = 40.0 + (Math.random() * 10);  // Τυχαίο γεωγραφικό πλάτος (π.χ. 40.0 - 50.0)
        double longitude = -75.0 + (Math.random() * 10); // Τυχαίο γεωγραφικό μήκος (π.χ. -75.0 - -65.0)
        ambulances.add(new Ambulance(i, latitude, longitude));
        }
        for (Ambulance ambulance : ambulances) {
            ambulance.displayAmbulanceInfo();
        }
    }
}        
