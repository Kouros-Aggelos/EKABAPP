public class Closest extends Hospital {
    public double currentLat;
    public double currentLon;
    double shortestDistance;
    Hospital nearestHospital;
    public Closest (double latitude, double longitude, double currentLat, double currentLon) {
        super (latitude, longitude);
        this.currentLat = currentLat;
        this.currentLon = currentLon;
    }
    for (Hospital hospital : hospitals) {
        double distance = calculateDistance(currentLat, currentLon, latitude, longitude);
        if (distance < shortestDistance) {
            shortestDistance = distance;
            nearestHospital = hospital;
        }
    }
    System.out.println("Το κοντινότερο νοσοκομείο είναι το" nearestHospital.name);
}