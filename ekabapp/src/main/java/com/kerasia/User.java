package com.kerasia;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class User {

        //TODO(Vicky): Add severity level when ESI class available
        //Maybe the fields should be final?

        private String fullName;
        private int age;
        private String address;
        private String municipality;
        private String dayOfWeek;
        private String time;

        Scanner scanner = new Scanner(System.in);
    
        public User() {
            getUserInput();
            saveDayAndTime();
        }
       
        public void getUserInput() {
        
            System.out.println("Παρακαλώ εισάγετε το ονοματεπώνυμο του ασθενή");
            fullName = scanner.nextLine();

            age = setAge();

            System.out.println("Εισάγετε την διεύθυνση του περιστατικού");
            address = scanner.nextLine();

            System.out.println("Δήμος περιστατικού:");
            municipality = scanner.nextLine();
        }
       
        //Possibility of error in data input from the user.
        public int setAge() throws InputMismatchException {
            
            int a = -1;
            boolean validInput = false; 
            do {   
                try {
                        System.out.println("Παρακαλώ εισάγετε την ηλικία του ασθενή");
                        a = scanner.nextInt();
                        scanner.nextLine();
                        if (a <= 0) {
                            System.out.println("Η ηλικία πρέπει να είναι θετικός αριθμός. Παρακαλώ δοκιμάστε ξανά.");
                        } else {
                            validInput = true;
                        }
                    } 
                catch (InputMismatchException e) {
                    System.err.println("Μη έγκυρη είσοδος. Παρακαλώ εισάγετε έναν αριθμό ακέραιο.");
                    scanner.nextLine(); 
                } 
            } while (!validInput);
            return a;
        } 


        public void saveDayAndTime(){
            LocalDateTime currentDateTime = LocalDateTime.now();
        // Αποθηκεύουμε τη μέρα της εβδομάδας στα ελληνικά
        dayOfWeek = currentDateTime.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("el"));
        // Αποθηκεύουμε την ώρα με τη μορφή HH:mm
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        time = currentDateTime.format(timeFormatter);

        }


        public String getFullName() {
            return fullName;
        }
        public int getAge() {
            return age;
        }
        public String getAddress() {
            return address;
        }
        public String getMunicipality() {
            return municipality;
        }
        public String getDayOfWeek() {
            return dayOfWeek;
        }
        public String getTime() {
            return time;
        }


        @Override
        public String toString() {
            return "Πληροφορίες χρήστη: [Όνοματεπώνυμο=" + fullName + ", Ηλικία=" + age + ", Διεύθυνση=" + address +
                   ", Δήμος=" + municipality + "]";
        }

        public static void main(String[] args) {
            User u1 = new User();
            System.out.println(u1);
        }
}
