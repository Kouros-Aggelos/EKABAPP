import java.util.Scanner;
public class User {
        private String fullName;
        private int age;
        private String incidentType;
        private String incidentDescription;
        private String address;
        private String municipality;

        Scanner scanner = new Scanner(System.in);
    
        public User() {
            this.fullName = setFullName();
            this.age = setAge();
            this.incidentType = setIncidentType();
            this.incidentDescription = setIncidentDescription();
            this.address = setAddress();
            this.municipality = setMunicipality();
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

        public void setFullName() {
            System.out.println("Παρακαλώ εισάγετε το ονοματεπώνυμο του ασθενή");
            this.fullName = scanner.nextLine();
        }
        //Possibility of error in data input from the user.
        //TODO(Vicky):add exception 
        public void setAge() {
            System.out.println("Παρακαλώ εισάγετε την ηλικία του ασθενή");
            this.age = scanner.nextInt();
        }
        public void setIncidentType() {
            System.out.println("Είδος συμβάντος:");
            this.incidentType = scanner.nextLine();
        }
        public void setIncidentDescription(String incidentDescription) {
            this.incidentDescription = incidentDescription;
        }
        public void setAddress() {
            System.out.println("Εισάγετε την διεύθυνση του περιστατικού");
            this.address = scanner.nextLine();
        }
        public void setMunicipality() {
            System.out.println("Δήμος περιστατικού:");
            this.municipality = scanner.nextLine();
        }
        @Override
        public String toString() {
            return "User [Όνοματεπώνυμο=" + fullName + ", Ηλικία=" + age + ", Είδος Περιστατικού=" + incidentType +
                   ", Περιγραφή Περιστατικού=" + incidentDescription + ", Διεύθυνση=" + address +
                   ", Δήμος=" + municipality + "]";
        }
}
    


