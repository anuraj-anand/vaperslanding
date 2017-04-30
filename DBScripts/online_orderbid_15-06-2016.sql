-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: orderbid
-- ------------------------------------------------------
-- Server version	5.6.28-log

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
-- Table structure for table `app_admin`
--

DROP TABLE IF EXISTS `app_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `app_admin` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_admin`
--

LOCK TABLES `app_admin` WRITE;
/*!40000 ALTER TABLE `app_admin` DISABLE KEYS */;
/*!40000 ALTER TABLE `app_admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_details`
--

DROP TABLE IF EXISTS `bank_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bank_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_HOLDER_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ACCOUNT_NUMBER` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `BRANCH` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CITY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IFSC_CODE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `BANK_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_details`
--

LOCK TABLES `bank_details` WRITE;
/*!40000 ALTER TABLE `bank_details` DISABLE KEYS */;
INSERT INTO `bank_details` VALUES (1,'test','1234456768663','Kothrud','Pune','UTI8338','IDBI',2);
/*!40000 ALTER TABLE `bank_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BID_AMOUNT` decimal(19,2) DEFAULT NULL,
  `BID_QTY` int(11) DEFAULT NULL,
  `BID_TIME` datetime DEFAULT NULL,
  `USER` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ORDER_NO` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `STATUS` varchar(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES (6,16.00,0,'2016-04-11 21:47:16','3','A123445','A'),(7,10.00,10,'2016-05-01 22:31:21','3','1.0','C'),(8,9.00,10,'2016-05-01 22:33:46','3','1.0','C'),(9,10.00,10,'2016-05-01 22:40:44','3','A123456','C'),(10,9.00,10,'2016-05-01 22:45:24','3','2.0','C'),(11,9.00,10,'2016-05-01 22:51:46','3','A123456','A'),(12,8.00,10,'2016-05-01 22:55:31','3','1.0','A'),(13,8.00,10,'2016-05-01 22:58:13','3','B123451','C'),(14,8.00,10,'2016-05-01 23:19:00','3','B123454','C');
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bid_sessions`
--

DROP TABLE IF EXISTS `bid_sessions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid_sessions` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FROM_TIME` time NOT NULL DEFAULT '00:00:00',
  `TO_TIME` time NOT NULL DEFAULT '00:00:00',
  `MODE` int(4) NOT NULL DEFAULT '0',
  `DAY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid_sessions`
--

LOCK TABLES `bid_sessions` WRITE;
/*!40000 ALTER TABLE `bid_sessions` DISABLE KEYS */;
INSERT INTO `bid_sessions` VALUES (2,'22:42:39','23:42:39',0,'Monday'),(3,'22:42:39','23:42:39',0,'All days'),(4,'22:42:39','23:42:39',0,'Tuesday'),(5,'22:42:39','23:42:39',0,'Thursday'),(6,'22:42:39','23:42:39',0,'Wednesday'),(7,'22:42:39','23:42:39',0,'Sunday'),(8,'15:42:39','16:42:39',0,'All days'),(9,'15:42:39','16:42:39',0,'Monday'),(10,'15:42:39','16:42:39',0,'Wednesday'),(11,'15:42:39','16:42:39',0,'Saturday'),(12,'15:42:39','16:42:39',0,'Tuesday'),(13,'15:42:39','16:42:39',0,'Thursday'),(14,'15:42:39','16:42:39',0,'Sunday'),(15,'15:42:39','16:42:39',0,'All days'),(16,'15:42:39','16:42:39',0,'Monday'),(17,'15:42:39','16:42:39',0,'Tuesday'),(18,'15:42:39','16:42:39',0,'Thursday'),(19,'15:42:39','16:42:39',0,'Wednesday'),(20,'15:42:39','16:42:39',0,'Saturday'),(21,'15:42:39','16:42:39',0,'Sunday'),(22,'00:15:00','01:35:00',0,'Friday'),(27,'16:21:03','16:21:03',0,'Friday'),(28,'22:10:01','23:10:01',0,'Sunday');
/*!40000 ALTER TABLE `bid_sessions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(999) DEFAULT NULL,
  `TYPE` int(11) NOT NULL DEFAULT '1',
  `ACTIVE` int(4) NOT NULL DEFAULT '0',
  `FLAG` varchar(255) DEFAULT NULL,
  `UPDATED_DATETIME` int(11) DEFAULT NULL,
  `CREATED_DATETIME` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (1,'Admin',0,1,'1',NULL,1464719400),(2,'Shopseller',1,1,'1',NULL,1464805800),(3,'KYC_logistics',2,1,'1',NULL,1464805800),(5,'TestSeller',1,0,'0',NULL,1464805800);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `display_details`
--

DROP TABLE IF EXISTS `display_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `display_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `DESC_BUSINESS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DISPLAY_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `REFUND_POLICY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `display_details`
--

LOCK TABLES `display_details` WRITE;
/*!40000 ALTER TABLE `display_details` DISABLE KEYS */;
INSERT INTO `display_details` VALUES (1,2,'Our business to generate orders to deliver goods.','Parag Bhangale',NULL);
/*!40000 ALTER TABLE `display_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kyc_documents`
--

DROP TABLE IF EXISTS `kyc_documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kyc_documents` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS_PROOF` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CANCELLED_CHEQUE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `ID_PROOF` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kyc_documents`
--

LOCK TABLES `kyc_documents` WRITE;
/*!40000 ALTER TABLE `kyc_documents` DISABLE KEYS */;
INSERT INTO `kyc_documents` VALUES (1,'test address','test cheques',2,'test ID proof'),(2,'logistics Address proof','logistics Address proof chequqe',3,'logistics ID proof');
/*!40000 ALTER TABLE `kyc_documents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logistics`
--

DROP TABLE IF EXISTS `logistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `logistics` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logistics`
--

LOCK TABLES `logistics` WRITE;
/*!40000 ALTER TABLE `logistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `logistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pickup_address`
--

DROP TABLE IF EXISTS `pickup_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pickup_address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS_LINE_1` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDRESS_LINE_2` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `PICKUP_CITY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PIN_CODE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pickup_address`
--

LOCK TABLES `pickup_address` WRITE;
/*!40000 ALTER TABLE `pickup_address` DISABLE KEYS */;
INSERT INTO `pickup_address` VALUES (1,'bavdhan','kothrud',2,'Pune','411038');
/*!40000 ALTER TABLE `pickup_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pltorder`
--

DROP TABLE IF EXISTS `pltorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pltorder` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDITIONAL_FIELDS` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SHIPMENT_DATE` datetime DEFAULT NULL,
  `ITEM_SYMBOL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DELIVERY_DATE` datetime DEFAULT NULL,
  `DEST_PIN` int(11) unsigned DEFAULT NULL,
  `DEST_ADDRESS` varchar(999) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `LAST_MODIFIEDTIME` datetime DEFAULT NULL,
  `ORDER_BID_EXPIRYTIME` datetime DEFAULT NULL,
  `ORDER_DATE` datetime DEFAULT NULL,
  `ORDER_NO` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRIORITY` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ASK_QUANTITY` int(11) DEFAULT NULL,
  `SOURCE_ADDRESS` longtext COLLATE utf8mb4_unicode_ci,
  `STATUS` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `UUID` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SOURCE_PIN` int(11) DEFAULT NULL,
  `COMPANY_ID` int(11) NOT NULL,
  `CREATED_BY` int(11) DEFAULT NULL,
  `UPDATED_BY` int(11) DEFAULT NULL,
  `ASK_RATE` double DEFAULT NULL,
  `WEIGHT` bigint(20) DEFAULT NULL,
  `rowCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_8o2iomu3dirwvtmbenlnhqs2b` (`COMPANY_ID`),
  CONSTRAINT `FK_8o2iomu3dirwvtmbenlnhqs2b` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pltorder`
--

LOCK TABLES `pltorder` WRITE;
/*!40000 ALTER TABLE `pltorder` DISABLE KEYS */;
INSERT INTO `pltorder` VALUES (1,'gift','2016-03-27 12:19:26','test','2016-03-27 12:19:34',411021,'jalgaon','2016-03-27 12:18:53','2016-03-27 12:19:37','2016-03-27 12:18:53','1.0','S',10,'A123456','A','1.0',411038,2,2,2,11,10,NULL),(2,'gift','2016-03-27 12:19:27','test','2016-03-27 12:19:35',411022,'jalgaon','2016-03-27 12:18:53','2016-03-27 12:19:38','2016-03-27 12:18:53','2.0','S',10,'B123457','A','2.0',411038,2,2,2,10,3,NULL),(10,'gift','2016-03-31 12:00:00','parag','2016-04-01 12:00:00',411034,'Hadapsar','2016-03-27 15:31:58','2016-04-08 01:15:50','2016-03-27 15:31:58','A123445','M',1,'Kothrud','D','79890c0b-991d-4479-8286-ef4c36def545',411038,2,2,2,22,12,NULL),(11,'gift','2016-02-22 00:00:00','1.0','2016-02-22 00:00:00',411038,'C-17, Shivnagari, Kothrud, Pune','2016-04-04 22:10:07',NULL,'2016-04-04 22:10:07','A123456','N',10,'Bhugaon MIDC, Bhugan, Pune','A','1.0',411032,2,2,2,12,10,NULL),(12,'gift','2016-02-22 00:00:00','2.0','2016-02-22 00:00:00',411023,'C-17, Shivnagari, Kothrud, Pune','2016-04-04 22:10:08',NULL,'2016-04-04 22:10:08','B123457','N',10,'Bhugaon MIDC, Bhugan, Pune','A','2.0',411030,2,2,2,13,12,NULL),(13,'Added','2016-02-22 00:00:00','Raj','2016-02-22 00:00:00',411034,'Sangavi, Pune','2016-04-11 21:20:03',NULL,'2016-04-11 21:20:03','A123458','N',NULL,'Bhugaon MIDC, Bhugan, Pune','A','1001.0',411030,2,2,2,14,10,NULL),(14,'delrted','2016-02-23 00:00:00','Rajesh','2016-02-23 00:00:00',411035,'Chinchwad, Pune','2016-04-11 21:20:03',NULL,'2016-04-11 21:20:03','B123451','N',NULL,'Bhugaon MIDC, Bhugan, Pune','A','1002.0',411030,2,2,2,15,11,NULL),(15,'abc','2016-02-24 00:00:00','Prakash','2016-02-24 00:00:00',411036,'Wanwori, Pune','2016-04-11 21:20:03',NULL,'2016-04-11 21:20:03','B123452','N',NULL,'Bhugaon MIDC, Bhugan, Pune','A','1003.0',411030,2,2,2,16,12,NULL),(16,'xyz','2016-02-25 00:00:00','Test','2016-02-25 00:00:00',411037,'Vimannagar, Pune','2016-04-11 21:20:03',NULL,'2016-04-11 21:20:03','B123453','N',NULL,'Bhugaon MIDC, Bhugan, Pune','A','1004.0',411030,2,2,2,17,13,NULL),(17,'pqr','2016-02-26 00:00:00','test1','2016-02-26 00:00:00',411031,'Karve road, Pune','2016-04-11 21:20:03',NULL,'2016-04-11 21:20:03','B123454','N',NULL,'Bhugaon MIDC, Bhugan, Pune','C','1005.0',411030,2,2,2,18,14,NULL),(18,'lmn','2016-02-27 00:00:00','test','2016-02-27 00:00:00',411032,'Shivajinagar, Pune','2016-04-11 21:20:03',NULL,'2016-04-11 21:20:03','B123455','N',NULL,'Bhugaon MIDC, Bhugan, Pune','A','1006.0',411030,2,2,2,19,15,NULL);
/*!40000 ALTER TABLE `pltorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `primary_details`
--

DROP TABLE IF EXISTS `primary_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `primary_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `PRIMARY_EMAIL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRIMARY_MOBILE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRIMARY_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `primary_details`
--

LOCK TABLES `primary_details` WRITE;
/*!40000 ALTER TABLE `primary_details` DISABLE KEYS */;
INSERT INTO `primary_details` VALUES (1,2,'bhangale.parag@gmail.com','9545738883','Parag Bhangale');
/*!40000 ALTER TABLE `primary_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tariff`
--

DROP TABLE IF EXISTS `tariff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tariff` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CHARGE` decimal(19,2) DEFAULT NULL,
  `PINCODE` int(11) DEFAULT NULL,
  `AREA` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CARD_TYPE` int(11) NOT NULL,
  `COMPANY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tariff`
--

LOCK TABLES `tariff` WRITE;
/*!40000 ALTER TABLE `tariff` DISABLE KEYS */;
INSERT INTO `tariff` VALUES (1,11.00,411021,'Bavdhan',1,3),(2,12.00,411022,'Bhugaon',1,3),(3,13.00,411023,'Kothrud',1,3),(4,14.00,411024,'Camp',1,3),(5,15.00,411025,'Vishrantwadi',1,3),(6,16.00,411026,'Pune Station',1,3),(7,17.00,411027,'Hadapsar',1,3),(8,18.00,411028,'Kothrud depo',1,3),(9,19.00,411029,'MIT college',1,3),(10,20.00,411030,'Yerwada',1,3),(11,21.00,411031,'Vimannagar',1,3),(12,22.00,411032,'Pirangut',1,3),(13,23.00,411033,'Pimpale Saudagar',1,3),(14,24.00,411034,'Pimpale nilakh',1,3),(15,25.00,411035,'Chinchwad',1,3),(16,26.00,411036,'Pimpri',1,3),(17,27.00,411037,'Sangavi',1,3),(18,28.00,411038,'Nana peth',1,3),(19,29.00,411039,'Shivajinagar',1,3),(20,30.00,411040,'Warje',1,3),(21,100.00,410210,'Mumbai',2,3),(22,2000.00,1234,'New York',3,3),(23,500.00,419201,'Banglore',2,3),(24,NULL,6373989,'New jersey',3,3);
/*!40000 ALTER TABLE `tariff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MOBILE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PASSWORD` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TYPE` int(4) DEFAULT '2',
  `USERNAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMPANY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_m68drmr05xgsv4w2b12apnnua` (`COMPANY_ID`),
  CONSTRAINT `FK_m68drmr05xgsv4w2b12apnnua` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@orderbd.com','+917588003251','098f6bcd4621d373cade4e832627b4f6',1,'admin',1),(2,'bhangale.parag@gmail.com','9545738883','098f6bcd4621d373cade4e832627b4f6',1,'bhangale',2),(3,'kunalshinde098@gmail.com','7588648709','098f6bcd4621d373cade4e832627b4f6',1,'parag',3),(6,'bhangale.sudhak2ar@gmail.com','7588647709','098f6bcd4621d373cade4e832627b4f6',1,'testseller',5),(7,'bhangale.sudhakar2@gmil.com','6579797979','098f6bcd4621d373cade4e832627b4f6',3,'delivery',3),(8,'bhangale.sudhakar3@gmil.com','6579797979','098f6bcd4621d373cade4e832627b4f6',2,'bidder',3),(9,'eretre@trrt.jhu','123456789','96f4f55b0aa5fecc36d9033773d9b2b9',2,'tesr',2),(10,'bhangale.test@gmail.com','87689790090','cbaa653edf024a98704fe63262b3a8bb',2,'newtest',2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `warehouse`
--

DROP TABLE IF EXISTS `warehouse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warehouse` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warehouse`
--

LOCK TABLES `warehouse` WRITE;
/*!40000 ALTER TABLE `warehouse` DISABLE KEYS */;
/*!40000 ALTER TABLE `warehouse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `watches`
--

DROP TABLE IF EXISTS `watches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `watches` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(10) NOT NULL,
  `USER_ID` int(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `watches`
--

LOCK TABLES `watches` WRITE;
/*!40000 ALTER TABLE `watches` DISABLE KEYS */;
INSERT INTO `watches` VALUES (1,1,3),(2,2,3),(3,10,3),(4,13,3);
/*!40000 ALTER TABLE `watches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `winnerbid`
--

DROP TABLE IF EXISTS `winnerbid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `winnerbid` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BID_AMOUNT` decimal(19,2) DEFAULT NULL,
  `STATUS` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `BID_TIME` datetime DEFAULT NULL,
  `USER` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ORDER_NO` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `winnerbid`
--

LOCK TABLES `winnerbid` WRITE;
/*!40000 ALTER TABLE `winnerbid` DISABLE KEYS */;
INSERT INTO `winnerbid` VALUES (4,8.00,'W','2016-05-01 22:58:13','3','B123451'),(5,8.00,'W','2016-05-01 23:19:00','3','B123454');
/*!40000 ALTER TABLE `winnerbid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'orderbid'
--

--
-- Dumping routines for database 'orderbid'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-15  1:01:34
