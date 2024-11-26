-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: ekab
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `hospitals`
--

DROP TABLE IF EXISTS `hospitals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospitals` (
  `hospital_id` int NOT NULL AUTO_INCREMENT,
  `hospital_name` varchar(100) NOT NULL,
  `hospital_address` varchar(150) DEFAULT NULL,
  `area` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`hospital_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospitals`
--

LOCK TABLES `hospitals` WRITE;
/*!40000 ALTER TABLE `hospitals` DISABLE KEYS */;
INSERT INTO `hospitals` VALUES (1,'Γ.Ν.Ν. ΙΩΝΙΑΣ ΚΩΝ/ΠΟΥΛΕΙΟ','Θεοδ. Κωνσταντοπούλου 3-5','Νέα Ιωνία'),(2,'Γ.Ν.Α. Γ. ΓΕΝΝΗΜΑΤΑΣ','Λεωφ. Μεσογείων 154','Αθήνα'),(3,'Γ.Ν.Α. ΑΛΕΞΑΝΔΡΑ','Βασ. Σοφίας 80','Αθήνα'),(4,'Γ.Ν.Α. ΣΩΤΗΡΙΑ','Λεωφ. Μεσογείων 152','Αθήνα'),(5,'Γ.Ν.Π. ΤΖΑΝΕΙΟ','Ζαννή & Αφεντούλη','Πειραιάς'),(6,'Π.Γ.Ν. ΑΤΤΙΚΟΝ','Ρίμινι 1','Χαϊδάρι'),(7,'Γ.Ν.Α. ΣΙΣΜΑΝΟΓΛΕΙΟ','Σισμανογλείου 1','Μαρούσι'),(8,'Γ.Ν.Α. ΕΥΑΓΓΕΛΙΣΜΟΣ','Υψηλάντου 45-47','Αθήνα'),(9,'Γ.Ν.Α. ΚΑΤ','Νίκης 2','Κηφισιά'),(10,'Γ.Ν. ΑΣΚΛΗΠΙΕΙΟ ΒΟΥΛΑΣ','Βασ. Παύλου 1','Βούλα'),(11,'Γ.Ν.Α. ΛΑΪΚΟ','Αγίου Θωμά 17','Αθήνα'),(12,'Γ.Ο.Ν.Κ. ΑΓ. ΑΝΑΡΓΥΡΟΙ','Νεαπόλεως 4-6','Κηφισιά'),(13,'Α.Ο.Ν.Α. ΑΓ. ΣΑΒΒΑΣ','Λεωφ. Αλεξάνδρας 171','Αθήνα'),(14,'Ν.Δ.Ν.Α. Α. ΣΥΓΓΡΟΣ','Ι. Δραγούμη 5','Αθήνα'),(15,'Γ.Ν.Μ. ΕΛ. ΒΕΝΙΖΕΛΟΥ','Πλατεία Έλενας Βενιζέλου 2','Αθήνα'),(16,'Π.Γ.Ν.Α. ΑΡΕΤΑΙΕΙΟ','Βασ. Σοφίας 76','Αθήνα'),(17,'Γ.Ν. Π.Α. ΑΓΛ. ΚΥΡΙΑΚΟΥ','Θηβών & Λειβαδιάς','Αθήνα'),(18,'Γ.Ν. ΠΑΙΔΩΝ ΠΕΝΤΕΛΗΣ','Ιπποκράτους 8','Πεντέλη'),(19,'Ν.Α. ΟΦΘΑΛΜΙΑΤΡΕΙΟ ΑΘΗΝΩΝ','Πανεπιστημίου 26','Αθήνα'),(20,'Ψ.Ν.Α. ΔΑΦΝΙ','Ιερά Οδός 343','Χαϊδάρι'),(21,'Ψ.Ν.Α. ΔΡΟΜΟΚΑΪΤΕΙΟ','Ιερά Οδός 343','Χαϊδάρι'),(22,'Ν.Π. ΑΙΓΙΝΗΤΕΙΟ','Βασ. Σοφίας 72-74','Αθήνα'),(23,'Ε.Α.Ν.Π. ΜΕΤΑΞΑ','Μπόταση 51','Πειραιάς'),(24,'ΚΟΡΓ. ΜΠΕΝ. ΕΕΣ','Ερυθρού Σταυρού 1','Αθήνα'),(25,'ΙΠΠΟΚΡΑΤΕΙΟ','Βασ. Σοφίας 114','Αθήνα'),(26,'ΕΛΠΙΣ','Δημητσάνας 7','Αθήνα'),(27,'ΑΓ. ΠΑΝΤΕΛΕΗΜΟΝΑΣ','Νίκαιας 28','Νίκαια'),(28,'ΠΑΜΜΑΚΑΡΙΣΤΟΣ','Ιακωβάτων 43','Αθήνα'),(29,'ΑΓ. ΣΟΦΙΑ','Θηβών & Παπαδιαμαντοπούλου','Αθήνα');
/*!40000 ALTER TABLE `hospitals` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-26  3:14:12
