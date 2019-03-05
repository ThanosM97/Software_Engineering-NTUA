
-- MySQL dump for Stingy by JavaMagkes

CREATE DATABASE  IF NOT EXISTS `testStingy`;
USE testStingy;

-- Host: localhost    Database: testStingy

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

DROP FUNCTION IF EXISTS GetDistance;
create function GetDistance(lat1 real, lng1 real, lat2 real, lng2 real) returns real no sql
return ATAN2(SQRT(POW(COS(RADIANS(lat2)) * SIN(RADIANS(lng1 - lng2)), 2) +
POW(COS(RADIANS(lat1)) * SIN(RADIANS(lat2)) - SIN(RADIANS(lat1)) *
COS(RADIANS(lat2)) * COS(RADIANS(lng1 - lng2)), 2)),
(SIN(RADIANS(lat1)) * SIN(RADIANS(lat2)) + COS(RADIANS(lat1)) *
COS(RADIANS(lat2)) * COS(RADIANS(lng1 - lng2)))) * 6372.795;

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
