
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
	`profilePic` varchar(600),
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `User_Favourites`
--
DROP TABLE IF EXISTS `User_Favourites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Favourites` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`UserId` int(11) NOT NULL,
	`ProductId` int(11) NOT NULL,
  	CONSTRAINT fk_userFav1 FOREIGN KEY (ProductId) REFERENCES Product(id) ON DELETE CASCADE ON UPDATE CASCADE,
  	CONSTRAINT fk_userFav2 FOREIGN KEY (UserId) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE,  
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `User_Rating`
--
DROP TABLE IF EXISTS `User_Rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Rating` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`UserId` int(11) NOT NULL,
	`ProductId` int(11) NOT NULL,
	`Stars` int(1) NOT NULL,
  	CONSTRAINT fk_userRat1 FOREIGN KEY (ProductId) REFERENCES Product(id) ON DELETE CASCADE ON UPDATE CASCADE,
  	CONSTRAINT fk_userRat2 FOREIGN KEY (UserId) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE,  
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `User_Comment`
--
DROP TABLE IF EXISTS `User_Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Comment` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`UserId` int(11) NOT NULL,
	`ProductId` int(11) NOT NULL,
	`Comment` TEXT NOT NULL,
  	CONSTRAINT fk_userCom1 FOREIGN KEY (ProductId) REFERENCES Product(id) ON DELETE CASCADE ON UPDATE CASCADE,
  	CONSTRAINT fk_userCom2 FOREIGN KEY (UserId) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE,  
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `User_Like`
--
DROP TABLE IF EXISTS `User_Like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User_Like` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`UserId` int(11) NOT NULL,
	`CommentId` int(11) NOT NULL,
  	CONSTRAINT fk_userLike1 FOREIGN KEY (UserId) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE,  
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



--
-- Table structure for table `Company`
--
DROP TABLE IF EXISTS `Company`;
CREATE TABLE `Company` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(45) NOT NULL,
	`password` varchar(45) NOT NULL,
	`name` varchar(45) NOT NULL,
	`token` varchar(45) NOT NULL,
	`key` varchar(45) NOT NULL,
	`email` varchar(45) NOT NULL,
	`phoneNumber` varchar(45) NOT NULL,
	`profilePic` varchar(600) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




--
-- Table structure for table `Administrator`
--
DROP TABLE IF EXISTS `Administrator`;
CREATE TABLE `Administrator` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`username` varchar(45) NOT NULL,
	`password` varchar(45) NOT NULL,
	`firstName` varchar(45) NOT NULL,
	`lastName` varchar(45) NOT NULL,
	`email` varchar(45) NOT NULL,
	`token` varchar(45) NOT NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




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
	`image` varchar(450) NULL,
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
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
	`image` varchar(450),
	`withdrawn` bit(1) NOT NULL DEFAULT b'0',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `Record`
--
DROP TABLE IF EXISTS `Record`;
CREATE TABLE `Record` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`price` FLOAT(8,3) NOT NULL,
	`validity` int(11),
	`date` DATE NOT NULL,
	`productId` int(11) NOT NULL,
	`shopId` int(11) NOT NULL,
	`userId` int(11) NOT NULL,
	CONSTRAINT fk_record1 FOREIGN KEY (productId) REFERENCES Product(id) ON DELETE CASCADE ON UPDATE CASCADE, 
	CONSTRAINT fk_record2 FOREIGN KEY (shopId) REFERENCES Shop(id) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_record3 FOREIGN KEY (userId) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




-- Table structure for table `Record_Validation`
--
DROP TABLE IF EXISTS `Record_Validation`;

CREATE TABLE `Record_Validation` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`recordId` int(11) NOT NULL,
	`UserId` int(11) NOT NULL,
  	CONSTRAINT fk_recordVal1 FOREIGN KEY (UserId) REFERENCES User(id) ON DELETE CASCADE ON UPDATE CASCADE,  
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--
LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES
						  (1,'junkietech','GngN4ftJ#1VX', 'Nikolaos','Papantoniou', 'nEpwYsBHGBLHNaJ9', 'eDoCmblwHzkY7SmT','junkiepapa@gmail.com','+30 69 63064771',50,'static/profiles/profile1.png'),
						  (2,'bulletproofmaniactech','S6~xE*l&t[|-', 'Dimitrios','Anagnostou', 'o$NlUy1Ef|@5S;I-', 'Hug#G$h/:|`ymu./','bulletanagnostou@gmail.com','+30 69 32161887',61,'static/profiles/profile2.png'),
                          (3,'responsibleapple','z},/gG|Jsak+', 'Antonios','Koutsouris', 'Aa@cl1b41XXR$d5`','xyT{<@^[2y:%b>5!' ,'responsiblekoutsouris@gmail.com','+30 69 14612977',18,'static/profiles/profile3.png'),
                          (4,'nightowlcybernetics','Bn,!1jg">EL7', 'Marios','Pagourtzis', '9&z@ivo-LKBQX3/2', 'G^I:EU*3Hajwi)Mq','cyberneticspagourtzis@yahoo.com',' +30 696 9578413',75,'static/profiles/profile4.png'),
                          (5,'biomedicalmonitor','qQX{"_|jI[U]', 'Nikolados','Tsanakas', 'MvH._:6!m)UgHIPN','2^bk8=.u%kG!EedH' ,'biomedicaltsanakas@yahoo.com','+30 69 47029338',0,'static/profiles/profile5.png'),
                          (6,'skilledelectro','8ht)Ed=lT;^c', 'Anastasios','Xanthakis','u;8Uda71mk?cBA!.' , 'u3UHVg(Fbqx~lea(','skilledxanthakis@gmail.com','+30 69 20216577',34,'static/profiles/profile6.png'),
                          (7,'anonymousneural','8(fi~f#1Ps2?', 'Fotis','Sykas','t:Xbb!<W 4%VUo|>' ,'yJ1*Ih$CYK~%Bn Z' ,'neuralsykas@gmail.com','+30 69 80608764',45,'static/profiles/profile7.png'),
                          (8,'investingcs','o,_{qUVPT5Iq', 'Kostantinos','Theodorou','BL{!*<I|w)a1L/A.' , 'p<=8fKUIT^VLlUS9','investingtheodorou@gmail.com','+30 69 06029084',39,'static/profiles/profile8.png'),
                          (9,'bluewavequantum','tIpdknWw/+F>', 'Maria','Papaspirou', 'X?D75oKaMM.Y["k^', 'aS(Y=]!l4b9 >yUB','bluewavepapaspirou@yahoo.com','+30 69 89677280',27,'static/profiles/profile9.png'),
                          (10,'balancedcryptology','8(fi~f#1Ps2?', 'Eleni','Nikita', 'ay[yt~@P"sT_s&1:','3%]B*{yY5W6XuWn2' ,'cryptologynikita@yahoo.com',' +30 69 17568463',33,'static/profiles/profile10.png');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `Administrator`
