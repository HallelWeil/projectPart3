CREATE DATABASE  IF NOT EXISTS `zelisystem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `zelisystem`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: zelisystem
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `authorizedcustomer`
--

DROP TABLE IF EXISTS `authorizedcustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorizedcustomer` (
  `userName` varchar(45) NOT NULL,
  `cardDetails` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userName`),
  UNIQUE KEY `UserName_UNIQUE` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorizedcustomer`
--

LOCK TABLES `authorizedcustomer` WRITE;
/*!40000 ALTER TABLE `authorizedcustomer` DISABLE KEYS */;
/*!40000 ALTER TABLE `authorizedcustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `branch` (
  `branchName` varchar(45) NOT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`branchName`),
  UNIQUE KEY `branchName_UNIQUE` (`branchName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

LOCK TABLES `branch` WRITE;
/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES ('test','123456');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `complaint`
--

DROP TABLE IF EXISTS `complaint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `complaint` (
  `complaintNumber` int NOT NULL AUTO_INCREMENT,
  `responsibleEmployeeUsername` varchar(45) DEFAULT NULL,
  `complaint` varchar(200) DEFAULT NULL,
  `answer` varchar(200) DEFAULT NULL,
  `compensation` double DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `customerUsername` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`complaintNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `complaint`
--

LOCK TABLES `complaint` WRITE;
/*!40000 ALTER TABLE `complaint` DISABLE KEYS */;
/*!40000 ALTER TABLE `complaint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditdetails`
--

DROP TABLE IF EXISTS `creditdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `creditdetails` (
  `username` varchar(45) NOT NULL,
  `cardInfo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditdetails`
--

LOCK TABLES `creditdetails` WRITE;
/*!40000 ALTER TABLE `creditdetails` DISABLE KEYS */;
INSERT INTO `creditdetails` VALUES ('Or','nice'),('User1','user1card details');
/*!40000 ALTER TABLE `creditdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliverydetails`
--

DROP TABLE IF EXISTS `deliverydetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deliverydetails` (
  `orderNumber` varchar(45) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `comments` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliverydetails`
--

LOCK TABLES `deliverydetails` WRITE;
/*!40000 ALTER TABLE `deliverydetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `deliverydetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `itemID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  `priceRange` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`itemID`),
  UNIQUE KEY `itemID_UNIQUE` (`itemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `orderNumber` int NOT NULL AUTO_INCREMENT,
  `orderDate` datetime DEFAULT NULL,
  `arrivalDate` datetime DEFAULT NULL,
  `homeDelivery` tinyint DEFAULT NULL,
  `branchName` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `customerID` varchar(45) DEFAULT NULL,
  `personalLetter` varchar(200) DEFAULT NULL,
  `orderStatus` varchar(45) DEFAULT NULL,
  `data` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`),
  UNIQUE KEY `orderID_UNIQUE` (`orderNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (13,'2022-05-27 15:24:04','2022-05-27 03:10:00',0,'null',88,'User1','a','WAITING_FOR_CANCELATION_APPROVAL',''),(14,'2022-05-27 15:26:32','2022-05-27 03:05:00',0,'null',159.9,'User1','null','WAITING_FOR_CANCELATION_APPROVAL',''),(15,'2020-12-12 10:00:00','2020-12-12 10:00:00',1,'main',50,'omer','','WAITING_FOR_CANCELATION_APPROVAL','data'),(16,'2020-12-12 10:00:00','2020-12-12 10:00:00',1,'main',50,'omer','','WAITING_FOR_CANCELATION_APPROVAL','data'),(17,'2022-05-27 15:31:30','2022-05-27 02:05:00',0,'null',91,'User1','fh','WAITING_FOR_CANCELATION_APPROVAL',''),(18,'2022-05-27 15:33:37','2022-05-27 03:10:00',0,'null',162.8,'User1','yufii','WAITING_FOR_CANCELATION_APPROVAL',''),(19,'2022-05-27 15:37:34','2022-05-27 03:10:00',0,'null',49,'User1','a','WAITING_FOR_CANCELATION_APPROVAL',''),(20,'2022-05-27 16:26:55','2022-05-27 03:10:00',0,'null',130,'User1','adsf','WAITING_FOR_CANCELATION_APPROVAL',''),(21,'2022-05-27 16:27:29','2022-05-27 03:10:00',0,'null',143,'User1','adsf','WAITING_FOR_CANCELATION_APPROVAL',''),(22,'2022-05-27 17:40:49','2022-05-27 02:05:00',1,'null',26,'User1','null','WAITING_FOR_APPROAVL',''),(23,'2022-05-27 17:42:50','2022-06-04 02:05:00',1,'null',26,'User1','null','WAITING_FOR_APPROAVL',''),(24,'2022-05-27 17:45:40','2022-06-04 02:05:00',1,'null',46,'User1','gfhs','WAITING_FOR_APPROAVL','');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `colors` varchar(45) DEFAULT NULL,
  `image` blob,
  `category` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`productID`),
  UNIQUE KEY `ProductID_UNIQUE` (`productID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'flower',1.9,'nice flower','red',NULL,'congratulationFlowers'),(2,'another flower',2.9,'also nice','blue',NULL,'congratulationFlowers'),(3,'rose',3.9,'very nice','black',NULL,'congratulationFlowers'),(4,'blue flower',4.9,'ugly flower','green',NULL,'singleItems'),(5,'red flower',5.9,'very boring','brown',NULL,'singleItems'),(6,'some shit',66,'yeah','white',NULL,'weddingFlowers'),(7,'idk',11,'really','grey',NULL,'weddingFlowers'),(8,'flower5',23,'5','red',NULL,'birthdayFlowers'),(9,'flower6',65,'6','blue',NULL,'birthdayFlowers'),(10,'flower55',6,'ok','green',NULL,'weddingFlowers'),(11,'flower12',13,'nice','red',NULL,'birthdayFlowers'),(12,'flower 6',31,'very nice','blue',NULL,'babyFlowers'),(13,'black flower',31,'wow','green',NULL,'babyFlowers'),(14,'red and blue flower',23,'thats  cool','red',NULL,'babyFlowers'),(15,'flower8',2,'how fun','blue',NULL,'AnniversaryFlowers'),(16,'flower9',2,'nice2','green',NULL,'AnniversaryFlowers'),(17,'flower10',3,'nice 4','red',NULL,'AnniversaryFlowers'),(18,'flower11',156,'nice 5','blue',NULL,'congratulationFlowers'),(19,'flower13',3,'nice 6','green',NULL,'congratulationFlowers'),(20,'flower14',321,'nice 7','red',NULL,'congratulationFlowers'),(21,'flower15',13,'nice 8','blue',NULL,'birthdayFlowers'),(22,'Sunflower',2.5,'a single sunflower','yellow',NULL,'singleItems'),(23,'brown stick',0.2,'really ugly stick','brown',NULL,'singleItems'),(24,'item1',1,'0','ok',NULL,'singleItems'),(25,'item2',2,'1','red',NULL,'singleItems'),(26,'item3',3,'2','red',NULL,'singleItems'),(27,'item4',4,'3','red',NULL,'singleItems'),(28,'item5',5,'4','red',NULL,'singleItems'),(29,'item6',6,'5','red',NULL,'singleItems');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productinorder`
--

DROP TABLE IF EXISTS `productinorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productinorder` (
  `orderNumber` int DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productinorder`
--

LOCK TABLES `productinorder` WRITE;
/*!40000 ALTER TABLE `productinorder` DISABLE KEYS */;
INSERT INTO `productinorder` VALUES (0,'flower5',23,1,'birthdayFlowers'),(19,'flower5',23,1,'birthdayFlowers'),(19,'flower15',13,1,'birthdayFlowers'),(19,'flower12',13,1,'birthdayFlowers'),(20,'flower6',65,1,'birthdayFlowers'),(20,'flower12',13,2,'birthdayFlowers'),(20,'flower15',13,3,'birthdayFlowers'),(21,'flower6',65,1,'birthdayFlowers'),(21,'flower12',13,2,'birthdayFlowers'),(21,'flower15',13,4,'birthdayFlowers'),(22,'flower15',13,1,'birthdayFlowers'),(22,'flower12',13,1,'birthdayFlowers'),(23,'flower12',13,1,'birthdayFlowers'),(23,'flower15',13,1,'birthdayFlowers'),(24,'flower12',13,1,'birthdayFlowers'),(24,'flower15',13,1,'birthdayFlowers'),(24,'Ronen(Customized)',20,1,'CustomizedProduct');
/*!40000 ALTER TABLE `productinorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `productID` int NOT NULL AUTO_INCREMENT,
  `discount` double DEFAULT NULL,
  `promotionText` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `branchName` varchar(45) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  `year` int DEFAULT NULL,
  `month` int DEFAULT NULL,
  `data` blob,
  PRIMARY KEY (`branchName`),
  UNIQUE KEY `BranchName_UNIQUE` (`branchName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES ('branch','rev',2020,6,_binary 'rO0ABXNyAAl1c2VyLlVzZXIAAAAAAAAAAgIAC1oACWNvbm5lY3RlZEwABmJyYW5jaHQAEkxqYXZhL2xhbmcvU3RyaW5nO0wABWVtYWlscQB+AAFMAAlmaXJzdE5hbWVxAH4AAUwACGxhc3ROYW1lcQB+AAFMAAhwYXNzd29yZHEAfgABTAAIcGVyc29uSURxAH4AAUwAC3Bob25lTnVtYmVycQB+AAFMAAZzdGF0dXN0ABFMdXNlci9Vc2VyU3RhdHVzO0wACHVzZXJUeXBldAAPTHVzZXIvVXNlclR5cGU7TAAIdXNlcm5hbWVxAH4AAXhwAHBwcHBwcHBwcHQABE9tZXI='),('mainBranch','MONTHLY_ORDERS_REPORT',2000,6,_binary 'rO0ABXNyABNyZXBvcnQuT3JkZXJzUmVwb3J0AAAAAAAAAAECAAVEABRhdmFyYWdlTW9udGhseU9yZGVyc0kAC3RvdGFsT3JkZXJzTAAPbW9zdFBvcHVsYXJJdGVtdAASTGphdmEvbGFuZy9TdHJpbmc7TAARb3JkZXJzUGVyQ2F0ZWdvcnl0ABNMamF2YS91dGlsL0hhc2hNYXA7WwAMb3JkZXJzUGVyRGF5dAACW0l4cgANcmVwb3J0LlJlcG9ydAAAAAAAAAABAgAESQAFbW9udGhJAAR5ZWFyTAAKQnJhbmNoTmFtZXEAfgABTAAEdHlwZXQAE0xyZXBvcnQvUmVwb3J0VHlwZTt4cAAAAAYAAAfQdAAKbWFpbkJyYW5jaH5yABFyZXBvcnQuUmVwb3J0VHlwZQAAAAAAAAAAEgAAeHIADmphdmEubGFuZy5FbnVtAAAAAAAAAAASAAB4cHQAFU1PTlRITFlfT1JERVJTX1JFUE9SVAAAAAAAAAAAAAAABXBzcgARamF2YS51dGlsLkhhc2hNYXAFB9rBwxZg0QMAAkYACmxvYWRGYWN0b3JJAAl0aHJlc2hvbGR4cD9AAAAAAAAAdwgAAAAQAAAAAHh1cgACW0lNumAmduqypQIAAHhwAAAAHwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey` (
  `surveyNumber` int NOT NULL AUTO_INCREMENT,
  `q1` varchar(45) DEFAULT NULL,
  `q2` varchar(45) DEFAULT NULL,
  `q3` varchar(45) DEFAULT NULL,
  `q4` varchar(45) DEFAULT NULL,
  `q5` varchar(45) DEFAULT NULL,
  `q6` varchar(45) DEFAULT NULL,
  `a1` int DEFAULT NULL,
  `a2` int DEFAULT NULL,
  `a3` int DEFAULT NULL,
  `a4` int DEFAULT NULL,
  `a5` int DEFAULT NULL,
  `a6` int DEFAULT NULL,
  `participants` int DEFAULT NULL,
  `surveyResult` blob,
  PRIMARY KEY (`surveyNumber`),
  UNIQUE KEY `surveyNumber_UNIQUE` (`surveyNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `userType` varchar(45) DEFAULT NULL,
  `connected` tinyint DEFAULT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phoneNumber` varchar(45) DEFAULT NULL,
  `personID` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `branch` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `UserName_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Avicohen','123456','AuthorizedCustomer',0,'Avi','Cohen','AviCohen@gmail.com','0500000000','1325960','Active',NULL),('Or','Balmas','AuthorizedCustomer',0,'Or','Balmas','Balmas@qw','054','1','Active',NULL),('Ronen','Zeyan','CEO',0,'Ronen','Zeyan','Ronen@gmail.com','123456','123456','Active','all'),('User1','123456','AuthorizedCustomer',0,'Authorized ','Customer ','mail@gmail.com','123456','123456','Active',' '),('User2','123456','BranchManager',0,'Branch ','Manager','mail@gmail.com','123456','123456','Active','test'),('User3','123456','BranchEmployee',0,'Branch ','Emloyee','mail@gmail.com','123456','123456','Active','test'),('User4','123456','CustomerServiceEmloyee',0,'CustomerService ','Emloyee','mail@gmail.com','123456','123456','Active',' '),('User5','123456','MarketingEmployee',0,'Marketing ','Emloyee','mail@gmail.com','123456','123456','Active',' '),('User6','123456','Courier',0,'Courier','Courier','mail@gmail.com','123456','123456','Active',' '),('User7','123456','CEO',0,'CEO','CEO','mail@gmail.com','123456','123456','Active',' ');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-27 17:53:34
