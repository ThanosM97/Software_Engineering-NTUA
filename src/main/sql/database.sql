-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: stingy
-- ------------------------------------------------------
-- Server version	5.6.24-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` mediumtext,
  `category` varchar(128) NOT NULL,
  `withdrawn` bit(1) NOT NULL DEFAULT b'0',
  `tags` mediumtext,
  `extraData` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `shop`;
CREATE TABLE `shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `address` mediumtext,
  `lng` FLOAT(13,10) NOT NULL,
  `lat` FLOAT(13,10) NOT NULL,
  `tags` mediumtext,
  `withdrawn` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price` FLOAT(8,3) NOT NULL,
  `date` DATE NOT NULL,
  `productId` int(11) NOT NULL,
  `shopId` int(11) NOT NULL,
  CONSTRAINT fk_record1 FOREIGN KEY (productId) REFERENCES product(id) ON DELETE CASCADE ON UPDATE CASCADE, 
  CONSTRAINT fk_record2 FOREIGN KEY (shopId) REFERENCES shop(id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
--
-- Dumping data for table `product`
--
/* For shops table plaisio, plaisio,
TVs have the following extra data: 4k, smart, frequency
Laptops have the following extra data: CPU, RAM, hd, OS, size, graphics card
Smartphones have the following extra data: CPU cores, cpu freq, RAM, capacity, size , camera pixels 
*/
LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'SAMSUNG TV 43\'\' UE43NU7122','Μινιμαλιστικό design και ποιότητα κατασκευής, 4Κ ανάλυση, Smart εφαρμογές και πρωτοποριακές τεχνολογίες για ακόμη ελκυστικότερη εικόνα στην πιο προσιτή τιμή','TV','\0','TV, Smart TV, 4K,','4K, Smart, 1300 PQI'),(2,'LG TV 49\'\' 49UK6200','Με ανάλυση UHD 4K, τεχνολογία 4K Active HDR, «έξυπνο» λειτουργικό webOS και ήχοUltra Surround για ανεπανάληπτη εμπειρία θέασης','TV','\0','TV, Smart TV, 4K', '4K, Smart, 1500 PMI'),(3,'DELL Laptop Inspiron 3573', 'Laptop Dell Inspiron 3573 με επεξεργαστή Intel Celeron N4000, μνήμη RAM 4GB, σκληρό δίσκο 500GB και HD αντι-ανακλαστική οθόνη 15.6 ιντσών, ιδανικό για καθημερινή χρήση.', 'Laptop','\0','Laptop, Windows 10','Intel Celeron N4000, 4GB, 500GB, Windows 10, 15.6, Intel HD Graphics 600'),(4,' Lenovo Laptop IdeaPad 330S-14AST', 'Αν ψάχνεις έναν υπερπλήρη φορητό ηλεκτρονικό υπολογιστή, για υψηλής απόδοσης εφαρμογές multimedia, ήχο και επεξεργαστική ισχύ, τότε το Lenovo IdeaPad 330S-14AST είναι το ιδανικό Laptop για εσένα!', 'Laptop', '\0','Laptop, Windows 10', 'AMD A-Series, 4GB, 128GB,Windows 10, 14,  Radeon R5'),(5,' Xiaomi Smartphone Redmi Note 6 Pro', 'Το Redmi Note 6 Pro διαθέτει διπλή εμπρόσθια και πίσω κάμερα που κάνει τη διαφορά, ενσωματώνει τον Snapdragon 636, τον νεότερο 14nm επεξεργαστή της Qualcomm, βελτιώνοντας σημαντικά τις συνολικές επιδόσεις και την ενεργειακή αποτελεσματικότητα. Επιπλέον, τα 3GB μνήμη RAM συνεισφέρουν στην ομαλή λειτουργία, ενώ η μπαταρία των 4000mAh θα καλύψει κάθε σου ανάγκη.', 'Smartphone', '\0', 'Black, Dual Sim, Android','Octa core, 1.8GHz, 3GB, 32GB, 6.2, 12 Mp');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Dumping data for table `shop`
--
LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (1, 'Πλαίσιο Αθήνα', 'Βουλής 3,10562,Αθήνα', 23.732913, 37.977344, 'computing, laptops, TV', '\0'),(2, 'Κωτσόβολος Αθήνα',
'Σταδίου 34 & Κοραή,10564,Αθήνα', 23.732025,37.979795, 'laptops, TV, monitors', '\0'),(3, 'Public Αθήνα', 'Καραγεώργη Σερβίας 1,10563,Αθήνα', 23.733666
,37.976454, 'laptops, TV', '\0');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `record`
--
LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES (1, 300, '2018-12-12', 1, 1), (2, 250, '2018-12-20', 1, 2), (3, 280, '2018-11-15', 2, 1), (4, 220,'2018-11-29', 2, 3), (5, 100, '2018-10-10', 3,2),(6,250,'2018-10-11', 3,3);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-02 14:47:43