--
LOCK TABLES `Administrator` WRITE;
/*!40000 ALTER TABLE `Administrator` DISABLE KEYS */;
INSERT INTO Administrator(id,username,password,firstName, lastName,email, token) VALUES 
								   (1,'admin1','RFjknN^+FWFw)<', 'Panagiotis','Antoniou', 'admin1@stingy.com', 'qwerty1234'),
								   (2,'admin2','1Ps2^+FWFw)<(e', 'Pantelis','Eustathiou', 'admin2@stingy.com', 'aekmono');
/*!40000 ALTER TABLE `Administrator` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `Company`
--
LOCK TABLES `Company` WRITE;
/*!40000 ALTER TABLE `Company` DISABLE KEYS */;
INSERT INTO `Company` VALUES
						  (1,'plaisio','GngN4+tJ#1VX', 'Plaisio', '324dsfqwf4qf)erw', '245wrfwcdwetqef<23','plaisio@info.gr','2102895000','static/company_profiles/plaisio.png'),
						  (2,'kotsovolos','S6~xE*l&t[|-', 'Kotsovolos', 'o$NlUy1Ef|@5S;I-', 'Hug#G$h/:|`ymu./','kotsovolos@info.gr','2102899999','static/company_profiles/kotsovolos.png'),
                          (3,'Public','z},/gG|Jsak+', 'Public', 'Aa@cl1b41XXR$d5`','xyT{<@^[2y:%b>5!' ,'public@info.gr','2108181333','static/company_profiles/public.png');
/*!40000 ALTER TABLE `Company` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `User`
--
LOCK TABLES `User_Favourites` WRITE;
/*!40000 ALTER TABLE `User_Favourites` DISABLE KEYS */;
INSERT INTO User_Favourites(id,UserId,ProductId) VALUES 
									 (1,1,1),(2,1,2),(3,1,3),(4,1,5),(5,1,9),(6,1,15),(7,1,13),
									 (8,2,5), (9,2,6),(10,2,9),(11,2,4),
									 (12,3,4),(13,3,7),
									 (14,4,4),(15,4,7), (16,4,10),(17,4,12), (18,4,6),
	  /*favourite all not recorded*/ (19,5,10),(20,5,11),(21,5,12),
	  /*favourite some not recorded*/(22,6,5), (23,6,7),(24,6,13),
	  /*favourite all recorded*/     (25,7,8),(26,7,12),(27,7,14),
	  								 (28,8,13),(29,8,14),(30,8,2),
	  								 (31,9,7),(32,9,8),(33,9,11), (34,9,12),
	  								 (35,10,8), (36,10,13), (37,10,1), (38,10,2) , (39,10,3);

/*!40000 ALTER TABLE `User_Favourites` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `User_Rating`
--
LOCK TABLES `User_Rating` WRITE;
/*!40000 ALTER TABLE `User_Rating` DISABLE KEYS */;
INSERT INTO User_Rating(id,UserId,ProductId,Stars) VALUES 
								 (1,1,1,8),(2,1,2,9),(3,1,3,8),(4,1,5,7),(5,1,9,10),(6,1,15,4),(7,1,14,3),
								 (8,2,5,8), (9,2,6,8),(10,2,9,8),(11,2,4,9),(12,2,5,4),(13,2,7,5), (14,2,8,1),
								 (15,3,4,10),(16,3,7,9), (17,3,8,2),(18,3,11,4),
								 (19,4,4,8),(20,4,7,8), (21,4,10,8),(22,4,12,8), (23,4,6,4), (24,4,14,4),
 /*favourite all not recorded*/  (25,5,10,9),(26,5,11,9),(27,5,12,8), (28,5,1,2),(29,5,1,6),
 /*favourite some not recorded*/ (30,6,5,7), (31,6,7,8),(32,6,13,8), (33,6,14,2), (34,6,2,2),
 /*favourite all recorded*/      (35,7,8,7),(36,7,12,7),(37,7,11,2),(38,7,12,3),(39,7,13,3),
	  							 (40,8,13,7),(41,8,14,7),(42,8,2,10), (43,8,3,10),(44,8,4,9),
	  							 (45,9,7,6),(46,9,8,6),(47,9,11,9), (48,9,12,10), (49,9,15,1),
	  							 (50,10,8,9), (51,10,13,6), (52,10,1,7), (53,10,2,8) , (54,10,3,9), (55,10,5,2);

