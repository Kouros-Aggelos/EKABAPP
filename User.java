import java.util.Scanner;
public class User {
        private String fullName;
        private int age;
        private String incidentType;
        private String incidentDescription;
        private String address;
        private String municipality;
    
        public User(String fullName, int age, String incidentType, String incidentDescription, String address, String municipality) {
            this.fullName = fullName;
            this.age = age;
            this.incidentType = incidentType;
            this.incidentDescription = incidentDescription;
            this.address = address;
            this.municipality = municipality;
        }
        public String getFullName() {
            return fullName;
        }
        public int getAge() {
            return age;
        }
        public String getIncidentType() {
            return incidentType;
        }
        public String getIncidentDescription() {
            return incidentDescription;
        }
        public String getAddress() {
            return address;
        }
        public String getMunicipality() {
            return municipality;
        }
        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
        public void setAge(int age) {
            this.age = age;
        }
        public void setIncidentType(String incidentType) {
            this.incidentType = incidentType;
        }
        public void setIncidentDescription(String incidentDescription) {
            this.incidentDescription = incidentDescription;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public void setMunicipality(String municipality) {
            this.municipality = municipality;
        }
        @Override
        public String toString() {
            return "User [Όνοματεπώνυμο=" + fullName + ", Ηλικία=" + age + ", Είδος Περιστατικού=" + incidentType +
                   ", Περιγραφή Περιστατικού=" + incidentDescription + ", Διεύθυνση=" + address +
                   ", Δήμος=" + municipality + "]";
        }

        public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Εισάγετε το ονοματεπώνυμο: ");
        String fullName = scanner.nextLine();

        System.out.print("Εισάγετε την ηλικία: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Εισάγετε το είδος περιστατικού (π.χ., καρδιολογικό, παθολογικό): ");
        String incidentType = scanner.nextLine();

        System.out.print("Περιγράψτε το περιστατικό: ");
        String incidentDescription = scanner.nextLine();

        System.out.print("Εισάγετε τη διεύθυνση: ");
        String address = scanner.nextLine();

        System.out.print("Εισάγετε το δήμο: ");
        String municipality = scanner.nextLine();

        User user = new User(fullName, age, incidentType, incidentDescription, address, municipality);

        System.out.println("\nΣτοιχεία Χρήστη:");
        System.out.println(user);
        scanner.close();
    }
}
    


