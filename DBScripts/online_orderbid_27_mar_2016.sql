-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.44-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.2.0.4947
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for orderbid
CREATE DATABASE IF NOT EXISTS `orderbid` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `orderbid`;


-- Dumping structure for table orderbid.ACCOUNT
CREATE TABLE IF NOT EXISTS `ACCOUNT` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `DESC_BUSINESS` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DISPLAY_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `REFUND_POLICY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.ACCOUNT: ~0 rows (approximately)
/*!40000 ALTER TABLE `ACCOUNT` DISABLE KEYS */;
/*!40000 ALTER TABLE `ACCOUNT` ENABLE KEYS */;


-- Dumping structure for table orderbid.APP_ADMIN
CREATE TABLE IF NOT EXISTS `APP_ADMIN` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PASSWORD` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.APP_ADMIN: ~0 rows (approximately)
/*!40000 ALTER TABLE `APP_ADMIN` DISABLE KEYS */;
/*!40000 ALTER TABLE `APP_ADMIN` ENABLE KEYS */;


-- Dumping structure for table orderbid.BANK_DETAILS
CREATE TABLE IF NOT EXISTS `BANK_DETAILS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_HOLDER_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ACCOUNT_NUMBER` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `BRANCH` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CITY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `IFSC_CODE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `BANK_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.BANK_DETAILS: ~0 rows (approximately)
/*!40000 ALTER TABLE `BANK_DETAILS` DISABLE KEYS */;
/*!40000 ALTER TABLE `BANK_DETAILS` ENABLE KEYS */;


-- Dumping structure for table orderbid.bid
CREATE TABLE IF NOT EXISTS `BID` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BID_AMOUNT` decimal(19,2) DEFAULT NULL,
  `BID_QTY` int(11) DEFAULT NULL,
  `BID_TIME` datetime DEFAULT NULL,
  `USER` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ORDER_NO` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `STATUS` varchar(4) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderBID.BID: ~4 rows (approximately)
/*!40000 ALTER TABLE `BID` DISABLE KEYS */;
INSERT INTO `BID` (`ID`, `BID_AMOUNT`, `BID_QTY`, `BID_TIME`, `USER`, `ORDER_NO`, `STATUS`) VALUES
	(1, 21.00, 0, '2016-03-28 16:29:29', '3', 'A123445', 'A'),
	(2, 20.00, 0, '2016-03-28 16:30:11', '3', 'A123445', 'A'),
	(3, 20.00, 0, '2016-03-28 16:31:43', '3', 'A123445', 'A'),
	(4, 19.00, 10, '2016-03-28 16:49:50', '3', 'A123445', 'A');
/*!40000 ALTER TABLE `BID` ENABLE KEYS */;


-- Dumping structure for table orderbid.BID_SESSIONS
CREATE TABLE IF NOT EXISTS `BID_SESSIONS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FROM_TIME` time NOT NULL DEFAULT '00:00:00',
  `TO_TIME` time NOT NULL DEFAULT '00:00:00',
  `MODE` int(4) NOT NULL DEFAULT '0',
  `DAY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table orderbid.BID_SESSIONS: ~0 rows (approximately)
/*!40000 ALTER TABLE `BID_SESSIONS` DISABLE KEYS */;
/*!40000 ALTER TABLE `BID_SESSIONS` ENABLE KEYS */;


-- Dumping structure for table orderbid.COMPANY
CREATE TABLE IF NOT EXISTS `COMPANY` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(999) DEFAULT NULL,
  `TYPE` int(11) NOT NULL DEFAULT '1',
  `ACTIVE` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table orderbid.COMPANY: ~3 rows (approximately)
/*!40000 ALTER TABLE `COMPANY` DISABLE KEYS */;
INSERT INTO `COMPANY` (`ID`, `NAME`, `TYPE`, `ACTIVE`) VALUES
	(1, 'Admin', 0, 1),
	(2, 'Shopseller', 1, 1),
	(3, 'KYC_logistics', 2, 1);
/*!40000 ALTER TABLE `COMPANY` ENABLE KEYS */;


-- Dumping structure for table orderbid.kyc_documents
CREATE TABLE IF NOT EXISTS `kyc_documents` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS_PROOF` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CANCELLED_CHEQUE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `ID_PROOF` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.kyc_documents: ~0 rows (approximately)
/*!40000 ALTER TABLE `kyc_documents` DISABLE KEYS */;
/*!40000 ALTER TABLE `kyc_documents` ENABLE KEYS */;