/*!40000 ALTER TABLE `User_Rating` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `User_Comment`
--
LOCK TABLES `User_Comment` WRITE;
/*!40000 ALTER TABLE `User_Comment` DISABLE KEYS */;
INSERT INTO User_Comment(id,UserId,ProductId,Comment) VALUES 
								 (1,1,1,'Εξαιρετική τηλεόραση. Η εικόνα είναι πεντακάθαρη και πολύ ζωηρή. Με τις κατάλληλες ρυθμίσεις το αποτέλεσμα είναι εκπληκτικό. '),
								 (2,1,2,'Περίμενα κάτι καλό για τα λεφτά της, τελικά έκανα λάθος. Η τηλεόραση είναι εξαιρετική. Δεν της λείπει τίποτα σε σχέση με άλλες που έχουν την διπλάσια τιμή. Έχει γρήγορη απόκριση στις εναλλαγές καναλιών.'),
								 (4,1,5,'Με προβληματίζουν κάποια θέματα που αφορούν την κάμερα, την εμπρόσθια συγκεκριμένα. Μια φορά μόνο, λειτουργούσε με χρονοκαθυστέρηση 1 δευτερολέπτου τουλάχιστον. Θα επανέλθω αν το ξανακάνει.'),
								 (5,1,9,'Απλά υπέροχο κινητό και πολύ όμορφο "Ο βασιλιάς των android"..όχι δε μιλάω υπερβολικά..πιστέψτε με.....φοβερά γρήγορο και "πανέξυπνο" ...μεγάλη oled οθόνη με σούπερ ανάλυση,ζωντανά χρώματα, φοβερή απόκριση και με σούπερ αναλογία, έχω BOE οθόνη και κανένα πρόβλημα μέχρι τώρα'),
								 (7,1,14,'Δεν αξίζει τα λεφτά του σε καμία περίπτωση..ελπίζω τουλάχιστον να αντέξει...'),
								 (8,2,5,'Διάβασα τις κριτικές εδώ κι έτσι αποφάσισα να πάρω το συγκεκριμένο τηλέφωνο. Δεν το μετάνιωσα καθόλου!! Είναι αψογο, για τις δικές μου ανάγκες τουλάχιστον. Έχει πολύ ωραίο design, είναι γρήγορο, με πολύ καθαρη οθονη και μπαταρία που αντέχει παρα πολυ'), 
								 (9,2,6,'Για το κόστος που έχει είναι πολύ καλό, καλή κατασκευή χτυπάει τα xiaomi με αυτό η Samsung'),
								 (10,2,9,'το καλυτερο κινητο μακραν που εχει βγει εως τωρα μαζι ισως με το note 9. Aπιστευτη μπαταρια που δεν τελειωνει ποτε απιστευτη ποιοτητα κατασκευης και φωτογραφιες που σε κανουν να ασχοληθεις με τη φωτογραφια ακομα κι αν δε σε ενδιεφερε μεχρι τωρα. '),
								 (11,2,5,'λόγω υψηλού sar head το τηλέφωνο ψήνει κανονικά τον εγκέφαλο ακόμα κ με hands free είναι επικίνδυνα υψηλο. κρίμα.'),
								 (12,2,7,'Υπερβολικά και τρομερά καλό κινητό αλλά...€1000+ δεν τα αξίζει! (Δείτε το βίντεο: https://youtu.be/8DbkK0xs5hQ) Με τα μισά λεφτά παίρνεις κάτι εξίσου τρομερά και φανταστικά καλό!'), 
								 (13,2,8,'λόγω της δουλειάς μου ασχολούμαι με πάρα πολλά κινητά λοιπόν το κινητό πέρα της άριστης οθόνης και τον ήχο στις κλήσεις.......... είναι για πέταμα ο κυριν 710 σέρνεται κολλάει Στο Facebook κολλάει στο messenger κολλάει Στο multitasking κολλάει στα πιο απλά πράγματα μου σπάσε τα νεύρα και για αυτό το έδωσα φαίνεται ο επεξεργαστής ζορίζεται.'),
								 (14,3,4,'ΕΞΑΙΡΕΤΙΚΟ, ειδικα σε επιπεδα multitasking που αφορυν και την δουλεια μου'),
								 (15,3,7,'Πέρα από το χορταστικό μέγεθος και την απίστευτα καθαρή οθόνη σε βάθος, χρώμα, η συσκευή είναι ένα κόσμημα που σου δίνει άπειρες δυνατότητες. Σαφής βελτίωση στη μπαταρία από τη προηγούμενη μου συσκευή ενώ το μέγεθος του με βοηθάει να επεξεργάζομαι άνετα βίντεο μέσω εφαρμογής (luma) που μου είναι απαραίτητο.'), 
								 (16,3,8,'ενικά η Huawei με τους επεξεργαστές της έχει ένα θέμα Αν δεν πάρεις ναυαρχίδα είσαι μες στο λακ ακόμα και ναυαρχίδα το p20 Pro ας πούμε στο fortnite κολλάει δεν μπορείς να το παίξεις οπότε από θέμα επεξεργαστή είναι απλά για πέταμα..... οι φωτογραφίες του είναι μέτριες πολύ και το βράδυ χάλια Ειδικά το night mode απλά αυτό που κάνει είναι να ανεβαζει απλα Το κλαριτι... το βίντεο χάλια...'),
								 (17,3,11,'Πληρώνεις το όνομα δεκαετιών. Να έυχεσαι να μην σου πάθει κάτι γιατί άντε να στο φτιάξουν. Κλασικη Apple που βγάζει προϊον χωρίς τα αντίστοιχα ανταλλακτικα διαθέσιμα. θυμωμένη με την επιλογή μου. Πέρα ότι η μπαταρία για να κρατήσει όσο γράφει πρέπει να είσαι σε low vision στην οθόνη.'),
								 (18,4,7,'Πραγματικα σχεδόν ολοκληρωμενο σαν συνολο.. Εψαχνα κατι με καλη μπαταρια και καμερα.. Θεωρω οτι ειναι απο τις καλυτερες επιλογες για αυτα,αν οχι η καλυτερη.. Αψογο σε ολα του! Πρωην κατοχος S9+, μπορω να πω με απολυτη σιγουρια οτι δεν υπαρχει καμια επαφη μεταξυ τους..'), 
								 (19,4,10,'Τι να πρώτο πω.. Εμφάνιση απίστευτη..η καλύτερη κάμερα που υπάρχει σε αφήνει με το στόμα ανοιχτό.. Οθόνη υπέροχη (να αναφέρω ότι βλέπω εδώ τα σχόλια οι μισοί από αυτούς ούτε καν το έχουν πιάσει στα χέρια τους) μπαταρία 2 μέρες με βαριά χρήση.. '),
								 (20,4,12,'καλη ΟθόνηΤαχύτητα, Touchpad, Διάρκεια μπαταρίας, Θερμοκρασία, Σχέση ποιότητας τιμής'), 
								 (21,4,6,'Τα αρνητικά τώρα:  1. Γυάλινη πλάτη που λατρεύει υπέρμετρα τις δαχτυλιές και προφανώς εύθραστη όσο και γλυστερή. Μια θήκη τουλάχιστον πλάτης εντελώς απαραίτητη συν ένα τζαμάκι μπροστά. 2. Δεν έχει led ειδοποιήσεων, πυξίδα. 3. Μέτριο το εξωτερικό ηχείο.  4. Κάμερα πολύ μέτρια περισσότερο εξαιτίας του λογισμικού. Συνιστάται να την έχετε μόνιμα γυρισμένη στο HDR όπου σαφώς βελτιώνονται οι φωτογραφίες. Βραδυνές ξεχάστε τις αν δεν υπάρχει επαρκής φωτισμός. Το φλας απαράδεκτο όσο και το βίντεο. Για σέλφι ούτε συζήτηση.  5. Αργός και πλέον ξεπερασμένος επεξεργαστής Snapdragon 425, έχει ξεπεράσει τη διετία. Όπως και είναι λίγα πια τα 2 RAM. Δεν κάνει για βαριά χρήση, ούτε σαν παιχνιδομηχανή για το έφηβο παιδί σας που θα θέλει να παίζει και Fortnite με τις ώρες. Πάρτε του κάτι σαφώς πολύ καλύτερο, πχ με 150-180€ σκρουτζοτιμή ένα Xiaomi, θα σας ευγνωμονεί μετά.  6. Δεν έχει δακτυλικό αποτύπωμα (γιατί;) ενώ η αναγνώριση προσώπου είναι για τα κλάματα. Τη δική μου φάτσα έβαλα, το ξεκλειδώνει η αδερφή μου. '), 
								 (22,4,14,'Απαγορευτικά κακός ήχος'),
								 (23,5,10,'Έχω τον υπολογιστή στα χέρια μου 2 μήνες. Στην αρχή είχα διλημματα αλλα αποδείχθηκε πολύ καλός υπολογιστής και δεν μετανιώνω ούτε μία στιγμη. Αν και είναι του 2017 ο υπολογιστής είναι σαν του 2019, πετάει. Η οθόνη απλώς τα σπάει και δεν κολλάει πουθενά. Μερικοί λένε πως επιδή είναι apple δεν αξίζει. Ένα έχω να πω μην το σκέφτεστε καθόλου πάρτε τον.'),
								 (24,5,1,'Κακή εικόνα και αλλοιωμένα χρώματα! Αν δεν βλέπεις στην απόλυτη ευθεία και είσαι ελαφρώς στο πλάι, η εικόνα δεν είναι καθόλου καλή. Από οποιαδήποτε γωνία θέασης τα μπλε είναι γαλάζια και τα κόκκινα ροζ. Ο ήχος καλός και το μενού εύκολο.'),
								 (25,5,1,'Την αγόρασα γεμάτος ενθουσιασμό τέλη Νοέμβρη, καθότι και η προηγούμενη μου FULL HD ήτανε Samsung και εξαιρετική. Με μεγάλη μου λύπη διαπίστωσα άμεσα σχεδόν ότι έπασχε απο αισθητό backlight bleed και άμεσα κινητοποίησα διαδικασίες για αντικατάσταση.Απο την Samsung μου πρότειναν αντικατάσταση πάνελ, κάτι που πρακτικά δεν λύνει τίποτε, καθώς σε αυτές τις περιπτώσεις φταίνε 99% τα ledακια. Δέχθηκα με την ελπίδα ότι θα αντικατασταθεί σύντομα. Αμ δε. Έναν μήνα σχεδόν στα τηλέφωνα να προσπαθώ να μάθω πότε θα την παραλάβω και να μην ξέρει ΚΑΝΕΝΑΣ να μου απαντήσει υπεύθυνα σαν να μιλάμε για απολεσθέν αντικείμενο.'),
								 (26,6,5,'Καλές φωτογραφίες γενικά πολύ καλές !  Το μεγάλο πρόβλημα όμως είναι ένα !  Το κινητό κολλάει αρκετά ειδικά όταν το βάζεις σε πλάγια θέση και μετά σε ευθεία πάλι παίζει να έχει 2-3 second delay + οτι πολλές φορές "Freezάρει" και κολλάει για 5-6 δευτερόλεπτα χωρίς να κάνει τίποτα '), 
 								 (27,6,7,'Χωράει άνετα στην τσέπη  Το χρησιμοποιείς άνετα με το ένα χέρι  Οθόνη καταπληκτική  Με χαλάει μερικές φορές το True Tone '),
 								 (28,6,13,'Το ταμπλετ αυτό εγώ προσωπικά το πήρα με προσφορά στα 150€ είναι πραγματ ελπίζω τουλάχιστον να αντέξει...'),
 								 (29,6,2,'Δεν ξέρω εαν έπεσα στην περίπτωση αλλά σε αυτην που αγόρασα ισχύουν τα εξής. 1. Έλειπε το τηλεκοντρόλ 2. Η εικόνα Είναι πραγματικα πολύ κακή 3 το INTERNET σέρνεται . Την αγόρασα σήμερα και αύριο θα κοιτάξω να δω εαν μπορώ να την επιστρέψω,  γιατι εσν δεν ελαττωματική τοτε πραγματικά ειναι πολυ κακη αγορα.'),
							     (30,7,8,'Πολύ καλό κινητό αξίζει τα λεφτά του το μόνο που δεν μου αρέσει ειναι το ηχείο. Είναι κακής ποιότητας απο τη μέση και πάνω παραμορφώνει παααααρα πολύ.. Με ακουστικά είναι μια χαρά '),
	  							 (31,8,13,'Το ταμπλετ αυτό εγώ προσωπικά το πήρα με προσφορά στα 150€ είναι πραγματικά ένα μικρό διαμάντι. Τα κάνει όλα απροβλημάτιστα. Έχω πολύ καιρό να ανοίξω το pc. Το προτείνω ανεπιφύλακτα ειδικά σε όσους έχουν επίσης συσκευή iPhone καθώς η σύνδεση μεταξύ τους είναι εκπληκτική'),
	  							 (32,8,2,'αρκετά καλή. Καθαρή εικόνα και σε 4κ βίντεο δεν κόλλησε ούτε στο ελάχιστο. Εύκολη χρήση youtube. Το μόνο που θέλω να δοκιμάσω είναι το netflix για να έχω πλήρη εικόνα της tv. Το μόνο αρνητικό θα έλεγα είναι τα πλαστικά της.'),
	  							 (33,9,7,'αν και νομίζω ότι μέχρι το iPhone 7 έβγαζες φυσικές φωτός, τώρα σαν να τονίζει λίγο παραπάνω τα χρώματα, που δεν μ αρέσει αυτό. Η μπαταρία δεν σε βγάζει μια μέρα με βαριά χρήση. Βάλε ρε Apple κι εσύ μπαταρίες 4000mh να γουστάρουμε.'),
	  							 (34,9,8,'Κοιτάξτε εγώ το χω 2 μέρες μέχρι στιγμής... Είναι απίστευτα γρήγορο σε σχέση με το Α3 που είχα.... Έχει απίστευτη ανάλυση και οι φώτο και γενικά αλλά με χαλάει το ότι δν έχει φλας μπροστά αλλά η κάμερα είναι φοβερή... '),
	  							 (35,10,8,'Πανέμορφο κινητό(στο μπλέ χρώμα από τα πιο όμορφα κινητά που κυκλοφορούν στην αγορά).Εκπληκτική οθόνη και χρώματα!Θα ικανοποιήση στο έπακρο τον μέσο χρήστη.Στην τιμή του ότι καλύτερο!'), 
	  							 (36,10,5,'ένα αστέρι γιατί. από τη 2ή μέρα ξεκίνησε να καταναλώνει μπαταρία και επικοινώνησα με το κατάστημα που το πήρα,το πήγα στο σέρβις και εν τέλει μου επιστρέφουν τα χρήματα πίσω... μητρική κυρίες και κύριοι. δυστυχώς περίμενα παραπάνω από την εταιρεία. απογοήτευση...');

