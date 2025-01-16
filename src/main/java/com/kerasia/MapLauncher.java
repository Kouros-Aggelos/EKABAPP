package com.kerasia;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class MapLauncher {
    public void launchMap() {
        try {
            // Δημιουργία διαδρομής στο αρχείο HTML
            File htmlFile = new File("src/main/WEBAPP/ekab.html"); // Προσαρμόστε τη διαδρομή στο αρχείο σας

            // Άνοιγμα του αρχείου HTML στον προεπιλεγμένο browser
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } else {
                System.out.println("Το άνοιγμα του χάρτη δεν υποστηρίζεται στο σύστημά σας.");
            }
        } catch (IOException e) {
            System.err.println("Σφάλμα κατά το άνοιγμα του χάρτη: " + e.getMessage());
        }
    }
}