-- Dumping structure for table orderbid.logistics
CREATE TABLE IF NOT EXISTS `logistics` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.logistics: ~0 rows (approximately)
/*!40000 ALTER TABLE `logistics` DISABLE KEYS */;
/*!40000 ALTER TABLE `logistics` ENABLE KEYS */;


-- Dumping structure for table orderbid.pickup_address
CREATE TABLE IF NOT EXISTS `pickup_address` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS_LINE_1` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ADDRESS_LINE_2` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `PICKUP_CITY` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PIN_CODE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.pickup_address: ~0 rows (approximately)
/*!40000 ALTER TABLE `pickup_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `pickup_address` ENABLE KEYS */;


-- Dumping structure for table orderbid.pltorder
CREATE TABLE IF NOT EXISTS `pltorder` (
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
  CONSTRAINT `FK_8o2iomu3dirwvtmbenlnhqs2b` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.pltorder: ~3 rows (approximately)
/*!40000 ALTER TABLE `pltorder` DISABLE KEYS */;
INSERT INTO `pltorder` (`ID`, `ADDITIONAL_FIELDS`, `SHIPMENT_DATE`, `ITEM_SYMBOL`, `DELIVERY_DATE`, `DEST_PIN`, `DEST_ADDRESS`, `LAST_MODIFIEDTIME`, `ORDER_BID_EXPIRYTIME`, `ORDER_DATE`, `ORDER_NO`, `PRIORITY`, `ASK_QUANTITY`, `SOURCE_ADDRESS`, `STATUS`, `UUID`, `SOURCE_PIN`, `COMPANY_ID`, `CREATED_BY`, `UPDATED_BY`, `ASK_RATE`, `WEIGHT`, `rowCount`) VALUES
	(1, NULL, '2016-03-27 12:19:26', 'test', '2016-03-27 12:19:34', 10, 'jalgaon', '2016-03-27 12:18:53', '2016-03-27 12:19:37', '2016-03-27 12:18:53', '1.0', 'S', 10, 'A123456', 'A', '1.0', 411038, 2, 2, 2, NULL, NULL, NULL),
	(2, NULL, '2016-03-27 12:19:27', 'test', '2016-03-27 12:19:35', 10, 'jalgaon', '2016-03-27 12:18:53', '2016-03-27 12:19:38', '2016-03-27 12:18:53', '2.0', 'S', 10, 'B123457', 'A', '2.0', 411038, 2, 2, 2, NULL, NULL, NULL),
	(10, NULL, '2016-03-31 12:00:00', 'parag', '2016-04-01 12:00:00', 411034, 'Hadapsar', '2016-03-27 15:31:58', NULL, '2016-03-27 15:31:58', 'A123445', 'M', 1, 'Kothrud', 'D', '79890c0b-991d-4479-8286-ef4c36def545', 411038, 2, 2, 2, 22, 12, NULL);
/*!40000 ALTER TABLE `pltorder` ENABLE KEYS */;


-- Dumping structure for table orderbid.primary_details
CREATE TABLE IF NOT EXISTS `primary_details` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `PRIMARY_EMAIL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRIMARY_MOBILE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PRIMARY_NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.primary_details: ~0 rows (approximately)
/*!40000 ALTER TABLE `primary_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `primary_details` ENABLE KEYS */;


-- Dumping structure for table orderbid.RATE_CARD
CREATE TABLE IF NOT EXISTS `RATE_CARD` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CARD_TYPE` int(11) DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `RATE_DATA` int(11) DEFAULT NULL,
  `LOGISTICS_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.RATE_CARD: ~3 rows (approximately)
/*!40000 ALTER TABLE `RATE_CARD` DISABLE KEYS */;
INSERT INTO `RATE_CARD` (`ID`, `CARD_TYPE`, `COMPANY_ID`, `RATE_DATA`, `LOGISTICS_ID`) VALUES
	(1, 1, 2, 1, NULL),
	(2, 1, 12, 1, NULL),
	(3, 2, 3, 1, NULL);
/*!40000 ALTER TABLE `RATE_CARD` ENABLE KEYS */;


-- Dumping structure for table orderbid.TARIFF
CREATE TABLE IF NOT EXISTS `TARIFF` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CHARGE` decimal(19,2) DEFAULT NULL,
  `PINCODE` int(11) DEFAULT NULL,
  `RATE_CARD_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_jx9vdvlhqmd9mjuha4g9vmdr` (`RATE_CARD_ID`),
  CONSTRAINT `FK_jx9vdvlhqmd9mjuha4g9vmdr` FOREIGN KEY (`RATE_CARD_ID`) REFERENCES `RATE_CARD` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.TARIFF: ~21 rows (approximately)
/*!40000 ALTER TABLE `TARIFF` DISABLE KEYS */;
INSERT INTO `TARIFF` (`ID`, `CHARGE`, `PINCODE`, `RATE_CARD_ID`) VALUES
	(1, 11.00, 411021, 1),
	(2, 12.00, 411022, 1),
	(3, 13.00, 411023, 1),
	(4, 14.00, 411024, 1),
	(5, 15.00, 411025, 1),
	(6, 16.00, 411026, 1),
	(7, 17.00, 411027, 1),
	(8, 18.00, 411028, 1),
	(9, 19.00, 411029, 1),
	(10, 20.00, 411030, 1),
	(11, 21.00, 411031, 1),
	(12, 22.00, 411032, 1),
	(13, 23.00, 411033, 1),
	(14, 24.00, 411034, 1),
	(15, 25.00, 411035, 1),
	(16, 26.00, 411036, 1),
	(17, 27.00, 411037, 1),
	(18, 28.00, 411038, 1),
	(19, 29.00, 411039, 1),
	(20, 30.00, 411040, 1),
	(21, 10.00, 411038, 2);
/*!40000 ALTER TABLE `TARIFF` ENABLE KEYS */;


-- Dumping structure for table orderbid.USER
CREATE TABLE IF NOT EXISTS `USER` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `EMAIL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MOBILE` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `PASSWORD` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TYPE` int(4) DEFAULT '2',
  `USERNAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `COMPANY_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_m68drmr05xgsv4w2b12apnnua` (`COMPANY_ID`),
  CONSTRAINT `FK_beptshh74dsvh9u0g3p57rpbv` FOREIGN KEY (`ID`) REFERENCES `COMPANY` (`ID`),
  CONSTRAINT `FK_m68drmr05xgsv4w2b12apnnua` FOREIGN KEY (`COMPANY_ID`) REFERENCES `COMPANY` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.USER: ~3 rows (approximately)
/*!40000 ALTER TABLE `USER` DISABLE KEYS */;
INSERT INTO `USER` (`ID`, `EMAIL`, `MOBILE`, `PASSWORD`, `TYPE`, `USERNAME`, `COMPANY_ID`) VALUES
	(1, 'admin@orderbd.com', '+917588003251', '098f6bcd4621d373cade4e832627b4f6', 1, 'admin', 1),
	(2, 'bhangale.parag@gmail.com', '9545738883', '098f6bcd4621d373cade4e832627b4f6', 3, 'bhangale', 2),
	(3, 'kunalshinde098@gmail.com', '7588648709', '098f6bcd4621d373cade4e832627b4f6', 3, 'parag', 3);
/*!40000 ALTER TABLE `USER` ENABLE KEYS */;


-- Dumping structure for table orderbid.WAREHOUSE
CREATE TABLE IF NOT EXISTS `WAREHOUSE` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.WAREHOUSE: ~0 rows (approximately)
/*!40000 ALTER TABLE `WAREHOUSE` DISABLE KEYS */;
/*!40000 ALTER TABLE `WAREHOUSE` ENABLE KEYS */;


-- Dumping structure for table orderbid.WATCHES
CREATE TABLE IF NOT EXISTS `WATCHES` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(10) NOT NULL,
  `USER_ID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.WATCHES: ~0 rows (approximately)
/*!40000 ALTER TABLE `WATCHES` DISABLE KEYS */;
/*!40000 ALTER TABLE `WATCHES` ENABLE KEYS */;


-- Dumping structure for table orderbid.WINNERBID
CREATE TABLE IF NOT EXISTS `WINNERBID` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BID_AMOUNT` decimal(19,2) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `BID_TIME` datetime DEFAULT NULL,
  `USER` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ORDER_NO` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table orderbid.WINNERBID: ~0 rows (approximately)
/*!40000 ALTER TABLE `WINNERBID` DISABLE KEYS */;
/*!40000 ALTER TABLE `WINNERBID` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
