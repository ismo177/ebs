-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: ebs
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `units_off/peak` int DEFAULT NULL,
  `units_on/peak` int DEFAULT NULL,
  `read_date` varchar(45) DEFAULT NULL,
  `issue_date` varchar(45) DEFAULT NULL,
  `payment_date` varchar(45) DEFAULT NULL,
  `deadline_date` varchar(45) DEFAULT NULL,
  `month` varchar(45) DEFAULT NULL,
  `offPeakAmount` decimal(8,2) DEFAULT NULL,
  `onPeakAmount` decimal(8,2) DEFAULT NULL,
  `amount` decimal(8,2) DEFAULT NULL,
  `invoice_status` tinyint DEFAULT NULL,
  `customer_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_usersID_idx` (`user_id`),
  KEY `fk_customerID_idx` (`customer_id`),
  CONSTRAINT `fk_customerID` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_usersID` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (79,100,100,'05.01.2024','15.01.2024','04-10-2024 ','25.01.2024','January',17.00,21.00,50.54,1,14,39),(80,0,0,'05.02.2024','15.02.2024','--','25.02.2024','February',0.00,0.00,0.00,0,14,39),(81,0,0,'05.03.2024','15.03.2024','--','25.03.2024','March',0.00,0.00,0.00,0,14,39),(82,0,0,'05.04.2024','15.04.2024','--','25.04.2024','April',0.00,0.00,0.00,0,14,39),(83,0,0,'05.05.2024','15.05.2024','--','25.05.2024','May',0.00,0.00,0.00,0,14,39),(84,0,0,'05.06.2024','15.06.2024','--','25.06.2024','June',0.00,0.00,0.00,0,14,39),(85,0,0,'05.07.2024','15.07.2024','--','25.07.2024','July',0.00,0.00,0.00,0,14,39),(86,0,0,'05.08.2024','15.08.2024','--','25.08.2024','August',0.00,0.00,0.00,0,14,39),(87,0,0,'05.09.2024','15.09.2024','--','25.09.2024','September',0.00,0.00,0.00,0,14,39),(88,0,0,'05.10.2024','15.10.2024','--','25.10.2024','October',0.00,0.00,0.00,0,14,39),(89,0,0,'05.11.2024','15.11.2024','--','25.11.2024','November',0.00,0.00,0.00,0,14,39),(90,0,0,'05.12.2024','15.12.2024','--','25.12.2024','December',0.00,0.00,0.00,0,14,39);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-05 12:35:23
