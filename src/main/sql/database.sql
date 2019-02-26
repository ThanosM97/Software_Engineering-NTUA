-- MySQL dump for Stingy by JavaMagkes

CREATE DATABASE  IF NOT EXISTS `stingy`;
USE stingy;

-- Host: localhost    Database: stingy

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
-- Table structure for table 'Product`
--
DROP TABLE IF EXISTS `Product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`description` mediumtext,
	`category` varchar(128) NOT NULL,
	`withdrawn` bit(1) NOT NULL DEFAULT b'0',
	`image` varchar(45),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Tag`
--
DROP TABLE IF EXISTS `Tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tag` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(45) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Product_Tag`
--
DROP TABLE IF EXISTS `Product_Tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Product_Tag` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`ProductId` int(11) NOT NULL,
	`TagId` int(11) NOT NULL,
  	CONSTRAINT fk_prodTag1 FOREIGN KEY (ProductId) REFERENCES Product(id) ON DELETE CASCADE ON UPDATE CASCADE,
  	CONSTRAINT fk_prodTag2 FOREIGN KEY (TagId) REFERENCES Tag(id) ON DELETE CASCADE ON UPDATE CASCADE,  
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `extraData`
--
DROP TABLE IF EXISTS `extraData`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `extraData` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`characteristic` varchar(45) NOT NULL,
	`value` varchar(45) NOT NULL,
	`ProductId` int(11) NOT NULL,
  	CONSTRAINT fk_extraData FOREIGN KEY (ProductId) REFERENCES Product(id) ON DELETE CASCADE ON UPDATE CASCADE, 
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Shop`
--
DROP TABLE IF EXISTS `Shop`;
CREATE TABLE `Shop` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`address` mediumtext,
	`lng` FLOAT(13,10) NOT NULL,
	`lat` FLOAT(13,10) NOT NULL,
	`image` varchar(45),
	`withdrawn` bit(1) NOT NULL DEFAULT b'0',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `Shop_Tag`
--
DROP TABLE IF EXISTS `Shop_Tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Shop_Tag` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`ShopId` int(11) NOT NULL,
	`TagId` int(11) NOT NULL,
  	CONSTRAINT fk_shopTag1 FOREIGN KEY (ShopId) REFERENCES Shop(id) ON DELETE CASCADE ON UPDATE CASCADE,
  	CONSTRAINT fk_shopTag2 FOREIGN KEY (TagId) REFERENCES Tag(id) ON DELETE CASCADE ON UPDATE CASCADE, 
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `User`
--
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(45) NOT NULL,
	`password` varchar(45) NOT NULL,
	`firstName` varchar(45) NOT NULL,
	`lastName` varchar(45) NOT NULL,
	`token` varchar(45) NOT NULL,
	`key` varchar(45) NOT NULL,
	`email` varchar(45) NOT NULL,
	`phoneNumber` varchar(45) NOT NULL,
	`points`  int(11),
	`profilePic` varchar(45),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `Record`
