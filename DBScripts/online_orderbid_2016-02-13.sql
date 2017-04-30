# ************************************************************
# Sequel Pro SQL dump
# Version 4500
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: db4free.net (MySQL 5.7.11)
# Database: orderbid
# Generation Time: 2016-02-13 06:43:25 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table APP_ADMIN
# ------------------------------------------------------------

DROP TABLE IF EXISTS `APP_ADMIN`;

CREATE TABLE `APP_ADMIN` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



# Dump of table BID
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BID`;

CREATE TABLE `BID` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BID_AMOUNT` decimal(19,2) DEFAULT NULL,
  `BID_TIME` datetime DEFAULT NULL,
  `USER` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ORDER_NO` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `STATUS` varchar(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `BID` WRITE;
/*!40000 ALTER TABLE `BID` DISABLE KEYS */;

INSERT INTO `BID` (`ID`, `BID_AMOUNT`, `BID_TIME`, `USER`, `ORDER_NO`, `STATUS`)
VALUES
	(1,10.00,'2016-02-09 21:33:49','1','A123459','A'),
	(2,9.00,'2016-02-09 21:36:21','2','A123459','A'),
	(3,8.00,'2016-02-09 21:37:02','3','A123459','A'),
	(4,12.00,'2016-02-09 21:37:25','1','A123460','A'),
	(5,15.00,'2016-02-09 21:38:26','3','A123460','A'),
	(6,19.00,'2016-02-09 21:38:46','2','A123460','A'),
	(7,19.00,'2016-02-09 22:46:04','4','A123460','A');

/*!40000 ALTER TABLE `BID` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table LOGISTICS
# ------------------------------------------------------------

DROP TABLE IF EXISTS `LOGISTICS`;

CREATE TABLE `LOGISTICS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `LOGISTICS` WRITE;
/*!40000 ALTER TABLE `LOGISTICS` DISABLE KEYS */;

INSERT INTO `LOGISTICS` (`ID`, `NAME`)
VALUES
	(1,'kunal'),
	(2,'PARAG'),
	(3,'test'),
	(4,'dummy');

/*!40000 ALTER TABLE `LOGISTICS` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table ORDER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `ORDER`;

CREATE TABLE `ORDER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDITIONAL_FIELDS` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DELIVERY_DATE` datetime DEFAULT NULL,
  `DESTINATION` varchar(999) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `LAST_MODIFIEDTIME` datetime DEFAULT NULL,
  `ORDER_BID_EXPIRYTIME` datetime DEFAULT NULL,
  `ORDER_DATE` datetime DEFAULT NULL,
  `ORDER_NO` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRIORITY` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `QUANTITY` int(11) DEFAULT NULL,
  `SOURCE` longtext COLLATE utf8mb4_unicode_ci,
  `STATUS` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `UUID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PINCODE` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `ORDER` WRITE;
/*!40000 ALTER TABLE `ORDER` DISABLE KEYS */;

INSERT INTO `ORDER` (`ID`, `ADDITIONAL_FIELDS`, `DELIVERY_DATE`, `DESTINATION`, `LAST_MODIFIEDTIME`, `ORDER_BID_EXPIRYTIME`, `ORDER_DATE`, `ORDER_NO`, `PRIORITY`, `QUANTITY`, `SOURCE`, `STATUS`, `UUID`, `PINCODE`)
VALUES
	(1,'gift','2015-10-15 00:00:00','411040','2016-02-10 05:50:28','2016-02-12 05:51:08','2015-10-15 00:00:00','A123456','N',10,'jalgaon','S','1.0',511021),
	(2,'gift','2015-10-15 00:00:00','411032','2016-02-10 05:50:28','2016-02-12 05:50:28','2015-10-15 00:00:00','B123457','N',10,'jalgaon','S','2.0',411032),
	(3,'gift','2016-02-11 13:40:10','411023','2016-02-09 13:40:10','2016-02-12 13:40:10','2015-10-15 00:00:00','A123458','N',10,'jalgaon','S','1.0',411023),
	(4,'gift','2016-02-11 13:40:10','411024','2016-02-09 13:40:10','2016-02-10 13:40:10','2015-10-15 00:00:00','A123459','N',10,'jalgaon','S','1.0',411024),
	(5,'gift','2016-02-11 13:40:11','411025','2016-02-09 13:40:11','2016-02-08 13:40:11','2015-10-15 00:00:00','A123460','N',10,'jalgaon','S','1.0',411025),
	(6,'gift','2016-02-11 13:41:07','411023','2016-02-09 13:41:07','2016-02-10 13:41:07','2015-10-15 00:00:00','A123458','N',10,'jalgaon','S','1.0',411023),
	(7,'gift','2016-02-11 13:41:07','411024','2016-02-09 13:41:07','2016-02-10 13:41:07','2015-10-15 00:00:00','A123459','N',10,'jalgaon','S','1.0',411024),
	(8,'gift','2016-02-11 13:41:08','411025','2016-02-09 13:41:08','2016-02-08 13:41:08','2015-10-15 00:00:00','A123460','N',10,'jalgaon','S','1.0',411025);

