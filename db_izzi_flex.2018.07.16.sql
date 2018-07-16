-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: db_izzi_flex
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `accountId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `productId` varchar(10) DEFAULT NULL,
  `deviceIMEI` varchar(15) DEFAULT NULL,
  `installationOrder` varchar(10) DEFAULT NULL,
  `installationOrderDate` date DEFAULT NULL,
  `activationDate` date DEFAULT NULL,
  PRIMARY KEY (`accountId`),
  KEY `account_userId_idx` (`userId`),
  KEY `account_productId_idx` (`productId`),
  CONSTRAINT `account_productId` FOREIGN KEY (`productId`) REFERENCES `product` (`productid`),
  CONSTRAINT `account_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `delivery_address`
--

DROP TABLE IF EXISTS `delivery_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `delivery_address` (
  `deliveryAddressId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `streetName` varchar(60) DEFAULT NULL,
  `streetNumber` varchar(8) DEFAULT NULL,
  `interiorNumber` varchar(8) DEFAULT NULL,
  `blockName` varchar(40) DEFAULT NULL,
  `zipCode` int(11) DEFAULT NULL,
  `cityName` varchar(50) DEFAULT NULL,
  `deliveryRef` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`deliveryAddressId`),
  KEY `delivery_address_userId_idx` (`userId`),
  CONSTRAINT `delivery_address_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice_address`
--

DROP TABLE IF EXISTS `invoice_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `invoice_address` (
  `serviceAddressId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `streetName` varchar(60) DEFAULT NULL,
  `streetNumber` varchar(8) DEFAULT NULL,
  `interiorNumber` varchar(8) DEFAULT NULL,
  `blockName` varchar(40) DEFAULT NULL,
  `zipCode` int(11) DEFAULT NULL,
  `cityName` varchar(50) DEFAULT NULL,
  `rfc` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`serviceAddressId`),
  KEY `invoice_address_userId_idx` (`userId`),
  CONSTRAINT `invoice_address_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `log_registry`
--

DROP TABLE IF EXISTS `log_registry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `log_registry` (
  `logRegistryId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `operationId` int(11) DEFAULT NULL,
  `registryDate` timestamp(6) NULL DEFAULT NULL,
  `responseCode` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`logRegistryId`),
  KEY `userId_idx` (`userId`),
  KEY `operationId_idx` (`operationId`),
  CONSTRAINT `log_registry_operationId` FOREIGN KEY (`operationId`) REFERENCES `operation` (`operationid`),
  CONSTRAINT `log_registry_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `notification` (
  `notificationId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `notificationType` varchar(20) DEFAULT NULL,
  `notificationMessage` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`notificationId`),
  KEY `userId_idx` (`userId`),
  CONSTRAINT `notification_userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operation`
--

DROP TABLE IF EXISTS `operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `operation` (
  `operationId` int(11) NOT NULL,
  `operationName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`operationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `product` (
  `productId` varchar(10) NOT NULL,
  `productType` varchar(5) DEFAULT NULL,
  `productName` varchar(20) DEFAULT NULL,
  `productDescription` varchar(200) DEFAULT NULL,
  `productMB` int(11) DEFAULT NULL,
  `productCost` float DEFAULT NULL,
  `productStatus` int(11) DEFAULT NULL,
  `productPriority` int(11) DEFAULT NULL,
  PRIMARY KEY (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `service_address`
--

DROP TABLE IF EXISTS `service_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `service_address` (
  `serviceAddressId` int(11) NOT NULL,
  `accountId` int(11) DEFAULT NULL,
  `streetName` varchar(60) DEFAULT NULL,
  `streetNumber` varchar(8) DEFAULT NULL,
  `zipCode` int(11) DEFAULT NULL,
  `cityName` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`serviceAddressId`),
  KEY `service_address_accountId_idx` (`accountId`),
  CONSTRAINT `service_address_accountId` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `token` (
  `tokenId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `token` varchar(50) DEFAULT NULL,
  `tokenType` int(11) DEFAULT NULL,
  PRIMARY KEY (`tokenId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `username` varchar(40) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `deviceToken` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-16 11:47:28