--
DROP TABLE IF EXISTS `Record`;
CREATE TABLE `Record` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`price` FLOAT(8,3) NOT NULL,
	`date` DATE NOT NULL,
	`validity` int(11),
	`productId` int(11) NOT NULL,
	`shopId` int(11) NOT NULL,
	`userId` int(11) NOT NULL,
	CONSTRAINT fk_record1 FOREIGN KEY (productId) REFERENCES Product(id) ON DELETE CASCADE ON UPDATE CASCADE, 
	CONSTRAINT fk_record2 FOREIGN KEY (shopId) REFERENCES Phop(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_record3 FOREIGN KEY (userId) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `Product`
--
/* For shops table plaisio, plaisio,
TVs have the following extra data: 4k, smart, frequency
Laptops have the following extra data: CPU, RAM, hd, OS, size, graphics card
Smartphones have the following extra data: CPU cores, cpu freq, RAM, capacity, size , camera pixels 

'TV, Smart TV, 4K,','4K, Smart, 1300 PQI'
,'TV, Smart TV, 4K', '4K, Smart, 1500 PMI'
,'Laptop, Windows 10','Intel Celeron N4000, 4GB, 500GB, Windows 10, 15.6, Intel HD Graphics 600'
'Laptop, Windows 10', 'AMD A-Series, 4GB, 128GB,Windows 10, 14,  Radeon R5'
 'Black, Dual Sim, Android','Octa core, 1.8GHz, 3GB, 32GB, 6.2, 12 Mp'
*.
LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO `Product` VALUES (1,'SAMSUNG TV 43\'\' UE43NU7122','Μινιμαλιστικό design και ποιότητα κατασκευής, 4Κ ανάλυση, Smart εφαρμογές και πρωτοποριακές τεχνολογίες για ακόμη ελκυστικότερη εικόνα στην πιο προσιτή τιμή','TV','\0', NULL),(2,'LG TV 49\'\' 49UK6200','Με ανάλυση UHD 4K, τεχνολογία 4K Active HDR, «έξυπνο» λειτουργικό webOS και ήχοUltra Surround για ανεπανάληπτη εμπειρία θέασης','TV','\0', NULL),(3,'DELL Laptop Inspiron 3573', 'Laptop Dell Inspiron 3573 με επεξεργαστή Intel Celeron N4000, μνήμη RAM 4GB, σκληρό δίσκο 500GB και HD αντι-ανακλαστική οθόνη 15.6 ιντσών, ιδανικό για καθημερινή χρήση.', 'Laptop','\0', NULL),(4,' Lenovo Laptop IdeaPad 330S-14AST', 'Αν ψάχνεις έναν υπερπλήρη φορητό ηλεκτρονικό υπολογιστή, για υψηλής απόδοσης εφαρμογές multimedia, ήχο και επεξεργαστική ισχύ, τότε το Lenovo IdeaPad 330S-14AST είναι το ιδανικό Laptop για εσένα!', 'Laptop', '\0', NULL),(5,' Xiaomi Smartphone Redmi Note 6 Pro', 'Το Redmi Note 6 Pro διαθέτει διπλή εμπρόσθια και πίσω κάμερα που κάνει τη διαφορά, ενσωματώνει τον Snapdragon 636, τον νεότερο 14nm επεξεργαστή της Qualcomm, βελτιώνοντας σημαντικά τις συνολικές επιδόσεις και την ενεργειακή αποτελεσματικότητα. Επιπλέον, τα 3GB μνήμη RAM συνεισφέρουν στην ομαλή λειτουργία, ενώ η μπαταρία των 4000mAh θα καλύψει κάθε σου ανάγκη.', 'Smartphone', '\0', NULL);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Dumping data for table `Shop`
--
, 'computing, laptops, TV'
, 'laptops, TV, monitors'
, 'laptops, TV'
LOCK TABLES `Shop` WRITE;
/*!40000 ALTER TABLE `Shop` DISABLE KEYS */;
INSERT INTO `Shop` VALUES (1, 'Πλαίσιο Αθήνα', 'Βουλής 3,10562,Αθήνα', 23.732913, 37.977344, '\0'),(2, 'Κωτσόβολος Αθήνα','Σταδίου 34 & Κοραή,10564,Αθήνα', 23.732025,37.979795, '\0'),(3, 'Public Αθήνα', 'Καραγεώργη Σερβίας 1,10563,Αθήνα', 23.733666 ,37.976454, '\0');
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Tag`
--
LOCK TABLES `Tag` WRITE;
/*!40000 ALTER TABLE `Tag` DISABLE KEYS */;
INSERT INTO `Tag` VALUES (1, 'TV'), (2, 'Smart TV'), (3, '4K'), (4, 'Laptop'), (5, 'Windows 10'), (6, 'Black'), (7, 'Dual Sim'), (8, 'Android'),(9, 
'computing'), (10, 'monitor');
/*!40000 ALTER TABLE `Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `extraData`
--
LOCK TABLES `extraData` WRITE;
/*!40000 ALTER TABLE `extraData` DISABLE KEYS */;
INSERT INTO `extraData` VALUES (1, 'Resolution', '4K', 1), (2, 'Smart', 'Yes', 1), (3, 'Frequency', '1300 PQI', 1), (4, 'Resolution', '4K', 2), (5, 'Smart', 'Yes', 2), (6, 'Frequency', '1500 PMI', 2), (7, 'CPU', 'Intel Celeron N4000', 3), (8, 'RAM', '4GB', 3), (9, 'Hard Drive', '500GB', 3), (10, 'OS', 'Windows 10', 3), (10, 'Graphics Card', 'Intel HD Graphics 600', 3), (11, 'CPU', 'AMD A-Series', 4), (12, 'RAM', '4GB', 4), (13, 'Hard Drive', '128GB', 4), (14, 'OS', 'Windows 10', 4), (15, 'Graphics Card', 'Radeon R5', 4), (16, 'Number of Cores', 'Octa Core', 5), (17, 'CPU frequency', '1.8GHz', 5), (18, 'RAM', '3GB', 5), (19, 'Capacity', '32GB', 5), (20, 'Camera', '12Mp', 5);
/*!40000 ALTER TABLE `extraData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Product_Tag`
--
LOCK TABLES `Product_Tag` WRITE;
/*!40000 ALTER TABLE `Product_Tag` DISABLE KEYS */;
INSERT INTO `Product_Tag` VALUES (1, 1, 1), (2, 1, 2), (3, 1, 3), (4, 2, 1), (5, 2, 2), (6, 2, 3), (7, 3, 4), (8, 3, 5), (9, 4, 4), (10, 4, 5), (11, 5, 6), (12, 5, 7), (13, 5, 8); 
/*!40000 ALTER TABLE `Product_Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Shop_Tag`
--
LOCK TABLES `Shop_Tag` WRITE;
/*!40000 ALTER TABLE `Shop_Tag` DISABLE KEYS */;
INSERT INTO `Shop_Tag` VALUES (1, 1, 9), (2, 1, 4), (3, 1, 1), (4, 2, 1), (5, 2, 4), (6, 2, 10), (7, 3, 1), (8, 3, 4);
/*!40000 ALTER TABLE `Shop_Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `User`
--
LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `record`
--
LOCK TABLES `Record` WRITE;
/*!40000 ALTER TABLE `Record` DISABLE KEYS */;
INSERT INTO `Record` VALUES (1, 300, '2018-12-12', 1, 1), (2, 250, '2018-12-20', 1, 2), (3, 280, '2018-11-15', 2, 1), (4, 220,'2018-11-29', 2, 3), (5, 100, '2018-10-10', 3,2),(6,250,'2018-10-11', 3,3);
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

-- Dump completed on 2019-02-29 14:47:43