/*!40000 ALTER TABLE `ORDER` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table RATE_CARD
# ------------------------------------------------------------

DROP TABLE IF EXISTS `RATE_CARD`;

CREATE TABLE `RATE_CARD` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CARD_TYPE` int(11) DEFAULT NULL,
  `LOGISTICS_ID` int(11) DEFAULT NULL,
  `RATE_DATA` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `RATE_CARD` WRITE;
/*!40000 ALTER TABLE `RATE_CARD` DISABLE KEYS */;

INSERT INTO `RATE_CARD` (`ID`, `CARD_TYPE`, `LOGISTICS_ID`, `RATE_DATA`)
VALUES
	(1,1,1,1);

/*!40000 ALTER TABLE `RATE_CARD` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table TARIFF
# ------------------------------------------------------------

DROP TABLE IF EXISTS `TARIFF`;

CREATE TABLE `TARIFF` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CHARGE` decimal(19,2) DEFAULT NULL,
  `PINCODE` int(11) DEFAULT NULL,
  `RATE_CARD_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `TARIFF` WRITE;
/*!40000 ALTER TABLE `TARIFF` DISABLE KEYS */;

INSERT INTO `TARIFF` (`ID`, `CHARGE`, `PINCODE`, `RATE_CARD_ID`)
VALUES
	(1,11.00,411021,1),
	(2,12.00,411022,1),
	(3,13.00,411023,1),
	(4,14.00,411024,1),
	(5,15.00,411025,1),
	(6,16.00,411026,1),
	(7,17.00,411027,1),
	(8,18.00,411028,1),
	(9,19.00,411029,1),
	(10,20.00,411030,1),
	(11,21.00,411031,1),
	(12,22.00,411032,1),
	(13,23.00,411033,1),
	(14,24.00,411034,1),
	(15,25.00,411035,1),
	(16,26.00,411036,1),
	(17,27.00,411037,1),
	(18,28.00,411038,1),
	(19,29.00,411039,1),
	(20,30.00,411040,1);

/*!40000 ALTER TABLE `TARIFF` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table USER
# ------------------------------------------------------------

DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MOBILE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PASSWORD` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TYPE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `USER` WRITE;
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;

INSERT INTO `USER` (`ID`, `EMAIL`, `MOBILE`, `PASSWORD`, `TYPE`, `USERNAME`)
VALUES
	(1,'kunalshinde098@gmail.com','+917588003251','b33a46f5ee81f6f0790f3ea9f02468e1','warehouse','kunal'),
	(3,'test@test.cm','9545738883','098f6bcd4621d373cade4e832627b4f6','logistics','test'),
	(4,'bhangale.parag@gmail.com','9545738883','098f6bcd4621d373cade4e832627b4f6','warehouse','bhangale'),
	(5,'bhangale.parag@gmail.com','9545738883','098f6bcd4621d373cade4e832627b4f6','warehouse','parag'),
	(6,'bhangale.parag@gmail.com','sdfsdfsdfsdf','4128513f18e1b9cb5299bac36502b961','warehouse','testqqqadf'),
	(7,'satish.mangwane@yahoo.com','9325875420','247e0a31048554f35902283df30263ab','warehouse','satish.mangwane'),
	(8,'proxoffer@gmail.com','9001234567','4bc48e00300464d2670958ab3c8982ea','warehouse','proxoffer');

/*!40000 ALTER TABLE `USER` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table WAREHOUSE
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WAREHOUSE`;

CREATE TABLE `WAREHOUSE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK TABLES `WAREHOUSE` WRITE;
/*!40000 ALTER TABLE `WAREHOUSE` DISABLE KEYS */;

INSERT INTO `WAREHOUSE` (`ID`, `NAME`)
VALUES
	(1,'abc'),
	(2,'abc'),
	(3,'test'),
	(4,'tst'),
	(5,'test1'),
	(6,'asd'),
	(7,'self'),
	(8,'XXL CO');

/*!40000 ALTER TABLE `WAREHOUSE` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table WINNERBID
# ------------------------------------------------------------

DROP TABLE IF EXISTS `WINNERBID`;

CREATE TABLE `WINNERBID` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BID_AMOUNT` decimal(19,2) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `BID_TIME` datetime DEFAULT NULL,
  `USER` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ORDER_NO` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