/*!40000 ALTER TABLE `User_Comment` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping data for table `User_Like`
--
LOCK TABLES `User_Like` WRITE;
/*!40000 ALTER TABLE `User_Like` DISABLE KEYS */;
INSERT INTO User_Like(id,UserId,CommentId) VALUES (1,1,1), (2,1,4), (3,1,7), (4,1, 28),
							   (5,2,1), (6,2,4), (7,2,7), (8,2, 28), (9,2,20), (10,2,36), (11,2,25),
							   (12,3,5), (13,3,13), (14,3,19), (15,3,23),
							   (16,4,10), (17,4,12), (18,4,5), (19,4,9), (20,4,3),
							   (21,5,11), (22,5,12), (23,5,13), (24,5,18), (25,5,32),
							   (26,6,32), (27,6,35), (28,6,31), (29,6,1), (30,6,24),
							   (31,7,15), (32,7,16), (33,7,17), (34,7,18), (35,7,19),
							   (36,8,6), (37,8,9), (38,8,12), (39,8,15), (40,8,20),
							   (41,9,29), (42,9,31), (43,9,36), (44,9,17),
							   (45,10,22), (46,10,23), (47,10,24), (48,10,26);
	  							 
/*!40000 ALTER TABLE `User_Like` ENABLE KEYS */;
UNLOCK TABLES;


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
*/
LOCK TABLES `Product` WRITE;
/*!40000 ALTER TABLE `Product` DISABLE KEYS */;
INSERT INTO Product(id,name,description,category,image,withdrawn) VALUES 
							 (1,'Samsung TV 43\'\' UE43NU7122','Μινιμαλιστικό design και ποιότητα κατασκευής, 4Κ ανάλυση, Smart εφαρμογές και πρωτοποριακές τεχνολογίες για ακόμη ελκυστικότερη εικόνα στην πιο προσιτή τιμή','tv','static/images1/image1.png',0),
							 (2,'LG TV 49\'\' 49UK6200','Με ανάλυση UHD 4K, τεχνολογία 4K Active HDR, «έξυπνο» λειτουργικό webOS και ήχοUltra Surround για ανεπανάληπτη εμπειρία θέασης','tv','static/images1/image2.png',0),
							 (3,'Dell Laptop Inspiron 3573', 'Laptop Dell Inspiron 3573 με επεξεργαστή Intel Celeron N4000, μνήμη RAM 4GB, σκληρό δίσκο 500GB και HD αντι-ανακλαστική οθόνη 15.6 ιντσών, ιδανικό για καθημερινή χρήση.', 'laptop','static/images1/image3.png',0),
							 (4,'Lenovo Laptop IdeaPad 330S-14AST', 'Αν ψάχνεις έναν υπερπλήρη φορητό ηλεκτρονικό υπολογιστή, για υψηλής απόδοσης εφαρμογές multimedia, ήχο και επεξεργαστική ισχύ, τότε το Lenovo IdeaPad 330S-14AST είναι το ιδανικό Laptop για εσένα!', 'laptop','static/images1/image4.png',0),
							 (5,'Xiaomi Smartphone Redmi Note 6 Pro', 'Το Redmi Note 6 Pro διαθέτει διπλή εμπρόσθια και πίσω κάμερα που κάνει τη διαφορά, ενσωματώνει τον Snapdragon 636, τον νεότερο 14nm επεξεργαστή της Qualcomm, βελτιώνοντας σημαντικά τις συνολικές επιδόσεις και την ενεργειακή αποτελεσματικότητα. Επιπλέον, τα 3GB μνήμη RAM συνεισφέρουν στην ομαλή λειτουργία, ενώ η μπαταρία των 4000mAh θα καλύψει κάθε σου ανάγκη.', 'smartphone', 'static/images1/image5.png',0),
							 (6,'Samsung Galaxy J4 PLUS DUAL SIM GOLD','Με True HD+ Infinity Display, Dual-SIM και Emotify για ξεχωριστή επικοινωνία, Snapdragon SoC και Android Oreο απολαμβάνεις μια ανώτερη εμπειρία χρήσης','smartphone','static/images1/image6.png',0),
							 (7,'Apple iPhone XS','Με τη μεγαλύτερη οθόνη σε iPhone, Super Retina 6,5" τεχνολογίας OLED, το πιο έξυπνο & ισχυρό επεξεργαστή σε smartphone, ταχύτερη αναγνώριση προσώπου Face ID και διπλή πίσω κάμερα για μοναδικές λήψεις','smartphone','static/images1/image7.png',0),
							 (8,'Honor 10 Lite Smartphone Blue', 'Dewdrop οθόνη 6,21" με FHD+ ανάλυση, Dual AI και 24MP Selfie κάμερες, 8πύρηνο SoC Kirin 710 με GPU Turbo και EMUI 9.0 out-of-the-box!', 'smartphone','static/images1/image8.png',0),
							 (9,'Huawei Mate 20 Pro Smartphone Μαύρο', 'Μεγάλη FullView 2K+ οθόνη 6,39 ιντσών, 8πύρηνος Kirin 980 με διπλό NPU, 6GB μνήμης RAM, μπαταρία 4.200mAh, σύστημα τριπλής AI κάμερας και αντοχή σε νερό και σκόνη', 'smartphone', 'static/images1/image9.png',0),
							 (10,'Apple MacBook Air 13\'\' MQD42GR/A', 'Απίστευτα ελαφρύ και απίστευτα λεπτό MacBook Air, με μπαταρία που κρατάει 12 συνεχόμενες ώρες, φωτιζόμενο keyboard και μεγάλες ταχύτητες μεταφοράς δεδομένων', 'laptop','static/images1/image10.png',0),
							 (11,'Dell Alienware 17 R5 Laptop','Με Intel Core i7-7700HQ επεξεργαστή, αυτόνομη κάρτα γραφικών 4GB, 12GB RAM και 17’’ οθόνη Full HD IPS το extreme gaming πάει παντού!','laptop','static/images1/image11.png',0),
							 (12,'Toshiba Tecra X40-D-10H Laptop','Ένας δυνατός φορητός με βάρος μόλις 1,25 Kg επεξεργαστή Intel Core i7-7500U, 16GB RAM DDR4, Intel HD Graphics 620 και SSD με χωρητικότητα 512GB','laptop','static/images1/image12.png',0),
							 (13,'Apple iPad mini 4 Tablet 7.9\'\'Gold','Είναι πολύ λεπτό, απίστευτα ελαφρύ και ασυμβίβαστα ισχυρό! Έχει κάμερα στα 8MP, Retina οθόνη και iOS9.','tablet','static/images1/image13.png',0),
							 (14,'Samsung Galaxy Tab S4 T835 Tablet 10.5\'\'  Γκρι','Με εντυπωσιακή οθόνη Super AMOLED 10,5", 8πυρηνο Snapdragon 835 και 4GB μνήμης RAM, σύνδεση σε 4G δίκτυα, αποθηκευτικό χώρο 64GB, νέο S-Pen, 4 ηχεία AKG και μεγάλη μπαταρία 7.300mAh','tablet','static/images1/image14.png',0),
							 (15,'LG OLED TV OLED77C8 77\'\'', 'Υψηλή αισθητική και UHD ευκρίνεια, με τεχνολογίες OLED και Cinema HDR, επεξεργαστή α9, ήχο Dolby Atmos και webOS 4.0 για πρόσβαση σε multimedia περιεχόμενο','tv','static/images1/image15.png',0);
