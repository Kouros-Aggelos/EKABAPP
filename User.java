
import java.util.InputMismatchException;
import java.util.Scanner;

public class User {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userId;
        int age = 0;
        int caseType = 0;
        // Δηλαδή αν είναι οφθαλμολογικού, ορθοπεδικού κτλ
        String caseDescription = "";
        String accidentAddress = " ";
        String accidentDistrict = " ";
        try {
            System.out.print("Παρακαλώ εισάγετε ηλικία θύματος: ");
            age = sc.nextInt();
            while (age <= 0) {
                System.out.print("Η ηλικία πρέπει να είναι ένας θετικός αριθμός. Παρακαλώ προσπαθήστε ξανά: ");
                age = sc.nextInt();
            }
            while (caseType < 1 || caseType > 20) {
                System.out.print("Παρακαλώ επιλέξτε είδος περιστατικού (1-20): ");
                caseType = sc.nextInt();
            }
            System.out.print("Παρακαλώ εισάγετε σύντομη περιγραφή του ατυχήματος: ");
           while ((caseDescription.isEmpty())) {
            caseDescription = sc.nextLine();
           }
            System.out.print("Παρακαλώ εισάγετε τη διεύθυνση του ατυχήματος: ");
            accidentAddress = sc.nextLine();
            System.out.println("\nΗ εισαγωγή ολοκληρώθηκε με επιτυχία! ");
            System.out.println("Ηλικία θύματος: " + age);
            System.out.println("Είδος περιστατικού: " + caseType);
            System.out.println("Περιγραφή: " + caseDescription);
            System.out.println("Διεύθυνση ατυχήματος: " + accidentAddress);
        } catch (InputMismatchException e) {
            System.out.print("Παρακαλώ εισάγετε έγκυρη ηλικία θύματος: ");
        } catch (Exception e) {
            System.out.print("Προέκυψε ενα σφάλμα. Παρακαλώ προσπαθήστε ξανά! ");
        } finally {
            sc.close();
        }
    }
}
