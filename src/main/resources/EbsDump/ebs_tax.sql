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
-- Table structure for table `tax`
--

DROP TABLE IF EXISTS `tax`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax` (
  `id` int NOT NULL,
  `meter_loc` varchar(45) DEFAULT NULL,
  `meter_type` varchar(45) DEFAULT NULL,
  `phase_code` int DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `meter_rent` decimal(4,2) DEFAULT NULL,
  `service_rent` decimal(4,2) DEFAULT NULL,
  `tier_rate` decimal(4,2) DEFAULT NULL,
  `off_peak_price` decimal(4,2) DEFAULT NULL,
  `on_peak_price` decimal(4,2) DEFAULT NULL,
  `PDVTax` decimal(4,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax`
--

LOCK TABLES `tax` WRITE;
/*!40000 ALTER TABLE `tax` DISABLE KEYS */;
INSERT INTO `tax` VALUES (1,'area1','analog',1,'private',3.00,7.00,0.90,0.17,0.21,0.17),(2,'area1','digital',3,'private',5.00,7.00,1.10,0.29,0.34,0.17),(3,'area1','analog',1,'legal_entity',4.00,11.00,1.30,NULL,NULL,NULL),(4,'are2','digital',3,'legal_entity',5.00,13.00,1.50,NULL,NULL,NULL);
/*!40000 ALTER TABLE `tax` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-09  0:45:39