/*!40000 ALTER TABLE `Product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;


--
-- Dumping data for table `extraData`
--
LOCK TABLES `extraData` WRITE;
/*!40000 ALTER TABLE `extraData` DISABLE KEYS */;
INSERT INTO extraData(id,characteristic,value,ProductId) VALUES 
							   (1, 'Resolution', '4K', 1), (2, 'Smart', 'yes', 1), (3, 'ScreenSize', '43', 1), 
							   (4, 'Resolution', '4K', 2), (5, 'Smart', 'yes', 2), (6, 'ScreenSize', '49', 2), 
							   (7, 'CPU', 'Intel-Celeron-N4000', 3), (8, 'RAM', '4', 3), (9, 'HardDrive', '500', 3), (10, 'OS', 'Windows-10', 3), (11, 'GraphicsCard', 'Intel-HD-Graphics-600', 3), (12, 'CPUcores', '2', 3), (13, 'ScreenSize', '15.6', 3), 
							   (14, 'CPU', 'AMD-A-Series', 4), (15, 'RAM', '4', 4), (16, 'HardDrive', '128', 4), (17, 'OS', 'Windows-10', 4), (18, 'GraphicsCard', 'Radeon-R5', 4), (19, 'CPUcores', '4', 4), (20, 'ScreenSize', '14', 4), 
							   (21, 'CPUcores', '8', 5), (22, 'RAM', '4', 5), (23, 'Capacity', '32', 5), (24, 'FrontCamera', '12MP', 5),(25, 'SelfieCamera', '20MP', 5), (26,'OS','Android-8.1-Oreo',5), (27, 'ScreenSize', '6.3', 5), 
							   (28, 'CPUcores', '4',6),(29,'RAM','2',6), (30,'Capacity','32',6), (31, 'FrontCamera', '13MP', 6), (32, 'SelfieCamera', '5MP', 6), (33,'OS','Android-8.1-Oreo',6), (34, 'ScreenSize', '6.3', 6), 
							   (35, 'CPUcores', '6',7),(36,'RAM','4',7), (37,'Capacity','256',7), (38, 'FrontCamera', '12MP', 7), (39, 'SelfieCamera', '7MP', 7), (40,'OS','IOS-12',7), (41, 'ScreenSize', '5.9', 7), 
							   (42, 'CPUcores', '8',8),  (43,'RAM','3',8), (44,'Capacity','32',8), (45, 'FrontCamera', '13MP', 8), (46, 'SelfieCamera', '24MP', 8), (47,'OS','Android-9',8), (48, 'ScreenSize', '6.3', 8), 
							   (49, 'CPUcores', '8',9) ,(50,'RAM','6',9), (51,'Capacity','128',9), (52, 'FrontCamera', '40MP', 9), (53, 'SelfieCamera', '24MP', 9), (54,'OS','Android-9',9), (55, 'ScreenSize', '6.3', 9), 
							   (56, 'CPU', 'Intel-i5-Core', 10), (57, 'RAM', '8', 10), (58, 'HardDrive', 'SSD-256', 10), (59, 'OS', 'Mac-OS', 10), (60, 'GraphicsCard', ' Intel-Iris-6000', 10), (61, 'ScreenSize', '13', 10), 
							   (62, 'CPU', 'Intel-i7-Core-8750H', 11), (63, 'RAM', '16', 11), (64, 'HardDrive', 'SSD-256', 11), (65, 'OS', 'Windows-10', 11), (66, 'GraphicsCard', 'Nvidia-GeForce-GTX-1070', 11), (67, 'ScreenSize', '17.3', 11), 
							   (68, 'CPU', 'Intel-i7-Core-7500U', 12), (69, 'RAM', '16', 12), (70, 'HardDrive', 'SSD-512', 12), (71, 'OS', 'Windows-10-Pro', 12), (72, 'GraphicsCard', 'Intel-HD-Graphics-620', 12), (73, 'ScreenSize', '14', 12), 
							   (74, 'CPUcores', '2',13) ,(75,'RAM','2',13), (76,'Capacity','128',13), (77, 'FrontCamera', '8MP', 13), (78, 'SelfieCamera', '1.2MP', 13), (79,'OS','IOS-9',13), (80, 'ScreenSize', 'medium', 13), 
							   (81, 'CPUcores', '8',14) ,(82,'RAM','4',14), (83,'Capacity','64',14), (84, 'FrontCamera', '13MP', 14), (85, 'SelfieCamera', '8MP', 14), (86,'OS','Android-Oreo',14), (87, 'ScreenSize', 'medium', 14), 
							   (88, 'Resolution', '4K', 15), (89, 'Smart', 'yes', 15), (90, 'ScreenSize', '77', 15);			
