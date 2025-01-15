# EKABAPP Optimazation System
## Περιγραφή Εφαρμογής

Το **EKAB Optimization** System είναι μια εφαρμογή που στοχεύει στη βελτιστοποίηση της διαχείρισης περιστατικών έκτακτης ανάγκης. Παρέχει την δυνατότητα να βρεθεί το κοντινότερο και καταλληλότερο νοσοκομείο, βελτιστοποιώντας την διαδρομή του ασθενοφόρου, παρουσιάζοντας ταυτόχρονα την διαδρομή σε πραγματικό γραφικό περιβάλλον🍒

## Λειτουργίες
1. Αποθήκευση δεδομένων
2. Εκτίμηση σοβαρότητας περιστατικού
3. Έρευση νοσοκομείων βάσει κατάλληλου department και εφημερίας
4. Διαλογή ενός μέσω υπολογισμού βέλτιστης απόστασης
5. Παρουσίαση βέλτιστης διαδρομής, χιλιομετρικής απόστασης, χρονικό διάστημα
6. Εμφάνιση διαδρομής στον χάρτη.



## Οδηγίες Μεταγλώττισης και Εκτέλεσης 
#### (μέσω cmd)

### Αλλάξτε την κωδικοποίηση της κονσόλας σε UTF-8 με την εντολή:

  chcp 65001
### Συνδεθείτε στην ενσωματωμένη βάση μέσω της εξής διαδικασίας:
   cd src/main/resources
   .\slqlite3 hospital_system.db
   cd .. (x3)

### Μεταγλωττίστε όλα τα αρχεία Java μαζί με τις βιβλιοθήκες με την εντολή:

javac -cp .;json-20250107.jar;sqlite-jdbc-3.47.2.0.jar *.java
ή mvn clean install

### Εκτελέστε την εφαρμογή με την εντολή:




## Δομή Περιεχομένων Αποθετηρίου
├───.github
│   └───workflows
├───.vscode
├───config
│   └───checkstyle
├───lib
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───kerasia
│   │   ├───resources
│   │   └───WEBAPP
│   └───test
│       └───java
│           └───com
│               └───kerasia
└───target
    ├───classes
    │   └───com
    │       └───kerasia
    ├───generated-sources
    │   └───annotations
    ├───generated-test-sources
    │   └───test-annotations
    ├───maven-status
    │   └───maven-compiler-plugin
    │       ├───compile
    │       │   └───default-compile
    │       └───testCompile
    │           └───default-testCompile
    ├───surefire-reports
    └───test-classes
        └───com
            └───kerasia

.github/workflows: Περιέχει αρχεία ρυθμίσεων για αυτοματοποιημένες ροές εργασίας και εκτέλεση αυτοματοποιημένων διαδικασιών ελέγχο (CI/CD) μέσω του GitHub Actions.

.vscode: Εξασφαλίζει κοινές ρυθμίσεις όλους τους χρήστες του αποθετηρίου , περιέχοντας ρυθμίσεις .json.

config/checkstyle: Χρήσιμο για τη διασφάλιση ότι ο κώδικας ακολουθεί προκαθορισμένα πρότυπα μορφοποίησης.

lib: Αποθηκεύει εξωτερικές βιβλιοθήκες (JAR αρχεία) που απαιτούνται για τη λειτουργία της εφαρμογής, συγκεκριμένα για την πρόσβαση στην sqlite και την διεκπαιρέωση της κλάσης closest που αξιοποιεί βιβλιοθήκες json(json-20250107.jar , sqlite-jdbc-3.47.2.0.jar).

src/main/java/com/kerasia: Περιέχει όλο τον πηγαίο κώδικα της εφαρμογής.
   Closest: Υπολογισμός απόστασης του κοντινότερου νοσοκομείου.
   Frommap: Υπολογισμός χρόνου διαδρομής μέσω API και του internal χάρτη index.html.
   MapLauncher:
   hospitalfind: Κλάση που πραγματοποιεί την σύνδεση με την ενσωματωμένη βάση δεδομένων.
   SeverityIndex: Υπολογισμός σοβαρότητας περιστατικού βάσει ESI algorithm.
   Suitable: Επιλογή κοντινότερου και καταλληλότερου νοσοκομείου.
   User: Διαχείριση πληροφοριών χρήστη.
   Main: Κεντρικός συντονιστής εφαρμογής