/*!40000 ALTER TABLE `extraData` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Tag`
--
LOCK TABLES `Tag` WRITE;
/*!40000 ALTER TABLE `Tag` DISABLE KEYS */;
INSERT INTO Tag(id,name) VALUES 
						 (1, 'TV'),  (2, 'Smart TV'), (3, 'laptop'), (4,'desktop'),(5,'tablet'),(6,'smartphone'),
		/*Γενικά*/		 (7, 'Windows 10'),  (8, 'Android'), (9,'Apple iOS'), (10,'macOS'),(11,'Linux'),
						 (12, 'black'),  (13,'white'), (14,'gold'), (15,'silver'),
						 (16,'Samsung'),(17,'Xiaomi'),(18,'Apple'),(19,'Huawei'),(20,'Honor'),(21,'LG'),(22,'Nokia'),(23,'Sony'), (24,'Asus'), (25,'Dell'), (26,'Toshiba'), (27,'Thomson'), (28,'Lenovo'), (29,'Philips'), (30,'Panasonic'),
		/*Smartphones*/	 (31,'Dual Sim'), (32,'Κάρτα Μνήμης'),(33,'Αδιάβροχο'),(34,'Fast Charging'),(35,'4G'),(36,'Ανθεκτικά'),(37,'Διπλή πίσω κάμερα'),(38,'Δακτυλικό Αποτύπωμα'),(39,'NFC'),

		/*Laptops*/		 (40,'Basic'),(41,'Gaming'), (42, 'Multimedia'), (43,'Επαγγελματική'),
						 (44,'Bluetooth'), (45,'VR Ready'), (46, 'Webcam'),
						 (47,'i3'),(48,'i5'),(49,'i7'),(50,'i9'),
						 (51,'SSD'),(52,'HDD') ,(53,'eMMC'), (54,'Οθόνη Αφής'),
						 (55,'GeForce GTX'), (56,'GeForce XM150'), (57,'Intel HD Graphics'),(58,'HD Graphics'),

		/*Tablets*/		 (59,'2 in 1'),(60,'HDMI'), (61,'USB On The Go'), (62,'Κάρτα SIM'),
		/*TVs*/          (63,'4K'),(64, 'QDH'), (65, 'FHD'), (66,'HDR'),(67,'FreeSync'), (68,'G-Sync'),(69,'Curved'),(70,'UltraWide'),(71,'USB Hub'),
						 (72,'TV Monitor'),(73,'Gaming Monitor'),
						 (74,'HDMI'), (75,'VGA'), (76,'DVI'), (77,'MHL'), (78,'USB-C'),

		/*Γενικά*/		 (79,'7.5\'\''),(80,'9.5\'\''),(81,'11\'\''),(82,'11.6\'\''),(83,'12.5\'\''),(84,'13.3\'\''),(85,'14.0\'\''),(86,'15.6\'\''),(87,'17\'\''),
						 (88,'21-25\'\''), (89,'25-30\'\''), (90,'30-35\'\''),(91,'35-40\'\''),(92,'40-45\'\''), (93,'45-50\'\''), (94,'75-80\'\''),
						 (95,'8core'),(96,'6core'),(97,'4core'),(98,'2core'),
						 (99,'5-10MP'),(100,'10-15MP'),(101,'15-20MP'),(102,'20-25MP'),
						 (103,'2015'),(104,'2016'),(105,'2017'),(106,'2018'),(107,'2019'),
		/*TVs again*/	 (108,'Direct LED'), (109,'Edge LED'), (110,'Netflix'), (111,'Youtube'),(112,'WiFi') ,(113,'Tizen'),(114,'WebOS');
/*!40000 ALTER TABLE `Tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `Product_Tag`
--
LOCK TABLES `Product_Tag` WRITE;
/*!40000 ALTER TABLE `Product_Tag` DISABLE KEYS */;
INSERT INTO Product_Tag(id,ProductId,TagId) VALUES 
								 (1, 1, 1), (2, 1, 2), (3,1, 16), (4,1, 63), (5,1, 72), (6,1,74), (7,1,92), (8,1,109), (9,1,110), (10,1,111), (11,1,112), (12,1,113), (13,1,106),
								 (14,2, 1), (15,2, 2), (16,2,21), (17,2,63), (18,2,72), (19,2,74), (20,2,93), (21,2,106), (22,2,108), (23,2,110), (24,2,111),(25,2,112), (26,3,114),
								 (27,3, 3), (28,3,12), (29,3,25), (30,3,44), (31,3,52),(32,3,55),(33,3,74),(34,3,86),(35,3,97),
								 (36,4,3), (37,4,12), (38,4, 28), (39,4,44), (40,4,51), (41,4,74), (42,4,78), (43,4,85),
								 (44,5,6), (45,5,8),(46,5,14),(47,5,17), (48,5,31), (49,5,32) ,(50,5,34),(51,5,37), (52,5,38), (53,5,97), (54,5,106),
								 (55,6,6), (56,6,8), (57,6,14), (58,6,17), (59,6,31), (60,6,32), (61,6,97), (62,6,100),
								 (63,7,6), (64,7,14), (65,7,15), (66,7,18), (67,7,33), (68,7,36), (69,7,37), (70,6,96), (71,7,100),
								 (72,8,6), (73,8,12), (74,8,20), (75,8,31), (76,8,32), (77,8,39), (78,8,65), (79,8,95), (80,8,102), (81,8,107),
								 (82,9,6), (83,9,8), (84,9,19), (85,9,32), (86,9,34), (87,9,36), (88,9,37), (89,9,39), (90,9,66), (91,9,95), (92,9,102),
								 (93,10,3), (94,10,10), (95,10,44), (96,10,46), (97,10,48), (98,10,51), (99,10,65), (100,10,78), (101,10,84), (102,10,98), (103,10,105),
								 (104,11,3), (105,11,7), (106,11,25), (107,11,32), (108,11,41), (109,11,44), (110,11,45), (111,11,46), (112,11,50), (113,11,51), (114,11,52), (115,11,54), (116,11,60), (117,11,64), (118,11,68), (119,11,87), (120,11,96), (121,11,106),
								 (122,12,3), (123,12,7), (124,12,26), (125,12,41), (126,12,44), (127,12,46), (128,12,49), (129,12,51), (130,12,57), (131,12,74), (132,12,75), (133,12,85), (134,12,98),
								 (135,13,5), (136,13,14), (137,13,9), (138,13,25), (139,13,44), (140,13,52), (141,13,79), (142,13,98), (143,13,99),
								 (144,14,5), (145,14,9), (146,14,16), (147,14,32), (148,14,34), (149,14,74), 
								 (150,15,1), (151,15,2), (152,15,21), (153,15,44), (154,15,63), (155,15,72), (156,15,94), (157,15,110), (158,15,111), (159,15,112), (160,650,114); 
/*!40000 ALTER TABLE `Product_Tag` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `Shop`
--
LOCK TABLES `Shop` WRITE;
/*!40000 ALTER TABLE `Shop` DISABLE KEYS */;
INSERT INTO Shop(id,name,address,lng,lat,image,withdrawn) VALUES 
						  (1, 'Πλαίσιο Αθήνα', 'Βουλής 3,10562,Αθήνα', 23.732913, 37.977344, 'static/company_profiles/plaisio.png', 0),
						  (2, 'Κωτσόβολος Αθήνα','Σταδίου 34 & Κοραή,10564,Αθήνα', 23.732025,37.979795, 'static/company_profiles/kotsovolos.png', 0),
						  (3, 'Public Αθήνα', 'Καραγεώργη Σερβίας 1,10563,Αθήνα', 23.733666 ,37.976454, 'static/company_profiles/public.png', 0),
						  (4, 'Media Markt Αθήνα', 'Λ.Χαλανδρίου 15Β,15343, Αγ. Παρασκευή', 23.818244,38.014187,  'static/company_profiles/media_markt.png',0),
						  (5, 'ΓΕΡΜΑΝΟΣ Αθήνα', 'Λεωφ. Μεσογείων 218, Χολαργός, 15561, Χολαργός', 23.792003,38.002558,  'static/company_profiles/germanos.png', 0);
/*!40000 ALTER TABLE `Shop` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `Shop_Tag`
--
LOCK TABLES `Shop_Tag` WRITE;
/*!40000 ALTER TABLE `Shop_Tag` DISABLE KEYS */;
INSERT INTO Shop_Tag(id,ShopId,TagId) VALUES 
							  (1, 1, 9), (2, 1, 4), (3, 1, 1), 
							  (4, 2, 1), (5, 2, 4), (6, 2, 10), 
							  (7, 3, 1), (8, 3, 4);
/*!40000 ALTER TABLE `Shop_Tag` ENABLE KEYS */;
UNLOCK TABLES;




--
-- Dumping data for table `record`
--
INSERT INTO Record(id,price,date,validity,productId,shopId,userId) VALUES 
							(1, 300, '2018-12-12', NULL, 1, 1, 1),  (2, 250, '2018-12-20', NULL, 1, 2, 1),  
							(3, 280, '2018-11-15', NULL, 2, 1, 1),  (4, 220,'2018-11-29', NULL, 2, 3, 1), 
							(5, 100, '2018-10-10', NULL, 3,2, 1), (6,250,'2018-10-11', NULL, 3,3, 1),
							(7,449,'2018-12-12',NULL,4,2,4), (8,399,'2019-01-12',NULL,4,2,3),
							(9,229,'2019-01-10',NULL,5,3,1), (10,259,'2018-12-03',NULL,5,5,2), (11,229,'2019-01-10',NULL,5,5,2), (12,259,'2018-12-03',NULL,5,2,4), (13,229,'2019-02-02',NULL,5,2,6),
							(14,189,'2018-10-14',NULL,6,4,2), (15,169,'2019-01-14',NULL,6,4,6), (16,169,'2018-11-03',NULL,6,2,10),
							(17,1539,'2018-11-04',NULL,7,1,8), (18,1539,'2018-11-08',NULL,7,2,9), (19,1539,'2018-11-08',NULL,7,3,3), (20,1539,'2018-11-15',NULL,7,4,4), (21,1539,'2018-11-15',NULL,7,5,4),
							(22,239,'2018-11-05',NULL,8,1,9),(23,199,'2019-01-13',NULL,8,1,10),(24,239,'2018-11-06',NULL,8,2,9),(25,199,'2019-01-14',NULL,8,2,10),(26,239,'2018-11-07',NULL,8,5,6),(27,199,'2019-01-15',NULL,8,5,10),(28,239,'2018-11-08',NULL,8,4,6),(29,199.77,'2019-01-16',NULL,8,4,7),(30,199,'2019-01-17',NULL,8,2,2),
							(31,1049,'2018-12-25',NULL,9,1,1),(32,899,'2019-02-15',NULL,9,1,1),(33,1049,'2018-12-25',NULL,9,3,1),(34,899,'2019-02-17',NULL,9,3,1),(35,1049,'2018-12-26',NULL,9,5,2),(36,899,'2019-02-01',NULL,9,5,2), (37,1049,'2018-12-26',NULL,9,4,1),(38,899,'2019-02-15',NULL,9,5,2),
							(39,1099,'2018-10-01',NULL,10,1,4),(40,1099,'2018-10-01',NULL,10,2,4),(41,1099,'2018-10-02',NULL,10,3,4),(42,1099,'2018-10-02',NULL,10,4,4),
							(43,3199,'2019-01-27',NULL,11,1,9),
							(44,2199,'2019-01-15',NULL,12,1,7),(45,2199,'2019-01-15',NULL,12,3,7),
							(46,629,'2018-12-20',NULL,13,1,10),(47,699,'2018-10-15',NULL,13,3,8),(48,629,'2019-01-05',NULL,13,3,8),
							(49,799,'2018-12-15',NULL,14,1,4),(50,749,'2019-01-25',NULL,14,1,8),(51,749,'2019-01-09',NULL,14,3,8),(52,749.99,'2019-01-08',NULL,14,5,7),(53,749.77,'2019-01-09',NULL,14,4,7),
							(54,8999,'2018-10-01',NULL,15,1,1), (55,8999,'2018-10-01',NULL,15,2,1),(56,8999,'2018-10-01',NULL,15,4,1);