src/main/resources: Περιέχει στατικά αρχεία και συγκεκριμένα το query της βάσης δεδομένων
src/main/WEBAPP: Περιέχει html,css,js αρχεία που σχετίζονται με το front-end μέρος της εφαρμογής και συγκεκριμένα τον χάρτη(ekab.html,ekab.css,ekab.js, Geocode Address.js, Geocode Address.html)

src/test/java/com/kerasia: Περιέχει τις test κλάσεις (UserTest, ClosestTest, MainTest, hospitalfinderTest, SuitableTest, SeverityIndexTest) για την επαλήθευση της ορθής λειτουργίας του πηγαίου κώδικα.

target: Χρησιμοποιείται από το Maven για να αποθηκεύει τα παραγόμενα αρχεία (build artifacts), κυρίως τα .class αρχεία.

  classes/com/kerasia: Περιέχει τα παραγόμενα .class αρχεία για την εφαρμογή.

  generated-sources/annotations: Περιέχει πηγαίο κώδικα που δημιουργείται αυτόματα από annotations.

  generated-test-sources/test-annotations: Περιέχει πηγαίο κώδικα που δημιουργείται αυτόματα από annotations δοκιμών.

  maven-status: Περιέχει πληροφορίες σχετικά με την κατάσταση του Maven build.

  maven-compiler-plugin: Περιέχει πληροφορίες για τη διαδικασία μεταγλώττισης (compile).

  surefire-reports: Περιέχει αναφορές από την εκτέλεση των δοκιμών μέσω του Maven Surefire Plugin.

  test-classes/com/kerasia: Περιέχει τα παραγόμενα .class αρχεία για τις κλάσεις δοκιμών.

umldiagram.png

pom.xml

README.md: Οδηγίες χρήσης και περιγραφή.

## Διάγραμμα UML
    ![Το διάγραμμα UML της εφαρμογής μας.](/umldiagram.png)


## Δομές Δεδομένων και Αλγόριθμοι

### Βάση Δεδομένων (SQLite):(????)
Πίνακας hospitals (ονόματα περιοχές νοσοκομείων)
Πίνακας hospital_schedule (εφημερείες νοσοκομείων)
Πίνακας schedule (εφημερείες διαφόρων departments)

### Αλγόριθμοι:
ESIcode:Ο αλγόριθμο ESI αξιολογεί την σοβαρότητα του περιστατικού και το ιεραρχεί σε μια κλίμακα 1-5 στην κλάση SeverityIndex.
(μείζονος - ήσσονος σοβαρότητας).
Pythagorean distance formula: Αλγόριθμος -μαθηματικός φορμαλισμός για τον υπολογισμό κοντινότερης απόστασης μεταξύ δύο σημείων.

### Δομές Δεδομένων!!!!!!!!!

## Προαπαιτούμενα

Κατεβασμένα αρχεία JAR:

json-20250107.jar

sqlite-jdbc-3.47.2.0.jar

SQLite3 tools.



## API Documentation
!!!!
## Αναλυτική χρήση ΠΤΝ-Ενδεικτικά prompts

### Ρωτήσαμε : 
The current URL loading the Maps JavaScript API has not been added to the list of allowed referrers. Please check the referrer settings of your API key in the Cloud console. ti simainei;
### ChatGPT said:
Το μήνυμα αυτό σημαίνει ότι το API Key που χρησιμοποιείς για το Google Maps JavaScript API έχει ρυθμιστεί να επιτρέπει αιτήματα (requests) μόνο από συγκεκριμένα URLs, και το URL που χρησιμοποιείς αυτή τη στιγμή δεν βρίσκεται στη λίστα των επιτρεπόμενων URLs (referrers).

Για να διορθώσεις το πρόβλημα, ακολούθησε τα παρακάτω βήματα:

#### 1. Σύνδεση στο Google Cloud Console
Πήγαινε στο Google Cloud Console.
#### 2. Εύρεση του API Key
Από το μενού, πήγαινε στη "APIs & Services" > "Credentials".
Βρες το API Key που χρησιμοποιείς για το Maps JavaScript API.
#### 3. Ρύθμιση Επιτρεπόμενων Referrers
Κάνε κλικ στο API Key που χρησιμοποιείς.
Στην ενότητα "Application restrictions", βεβαιώσου ότι έχεις επιλέξει "HTTP referrers (web sites)".
Στην ενότητα "Website restrictions", πρόσθεσε τα URLs της εφαρμογής σου που θα έχουν πρόσβαση στο API.
Παράδειγμα:
arduino
Copy code
https://example.com/*
http://localhost:8080/*
4. Αποθήκευση Αλλαγών
Αφού προσθέσεις τα URLs, κάνε κλικ στο "Save".
5. Δοκιμή της Εφαρμογής
Φόρτωσε ξανά την εφαρμογή σου και έλεγξε αν το πρόβλημα λύθηκε.
Σημαντικές Παρατηρήσεις:
Αν δουλεύεις τοπικά, πρέπει να προσθέσεις το localhost στις επιτρεπόμενες διευθύνσεις, π.χ., http://localhost:3000/* (ή τη θύρα που χρησιμοποιείς).
Αν το πρόβλημα συνεχίζει, επιβεβαίωσε ότι χρησιμοποιείς το σωστό API Key στο πρόγραμμά σου.


### Ρωτήσαμε:

### Το ChatGPT είπε:

### Ρωτήσαμε:
The severityindex code that i have written receives answers in greek like ναι/οχι but scanner doesnt recognize greek so when i press nai/oxi it goes straightly to the invalidinput exception.Why?
### ChatGPT said:

The issue you're encountering with the Scanner not recognizing Greek input (like "ναι" or "όχι") is likely because the Scanner is expecting input in a different encoding format, and Greek characters are not properly recognized by default.

To handle Greek (or other non-ASCII) characters properly in Java, you need to make sure you're using the right character encoding when reading the input. Here's how you can resolve the issue:

#### 1. Set the Scanner to Use UTF-8 Encoding
Java uses the system default character encoding for input, but in your case, it seems like the encoding is not handling Greek characters correctly. You can set the Scanner to use UTF-8 encoding to handle Greek characters properly.
#### 2. Ensure Your IDE Supports UTF-8
If you're using an IDE like IntelliJ IDEA, Eclipse, or Visual Studio Code, make sure your IDE is configured to use UTF-8 encoding for your source code files and console output.

#### In VS Code:
 Open Settings (Ctrl + ,).
Search for Files: Encoding and set it to utf8.
#### In IntelliJ IDEA:
Go to File > Settings > Editor > File Encodings and set both Global Encoding and Project Encoding to UTF-8.
### 3. Test Greek Input
After these changes, run your program and test the Greek inputs ("ναι" or "όχι"). It should now work without throwing the InvalidInputException for valid inputs.
If you're still encountering issues, let me know!

## Συγγραφείς
Η εφαρμογή αυτή δημιουργήθηκε στα πλαίσια εκπόνησης της εργασίας του μαθήματος Προγραμματισμού ΙΙ ,του τμήματος Διοικητικής Επιστήμης και Τεχνολογίας που ανήκει Οικονομικού Πανεπιστημίου Αθηνών.
Την επιμέλεια αυτής επωμίστηκε η ομάδα "Κεράσια" και κάτωθι παρουσιάζονται τα μέλη της αναλυτικά:
* Κούρος Άγγελος (8220070)
* Ζωντανού Βασιλική (8230039)
* Θεοδοσίου Βασίλης(8230041)
* Λαμάι Φλοριάν (8230071)
* Λίβα Ευθυμία (8230078)
* Μουραφέτης Δημήτρης (8230100)
* Πανούση Αικατερίνη (8230113)

## License

* Ρηγοπούλου Κωνσταντίνα-Δήμητρα (8230129)