UNLOCK TABLES;


--
-- Dumping data for table `Record_Validation`
--
LOCK TABLES `Record_Validation` WRITE;
/*!40000 ALTER TABLE `Record_Validation` DISABLE KEYS */;
INSERT INTO Record_Validation(id,recordId,userId) VALUES 
									   (1,1,2), (2,1,3),
									   (3,2,2), (4,2,3),
									   (5,3,3), (6,3,4),
									   (7,4,5), (8,4,6),
									   (9,5,7), (10,5,8),
									   (11,6,9), (12,6,10),
									   (13,7,1), (14,7,2),
									   (15,8,7), (16,8,9),
									   (17,9,5), (18,9,6),
									   (19,10,9),
									   (20,11,8), (21,11,9), (22,11,6),
									   (23,12,5), (24,12,1),
									   (25,13,2), (26,13,3),
									   (27,14,3), (28,14,4),
									   (29,15,4), (30,15,10), (31,15, 9),
									   (32,16,1), (33,16,2),
									   (34,17,3), (35,17,4),
									   (36,18,1), (37,18,6),
									   (38,19,5), (39,19,6),
									   (40,20,6), (41,20,7),
									   (42,21,8), (43,21,9),
									   (44,22,1), (45,22,2), (46,22,3), (47,22,4),
									   (48,23,2), (49,23,5),
									   (50,24,7), (51,24,8),
									   (52,25,8), (53,25,9),
									   (54,26,2), (55,26,10),
									   (56,27,8), (57,27,3),
									   (58,28,10), (59,28,7),
									   (60,29,1), (61,29,10),
									   (62,30,3), (63,30,4),
									   (64,31,4), (65,31,5),
									   (66,32,6), (67,32,7),
									   (68,33,9), (69,33,10),
									   (70,34,5), (71,34,2),
									   (72,35,3), (73,35,3),
									   (74,36,7), (75,36,8),
									   (76,37,2), (77,37,3), (78,37,7),
									   (79,38,5), (80,38,6),
									   (81,39,3), (82,39,6), (83,39,9), (84,39,10),
									   (85,40,2), (86,40,3),
									   (87,41,6), (88,41,7),
									   (89,42,5), (90,42,6),
									   (91,43,7), (92,43,8),
									   (93,44,8), (94,44,9), (95,44,10),
									   (96,45,3), (97,48,4),
									   (98,45,5), (99,45,6),
									   (100,46,1), (101,46,2),
									   (102,47,2), (103,47,3),
									   (104,48,2), (105,48,10),
									   (106,49,3), (107,49,5),
									   (108,50,10), (109,50,6),
									   (110,51,5), (111,51,6),
									   (112,52,2), (113,52,3),
									   (114,53,2), (115,53,3), (116,53,4), (117,53,5),
									   (118,54,9), (119,54,10),
									   (120,55,8), (121,55,9),
									   (122,56,9), (123,56,8), (124,56,7), (125,56,6);

/*!40000 ALTER TABLE `Record_Validation` ENABLE KEYS */;
UNLOCK TABLES;









/*-------------------------
-----------VIEWS-----------
---------------------------*/

DROP VIEW IF EXISTS `Top_Trends`;

CREATE VIEW `Top_Trends` 
AS SELECT new.name, new.description, new.image, new.avg
FROM ( SELECT DISTINCT p.name, p.description, p.image, AVG(ur.Stars) AS avg
	   FROM Product p, User_Rating ur
       WHERE p.id=ur.ProductId
       GROUP BY ur.ProductId ) AS new
ORDER BY new.avg
DESC LIMIT 5;


DROP VIEW IF EXISTS `Hot_Offers`;
CREATE VIEW `Hot_Offers` 
AS SELECT new.name, new.description, new.image, new.price
FROM ( SELECT p.name, p.description, p.image, r.price
	   FROM Record r,  Product p
	   WHERE p.id=r.productId
       GROUP BY r.productId, r.shopId
       HAVING COUNT(*) >= 2
       ORDER BY r.price
       DESC LIMIT 1 ) AS new
ORDER BY new.price
DESC LIMIT 5;
     


/*-------------------------
-----------TRIGGERS---------
---------------------------*/

/*Email Constraint, ensure that the given value is in the right format*/
-- DELIMITER $$
-- CREATE TRIGGER `user_validate_email_insert` BEFORE INSERT  ON `User`
-- FOR EACH ROW
-- 	BEGIN
-- 		IF NOT (SELECT NEW.email REGEXP '$[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$') THEN
-- 			SIGNAL SQLSTATE VALUE '45000';
-- 		END IF;
-- 	END$$

-- CREATE TRIGGER `user_validate_email_update` BEFORE UPDATE  ON `User`
-- FOR EACH ROW
-- 	BEGIN
-- 		IF NOT (SELECT NEW.email REGEXP '$[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$') THEN
-- 			SIGNAL SQLSTATE VALUE '45000';
-- 		END IF;
-- 	END$$

-- DELIMITER ;



-- /*First Name and Last Name before insert or update*/

-- DELIMITER $$
-- CREATE TRIGGER `user_validate_name_insert` BEFORE INSERT  ON `User`
-- FOR EACH ROW
-- 	BEGIN
-- 		IF NOT(NEW.`firstName` REGEXP '^[A-Za-z]+$') THEN
-- 			SIGNAL SQLSTATE VALUE '45000';
-- 		END IF;
-- 	END$$

-- CREATE TRIGGER `user_validate_name_update` BEFORE UPDATE  ON `User`
-- FOR EACH ROW
-- 	BEGIN
-- 		IF NOT(NEW.`firstName` REGEXP '^[A-Za-z]+$') THEN
-- 			SIGNAL SQLSTATE VALUE '45000';
-- 		END IF;
-- 	END$$

-- DELIMITER ;


/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-29 14:47:43

