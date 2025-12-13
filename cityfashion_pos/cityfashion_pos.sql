-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: cityfashion_pos
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `barcodes`
--

DROP TABLE IF EXISTS `barcodes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `barcodes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `product_code` varchar(255) NOT NULL,
  `num_labels` int NOT NULL,
  `header` varchar(255) DEFAULT NULL,
  `line1` varchar(255) DEFAULT NULL,
  `line2` varchar(255) DEFAULT NULL,
  `printer_type` varchar(100) DEFAULT NULL,
  `size_option` varchar(100) DEFAULT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_code` (`product_code`),
  UNIQUE KEY `UK_14bfwbpeiitea0to2v2hhm917` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barcodes`
--

LOCK TABLES `barcodes` WRITE;
/*!40000 ALTER TABLE `barcodes` DISABLE KEYS */;
INSERT INTO `barcodes` VALUES (3,'Jeans 1','27185420758',25,'CityFashion','Jeans (5-6 Years)','Sale Price: ₹ 450','regular','65','2025-10-15 05:43:56','2025-10-15 05:43:56'),(4,'Jeans 1','29060993606',25,'CityFashion','Jeans 5-6 Yrs ','Sale Price: ₹250','regular','65','2025-10-20 11:53:55','2025-10-20 11:53:55');
/*!40000 ALTER TABLE `barcodes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_t8o6pivur7nn124jehx7cygw5` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` text,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'sohaib_1234','7030228603','sohaib.rahman65@gmail.com','Blk. No. 528/1055, Opp. Shubhlaxmi Apt. Ambika Nagar, Blah Blah, Blah Blah Ulhasnagar - 421004','2025-04-27 15:38:36'),(3,'dheeraj','8381023878','dheeraj@xyz.com','','2025-05-01 18:30:06'),(4,'jannat','07030228604','sohaib.rahman64@gmail.com','Blk. No. 528/1055,, Opp. Shubhlaxmi Apt, Ambika Nagar, Nr. Fatima Church, Ulhasnagar','2025-05-10 10:41:07'),(5,'yasmin','07030228605','yasmin@gmail.com','Blk. No. 528/1055,, Opp. Shubhlaxmi Apt, Ambika Nagar, Nr. Fatima Church, Ulhasnagar','2025-05-17 14:36:27'),(6,'Humera','8381023879','humera@gmail.com','Blk. No. 528/1055,, Opp. Shubhlaxmi Apt, Ambika Nagar, Nr. Fatima Church, Ulhasnagar','2025-06-01 16:05:02'),(7,'Sameer','9022645079','sameer.khan456@gmail.com','Blk. No. 528/1055,\nOpp. Shubhlaxmi Apt, Ambika Nagar,','2025-06-19 11:00:53'),(8,'Jasmine','9860234586','jasmine@yahoo.com','Blk. No. 528/1055,\nOpp. Shubhlaxmi Apt, Ambika Nagar,','2025-07-03 13:24:45'),(50,'Humera','8381023879',NULL,NULL,'2025-09-30 02:25:58'),(51,'Humera','8381023879',NULL,NULL,'2025-09-30 02:43:44'),(52,'Humera','8381023879',NULL,NULL,'2025-09-30 03:03:00'),(53,'yasmin','07030228605',NULL,NULL,'2025-10-03 05:30:55'),(54,'Sameer','9022645079',NULL,NULL,'2025-10-05 08:03:39'),(55,'Sameer','9022645079',NULL,NULL,'2025-10-05 08:23:06'),(56,'Sameer','9022645079',NULL,NULL,'2025-10-05 08:28:18'),(57,'Sameer','9022645079',NULL,NULL,'2025-10-05 08:41:36'),(58,'Sameer','9022645079',NULL,NULL,'2025-10-05 13:00:29'),(59,'Jasmine','9860234586',NULL,NULL,'2025-10-05 23:19:23'),(62,'Jasmine','9860234586',NULL,NULL,'2025-10-06 00:48:03'),(63,'jannat','07030228604',NULL,NULL,'2025-10-06 01:15:16'),(66,'dheeraj','8381023878',NULL,NULL,'2025-10-06 01:51:30'),(68,'dheeraj','8381023878',NULL,NULL,'2025-10-06 02:12:59'),(69,'dheeraj','8381023878',NULL,NULL,'2025-10-06 02:15:33'),(70,'dheeraj','8381023878',NULL,NULL,'2025-10-06 03:50:37'),(71,'Sameer','9022645079',NULL,NULL,'2025-10-06 04:26:57'),(72,'sohaib_1234','7030228603',NULL,NULL,'2025-10-06 04:34:40'),(73,'dheeraj','8381023878',NULL,NULL,'2025-10-06 04:45:27'),(77,'Jasmine','9860234586',NULL,NULL,'2025-10-06 05:09:11'),(78,'dheeraj','8381023878',NULL,NULL,'2025-10-06 05:21:47'),(79,'dheeraj','8381023878',NULL,NULL,'2025-10-07 03:20:31'),(80,'dheeraj','8381023878',NULL,NULL,'2025-10-07 03:23:34'),(81,'sohaib_1234','7030228603',NULL,NULL,'2025-10-07 08:05:52'),(82,'dheeraj','8381023878',NULL,NULL,'2025-10-08 01:14:31'),(83,'sohaib_1234','7030228603',NULL,NULL,'2025-10-08 01:28:20'),(84,'dheeraj','8381023878',NULL,NULL,'2025-10-08 07:44:00'),(85,'dheeraj','8381023878',NULL,NULL,'2025-10-08 09:06:09'),(86,'dheeraj','8381023878',NULL,NULL,'2025-10-10 13:13:04'),(87,'dheeraj','8381023878',NULL,NULL,'2025-10-11 00:17:05'),(88,'dheeraj','8381023878',NULL,NULL,'2025-10-11 02:12:20'),(89,'dheeraj','8381023878',NULL,NULL,'2025-10-11 02:31:33'),(90,'dheeraj','8381023878',NULL,NULL,'2025-10-11 02:34:24'),(91,'dheeraj','8381023878',NULL,NULL,'2025-10-11 02:53:31'),(92,'dheeraj','8381023878',NULL,NULL,'2025-10-13 02:38:07'),(93,'dheeraj','8381023878',NULL,NULL,'2025-10-15 00:10:43'),(94,'Monish','9923536215',NULL,NULL,'2025-10-16 05:45:07'),(95,'Monish','9923536215',NULL,NULL,'2025-10-16 06:04:37'),(96,'Monish','9923536215',NULL,NULL,'2025-10-16 06:07:28'),(97,'Monish','9923536215',NULL,NULL,'2025-10-16 07:49:57'),(98,'Monish','9923536215',NULL,NULL,'2025-10-16 08:06:42'),(99,'Monish','9923536215',NULL,NULL,'2025-10-17 01:39:27'),(100,'Monish','9923536215',NULL,NULL,'2025-10-17 01:49:13'),(101,'Monish','9923536215',NULL,NULL,'2025-10-17 13:52:48'),(102,'Monish','9923536215',NULL,NULL,'2025-10-17 15:08:22'),(103,'Monish','9923536215',NULL,NULL,'2025-10-17 15:23:47'),(104,'Monish','9923536215',NULL,NULL,'2025-10-18 01:56:53'),(105,'Monish','9923536215',NULL,NULL,'2025-10-18 02:52:05'),(106,'Monish','9923536215',NULL,NULL,'2025-10-18 03:08:59'),(107,'Monish','9923536215',NULL,NULL,'2025-10-18 03:11:59'),(108,'Monish','9923536215',NULL,NULL,'2025-10-18 03:23:10'),(109,'Monish','9923536215',NULL,NULL,'2025-10-18 03:24:41'),(110,'Monish','9923536215',NULL,NULL,'2025-10-18 03:28:14'),(111,'Monish','9923536215',NULL,NULL,'2025-10-18 03:32:19'),(112,'Sameer Khan','8381023879',NULL,NULL,'2025-10-18 06:53:05'),(113,'Sohaib','7030228602',NULL,NULL,'2025-10-18 06:56:16'),(114,'Monish','9923536215',NULL,NULL,'2025-10-20 06:18:40'),(115,'Shree Krishna Clothing and Garments Shop','9870253518',NULL,NULL,'2025-10-22 03:41:34'),(116,'Shree Krishna Clothing and Garments Shop','9870253518',NULL,NULL,'2025-10-26 03:56:07'),(117,'Sohaib','7030228602',NULL,NULL,'2025-10-26 04:39:46'),(118,'Sohaib','7030228602',NULL,NULL,'2025-10-26 04:58:09'),(119,'Monish','9923536215',NULL,NULL,'2025-10-26 05:32:41'),(120,'Monish','9923536215',NULL,NULL,'2025-10-26 05:50:59'),(121,'Sameer Khan','8381023879',NULL,NULL,'2025-10-26 05:55:07'),(122,'Shree Krishna Clothing and Garments Shop','9870253518',NULL,NULL,'2025-10-26 07:51:57'),(123,'Shree Krishna Clothing and Garments Shop','9870253518',NULL,NULL,'2025-10-26 08:03:33');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `daily_sales_summary`
--

DROP TABLE IF EXISTS `daily_sales_summary`;
/*!50001 DROP VIEW IF EXISTS `daily_sales_summary`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `daily_sales_summary` AS SELECT 
 1 AS `transaction_date`,
 1 AS `total_transactions`,
 1 AS `total_sales_amount`,
 1 AS `total_received_amount`,
 1 AS `total_balance_amount`,
 1 AS `total_tax_amount`,
 1 AS `total_discount_amount`,
 1 AS `total_net_amount`,
 1 AS `average_transaction_value`,
 1 AS `total_items_sold`,
 1 AS `paid_transactions`,
 1 AS `partial_transactions`,
 1 AS `unpaid_transactions`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `discount_type`
--

DROP TABLE IF EXISTS `discount_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discount_type` varchar(100) NOT NULL,
  `discount_type_code` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount_type`
--

LOCK TABLES `discount_type` WRITE;
/*!40000 ALTER TABLE `discount_type` DISABLE KEYS */;
INSERT INTO `discount_type` VALUES (1,'Percentage','PERCENTAGE','2025-10-21 21:26:20','2025-10-21 21:26:20'),(2,'Amount','AMOUNT','2025-10-21 21:26:37','2025-10-21 21:26:37');
/*!40000 ALTER TABLE `discount_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estimate_quotation_transactions`
--

DROP TABLE IF EXISTS `estimate_quotation_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estimate_quotation_transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_number` varchar(255) NOT NULL,
  `estimate_quotation_id` varchar(255) DEFAULT NULL,
  `estimate_quotation_number` varchar(255) DEFAULT NULL,
  `party_id` varchar(255) DEFAULT NULL,
  `party_name` varchar(255) DEFAULT NULL,
  `transaction_date` varchar(255) NOT NULL,
  `transaction_time` time NOT NULL,
  `total_amount` decimal(15,2) NOT NULL,
  `tax_amount` decimal(15,2) NOT NULL,
  `discount_amount` decimal(15,2) NOT NULL,
  `item_count` int NOT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `total_quantity` decimal(10,2) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `balance_amount` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ax6io5pi4dtynmy94dd33c8e9` (`transaction_number`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estimate_quotation_transactions`
--

LOCK TABLES `estimate_quotation_transactions` WRITE;
/*!40000 ALTER TABLE `estimate_quotation_transactions` DISABLE KEYS */;
INSERT INTO `estimate_quotation_transactions` VALUES (5,'EQT-00001','23','EQ-00023','5','Monish','2025-12-06','11:54:46',3324.00,124.43,157.01,4,'Estimates Quotation Data | Quotation: EQ-00023','OPEN',4.00,'2025-12-06 06:25:15.225377','SYSTEM','2025-12-06 06:25:15.229400','system',3324.00),(6,'EQT-00006','24','EQ-00024','6','Sameer Khan','2025-12-06','13:50:43',3290.00,102.00,110.00,4,'Estimates Quotation Data | Quotation: EQ-00024','OPEN',4.00,'2025-12-06 08:20:43.539041','SYSTEM','2025-12-06 08:20:43.540040','system',3290.00),(7,'EQT-00007','25','EQ-00025','5','Monish','2025-12-06','14:00:28',3190.00,100.41,156.99,4,'Estimates Quotation Data | Quotation: EQ-00025','OPEN',4.00,'2025-12-06 08:30:29.475420','SYSTEM','2025-12-06 08:30:29.477439','system',3190.00),(8,'EQT-00008','26','EQ-00026','6','Sameer Khan','2025-12-06','14:01:51',3324.00,124.43,157.01,4,'Estimates Quotation Data | Quotation: EQ-00026','OPEN',4.00,'2025-12-06 08:31:51.548930','SYSTEM','2025-12-06 08:31:51.548930','system',3324.00);
/*!40000 ALTER TABLE `estimate_quotation_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gst_type`
--

DROP TABLE IF EXISTS `gst_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gst_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gst_type` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gst_type`
--

LOCK TABLES `gst_type` WRITE;
/*!40000 ALTER TABLE `gst_type` DISABLE KEYS */;
INSERT INTO `gst_type` VALUES (1,'Unregistered/Consumer','2025-10-14 16:14:02',_binary ''),(2,'Registered','2025-10-14 16:14:02',_binary ''),(3,'Registered - Composition','2025-10-14 16:14:02',_binary '');
/*!40000 ALTER TABLE `gst_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1),(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq2yge7ebtfuvwufr6lwfwqy9l` (`product_id`),
  CONSTRAINT `FKq2yge7ebtfuvwufr6lwfwqy9l` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `inventory_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (3,11,1),(4,12,1),(5,13,2),(6,29,3),(7,30,4),(8,34,16),(9,35,14),(10,36,19),(11,37,26),(12,38,24),(13,39,18),(14,40,26),(15,41,26),(16,42,12),(17,43,21),(18,44,12),(19,45,20),(20,46,20),(21,47,14),(22,48,24),(23,49,20),(24,50,10),(25,51,26),(26,52,16),(27,53,24),(28,54,46),(29,55,14),(30,56,28),(31,57,28),(32,58,26),(33,59,20),(34,60,18),(35,61,26),(36,62,28),(37,63,14),(38,64,12),(39,65,30),(40,66,30),(41,67,26),(42,68,16),(43,69,14),(44,70,30),(45,71,14),(46,72,14),(47,73,20),(48,74,18),(49,75,30),(50,76,18),(51,77,22),(52,78,12),(53,79,24),(54,80,14),(55,81,24),(56,82,18),(57,83,30),(58,84,14),(59,85,14),(60,86,30),(61,87,16),(62,88,12),(63,89,26),(64,90,14),(65,91,14),(66,92,16),(67,93,26),(68,94,14),(69,95,16),(70,96,24),(71,97,22),(72,98,18),(73,99,26),(74,100,24),(75,101,10),(76,102,12),(77,103,14),(78,104,16),(79,105,14),(80,106,30),(81,107,20),(82,108,16),(83,109,16),(84,110,10),(85,111,22),(86,112,24),(87,113,18),(88,114,30),(89,115,22),(90,116,10),(91,117,14),(92,118,18),(93,119,14),(94,120,18),(95,121,20),(96,122,38),(97,123,46),(98,124,46),(99,125,40),(100,126,34),(101,127,14),(102,128,10),(103,129,12),(104,130,30),(105,131,12),(106,132,28),(107,133,26),(108,134,26),(109,135,24),(110,136,28),(111,137,28),(112,138,28),(113,139,30),(114,140,24),(115,141,30),(116,142,18),(117,143,10),(118,144,12),(119,145,10),(120,146,18),(121,147,28),(122,148,30),(123,149,18),(124,150,22),(125,151,22),(126,152,10),(127,153,18),(128,154,14),(129,155,10),(130,156,18),(131,157,22),(132,158,22),(133,159,28),(134,160,10),(135,161,30),(136,162,30),(137,163,30),(138,164,30),(139,165,26),(140,166,28),(141,167,18),(142,168,16),(143,169,10),(144,170,30),(145,171,22),(146,172,16),(147,173,28),(148,174,16),(149,175,16),(150,176,12),(151,177,20),(152,178,12),(153,179,12),(154,180,14),(155,181,24),(156,182,18),(157,183,14),(158,184,14),(159,185,24),(160,186,26),(161,187,20),(162,188,30),(163,189,16),(164,190,24),(165,191,10),(166,192,22),(167,193,14),(168,194,22),(169,195,30),(170,196,22),(171,197,14),(172,198,22),(173,199,10),(174,200,28),(175,201,10),(176,202,14),(177,203,24),(178,204,20),(179,205,10),(180,206,14),(181,207,26),(182,208,16),(183,209,16),(184,210,16),(185,211,16),(186,212,26),(187,213,22),(188,214,24),(189,215,18),(190,216,10),(191,217,28),(192,218,18),(193,219,14),(194,220,18),(195,221,30),(196,222,16),(197,223,12),(198,224,24),(199,225,24),(200,226,18),(201,227,28),(202,228,10),(203,229,20),(204,230,22),(205,231,12),(206,232,20),(207,233,28),(208,234,30),(209,235,12),(210,236,10),(211,237,30),(212,238,12),(213,239,18),(214,240,10),(215,241,28),(216,242,28),(217,243,20),(218,244,22),(219,245,12),(220,246,10),(221,247,14),(222,248,10),(223,249,16),(224,250,20),(225,251,28),(226,252,28),(227,253,24),(228,254,12),(229,255,14),(230,256,22),(231,257,16),(232,258,14),(233,259,28),(234,260,22),(235,261,18),(236,262,12),(237,264,10),(238,263,8),(239,266,4),(240,267,5),(241,268,5);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_movement_type`
--

DROP TABLE IF EXISTS `inventory_movement_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_movement_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movement_type` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `movement_type` (`movement_type`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_movement_type`
--

LOCK TABLES `inventory_movement_type` WRITE;
/*!40000 ALTER TABLE `inventory_movement_type` DISABLE KEYS */;
INSERT INTO `inventory_movement_type` VALUES (4,'ADJUSTMENT'),(1,'PURCHASE'),(3,'RETURN'),(2,'SALE');
/*!40000 ALTER TABLE `inventory_movement_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory_movements`
--

DROP TABLE IF EXISTS `inventory_movements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_movements` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `quantity_change` int NOT NULL,
  `movement_type_id` int NOT NULL,
  `reference_id` varchar(255) DEFAULT NULL,
  `movement_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKshwxoaa83ek8k7yvawgh8ibkk` (`movement_type_id`),
  KEY `FK7lws1ve8g6b9itc054wj06uit` (`product_id`),
  CONSTRAINT `FK7lws1ve8g6b9itc054wj06uit` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `FKshwxoaa83ek8k7yvawgh8ibkk` FOREIGN KEY (`movement_type_id`) REFERENCES `inventory_movement_type` (`id`),
  CONSTRAINT `inventory_movements_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `inventory_movements_ibfk_2` FOREIGN KEY (`movement_type_id`) REFERENCES `inventory_movement_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_movements`
--

LOCK TABLES `inventory_movements` WRITE;
/*!40000 ALTER TABLE `inventory_movements` DISABLE KEYS */;
INSERT INTO `inventory_movements` VALUES (1,34,8,1,NULL,'2025-07-03 00:00:00'),(2,35,7,1,NULL,'2025-07-03 00:00:00'),(3,36,11,1,NULL,'2025-07-03 00:00:00'),(4,37,13,1,NULL,'2025-07-03 00:00:00'),(5,38,12,1,NULL,'2025-07-03 00:00:00'),(6,39,9,1,NULL,'2025-07-03 00:00:00'),(7,40,13,1,NULL,'2025-07-03 00:00:00'),(8,41,13,1,NULL,'2025-07-03 00:00:00'),(9,42,6,1,NULL,'2025-07-03 00:00:00'),(10,43,12,1,NULL,'2025-07-03 00:00:00'),(11,44,6,1,NULL,'2025-07-03 00:00:00'),(12,45,10,1,NULL,'2025-07-03 00:00:00'),(13,46,10,1,NULL,'2025-07-03 00:00:00'),(14,47,7,1,NULL,'2025-07-03 00:00:00'),(15,48,12,1,NULL,'2025-07-03 00:00:00'),(16,49,10,1,NULL,'2025-07-03 00:00:00'),(17,50,5,1,NULL,'2025-07-03 00:00:00'),(18,51,13,1,NULL,'2025-07-03 00:00:00'),(19,52,8,1,NULL,'2025-07-03 00:00:00'),(20,53,12,1,NULL,'2025-07-03 00:00:00'),(21,54,10,1,NULL,'2025-07-03 00:00:00'),(22,55,7,1,NULL,'2025-07-03 00:00:00'),(23,56,14,1,NULL,'2025-07-03 00:00:00'),(24,57,14,1,NULL,'2025-07-03 00:00:00'),(25,58,13,1,NULL,'2025-07-03 00:00:00'),(26,59,10,1,NULL,'2025-07-03 00:00:00'),(27,60,9,1,NULL,'2025-07-03 00:00:00'),(28,61,13,1,NULL,'2025-07-03 00:00:00'),(29,62,14,1,NULL,'2025-07-03 00:00:00'),(30,63,7,1,NULL,'2025-07-03 00:00:00'),(31,64,6,1,NULL,'2025-07-03 00:00:00'),(32,54,13,1,NULL,'2025-07-03 00:00:00'),(33,65,15,1,NULL,'2025-07-03 00:00:00'),(34,66,15,1,NULL,'2025-07-03 00:00:00'),(35,67,13,1,NULL,'2025-07-03 00:00:00'),(36,68,8,1,NULL,'2025-07-03 00:00:00'),(37,69,7,1,NULL,'2025-07-03 00:00:00'),(38,70,15,1,NULL,'2025-07-03 00:00:00'),(39,71,7,1,NULL,'2025-07-03 00:00:00'),(40,72,7,1,NULL,'2025-07-03 00:00:00'),(41,73,10,1,NULL,'2025-07-03 00:00:00'),(42,74,9,1,NULL,'2025-07-03 00:00:00'),(43,75,15,1,NULL,'2025-07-03 00:00:00'),(44,76,9,1,NULL,'2025-07-03 00:00:00'),(45,77,11,1,NULL,'2025-07-03 00:00:00'),(46,78,6,1,NULL,'2025-07-03 00:00:00'),(47,79,12,1,NULL,'2025-07-03 00:00:00'),(48,80,7,1,NULL,'2025-07-03 00:00:00'),(49,81,12,1,NULL,'2025-07-03 00:00:00'),(50,82,9,1,NULL,'2025-07-03 00:00:00'),(51,83,15,1,NULL,'2025-07-03 00:00:00'),(52,84,9,1,NULL,'2025-07-03 00:00:00'),(53,85,7,1,NULL,'2025-07-03 00:00:00'),(54,86,15,1,NULL,'2025-07-03 00:00:00'),(55,87,8,1,NULL,'2025-07-03 00:00:00'),(56,88,6,1,NULL,'2025-07-03 00:00:00'),(57,89,13,1,NULL,'2025-07-03 00:00:00'),(58,90,7,1,NULL,'2025-07-03 00:00:00'),(59,91,7,1,NULL,'2025-07-03 00:00:00'),(60,92,8,1,NULL,'2025-07-03 00:00:00'),(61,93,13,1,NULL,'2025-07-03 00:00:00'),(62,94,7,1,NULL,'2025-07-03 00:00:00'),(63,95,8,1,NULL,'2025-07-03 00:00:00'),(64,96,12,1,NULL,'2025-07-03 00:00:00'),(65,97,11,1,NULL,'2025-07-03 00:00:00'),(66,98,9,1,NULL,'2025-07-03 00:00:00'),(67,99,13,1,NULL,'2025-07-03 00:00:00'),(68,100,12,1,NULL,'2025-07-03 00:00:00'),(69,101,5,1,NULL,'2025-07-03 00:00:00'),(70,102,6,1,NULL,'2025-07-03 00:00:00'),(71,103,7,1,NULL,'2025-07-03 00:00:00'),(72,104,8,1,NULL,'2025-07-03 00:00:00'),(73,105,7,1,NULL,'2025-07-03 00:00:00'),(74,106,15,1,NULL,'2025-07-03 00:00:00'),(75,107,10,1,NULL,'2025-07-03 00:00:00'),(76,108,8,1,NULL,'2025-07-03 00:00:00'),(77,109,8,1,NULL,'2025-07-03 00:00:00'),(78,110,5,1,NULL,'2025-07-03 00:00:00'),(79,111,11,1,NULL,'2025-07-03 00:00:00'),(80,112,12,1,NULL,'2025-07-03 00:00:00'),(81,113,9,1,NULL,'2025-07-03 00:00:00'),(82,114,15,1,NULL,'2025-07-03 00:00:00'),(83,115,11,1,NULL,'2025-07-03 00:00:00'),(84,116,5,1,NULL,'2025-07-03 00:00:00'),(85,117,7,1,NULL,'2025-07-03 00:00:00'),(86,118,9,1,NULL,'2025-07-03 00:00:00'),(87,119,7,1,NULL,'2025-07-03 00:00:00'),(88,120,9,1,NULL,'2025-07-03 00:00:00'),(89,121,10,1,NULL,'2025-07-03 00:00:00'),(90,122,12,1,NULL,'2025-07-03 00:00:00'),(91,123,11,1,NULL,'2025-07-03 00:00:00'),(92,124,11,1,NULL,'2025-07-03 00:00:00'),(93,125,10,1,NULL,'2025-07-03 00:00:00'),(94,126,5,1,NULL,'2025-07-03 00:00:00'),(95,127,7,1,NULL,'2025-07-03 00:00:00'),(96,128,5,1,NULL,'2025-07-03 00:00:00'),(97,129,6,1,NULL,'2025-07-03 00:00:00'),(98,130,15,1,NULL,'2025-07-03 00:00:00'),(99,131,6,1,NULL,'2025-07-03 00:00:00'),(100,132,14,1,NULL,'2025-07-03 00:00:00'),(101,133,13,1,NULL,'2025-07-03 00:00:00'),(102,134,13,1,NULL,'2025-07-03 00:00:00'),(103,135,12,1,NULL,'2025-07-03 00:00:00'),(104,136,14,1,NULL,'2025-07-03 00:00:00'),(105,137,14,1,NULL,'2025-07-03 00:00:00'),(106,138,14,1,NULL,'2025-07-03 00:00:00'),(107,139,15,1,NULL,'2025-07-03 00:00:00'),(108,140,12,1,NULL,'2025-07-03 00:00:00'),(109,141,15,1,NULL,'2025-07-03 00:00:00'),(110,142,9,1,NULL,'2025-07-03 00:00:00'),(111,143,5,1,NULL,'2025-07-03 00:00:00'),(112,144,6,1,NULL,'2025-07-03 00:00:00'),(113,145,5,1,NULL,'2025-07-03 00:00:00'),(114,146,9,1,NULL,'2025-07-03 00:00:00'),(115,147,14,1,NULL,'2025-07-03 00:00:00'),(116,148,15,1,NULL,'2025-07-03 00:00:00'),(117,149,9,1,NULL,'2025-07-03 00:00:00'),(118,150,11,1,NULL,'2025-07-03 00:00:00'),(119,151,11,1,NULL,'2025-07-03 00:00:00'),(120,152,5,1,NULL,'2025-07-03 00:00:00'),(121,153,9,1,NULL,'2025-07-03 00:00:00'),(122,154,7,1,NULL,'2025-07-03 00:00:00'),(123,155,5,1,NULL,'2025-07-03 00:00:00'),(124,156,9,1,NULL,'2025-07-03 00:00:00'),(125,157,11,1,NULL,'2025-07-03 00:00:00'),(126,158,11,1,NULL,'2025-07-03 00:00:00'),(127,159,14,1,NULL,'2025-07-03 00:00:00'),(128,160,5,1,NULL,'2025-07-03 00:00:00'),(129,161,15,1,NULL,'2025-07-03 00:00:00'),(130,122,7,1,NULL,'2025-07-03 00:00:00'),(131,123,12,1,NULL,'2025-07-03 00:00:00'),(132,124,12,1,NULL,'2025-07-03 00:00:00'),(133,125,10,1,NULL,'2025-07-03 00:00:00'),(134,126,12,1,NULL,'2025-07-03 00:00:00'),(135,162,15,1,NULL,'2025-07-03 00:00:00'),(136,163,15,1,NULL,'2025-07-03 00:00:00'),(137,164,15,1,NULL,'2025-07-03 00:00:00'),(138,165,13,1,NULL,'2025-07-03 00:00:00'),(139,166,14,1,NULL,'2025-07-03 00:00:00'),(140,167,9,1,NULL,'2025-07-03 00:00:00'),(141,168,8,1,NULL,'2025-07-03 00:00:00'),(142,169,5,1,NULL,'2025-07-03 00:00:00'),(143,170,15,1,NULL,'2025-07-03 00:00:00'),(144,171,11,1,NULL,'2025-07-03 00:00:00'),(145,172,8,1,NULL,'2025-07-03 00:00:00'),(146,173,14,1,NULL,'2025-07-03 00:00:00'),(147,174,8,1,NULL,'2025-07-03 00:00:00'),(148,175,8,1,NULL,'2025-07-03 00:00:00'),(149,176,6,1,NULL,'2025-07-03 00:00:00'),(150,177,10,1,NULL,'2025-07-03 00:00:00'),(151,178,6,1,NULL,'2025-07-03 00:00:00'),(152,179,6,1,NULL,'2025-07-03 00:00:00'),(153,180,7,1,NULL,'2025-07-03 00:00:00'),(154,181,12,1,NULL,'2025-07-03 00:00:00'),(155,182,9,1,NULL,'2025-07-03 00:00:00'),(156,183,7,1,NULL,'2025-07-03 00:00:00'),(157,184,7,1,NULL,'2025-07-03 00:00:00'),(158,185,12,1,NULL,'2025-07-03 00:00:00'),(159,186,13,1,NULL,'2025-07-03 00:00:00'),(160,187,10,1,NULL,'2025-07-03 00:00:00'),(161,188,15,1,NULL,'2025-07-03 00:00:00'),(162,189,8,1,NULL,'2025-07-03 00:00:00'),(163,190,12,1,NULL,'2025-07-03 00:00:00'),(164,191,5,1,NULL,'2025-07-03 00:00:00'),(165,192,11,1,NULL,'2025-07-03 00:00:00'),(166,193,7,1,NULL,'2025-07-03 00:00:00'),(167,194,11,1,NULL,'2025-07-03 00:00:00'),(168,195,15,1,NULL,'2025-07-03 00:00:00'),(169,196,11,1,NULL,'2025-07-03 00:00:00'),(170,197,7,1,NULL,'2025-07-03 00:00:00'),(171,198,11,1,NULL,'2025-07-03 00:00:00'),(172,199,5,1,NULL,'2025-07-03 00:00:00'),(173,200,14,1,NULL,'2025-07-03 00:00:00'),(174,201,5,1,NULL,'2025-07-03 00:00:00'),(175,202,7,1,NULL,'2025-07-03 00:00:00'),(176,203,12,1,NULL,'2025-07-03 00:00:00'),(177,204,10,1,NULL,'2025-07-03 00:00:00'),(178,205,5,1,NULL,'2025-07-03 00:00:00'),(179,206,7,1,NULL,'2025-07-03 00:00:00'),(180,207,13,1,NULL,'2025-07-03 00:00:00'),(181,208,8,1,NULL,'2025-07-03 00:00:00'),(182,209,8,1,NULL,'2025-07-03 00:00:00'),(183,210,8,1,NULL,'2025-07-03 00:00:00'),(184,211,8,1,NULL,'2025-07-03 00:00:00'),(185,212,13,1,NULL,'2025-07-03 00:00:00'),(186,213,11,1,NULL,'2025-07-03 00:00:00'),(187,214,12,1,NULL,'2025-07-03 00:00:00'),(188,215,9,1,NULL,'2025-07-03 00:00:00'),(189,216,5,1,NULL,'2025-07-03 00:00:00'),(190,217,14,1,NULL,'2025-07-03 00:00:00'),(191,218,9,1,NULL,'2025-07-03 00:00:00'),(192,219,7,1,NULL,'2025-07-03 00:00:00'),(193,220,9,1,NULL,'2025-07-03 00:00:00'),(194,221,15,1,NULL,'2025-07-03 00:00:00'),(195,222,8,1,NULL,'2025-07-03 00:00:00'),(196,223,6,1,NULL,'2025-07-03 00:00:00'),(197,224,12,1,NULL,'2025-07-03 00:00:00'),(198,225,12,1,NULL,'2025-07-03 00:00:00'),(199,226,9,1,NULL,'2025-07-03 00:00:00'),(200,227,14,1,NULL,'2025-07-03 00:00:00'),(201,228,5,1,NULL,'2025-07-03 00:00:00'),(202,229,10,1,NULL,'2025-07-03 00:00:00'),(203,230,11,1,NULL,'2025-07-03 00:00:00'),(204,231,6,1,NULL,'2025-07-03 00:00:00'),(205,232,10,1,NULL,'2025-07-03 00:00:00'),(206,233,14,1,NULL,'2025-07-03 00:00:00'),(207,234,15,1,NULL,'2025-07-03 00:00:00'),(208,235,6,1,NULL,'2025-07-03 00:00:00'),(209,236,5,1,NULL,'2025-07-03 00:00:00'),(210,237,15,1,NULL,'2025-07-03 00:00:00'),(211,238,6,1,NULL,'2025-07-03 00:00:00'),(212,239,9,1,NULL,'2025-07-03 00:00:00'),(213,240,5,1,NULL,'2025-07-03 00:00:00'),(214,241,14,1,NULL,'2025-07-03 00:00:00'),(215,242,14,1,NULL,'2025-07-03 00:00:00'),(216,243,10,1,NULL,'2025-07-03 00:00:00'),(217,244,12,1,NULL,'2025-07-03 00:00:00'),(218,245,6,1,NULL,'2025-07-03 00:00:00'),(219,246,5,1,NULL,'2025-07-03 00:00:00'),(220,247,7,1,NULL,'2025-07-03 00:00:00'),(221,248,5,1,NULL,'2025-07-03 00:00:00'),(222,249,8,1,NULL,'2025-07-03 00:00:00'),(223,250,10,1,NULL,'2025-07-03 00:00:00'),(224,251,14,1,NULL,'2025-07-03 00:00:00'),(225,252,14,1,NULL,'2025-07-03 00:00:00'),(226,253,12,1,NULL,'2025-07-03 00:00:00'),(227,254,6,1,NULL,'2025-07-03 00:00:00'),(228,255,7,1,NULL,'2025-07-03 00:00:00'),(229,256,11,1,NULL,'2025-07-03 00:00:00'),(230,257,8,1,NULL,'2025-07-03 00:00:00'),(231,258,7,1,NULL,'2025-07-03 00:00:00'),(232,259,14,1,NULL,'2025-07-03 00:00:00'),(233,260,11,1,NULL,'2025-07-03 00:00:00'),(234,261,9,1,NULL,'2025-07-03 00:00:00'),(235,262,6,1,NULL,'2025-07-03 00:00:00'),(236,84,2,2,'Invoice#CFK-00020','2025-07-03 00:00:00'),(237,244,2,2,'Invoice#CFK-00020','2025-07-03 00:00:00'),(238,36,3,2,'Invoice#CFK-00022','2025-07-22 00:00:00'),(239,43,3,2,'Invoice#CFK-00022','2025-07-22 00:00:00');
/*!40000 ALTER TABLE `inventory_movements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_payments`
--

DROP TABLE IF EXISTS `invoice_payments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice_payments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoice_id` int DEFAULT NULL,
  `payment_mode_id` int DEFAULT NULL,
  `amount_paid` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKloiu1qttty00h1cfrikjs99u7` (`invoice_id`),
  KEY `FKiev86aw0lkftuqks9oc7cnw73` (`payment_mode_id`),
  CONSTRAINT `FKiev86aw0lkftuqks9oc7cnw73` FOREIGN KEY (`payment_mode_id`) REFERENCES `payment_modes` (`id`),
  CONSTRAINT `FKloiu1qttty00h1cfrikjs99u7` FOREIGN KEY (`invoice_id`) REFERENCES `sales_invoice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_payments`
--

LOCK TABLES `invoice_payments` WRITE;
/*!40000 ALTER TABLE `invoice_payments` DISABLE KEYS */;
INSERT INTO `invoice_payments` VALUES (1,1,1,949.00),(2,1,1,949.00),(3,2,1,978.00),(4,3,1,950.00),(5,4,2,1897.00),(6,5,1,1081.00),(7,6,1,1643.00),(8,7,1,1028.00),(9,8,3,1000.00),(10,8,1,643.00),(11,9,3,1089.00),(12,9,1,1000.00),(13,10,3,495.00),(14,10,1,1000.00),(15,11,3,500.00),(16,11,1,1143.00),(17,12,3,500.00),(18,12,1,994.00),(19,13,1,524.00),(20,14,1,524.00),(21,15,1,978.00),(22,16,1,524.00),(23,17,1,1081.00),(24,18,1,2194.00),(25,19,1,1953.00),(27,21,1,1467.00),(28,22,1,3244.00);
/*!40000 ALTER TABLE `invoice_payments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_transactions`
--

DROP TABLE IF EXISTS `item_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item_transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_id` bigint NOT NULL,
  `transaction_type` enum('SALE','PURCHASE','STOCK_ADJUSTMENT','STOCK_TRANSFER','RETURN','DAMAGE','EXPIRY') NOT NULL,
  `reference_number` varchar(100) DEFAULT NULL,
  `reference_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `quantity` decimal(10,2) NOT NULL,
  `unit_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `total_amount` decimal(12,2) NOT NULL,
  `description` text,
  `transaction_date` datetime NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `notes` varchar(1000) DEFAULT NULL,
  `reference_id` bigint DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `party_id` bigint DEFAULT NULL,
  `party_name` varchar(1000) DEFAULT NULL,
  `updated_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'SYSTEM',
  PRIMARY KEY (`id`),
  KEY `idx_item_id` (`item_id`),
  KEY `idx_transaction_type` (`transaction_type`),
  KEY `idx_transaction_date` (`transaction_date`),
  KEY `idx_reference_number` (`reference_number`),
  KEY `fk_item_transaction_party` (`party_id`),
  CONSTRAINT `FK2v5dgb6m6c89k9uv4gtmh639m` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`),
  CONSTRAINT `fk_item_transaction_party` FOREIGN KEY (`party_id`) REFERENCES `parties` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `item_transactions_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_transactions`
--

LOCK TABLES `item_transactions` WRITE;
/*!40000 ALTER TABLE `item_transactions` DISABLE KEYS */;
INSERT INTO `item_transactions` VALUES (22,1,'SALE','RS-00001','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-26 13:07:36','SYSTEM','2025-10-26 07:37:36',NULL,'Invoice: RS-00001, Customer: Sameer Khan',114,'COMPLETED',6,'Sameer Khan',NULL),(23,4,'SALE','RS-00001','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-10-26 13:07:36','SYSTEM','2025-10-26 07:37:36',NULL,'Invoice: RS-00001, Customer: Sameer Khan',114,'COMPLETED',6,'Sameer Khan',NULL),(24,5,'SALE','RS-00001','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Coffee Shirt - 32\" at ₹800.00 per unit','2025-10-26 13:07:36','SYSTEM','2025-10-26 07:37:36',NULL,'Invoice: RS-00001, Customer: Sameer Khan',114,'COMPLETED',6,'Sameer Khan',NULL),(25,6,'SALE','RS-00001','SALES_INVOICE',1.00,1000.00,1000.00,'Sale of 1 units of Pink Salwar Kameez - 1 pair  at ₹1000.00 per unit','2025-10-26 13:07:36','SYSTEM','2025-10-26 07:37:36',NULL,'Invoice: RS-00001, Customer: Sameer Khan',114,'COMPLETED',6,'Sameer Khan',NULL),(26,1,'STOCK_ADJUSTMENT','ADJ-21','STOCK_ADJUSTMENT',20.00,300.00,6000.00,'Added 20 numbers of Stone Jeans - 32\"','2025-10-26 20:40:18','SYSTEM','2025-10-26 15:10:39',NULL,NULL,21,'COMPLETED',NULL,NULL,NULL),(27,8,'STOCK_ADJUSTMENT','ADJ-22','STOCK_ADJUSTMENT',5.00,500.00,2500.00,'5 Items reduced from the stock','2025-10-27 11:40:58','SYSTEM','2025-10-27 06:10:59',NULL,NULL,22,'COMPLETED',NULL,NULL,NULL),(28,8,'STOCK_ADJUSTMENT','ADJ-23','STOCK_ADJUSTMENT',2.00,500.00,1000.00,'2 items reduced from the stock','2025-10-27 11:49:52','SYSTEM','2025-10-27 06:19:52',NULL,NULL,23,'COMPLETED',NULL,NULL,NULL),(29,8,'STOCK_ADJUSTMENT','ADJ-24','STOCK_ADJUSTMENT',3.00,500.00,1500.00,'3 Items Reduced from the stock','2025-10-27 12:03:45','SYSTEM','2025-10-27 06:33:45',NULL,NULL,24,'COMPLETED',NULL,NULL,NULL),(30,8,'STOCK_ADJUSTMENT','ADJ-25','STOCK_ADJUSTMENT',10.00,500.00,5000.00,'10 Qty of stock added','2025-10-27 12:06:45','SYSTEM','2025-10-27 06:36:45',NULL,NULL,25,'COMPLETED',NULL,NULL,NULL),(31,1,'SALE','RS-00115','SALES_INVOICE',5.00,800.00,4000.00,'Sale of 5 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-27 14:59:14','SYSTEM','2025-10-27 09:29:14',NULL,'Invoice: RS-00115, Customer: Sohaib',115,'COMPLETED',7,'Sohaib',NULL),(32,1,'SALE','RS-00116','SALES_INVOICE',5.00,800.00,4000.00,'Sale of 5 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-27 15:02:14','SYSTEM','2025-10-27 09:32:14',NULL,'Invoice: RS-00116, Customer: Monish',116,'COMPLETED',5,'Monish',NULL),(33,1,'SALE','RS-00117','SALES_INVOICE',5.00,800.00,4000.00,'Sale of 5 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-27 15:05:18','SYSTEM','2025-10-27 09:35:18',NULL,'Invoice: RS-00117, Customer: Sameer Khan',117,'COMPLETED',6,'Sameer Khan',NULL),(34,1,'SALE','RS-00118','SALES_INVOICE',5.00,800.00,4000.00,'Sale of 5 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-27 15:07:51','SYSTEM','2025-10-27 09:37:51',NULL,'Invoice: RS-00118, Customer: Monish',118,'COMPLETED',5,'Monish',NULL),(35,1,'SALE','RS-00119','SALES_INVOICE',5.00,800.00,4000.00,'Sale of 5 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-27 15:22:27','SYSTEM','2025-10-27 09:52:27',NULL,'Invoice: RS-00119, Customer: Monish',119,'COMPLETED',5,'Monish',NULL),(36,1,'SALE','RS-00120','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-30 14:42:32','SYSTEM','2025-10-30 09:12:32',NULL,'Invoice: RS-00120, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(37,4,'SALE','RS-00120','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-10-30 14:42:32','SYSTEM','2025-10-30 09:12:32',NULL,'Invoice: RS-00120, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(38,1,'SALE','RS-00121','SALES_INVOICE',2.00,800.00,1600.00,'Sale of 2 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-30 14:51:50','SYSTEM','2025-10-30 09:21:50',NULL,'Invoice: RS-00121, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(39,4,'SALE','RS-00121','SALES_INVOICE',2.00,800.00,1600.00,'Sale of 2 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-10-30 14:51:50','SYSTEM','2025-10-30 09:21:50',NULL,'Invoice: RS-00121, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(40,1,'SALE','RS-00122','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-30 15:01:19','SYSTEM','2025-10-30 09:31:19',NULL,'Invoice: RS-00122, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(41,4,'SALE','RS-00122','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-10-30 15:01:19','SYSTEM','2025-10-30 09:31:19',NULL,'Invoice: RS-00122, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(42,1,'SALE','RS-00123','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-30 15:04:01','SYSTEM','2025-10-30 09:34:01',NULL,'Invoice: RS-00123, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(43,4,'SALE','RS-00123','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-10-30 15:04:01','SYSTEM','2025-10-30 09:34:01',NULL,'Invoice: RS-00123, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(44,1,'SALE','RS-00124','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-30 15:57:16','SYSTEM','2025-10-30 10:27:16',NULL,'Invoice: RS-00124, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(45,4,'SALE','RS-00124','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-10-30 15:57:16','SYSTEM','2025-10-30 10:27:16',NULL,'Invoice: RS-00124, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(46,5,'SALE','RS-00124','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Coffee Shirt - 32\" at ₹800.00 per unit','2025-10-30 15:57:16','SYSTEM','2025-10-30 10:27:16',NULL,'Invoice: RS-00124, Customer: Monish',NULL,'COMPLETED',5,'Monish',NULL),(47,1,'SALE','RS-00126','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-30 16:07:21','SYSTEM','2025-10-30 10:37:21',NULL,'Invoice: RS-00126, Customer: Monish',127,'COMPLETED',5,'Monish',NULL),(48,4,'SALE','RS-00126','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-10-30 16:07:21','SYSTEM','2025-10-30 10:37:21',NULL,'Invoice: RS-00126, Customer: Monish',127,'COMPLETED',5,'Monish',NULL),(49,5,'SALE','RS-00126','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Coffee Shirt - 32\" at ₹800.00 per unit','2025-10-30 16:07:21','SYSTEM','2025-10-30 10:37:21',NULL,'Invoice: RS-00126, Customer: Monish',127,'COMPLETED',5,'Monish',NULL),(50,1,'SALE','RS-00128','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-30 16:33:46','SYSTEM','2025-10-30 11:03:46',NULL,'Invoice: RS-00128, Customer: Monish',128,'COMPLETED',5,'Monish',NULL),(51,4,'SALE','RS-00128','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-10-30 16:33:46','SYSTEM','2025-10-30 11:03:46',NULL,'Invoice: RS-00128, Customer: Monish',128,'COMPLETED',5,'Monish',NULL),(52,5,'SALE','RS-00128','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Coffee Shirt - 32\" at ₹800.00 per unit','2025-10-30 16:33:46','SYSTEM','2025-10-30 11:03:46',NULL,'Invoice: RS-00128, Customer: Monish',128,'COMPLETED',5,'Monish',NULL),(53,1,'SALE','RS-00129','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-10-30 16:55:03','SYSTEM','2025-10-30 11:25:03',NULL,'Invoice: RS-00129, Customer: Monish',129,'COMPLETED',5,'Monish',NULL),(54,4,'SALE','RS-00129','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-10-30 16:55:03','SYSTEM','2025-10-30 11:25:03',NULL,'Invoice: RS-00129, Customer: Monish',129,'COMPLETED',5,'Monish',NULL),(55,5,'SALE','RS-00129','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Coffee Shirt - 32\" at ₹800.00 per unit','2025-10-30 16:55:03','SYSTEM','2025-10-30 11:25:03',NULL,'Invoice: RS-00129, Customer: Monish',129,'COMPLETED',5,'Monish',NULL),(56,11,'SALE','RS-00130','SALES_INVOICE',5.00,1000.00,5000.00,'Sale of 5 units of Blue Jeans - 42\" at ₹1000.00 per unit','2025-11-01 04:49:14','SYSTEM','2025-10-31 23:19:14',NULL,'Invoice: RS-00130, Customer: Sameer International Exports Pvt. Ltd.',130,'COMPLETED',10,'Sameer International Exports Pvt. Ltd.',NULL),(57,1,'SALE','RS-00131','SALES_INVOICE',5.00,800.00,4000.00,'Sale of 5 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-11-01 05:06:40','SYSTEM','2025-10-31 23:36:40',NULL,'Invoice: RS-00131, Customer: Sameer International Exports Pvt. Ltd.',131,'COMPLETED',10,'Sameer International Exports Pvt. Ltd.',NULL),(58,11,'SALE','RS-00132','SALES_INVOICE',5.00,1000.00,5000.00,'Sale of 5 units of Blue Jeans - 42\" at ₹1000.00 per unit','2025-11-01 05:25:19','SYSTEM','2025-10-31 23:55:19',NULL,'Invoice: RS-00132, Customer: Sameer International Exports Pvt. Ltd.',132,'COMPLETED',10,'Sameer International Exports Pvt. Ltd.',NULL),(59,1,'SALE','RS-00133','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-11-01 05:36:54','SYSTEM','2025-11-01 00:06:54',NULL,'Invoice: RS-00133, Customer: Gala Traders ',133,'COMPLETED',12,'Gala Traders ',NULL),(60,4,'SALE','RS-00133','SALES_INVOICE',2.00,800.00,1600.00,'Sale of 2 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-11-01 05:36:54','SYSTEM','2025-11-01 00:06:54',NULL,'Invoice: RS-00133, Customer: Gala Traders ',133,'COMPLETED',12,'Gala Traders ',NULL),(61,8,'SALE','RS-00133','SALES_INVOICE',4.00,734.00,2936.00,'Sale of 4 units of Sangria Printed Floral Kurta - Medium at ₹734.00 per unit','2025-11-01 05:36:54','SYSTEM','2025-11-01 00:06:54',NULL,'Invoice: RS-00133, Customer: Gala Traders ',133,'COMPLETED',12,'Gala Traders ',NULL),(62,12,'SALE','RS-00133','SALES_INVOICE',3.00,1000.00,3000.00,'Sale of 3 units of Navy Blue Chinos Pants - 36\" at ₹1000.00 per unit','2025-11-01 05:36:54','SYSTEM','2025-11-01 00:06:54',NULL,'Invoice: RS-00133, Customer: Gala Traders ',133,'COMPLETED',12,'Gala Traders ',NULL),(63,12,'SALE','RS-00134','SALES_INVOICE',2.00,1000.00,2000.00,'Sale of 2 units of Navy Blue Chinos Pants - 36\" at ₹1000.00 per unit','2025-11-01 08:44:12','SYSTEM','2025-11-01 03:14:12',NULL,'Invoice: RS-00134, Customer: Gala Traders ',134,'COMPLETED',12,'Gala Traders ',NULL),(64,1,'SALE','RS-00134','SALES_INVOICE',3.00,800.00,2400.00,'Sale of 3 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-11-01 08:44:12','SYSTEM','2025-11-01 03:14:12',NULL,'Invoice: RS-00134, Customer: Gala Traders ',134,'COMPLETED',12,'Gala Traders ',NULL),(65,4,'SALE','RS-00134','SALES_INVOICE',4.00,800.00,3200.00,'Sale of 4 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-11-01 08:44:12','SYSTEM','2025-11-01 03:14:12',NULL,'Invoice: RS-00134, Customer: Gala Traders ',134,'COMPLETED',12,'Gala Traders ',NULL),(66,5,'SALE','RS-00134','SALES_INVOICE',5.00,800.00,4000.00,'Sale of 5 units of Coffee Shirt - 32\" at ₹800.00 per unit','2025-11-01 08:44:12','SYSTEM','2025-11-01 03:14:12',NULL,'Invoice: RS-00134, Customer: Gala Traders ',134,'COMPLETED',12,'Gala Traders ',NULL),(67,1,'SALE','RS-00135','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-12-03 06:04:44','SYSTEM','2025-12-03 00:34:44',NULL,'Invoice: RS-00135, Customer: Monish',135,'COMPLETED',5,'Monish',NULL),(68,4,'SALE','RS-00135','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Yellow T-Shirt XL at ₹800.00 per unit','2025-12-03 06:04:44','SYSTEM','2025-12-03 00:34:44',NULL,'Invoice: RS-00135, Customer: Monish',135,'COMPLETED',5,'Monish',NULL),(69,8,'SALE','RS-00135','SALES_INVOICE',1.00,734.00,734.00,'Sale of 1 units of Sangria Printed Floral Kurta - Medium at ₹734.00 per unit','2025-12-03 06:04:44','SYSTEM','2025-12-03 00:34:44',NULL,'Invoice: RS-00135, Customer: Monish',135,'COMPLETED',5,'Monish',NULL),(70,10,'SALE','RS-00135','SALES_INVOICE',1.00,734.00,734.00,'Sale of 1 units of Pink Salwar Suit at ₹734.00 per unit','2025-12-03 06:04:44','SYSTEM','2025-12-03 00:34:44',NULL,'Invoice: RS-00135, Customer: Monish',135,'COMPLETED',5,'Monish',NULL),(71,1,'SALE','RS-00136','SALES_INVOICE',1.00,800.00,800.00,'Sale of 1 units of Stone Jeans - 32\" at ₹800.00 per unit','2025-12-09 05:09:08','SYSTEM','2025-12-08 23:39:08',NULL,'Invoice: RS-00136, Customer: Mukesh Traders',136,'COMPLETED',11,'Mukesh Traders',NULL);
/*!40000 ALTER TABLE `item_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `hsn` varchar(50) DEFAULT NULL,
  `unit` varchar(50) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `sale_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `sale_price_type` varchar(20) DEFAULT NULL,
  `discount_amount` decimal(10,2) DEFAULT '0.00',
  `discount_type` varchar(20) DEFAULT NULL,
  `purchase_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `purchase_price_type` varchar(20) DEFAULT NULL,
  `tax_rate_id` bigint DEFAULT NULL,
  `opening_quantity` int DEFAULT '0',
  `at_price` decimal(10,2) DEFAULT '0.00',
  `as_of_date` date DEFAULT NULL,
  `min_stock` int DEFAULT '0',
  `location` varchar(100) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tax_rate_index` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `UK_9obt5anl2008e5xmt7r1cuwf2` (`code`),
  KEY `FKt4m30f1a3axumloqstxcuh05n` (`tax_rate_id`),
  CONSTRAINT `FKt4m30f1a3axumloqstxcuh05n` FOREIGN KEY (`tax_rate_id`) REFERENCES `tax_rates` (`id`),
  CONSTRAINT `items_ibfk_1` FOREIGN KEY (`tax_rate_id`) REFERENCES `tax_rates` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'Stone Jeans - 32\"','5211.32.40','PIECES','Jeans','99281973325','596fcf46-cf68-4ff7-826e-4229f50e3a69_ChatGPT Image Sep 1, 2025, 11_14_09 AM.png',800.00,'With Tax',30.00,'Amount',300.00,'With Tax',5,40,300.00,'2025-10-24',4,'','2025-10-23 15:32:15','2025-12-09 05:09:08',4),(4,'Yellow T-Shirt XL','6109','PIECES','T-Shirts','39017290439',NULL,800.00,'With Tax',30.00,'Amount',400.00,'With Tax',5,9,400.00,'2025-10-25',10,'','2025-10-25 07:06:20','2025-12-03 06:04:44',4),(5,'Coffee Shirt - 32\"','6205','PIECES','Jeans','20501330816',NULL,800.00,'With Tax',30.00,'Amount',400.00,'With Tax',5,16,400.00,'2025-10-25',10,'','2025-10-25 15:28:13','2025-11-01 08:44:12',4),(6,'Pink Salwar Kameez - 1 pair ','6104','PIECES','Dresses','77370476510',NULL,1000.00,'With Tax',50.00,'Amount',500.00,'With Tax',5,25,500.00,'2025-10-25',10,'','2025-10-25 15:30:36','2025-10-31 11:32:08',4),(7,'Copper Brown Stand Collar Shirt ','6109','PIECES','Shirts','85116382994',NULL,1000.00,'With Tax',20.00,'Amount',400.00,'With Tax',5,25,400.00,'2025-10-25',10,'','2025-10-25 15:35:26','2025-10-31 11:32:08',4),(8,'Sangria Printed Floral Kurta - Medium','6211.42.10','PIECES','Dresses','98192914369',NULL,734.00,'With Tax',30.00,'Amount',500.00,'With Tax',5,24,500.00,'2025-10-27',10,'','2025-10-27 06:09:34','2025-12-03 06:04:44',4),(9,'Orange Salwar Kameez Suit ','6211.42.10','PIECES','Dresses','12877809881',NULL,747.00,'With Tax',47.00,'Amount',500.00,'With Tax',5,25,500.00,'2025-10-31',5,'','2025-10-31 08:26:40','2025-10-31 14:32:44',4),(10,'Pink Salwar Suit','6211.42.10','PIECES','Dresses','76104363488',NULL,734.00,'With Tax',30.00,'Amount',450.00,'With Tax',5,24,400.00,'2025-10-31',10,'','2025-10-31 08:44:01','2025-12-03 06:04:44',4),(11,'Blue Jeans - 42\"','5211.32.40','PIECES','Jeans','72731621361',NULL,1000.00,'With Tax',50.00,'Amount',500.00,'With Tax',5,40,500.00,'2025-11-01',10,'','2025-11-01 04:48:00','2025-11-01 05:25:19',4),(12,'Navy Blue Chinos Pants - 36\"','6107','','Pants','25362751728',NULL,1000.00,'With Tax',30.00,'Amount',500.00,'With Tax',7,45,500.00,'2025-11-01',10,'','2025-11-01 05:35:42','2025-11-01 08:44:12',6);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKgeupubdqncc1lpgf2cn4fqwbc` (`parent_id`),
  CONSTRAINT `FKgeupubdqncc1lpgf2cn4fqwbc` FOREIGN KEY (`parent_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'Dashboard','/dashboard',NULL,'dashboard',1,'2025-04-07 06:48:06','2025-04-07 06:48:06'),(2,'Sales','/sales',NULL,'sales',1,'2025-04-07 06:48:06','2025-08-17 21:40:26'),(3,'Products','/products',NULL,'products',1,'2025-04-07 06:48:06','2025-07-20 18:11:48'),(5,'Purchase','/purchase',NULL,'purchase',1,'2025-04-07 06:48:06','2025-07-20 18:11:57'),(6,'Expenses','/expenses',NULL,'expenses',0,'2025-04-07 06:48:06','2025-07-20 18:12:04'),(7,'Reports','/reports',NULL,'reports',1,'2025-04-07 06:48:06','2025-07-20 18:12:10'),(8,'Customers','/customers',NULL,'customers',1,'2025-04-07 06:48:06','2025-07-20 18:12:15'),(9,'Settings','/settings',NULL,'settings',1,'2025-04-07 06:48:06','2025-07-20 18:12:21'),(10,'Users','/users',NULL,'users',1,'2025-04-07 06:48:06','2025-07-20 18:12:28'),(11,'Logout','/logout',NULL,'logout',1,'2025-04-07 06:48:06','2025-04-07 06:48:06'),(12,'New Invoice','/sales/new',2,NULL,1,'2025-04-07 06:48:40','2025-06-21 16:44:48'),(13,'Past Invoices','/sales/history',2,NULL,0,'2025-04-07 06:48:40','2025-09-20 11:52:47'),(14,'Add New Product','/products/add',3,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(15,'Product List','/products/list',3,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(16,'Bulk Import Products','/products/import',3,NULL,1,'2025-04-07 06:48:40','2025-06-30 21:23:32'),(20,'Add Purchase','/purchase/add',5,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(21,'Purchase History','/purchase/history',5,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(22,'Add Expenses','/expenses/add',6,NULL,0,'2025-04-07 06:48:40','2025-07-05 02:14:07'),(23,'View Expenses','/expenses/view',6,NULL,0,'2025-04-07 06:48:40','2025-07-05 02:14:07'),(24,'Daily/Monthly','/reports/daily-monthly',7,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(25,'GST','/reports/gst',7,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(26,'Stock Report','/reports/stock',7,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(27,'Add Customer','/customers/add',8,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(28,'Customer List','/customers/list',8,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(30,'Shop Details','/settings/shop',9,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(31,'Tax/GST','/settings/gst',9,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(32,'Add New User','/users/add',10,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(33,'Set Roles','/users/roles',10,NULL,1,'2025-04-07 06:48:40','2025-04-07 06:48:40'),(34,'Product Categories','/settings/categories',9,NULL,1,'2025-04-15 01:01:04','2025-04-16 00:58:41'),(35,'Inventory','/inventory',NULL,'inventory',0,'2025-06-21 23:19:00','2025-10-10 19:02:33'),(36,'Add Inventory','/inventory/add',35,'',0,'2025-06-21 23:21:27','2025-10-10 19:03:49'),(37,'Inventory List','/inventory/list',35,NULL,0,'2025-06-21 23:22:41','2025-10-10 19:03:50'),(38,'Inventory Movements','/inventory/movements',35,NULL,0,'2025-06-21 23:23:59','2025-10-10 19:03:50'),(39,'Low Inventory Alerts','/inventory/alerts',35,NULL,0,'2025-06-21 23:26:25','2025-10-10 19:03:50'),(40,'Inventory Adjustment','/inventory/adjustment',35,NULL,0,'2025-06-21 23:26:25','2025-10-10 19:03:50'),(41,'Inventory Reports','/inventory/reports',35,NULL,0,'2025-06-21 23:26:25','2025-10-10 19:03:50'),(42,'Suppliers','/suppliers',NULL,'',0,'2025-06-22 12:48:55','2025-10-10 19:05:47'),(43,'Add Supplier','/suppliers/add',42,NULL,0,'2025-06-22 13:09:19','2025-10-10 19:05:47'),(44,'Suppliers List','/suppliers/list',42,NULL,0,'2025-06-22 13:09:19','2025-10-10 19:05:47'),(45,'Barcode Generator','/settings/barcodegen',9,NULL,1,'2025-07-22 21:18:58','2025-07-22 21:18:58'),(46,'Product Transactions','/products/transactions',3,NULL,1,'2025-08-10 12:43:39','2025-08-10 12:43:39'),(47,'POS','/sales/pos',2,NULL,1,'2025-08-17 21:41:24','2025-08-17 21:41:24'),(48,'Sales Invoice Preview','/sales/preview',2,NULL,0,'2025-10-07 14:53:02','2025-10-11 13:33:18');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_new`
--

DROP TABLE IF EXISTS `menu_new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu_new` (
  `id` bigint NOT NULL DEFAULT '0',
  `name` varchar(100) NOT NULL,
  `path` varchar(255) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `filename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkduwdaeif23awr7cqa0jp66k9` (`parent_id`),
  CONSTRAINT `FKkduwdaeif23awr7cqa0jp66k9` FOREIGN KEY (`parent_id`) REFERENCES `menu_new` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_new`
--

LOCK TABLES `menu_new` WRITE;
/*!40000 ALTER TABLE `menu_new` DISABLE KEYS */;
INSERT INTO `menu_new` VALUES (0,'Items Dashboard','/items/dashboard',3,NULL,0,'2025-10-19 16:13:59','2025-10-21 02:51:47',NULL),(1,'Home','/home',NULL,'dashboard',1,'2025-04-07 06:48:06','2025-10-13 14:26:54','Home'),(2,'Parties','/parties',NULL,'parties',1,'2025-04-07 06:48:06','2025-10-13 16:44:50','PartiesDashboard'),(3,'Items','/items',NULL,'items',1,'2025-04-07 06:48:06','2025-10-13 15:08:40','ItemsDashboard'),(5,'Sales','/sales',NULL,'sales',1,'2025-04-07 06:48:06','2025-10-13 14:26:54','SalesDashboard'),(6,'Purchase','/purchase',NULL,'expenses',1,'2025-04-07 06:48:06','2025-10-13 14:58:49','PurchaseDashboard'),(7,'Barcode Generator','/barcodegen',NULL,'barcodegen',1,'2025-04-07 06:48:06','2025-10-13 14:26:54','BarcodeGenerator'),(8,'Reports','/reports',NULL,'reports',1,'2025-04-07 06:48:06','2025-10-13 14:26:54','ReportsDashboard'),(9,'My Business','/mybusiness',NULL,'business',1,'2025-04-07 06:48:06','2025-10-13 14:26:54','MyBusiness'),(10,'Logout','/logout',NULL,'logout',1,'2025-04-07 06:48:06','2025-10-13 14:26:54','Logout'),(11,'Add Party','/parties/add',2,'',0,'2025-04-07 06:48:06','2025-10-13 14:26:54','AddParty'),(12,'Add Items','/items/add',3,NULL,0,'2025-04-07 06:48:40','2025-10-13 14:32:44','AddItem'),(13,'Bulk Import','/items/import',3,NULL,1,'2025-04-07 06:48:40','2025-10-13 14:26:54','BulkImportItems'),(14,'New Invoice','/sales/new',5,NULL,1,'2025-04-07 06:48:40','2025-10-13 14:26:54','NewSalesNew'),(15,'POS','/sales/pos',5,NULL,1,'2025-04-07 06:48:40','2025-10-13 14:26:54','NewSalesPOS'),(16,'Add Purchase','/purchase/add',6,NULL,0,'2025-04-07 06:48:40','2025-10-13 14:26:54','AddPurchase'),(20,'Expenses','/purchase/expenses',6,NULL,1,'2025-04-07 06:48:40','2025-10-13 14:26:54','ExpensesDashboard'),(21,'Add Expenses','/purchase/expenses/add',20,NULL,0,'2025-04-07 06:48:40','2025-10-13 14:26:54','AddExpenses'),(22,'Payment-Out','/purchase/paymentout',6,NULL,1,'2025-04-07 06:48:40','2025-10-13 14:27:07','PaymentOutDashboard'),(23,'Add Payment-Out','/purchase/paymentout/add',22,NULL,0,'2025-04-07 06:48:40','2025-10-13 14:27:25','AddPaymentOut'),(24,'Estimate','/sales/estimate',5,NULL,1,'2025-04-07 06:48:40','2025-10-13 15:54:03','EstimateQuotationDashboard'),(25,'Proforma','/sales/proforma',5,NULL,1,'2025-04-07 06:48:40','2025-10-13 15:56:04','ProformaInvoiceDashboard'),(26,'Payment-In','/sales/paymentin',5,NULL,1,'2025-04-07 06:48:40','2025-10-13 14:27:49','PaymentInDashboard'),(27,'Sale Order','/sales/order',5,NULL,1,'2025-04-07 06:48:40','2025-10-13 14:28:00','SalesOrderDashboard'),(28,'Delivery Challan','/sales/challan',5,NULL,1,'2025-04-07 06:48:40','2025-10-13 14:28:13','DeliveryChallanDashboard'),(30,'Sale Return','/sales/return',5,NULL,1,'2025-04-07 06:48:40','2025-10-13 16:02:21','SalesReturnDashboard'),(31,'Categories','/items/categories',3,NULL,1,'2025-04-07 06:48:40','2025-10-13 14:28:46','ItemCategoriesDashboard'),(32,'Add Category','/items/categories/add',31,NULL,0,'2025-04-07 06:48:40','2025-10-13 14:38:34','AddItemCategories'),(33,'Transactions','/reports/transactions',8,NULL,1,'2025-04-07 06:48:40','2025-10-13 16:07:15','TransactionsReport'),(34,'Party','/reports/party',8,NULL,1,'2025-04-15 01:01:04','2025-10-13 16:07:16','PartyReport'),(35,'GST','/reports/gst',8,'',1,'2025-06-21 23:19:00','2025-10-13 16:07:16','GSTReport'),(36,'Item','/reports/items',8,'',1,'2025-06-21 23:21:27','2025-10-13 16:10:57','ItemsReport'),(37,'Tax','/reports/tax',8,NULL,1,'2025-06-21 23:22:41','2025-10-13 16:07:16','TaxReport'),(38,'Expense','/reports/expenses',8,NULL,1,'2025-06-21 23:23:59','2025-10-13 16:07:16','ExpenseReport'),(39,'Sales/Purchases','/reports/orders',8,NULL,1,'2025-06-21 23:26:25','2025-10-13 16:07:16','SalesPurchaseOrderReport'),(40,'Add Products','/items/products',3,NULL,0,'2025-10-21 16:23:23','2025-10-30 21:28:36',NULL);
/*!40000 ALTER TABLE `menu_new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `monthly_sales_summary`
--

DROP TABLE IF EXISTS `monthly_sales_summary`;
/*!50001 DROP VIEW IF EXISTS `monthly_sales_summary`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `monthly_sales_summary` AS SELECT 
 1 AS `year`,
 1 AS `month`,
 1 AS `month_year`,
 1 AS `total_transactions`,
 1 AS `total_sales_amount`,
 1 AS `total_received_amount`,
 1 AS `total_balance_amount`,
 1 AS `total_tax_amount`,
 1 AS `total_discount_amount`,
 1 AS `total_net_amount`,
 1 AS `average_transaction_value`,
 1 AS `total_items_sold`,
 1 AS `paid_transactions`,
 1 AS `partial_transactions`,
 1 AS `unpaid_transactions`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `new_estimate_quotation`
--

DROP TABLE IF EXISTS `new_estimate_quotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_estimate_quotation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estimate_quotation_number` varchar(255) DEFAULT NULL,
  `estimate_quotation_date` date DEFAULT NULL,
  `party_id` bigint DEFAULT NULL,
  `total_amount` decimal(10,0) DEFAULT NULL,
  `discount_amount` decimal(10,0) DEFAULT NULL,
  `total_tax_amount` decimal(10,0) DEFAULT NULL,
  `amount_in_words` varchar(1000) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `success` tinyint(1) DEFAULT NULL,
  `taxable_amount` double DEFAULT NULL,
  `total_quantity` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_estimate_quotation`
--

LOCK TABLES `new_estimate_quotation` WRITE;
/*!40000 ALTER TABLE `new_estimate_quotation` DISABLE KEYS */;
INSERT INTO `new_estimate_quotation` VALUES (10,'RS-00001','2025-12-01',5,3960,187,124,'Three Thousand Nine Hundred Sixty Rupees Only','Estimate/Quotation created successfully',1,3835.59,NULL,NULL),(11,'RS-00011','2025-12-01',5,3014,120,94,'Three Thousand Fourteen Rupees Only','Estimate/Quotation created successfully',1,2919.98,NULL,NULL),(12,'RS-00012','2025-12-01',5,3014,120,94,'Three Thousand Fourteen Rupees Only','Estimate/Quotation created successfully',1,2919.98,NULL,NULL),(13,'RS-00013','2025-12-02',10,2948,120,92,'Two Thousand Nine Hundred Forty Eight Rupees Only','Estimate/Quotation created successfully',1,2855.96,NULL,NULL),(14,'RS-00014','2025-12-02',6,4174,160,130,'Four Thousand One Hundred Seventy Four Rupees Only','Estimate/Quotation created successfully',1,4043.98,NULL,NULL),(15,'RS-00015','2025-12-02',5,3014,120,94,'Three Thousand Fourteen Rupees Only','Estimate/Quotation created successfully',1,2919.98,NULL,NULL),(16,'RS-00016','2025-12-03',5,3400,147,106,'Three Thousand Four Hundred Rupees Only','Estimate/Quotation created successfully',1,3293.59,NULL,NULL),(17,'RS-00017','2025-12-03',10,3394,140,126,'Three Thousand Three Hundred Ninety Four Rupees Only','Estimate/Quotation created successfully',1,3267.98,NULL,NULL),(18,'RS-00018','2025-12-06',12,3670,130,134,'Three Thousand Six Hundred Seventy Rupees Only','Estimate/Quotation created successfully',1,3536,NULL,NULL),(19,'RS-00019','2025-12-06',5,3924,157,122,'Three Thousand Nine Hundred Twenty Four Rupees Only','Estimate/Quotation created successfully',1,3801.57,NULL,NULL),(20,'EQ-00020','2025-12-06',5,3260,140,102,'Three Thousand Two Hundred Sixty Rupees Only','Estimate/Quotation created successfully',1,3158,NULL,NULL),(21,'EQ-00021','2025-12-06',5,4128,140,148,'Four Thousand One Hundred Twenty Eight Rupees Only','Estimate/Quotation created successfully',1,3979.96,NULL,NULL),(22,'EQ-00022','2025-12-06',12,2490,110,78,'Two Thousand Four Hundred Ninety Rupees Only','Estimate/Quotation created successfully',1,2412,NULL,NULL),(23,'EQ-00023','2025-12-06',5,3324,157,124,'Three Thousand Three Hundred Twenty Four Rupees Only','Estimate/Quotation created successfully',1,3199.57,NULL,NULL),(24,'EQ-00024','2025-12-06',6,3290,110,102,'Three Thousand Two Hundred Ninety Rupees Only','Estimate/Quotation created successfully',1,3188,NULL,NULL),(25,'EQ-00025','2025-12-06',5,3190,157,100,'Three Thousand One Hundred Ninety Rupees Only','Estimate/Quotation created successfully',1,3089.59,NULL,NULL),(26,'EQ-00026','2025-12-06',6,3324,157,124,'Three Thousand Three Hundred Twenty Four Rupees Only','Estimate/Quotation created successfully',1,3199.57,4,NULL);
/*!40000 ALTER TABLE `new_estimate_quotation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_estimate_quotation_items`
--

DROP TABLE IF EXISTS `new_estimate_quotation_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_estimate_quotation_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `estimate_quotation_id` bigint DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` decimal(15,2) DEFAULT NULL,
  `discount_percent` decimal(5,2) DEFAULT NULL,
  `tax_percent` decimal(5,2) DEFAULT NULL,
  `tax_amount` decimal(15,2) DEFAULT NULL,
  `tax_rate_id` bigint DEFAULT NULL,
  `tax_rate_index` bigint DEFAULT NULL,
  `discount_amount` decimal(15,2) DEFAULT NULL,
  `total_price` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `new_estimate_quotation_items_new_estimate_quotation_FK` (`estimate_quotation_id`),
  KEY `new_estimate_quotation_items_items_FK` (`item_id`),
  CONSTRAINT `new_estimate_quotation_items_items_FK` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `new_estimate_quotation_items_new_estimate_quotation_FK` FOREIGN KEY (`estimate_quotation_id`) REFERENCES `new_estimate_quotation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_estimate_quotation_items`
--

LOCK TABLES `new_estimate_quotation_items` WRITE;
/*!40000 ALTER TABLE `new_estimate_quotation_items` DISABLE KEYS */;
INSERT INTO `new_estimate_quotation_items` VALUES (10,10,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(11,10,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(12,10,5,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(13,10,6,1,1000.00,5.00,3.00,30.00,5,4,50.00,950.00),(14,10,9,1,747.00,6.29,3.00,22.41,5,4,46.99,700.01),(15,11,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(16,11,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(17,11,5,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(18,11,10,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(19,12,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(20,12,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(21,12,5,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(22,12,10,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(23,13,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(24,13,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(25,13,8,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(26,13,10,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(27,14,7,1,1000.00,2.00,3.00,30.00,5,4,20.00,980.00),(28,14,5,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(29,14,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(30,14,8,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(31,14,6,1,1000.00,5.00,3.00,30.00,5,4,50.00,950.00),(32,15,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(33,15,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(34,15,5,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(35,15,8,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(36,16,11,1,1000.00,5.00,3.00,30.00,5,4,50.00,950.00),(37,16,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(38,16,9,1,747.00,6.29,3.00,22.41,5,4,46.99,700.01),(39,16,7,1,1000.00,2.00,3.00,30.00,5,4,20.00,980.00),(40,17,5,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(41,17,10,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(42,17,11,1,1000.00,5.00,3.00,30.00,5,4,50.00,950.00),(43,17,12,1,1000.00,3.00,5.00,50.00,7,6,30.00,970.00),(44,18,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(45,18,11,1,1000.00,5.00,3.00,30.00,5,4,50.00,950.00),(46,18,12,1,1000.00,3.00,5.00,50.00,7,6,30.00,970.00),(47,18,7,1,1000.00,2.00,3.00,30.00,5,4,20.00,980.00),(48,19,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(49,19,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(50,19,7,1,1000.00,2.00,3.00,30.00,5,4,20.00,980.00),(51,19,8,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(52,19,9,1,747.00,6.29,3.00,22.41,5,4,46.99,700.01),(53,20,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(54,20,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(55,20,5,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(56,20,6,1,1000.00,5.00,3.00,30.00,5,4,50.00,950.00),(57,21,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(58,21,7,1,1000.00,2.00,3.00,30.00,5,4,20.00,980.00),(59,21,10,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(60,21,8,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(61,21,12,1,1000.00,3.00,5.00,50.00,7,6,30.00,970.00),(62,22,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(63,22,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(64,22,11,1,1000.00,5.00,3.00,30.00,5,4,50.00,950.00),(65,23,9,1,747.00,6.29,3.00,22.41,5,4,46.99,700.01),(66,23,10,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(67,23,12,1,1000.00,3.00,5.00,50.00,7,6,30.00,970.00),(68,23,11,1,1000.00,5.00,3.00,30.00,5,4,50.00,950.00),(69,24,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(70,24,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(71,24,5,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(72,24,7,1,1000.00,2.00,3.00,30.00,5,4,20.00,980.00),(73,25,1,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(74,25,4,1,800.00,3.75,3.00,24.00,5,4,30.00,770.00),(75,25,6,1,1000.00,5.00,3.00,30.00,5,4,50.00,950.00),(76,25,9,1,747.00,6.29,3.00,22.41,5,4,46.99,700.01),(77,26,9,1,747.00,6.29,3.00,22.41,5,4,46.99,700.01),(78,26,10,1,734.00,4.09,3.00,22.02,5,4,30.02,703.98),(79,26,12,1,1000.00,3.00,5.00,50.00,7,6,30.00,970.00),(80,26,11,1,1000.00,5.00,3.00,30.00,5,4,50.00,950.00);
/*!40000 ALTER TABLE `new_estimate_quotation_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_proforma_invoice`
--

DROP TABLE IF EXISTS `new_proforma_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_proforma_invoice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount_in_words` varchar(255) DEFAULT NULL,
  `discount_amount` double DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `party_id` bigint DEFAULT NULL,
  `proforma_invoice_date` date DEFAULT NULL,
  `proforma_invoice_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `success` bit(1) DEFAULT NULL,
  `taxable_amount` double DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  `total_quantity` bigint DEFAULT NULL,
  `total_tax_amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_proforma_invoice`
--

LOCK TABLES `new_proforma_invoice` WRITE;
/*!40000 ALTER TABLE `new_proforma_invoice` DISABLE KEYS */;
INSERT INTO `new_proforma_invoice` VALUES (1,'Three Thousand Two Hundred Twenty Four Rupees Only',110.0206,'Proforma Invoice created successfully',5,'2025-12-10','PI-00001','OPEN',_binary '',3123.98,3224,4,100.02),(2,'One Thousand Four Hundred Seventy Rupees Only',76.9863,'Proforma Invoice created successfully',8,'2025-12-10','PI-00002','OPEN',_binary '',1423.59,1470,2,46.41);
/*!40000 ALTER TABLE `new_proforma_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_proforma_invoice_items`
--

DROP TABLE IF EXISTS `new_proforma_invoice_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_proforma_invoice_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `discount_amount` double DEFAULT NULL,
  `discount_percent` double DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  `price` double DEFAULT NULL,
  `proforma_invoice_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `tax_amount` double DEFAULT NULL,
  `tax_percent` double DEFAULT NULL,
  `tax_rate_id` bigint DEFAULT NULL,
  `tax_rate_index` bigint DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_proforma_invoice_items`
--

LOCK TABLES `new_proforma_invoice_items` WRITE;
/*!40000 ALTER TABLE `new_proforma_invoice_items` DISABLE KEYS */;
INSERT INTO `new_proforma_invoice_items` VALUES (1,30,3.75,1,800,1,1,24,3,5,4,770),(2,30,3.75,4,800,1,1,24,3,5,4,770),(3,30.020599999999998,4.09,8,734,1,1,22.02,3,5,4,703.9794),(4,20,2,7,1000,1,1,30,3,5,4,980),(5,30,3.75,1,800,2,1,24,3,5,4,770),(6,46.9863,6.29,9,747,2,1,22.41,3,5,4,700.0137);
/*!40000 ALTER TABLE `new_proforma_invoice_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_sales_invoice`
--

DROP TABLE IF EXISTS `new_sales_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_sales_invoice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `invoice_number` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL,
  `invoice_date` date NOT NULL,
  `total_amount` decimal(12,2) NOT NULL DEFAULT '0.00',
  `received_amount` decimal(12,2) NOT NULL DEFAULT '0.00',
  `balance_amount` decimal(12,2) NOT NULL DEFAULT '0.00',
  `amount_in_words` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `success` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `discount_amount` double DEFAULT NULL,
  `total_tax_amount` decimal(12,2) DEFAULT NULL,
  `taxable_amount` decimal(12,2) DEFAULT NULL,
  `subtotal_amount` decimal(12,2) DEFAULT NULL,
  `party_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_new_sales_invoice_number` (`invoice_number`),
  KEY `idx_new_sales_invoice_date` (`invoice_date`),
  KEY `fk_new_sales_invoice_party` (`party_id`),
  CONSTRAINT `fk_new_sales_invoice_party` FOREIGN KEY (`party_id`) REFERENCES `parties` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_sales_invoice`
--

LOCK TABLES `new_sales_invoice` WRITE;
/*!40000 ALTER TABLE `new_sales_invoice` DISABLE KEYS */;
INSERT INTO `new_sales_invoice` VALUES (114,'RS-00001','2025-10-27',3260.00,3260.00,0.00,'Three Thousand Two Hundred Sixty Rupees Only','Sales invoice created successfully',1,'2025-10-26 18:37:35','2025-10-26 18:37:35',140,122.00,3138.00,3260.00,6),(115,'RS-00115','2025-10-28',3850.00,0.00,3850.00,'Three Thousand Eight Hundred Fifty Rupees Only','Sales invoice created successfully',1,'2025-10-27 20:29:13','2025-10-27 20:29:13',150,115.50,3734.50,3850.00,7),(116,'RS-00116','2025-10-28',3850.00,3850.00,0.00,'Three Thousand Eight Hundred Fifty Rupees Only','Sales invoice created successfully',1,'2025-10-27 20:31:14','2025-10-27 20:31:14',150,115.50,3734.50,3850.00,5),(117,'RS-00117','2025-10-28',3850.00,3850.00,0.00,'Three Thousand Eight Hundred Fifty Rupees Only','Sales invoice created successfully',1,'2025-10-27 20:34:27','2025-10-27 20:34:27',150,115.50,3734.50,3850.00,6),(118,'RS-00118','2025-10-28',3850.00,3850.00,0.00,'Three Thousand Eight Hundred Fifty Rupees Only','Sales invoice created successfully',1,'2025-10-27 20:37:24','2025-10-27 20:37:24',150,115.50,3734.50,3850.00,5),(119,'RS-00119','2025-10-28',3850.00,3850.00,0.00,'Three Thousand Eight Hundred Fifty Rupees Only','Sales invoice created successfully',1,'2025-10-27 20:50:55','2025-10-27 20:50:55',150,115.50,3734.50,3850.00,5),(120,'RS-00120','2025-10-31',1540.00,1540.00,0.00,'One Thousand Five Hundred Forty Rupees Only','Sales invoice created successfully',1,'2025-10-30 20:12:31','2025-10-30 20:12:31',60,48.00,1492.00,1540.00,5),(121,'RS-00121','2025-10-31',3160.00,3160.00,0.00,'Three Thousand One Hundred Sixty Rupees Only','Sales invoice created successfully',1,'2025-10-30 20:21:16','2025-10-30 20:21:16',40,92.40,3067.60,3160.00,5),(122,'RS-00122','2025-10-31',1540.00,1540.00,0.00,'One Thousand Five Hundred Forty Rupees Only','Sales invoice created successfully',1,'2025-10-30 20:29:38','2025-10-30 20:29:38',60,48.00,1492.00,1540.00,5),(123,'RS-00123','2025-10-31',1540.00,1540.00,0.00,'One Thousand Five Hundred Forty Rupees Only','Sales invoice created successfully',1,'2025-10-30 20:32:57','2025-10-30 20:32:57',60,48.00,1492.00,1540.00,5),(124,'RS-00124','2025-10-31',2310.00,2310.00,0.00,'Two Thousand Three Hundred Ten Rupees Only','Sales invoice created successfully',1,'2025-10-30 21:24:17','2025-10-30 21:24:17',90,72.00,2238.00,2310.00,5),(125,'RS-00125','2025-10-31',2310.00,2310.00,0.00,'Two Thousand Three Hundred Ten Rupees Only','Sales invoice created successfully',1,'2025-10-30 21:28:02','2025-10-30 21:28:02',90,72.00,2238.00,2310.00,5),(126,'RS-00126','2025-10-31',2310.00,2310.00,0.00,'Two Thousand Three Hundred Ten Rupees Only','Sales invoice created successfully',1,'2025-10-30 21:32:12','2025-10-30 21:32:12',90,72.00,2238.00,2310.00,5),(127,'RS-00127','2025-10-31',2310.00,2310.00,0.00,'Two Thousand Three Hundred Ten Rupees Only','Sales invoice created successfully',1,'2025-10-30 21:34:18','2025-10-30 21:34:18',90,72.00,2238.00,2310.00,5),(128,'RS-00128','2025-10-31',2310.00,0.00,2310.00,'Two Thousand Three Hundred Ten Rupees Only','Sales invoice created successfully',1,'2025-10-30 21:41:10','2025-10-30 21:41:10',90,72.00,2238.00,2310.00,5),(129,'RS-00129','2025-10-31',2310.00,2310.00,0.00,'Two Thousand Three Hundred Ten Rupees Only','Sales invoice created successfully',1,'2025-10-30 22:24:53','2025-10-30 22:24:53',90,72.00,2238.00,2310.00,5),(130,'RS-00130','2025-11-01',4750.00,4750.00,0.00,'Four Thousand Seven Hundred Fifty Rupees Only','Sales invoice created successfully',1,'2025-11-01 10:19:13','2025-11-01 10:19:13',250,142.50,4607.50,4750.00,10),(131,'RS-00131','2025-11-01',3850.00,3850.00,0.00,'Three Thousand Eight Hundred Fifty Rupees Only','Sales invoice created successfully',1,'2025-11-01 10:36:39','2025-11-01 10:36:39',150,115.50,3734.50,3850.00,10),(132,'RS-00132','2025-11-01',4750.00,4750.00,0.00,'Four Thousand Seven Hundred Fifty Rupees Only','Sales invoice created successfully',1,'2025-11-01 10:55:18','2025-11-01 10:55:18',250,142.50,4607.50,4750.00,10),(133,'RS-00133','2025-11-01',8035.92,8035.92,0.00,'Eight Thousand Thirty Five Rupees and Ninety Two Paisa Only','Sales invoice created successfully',1,'2025-11-01 11:06:53','2025-11-01 11:06:53',300.0824,300.18,7735.74,8035.92,12),(134,'RS-00134','2025-11-01',11180.00,11180.00,0.00,'Eleven Thousand One Hundred Eighty Rupees Only','Sales invoice created successfully',1,'2025-11-01 14:14:11','2025-11-01 14:14:11',420,374.20,10805.80,11180.00,12),(135,'RS-00135','2025-12-03',2948.00,2948.00,0.00,'Two Thousand Nine Hundred Forty Eight Rupees Only','Sales invoice created successfully',1,'2025-12-03 11:34:42','2025-12-03 11:34:42',120.0412,92.04,2855.96,2948.00,5),(136,'RS-00136','2025-12-09',770.00,770.00,0.00,'Seven Hundred Seventy Rupees Only','Sales invoice created successfully',1,'2025-12-09 10:39:07','2025-12-09 10:39:07',30,24.00,746.00,770.00,11);
/*!40000 ALTER TABLE `new_sales_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `new_sales_invoice_items`
--

DROP TABLE IF EXISTS `new_sales_invoice_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `new_sales_invoice_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `invoice_id` bigint NOT NULL,
  `item_id` bigint DEFAULT NULL,
  `quantity` int NOT NULL DEFAULT '0',
  `price` decimal(12,2) NOT NULL DEFAULT '0.00',
  `discount_percent` decimal(5,2) NOT NULL DEFAULT '0.00',
  `discount_amount` decimal(12,2) NOT NULL DEFAULT '0.00',
  `total_price` double DEFAULT NULL,
  `tax_percent` decimal(5,2) DEFAULT NULL COMMENT 'Snapshot of applied tax rate for historical accuracy',
  `tax_amount` double DEFAULT NULL,
  `tax_rate_id` bigint DEFAULT NULL,
  `tax_rate` double DEFAULT NULL,
  `tax_rate_index` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_new_invoice_items_invoice_id` (`invoice_id`),
  KEY `idx_new_invoice_items_product_id` (`item_id`),
  KEY `fk_new_sales_invoice_items_tax_rate` (`tax_rate_id`),
  CONSTRAINT `fk_new_invoice_items_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `new_sales_invoice` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_new_sales_invoice_items_tax_rate` FOREIGN KEY (`tax_rate_id`) REFERENCES `tax_rates` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=278 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `new_sales_invoice_items`
--

LOCK TABLES `new_sales_invoice_items` WRITE;
/*!40000 ALTER TABLE `new_sales_invoice_items` DISABLE KEYS */;
INSERT INTO `new_sales_invoice_items` VALUES (237,114,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(238,114,4,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(239,114,5,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(240,114,6,1,1000.00,5.00,50.00,950,3.00,50,5,NULL,4),(241,115,1,5,800.00,3.75,150.00,3850,3.00,115.5,5,NULL,4),(242,116,1,5,800.00,3.75,150.00,3850,3.00,115.5,5,NULL,4),(243,117,1,5,800.00,3.75,150.00,3850,3.00,115.5,5,NULL,4),(244,118,1,5,800.00,3.75,150.00,3850,3.00,115.5,5,NULL,4),(245,119,1,5,800.00,3.75,150.00,3850,3.00,115.5,5,NULL,4),(246,120,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(247,121,1,2,800.00,1.25,20.00,1580,3.00,46.2,5,NULL,4),(248,122,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(249,123,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(250,124,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(251,125,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(252,126,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(253,127,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(254,127,4,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(255,127,5,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(256,128,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(257,128,4,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(258,128,5,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(259,129,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(260,129,4,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(261,129,5,1,800.00,3.75,30.00,770,3.00,24,5,NULL,4),(262,130,11,5,1000.00,5.00,250.00,4750,3.00,142.5,5,NULL,NULL),(263,131,1,5,800.00,3.75,150.00,3850,3.00,115.5,5,NULL,NULL),(264,132,11,5,1000.00,5.00,250.00,4750,3.00,142.5,5,NULL,NULL),(265,133,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,NULL),(266,133,4,2,800.00,3.75,60.00,1540,3.00,46.2,5,NULL,NULL),(267,133,8,4,734.00,4.09,120.08,2815.92,3.00,84.48,5,NULL,NULL),(268,133,12,3,1000.00,3.00,90.00,2910,5.00,145.5,7,NULL,NULL),(269,134,12,2,1000.00,3.00,60.00,1940,5.00,97,7,NULL,NULL),(270,134,1,3,800.00,3.75,90.00,2310,3.00,69.3,5,NULL,NULL),(271,134,4,4,800.00,3.75,120.00,3080,3.00,92.4,5,NULL,NULL),(272,134,5,5,800.00,3.75,150.00,3850,3.00,115.5,5,NULL,NULL),(273,135,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,NULL),(274,135,4,1,800.00,3.75,30.00,770,3.00,24,5,NULL,NULL),(275,135,8,1,734.00,4.09,30.00,704,3.00,22.02,5,NULL,NULL),(276,135,10,1,734.00,4.09,30.00,704,3.00,22.02,5,NULL,NULL),(277,136,1,1,800.00,3.75,30.00,770,3.00,24,5,NULL,NULL);
/*!40000 ALTER TABLE `new_sales_invoice_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parties`
--

DROP TABLE IF EXISTS `parties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parties` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `party_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `gstin` varchar(15) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gst_type_id` bigint DEFAULT NULL,
  `state_id` bigint DEFAULT NULL,
  `email_id` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `billing_address` text COLLATE utf8mb4_unicode_ci,
  `shipping_address` text COLLATE utf8mb4_unicode_ci,
  `enable_shipping` tinyint(1) DEFAULT '0',
  `opening_balance` decimal(15,2) DEFAULT '0.00',
  `as_of_date` date DEFAULT NULL,
  `payment_type` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'toPay',
  `credit_limit_type` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT 'noLimit',
  `custom_limit` decimal(15,2) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `gstin` (`gstin`),
  KEY `idx_party_name` (`party_name`),
  KEY `idx_gstin` (`gstin`),
  KEY `idx_phone_number` (`phone_number`),
  KEY `idx_email_id` (`email_id`),
  KEY `idx_is_active` (`is_active`),
  KEY `idx_created_at` (`created_at`),
  KEY `FKnv8igh5fmiskks71x321f2hvu` (`gst_type_id`),
  KEY `FKokix7348yamkh0q7pjp3vo0sf` (`state_id`),
  CONSTRAINT `fk_party_gst_type` FOREIGN KEY (`gst_type_id`) REFERENCES `gst_type` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_party_state` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`) ON DELETE SET NULL,
  CONSTRAINT `FKnv8igh5fmiskks71x321f2hvu` FOREIGN KEY (`gst_type_id`) REFERENCES `gst_type` (`id`),
  CONSTRAINT `FKokix7348yamkh0q7pjp3vo0sf` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parties`
--

LOCK TABLES `parties` WRITE;
/*!40000 ALTER TABLE `parties` DISABLE KEYS */;
INSERT INTO `parties` VALUES (5,'Monish','27AABCU9603R1ZM','9923536215',1,11,'monish@gmail.com','Blah Blah Blah','',0,3000.00,'2025-10-16','toReceive','noLimit',NULL,1,'2025-10-15 06:47:08','2025-10-15 10:55:27'),(6,'Sameer Khan','29ABCDE1234F1Z5','8381023879',2,11,'sameer.khan@gmail.com','Blah Blah Blah, Blah Blah, Blah','',0,10000.00,'2025-10-15','toReceive','noLimit',NULL,1,'2025-10-15 08:29:58','2025-10-15 10:21:37'),(7,'Sohaib','29ABCDE1234F1Z6','7030228602',2,22,'sohaib.rahman64@gmail.com','Blk. No. 528/1055, Opp. Shubhlaxmi Apt., Ambika Nagar, Ulhasnagar - 421004','',0,25000.00,'2025-10-18','toPay','noLimit',NULL,1,'2025-10-18 06:54:51','2025-10-18 06:54:51'),(8,'Shree Krishna Clothing and Garments Shop','27AABCU9603R1ZR','9870253518',2,22,'shree.krishna@gmail.com','XYZ ABC','',0,25000.00,'2025-10-22','toPay','noLimit',NULL,1,'2025-10-22 03:38:58','2025-10-22 03:38:58'),(10,'Sameer International Exports Pvt. Ltd.','27AABCU9603R1ZY','8381023878',1,2,'sameer.int@gmail.com','Unit No 41, Ground Floor, V-1 Building, Vijay Condominium, Mehra Industrial Compound, Near Vijay Print Bus Stop, Sakinaka,\nAndheri East Mumbai- 400072 India.','',0,10000.00,'2025-11-01','toReceive','noLimit',NULL,1,'2025-11-01 04:44:52','2025-11-01 04:45:28'),(11,'Mukesh Traders','27AABCU9603R1MN','8381023878',1,2,'mukesh.traders@gmail.com','Unit No 41, Ground Floor, V-1 Building, Vijay Condominium, Mehra Industrial Compound, Near Vijay Print Bus Stop, Sakinaka,\nAndheri East Mumbai- 400072 India.','',0,10000.00,'2025-11-01','toReceive','noLimit',NULL,1,'2025-11-01 05:29:28','2025-11-01 05:29:54'),(12,'Gala Traders ','27AABCU9603R1LY','8381023878',2,22,'mukesh.traders@gmail.com','Unit No 41, Ground Floor, V-1 Building, Vijay Condominium, Mehra Industrial Compound, Near Vijay Print Bus Stop, Sakinaka,\nAndheri East Mumbai- 400072 India.','',0,10000.00,'2025-11-01','toReceive','noLimit',NULL,1,'2025-11-01 05:33:47','2025-11-01 05:33:47');
/*!40000 ALTER TABLE `parties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parties_transactions`
--

DROP TABLE IF EXISTS `parties_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parties_transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `party_id` bigint DEFAULT NULL,
  `party_balance` decimal(15,2) DEFAULT NULL,
  `description` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `invoice_id` bigint DEFAULT NULL,
  `invoice_number` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `purchase_id` bigint DEFAULT NULL,
  `purchase_bill_number` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `party_phone` varchar(25) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reference_id` bigint DEFAULT NULL,
  `reference_number` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `reference_type` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `party_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `transaction_type` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `date` datetime NOT NULL,
  `status` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'COMPLETED',
  `created_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT 'system',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `party_total` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_party_id` (`party_id`),
  KEY `idx_transaction_type` (`transaction_type`),
  KEY `idx_date` (`date`),
  KEY `idx_invoice` (`invoice_id`,`invoice_number`),
  KEY `idx_purchase` (`purchase_id`,`purchase_bill_number`),
  KEY `idx_reference` (`reference_type`,`reference_id`),
  KEY `idx_status` (`status`),
  CONSTRAINT `FK3wp2bl757y1ewntdhayvqm4j4` FOREIGN KEY (`party_id`) REFERENCES `parties` (`id`),
  CONSTRAINT `fk_pt_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `new_sales_invoice` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_pt_party` FOREIGN KEY (`party_id`) REFERENCES `parties` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_pt_purchase` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='product_transactions shaped by PartyTransactionDTO fields';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parties_transactions`
--

LOCK TABLES `parties_transactions` WRITE;
/*!40000 ALTER TABLE `parties_transactions` DISABLE KEYS */;
INSERT INTO `parties_transactions` VALUES (21,6,0.00,'Sale of 4 items to Sameer Khan',114,'RS-00001',NULL,NULL,'8381023879',114,'RS-00001','SALES_INVOICE','Sameer Khan','SALE','2025-10-26 13:07:37','COMPLETED','SYSTEM','2025-10-26 13:07:37','SYSTEM','2025-10-26 13:07:37',3260.00),(22,7,3850.00,'Sale of 1 items to Sohaib',115,'RS-00115',NULL,NULL,'7030228602',115,'RS-00115','SALES_INVOICE','Sohaib','SALE','2025-10-27 14:59:14','UNPAID','SYSTEM','2025-10-27 14:59:14','SYSTEM','2025-10-27 14:59:14',3850.00),(23,5,0.00,'Sale of 1 items to Monish',116,'RS-00116',NULL,NULL,'9923536215',116,'RS-00116','SALES_INVOICE','Monish','SALE','2025-10-27 15:02:14','COMPLETED','SYSTEM','2025-10-27 15:02:14','SYSTEM','2025-10-27 15:02:14',3850.00),(24,6,0.00,'Sale of 1 items to Sameer Khan',117,'RS-00117',NULL,NULL,'8381023879',117,'RS-00117','SALES_INVOICE','Sameer Khan','SALE','2025-10-27 15:05:18','COMPLETED','SYSTEM','2025-10-27 15:05:18','SYSTEM','2025-10-27 15:05:18',3850.00),(25,5,0.00,'Sale of 1 items to Monish',118,'RS-00118',NULL,NULL,'9923536215',118,'RS-00118','SALES_INVOICE','Monish','SALE','2025-10-27 15:07:51','COMPLETED','SYSTEM','2025-10-27 15:07:51','SYSTEM','2025-10-27 15:07:51',3850.00),(26,5,0.00,'Sale of 1 items to Monish',119,'RS-00119',NULL,NULL,'9923536215',119,'RS-00119','SALES_INVOICE','Monish','SALE','2025-10-27 15:22:28','COMPLETED','SYSTEM','2025-10-27 15:22:28','SYSTEM','2025-10-27 15:22:28',3850.00),(27,5,0.00,'Sale of 3 items to Monish',127,'RS-00127',NULL,NULL,'9923536215',127,'RS-00126','SALES_INVOICE','Monish','SALE','2025-10-30 16:07:21','COMPLETED','SYSTEM','2025-10-30 16:07:21','SYSTEM','2025-10-30 16:07:21',2310.00),(28,5,2310.00,'Sale of 3 items to Monish',128,'RS-00128',NULL,NULL,'9923536215',128,'RS-00128','SALES_INVOICE','Monish','SALE','2025-10-30 16:33:47','UNPAID','SYSTEM','2025-10-30 16:33:47','SYSTEM','2025-10-30 16:33:47',2310.00),(29,5,0.00,'Sale of 3 items to Monish',129,'RS-00129',NULL,NULL,'9923536215',129,'RS-00129','SALES_INVOICE','Monish','SALE','2025-10-30 16:55:03','COMPLETED','SYSTEM','2025-10-30 16:55:03','SYSTEM','2025-10-30 16:55:03',2310.00),(30,10,0.00,'Sale of 1 items to Sameer International Exports Pvt. Ltd.',130,'RS-00130',NULL,NULL,'8381023878',130,'RS-00130','SALES_INVOICE','Sameer International Exports Pvt. Ltd.','SALE','2025-11-01 04:49:14','COMPLETED','SYSTEM','2025-11-01 04:49:14','SYSTEM','2025-11-01 04:49:14',4750.00),(31,10,0.00,'Sale of 1 items to Sameer International Exports Pvt. Ltd.',131,'RS-00131',NULL,NULL,'8381023878',131,'RS-00131','SALES_INVOICE','Sameer International Exports Pvt. Ltd.','SALE','2025-11-01 05:06:40','COMPLETED','SYSTEM','2025-11-01 05:06:40','SYSTEM','2025-11-01 05:06:40',3850.00),(32,10,0.00,'Sale of 1 items to Sameer International Exports Pvt. Ltd.',132,'RS-00132',NULL,NULL,'8381023878',132,'RS-00132','SALES_INVOICE','Sameer International Exports Pvt. Ltd.','SALE','2025-11-01 05:25:19','COMPLETED','SYSTEM','2025-11-01 05:25:19','SYSTEM','2025-11-01 05:25:19',4750.00),(33,12,0.00,'Sale of 4 items to Gala Traders ',133,'RS-00133',NULL,NULL,'8381023878',133,'RS-00133','SALES_INVOICE','Gala Traders ','SALE','2025-11-01 05:36:54','COMPLETED','SYSTEM','2025-11-01 05:36:54','SYSTEM','2025-11-01 05:36:54',8035.92),(34,12,0.00,'Sale of 4 items to Gala Traders ',134,'RS-00134',NULL,NULL,'8381023878',134,'RS-00134','SALES_INVOICE','Gala Traders ','SALE','2025-11-01 08:44:13','COMPLETED','SYSTEM','2025-11-01 08:44:13','SYSTEM','2025-11-01 08:44:13',11180.00),(35,5,0.00,'Sale of 4 items to Monish',135,'RS-00135',NULL,NULL,'9923536215',135,'RS-00135','SALES_INVOICE','Monish','SALE','2025-12-03 06:04:44','COMPLETED','SYSTEM','2025-12-03 06:04:44','SYSTEM','2025-12-03 06:04:44',2948.00),(36,11,0.00,'Sale of 2 items to Mukesh Traders',136,'RS-00136',NULL,NULL,'8381023878',136,'RS-00136','SALES_INVOICE','Mukesh Traders','SALE','2025-12-09 05:09:08','COMPLETED','SYSTEM','2025-12-09 05:09:08','SYSTEM','2025-12-09 05:09:08',770.00);
/*!40000 ALTER TABLE `parties_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_modes`
--

DROP TABLE IF EXISTS `payment_modes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_modes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `mode_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mode_name` (`mode_name`),
  UNIQUE KEY `UK_gjw1p1k54fwgnfm1ygb5pkd5s` (`mode_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_modes`
--

LOCK TABLES `payment_modes` WRITE;
/*!40000 ALTER TABLE `payment_modes` DISABLE KEYS */;
INSERT INTO `payment_modes` VALUES (3,'Card'),(1,'Cash'),(4,'Net Banking'),(2,'UPI'),(5,'Wallet');
/*!40000 ALTER TABLE `payment_modes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_statuses`
--

DROP TABLE IF EXISTS `payment_statuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_statuses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `status_name` (`status_name`),
  UNIQUE KEY `UK_o6las05xmph4if0d1cdd5nvsv` (`status_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_statuses`
--

LOCK TABLES `payment_statuses` WRITE;
/*!40000 ALTER TABLE `payment_statuses` DISABLE KEYS */;
INSERT INTO `payment_statuses` VALUES (3,'Due'),(1,'Paid'),(2,'Partial');
/*!40000 ALTER TABLE `payment_statuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  `can_view` tinyint(1) DEFAULT '1',
  `can_edit` tinyint(1) DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKd9hl74l6x8mex0dkscgjfu29b` (`menu_id`),
  CONSTRAINT `FKd9hl74l6x8mex0dkscgjfu29b` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (64,1,1,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(65,1,2,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(66,1,3,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(68,1,5,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(69,1,6,0,0,'2025-04-16 00:36:27','2025-07-05 02:15:35'),(70,1,7,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(71,1,8,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(72,1,9,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(73,1,10,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(74,1,11,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(75,1,12,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(76,1,13,0,0,'2025-04-16 00:36:27','2025-09-20 11:53:45'),(77,1,14,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(78,1,15,0,0,'2025-04-16 00:36:27','2025-08-10 12:49:26'),(83,1,20,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(84,1,21,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(85,1,22,0,0,'2025-04-16 00:36:27','2025-07-05 02:15:53'),(86,1,23,0,0,'2025-04-16 00:36:27','2025-07-05 02:15:53'),(87,1,24,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(88,1,25,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(89,1,26,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(90,1,27,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(91,1,28,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(93,1,30,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(94,1,31,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(95,1,32,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(96,1,33,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(97,1,34,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(98,1,35,0,0,'2025-06-21 23:51:21','2025-10-10 19:02:54'),(99,1,36,0,0,'2025-06-21 23:51:42','2025-10-10 19:04:39'),(100,1,37,0,0,'2025-06-21 23:52:08','2025-10-10 19:04:39'),(101,1,38,0,0,'2025-06-21 23:52:30','2025-10-10 19:04:39'),(102,1,39,0,0,'2025-06-21 23:52:55','2025-10-10 19:04:39'),(103,1,40,0,0,'2025-06-21 23:53:15','2025-10-10 19:04:39'),(104,1,41,0,0,'2025-06-21 23:53:40','2025-10-10 19:04:39'),(105,1,42,0,0,'2025-06-22 13:10:27','2025-10-10 19:06:11'),(106,1,43,0,0,'2025-06-22 13:10:43','2025-10-10 19:06:11'),(107,1,44,0,0,'2025-06-22 13:11:02','2025-10-10 19:06:11'),(108,1,16,1,1,'2025-06-30 21:24:33','2025-06-30 21:24:33'),(109,1,45,1,1,'2025-07-22 21:26:19','2025-07-22 21:26:19'),(110,1,46,0,0,'2025-08-10 12:47:54','2025-10-09 15:05:01'),(111,1,47,1,1,'2025-08-17 21:42:01','2025-08-17 21:42:01'),(112,1,48,0,0,'2025-10-07 14:55:05','2025-10-11 13:33:29');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions_new`
--

DROP TABLE IF EXISTS `permissions_new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permissions_new` (
  `id` bigint NOT NULL DEFAULT '0',
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  `can_view` tinyint(1) DEFAULT '1',
  `can_edit` tinyint(1) DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions_new`
--

LOCK TABLES `permissions_new` WRITE;
/*!40000 ALTER TABLE `permissions_new` DISABLE KEYS */;
INSERT INTO `permissions_new` VALUES (64,1,1,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(65,1,2,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(66,1,3,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(68,1,5,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(69,1,6,0,0,'2025-04-16 00:36:27','2025-07-05 02:15:35'),(70,1,7,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(71,1,8,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(72,1,9,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(73,1,10,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(74,1,11,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(75,1,12,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(76,1,13,0,0,'2025-04-16 00:36:27','2025-09-20 11:53:45'),(77,1,14,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(78,1,15,0,0,'2025-04-16 00:36:27','2025-08-10 12:49:26'),(83,1,20,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(84,1,21,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(85,1,22,0,0,'2025-04-16 00:36:27','2025-07-05 02:15:53'),(86,1,23,0,0,'2025-04-16 00:36:27','2025-07-05 02:15:53'),(87,1,24,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(88,1,25,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(89,1,26,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(90,1,27,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(91,1,28,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(93,1,30,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(94,1,31,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(95,1,32,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(96,1,33,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(97,1,34,1,1,'2025-04-16 00:36:27','2025-04-16 00:36:27'),(98,1,35,0,0,'2025-06-21 23:51:21','2025-10-10 19:02:54'),(99,1,36,0,0,'2025-06-21 23:51:42','2025-10-10 19:04:39'),(100,1,37,0,0,'2025-06-21 23:52:08','2025-10-10 19:04:39'),(101,1,38,0,0,'2025-06-21 23:52:30','2025-10-10 19:04:39'),(102,1,39,0,0,'2025-06-21 23:52:55','2025-10-10 19:04:39'),(103,1,40,0,0,'2025-06-21 23:53:15','2025-10-10 19:04:39'),(104,1,41,0,0,'2025-06-21 23:53:40','2025-10-10 19:04:39'),(105,1,42,0,0,'2025-06-22 13:10:27','2025-10-10 19:06:11'),(106,1,43,0,0,'2025-06-22 13:10:43','2025-10-10 19:06:11'),(107,1,44,0,0,'2025-06-22 13:11:02','2025-10-10 19:06:11'),(108,1,16,1,1,'2025-06-30 21:24:33','2025-06-30 21:24:33'),(109,1,45,1,1,'2025-07-22 21:26:19','2025-07-22 21:26:19'),(110,1,46,0,0,'2025-08-10 12:47:54','2025-10-09 15:05:01'),(111,1,47,1,1,'2025-08-17 21:42:01','2025-08-17 21:42:01'),(112,1,48,0,0,'2025-10-07 14:55:05','2025-10-11 13:33:29');
/*!40000 ALTER TABLE `permissions_new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categories`
--

DROP TABLE IF EXISTS `product_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories`
--

LOCK TABLES `product_categories` WRITE;
/*!40000 ALTER TABLE `product_categories` DISABLE KEYS */;
INSERT INTO `product_categories` VALUES (1,'T-Shirts','2025-04-13 22:41:27',_binary ''),(2,'Shirts','2025-04-13 22:41:27',_binary ''),(3,'Jeans','2025-04-13 22:41:27',_binary ''),(4,'Shorts','2025-04-13 22:41:27',_binary ''),(5,'Frocks','2025-04-13 22:41:27',_binary ''),(6,'Dresses','2025-04-13 22:41:27',_binary ''),(7,'Leggings','2025-04-13 22:41:27',_binary ''),(8,'Skirts','2025-04-13 22:41:27',_binary ''),(9,'Jackets','2025-04-13 22:41:27',_binary ''),(10,'Sweaters','2025-04-13 22:41:27',_binary ''),(11,'Onesies','2025-04-13 22:41:27',_binary ''),(12,'Nightwear','2025-04-13 22:41:27',_binary ''),(13,'Ethnic Wear','2025-04-13 22:41:27',_binary ''),(14,'School Uniforms','2025-04-13 22:41:27',_binary ''),(15,'Accessories','2025-04-13 22:41:27',_binary ''),(16,'Footwear','2025-04-13 22:41:27',_binary ''),(17,'Pants','2025-07-01 23:48:43',_binary ''),(18,'XYZ','2025-08-20 13:01:50',_binary ''),(19,'Bangles','2025-10-22 19:17:22',_binary ''),(20,'Baby Products','2025-10-22 19:37:46',_binary '');
/*!40000 ALTER TABLE `product_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_categories_new`
--

DROP TABLE IF EXISTS `product_categories_new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_categories_new` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` text,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_categories_new`
--

LOCK TABLES `product_categories_new` WRITE;
/*!40000 ALTER TABLE `product_categories_new` DISABLE KEYS */;
INSERT INTO `product_categories_new` VALUES (1,'T-Shirts','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(2,'Shirts','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(3,'Jeans','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(4,'Shorts','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(5,'Frocks','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(6,'Dresses','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(7,'Leggings','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(8,'Skirts','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(9,'Jackets','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(10,'Sweaters','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(11,'Onesies','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(12,'Nightwear','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(13,'Ethnic Wear','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(14,'School Uniforms','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(15,'Accessories','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(16,'Footwear','2025-04-13 22:41:27',_binary '',NULL,NULL,NULL),(17,'Pants','2025-07-01 23:48:43',_binary '',NULL,NULL,NULL);
/*!40000 ALTER TABLE `product_categories_new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_stock`
--

DROP TABLE IF EXISTS `product_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_stock` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `opening_quantity` decimal(10,2) DEFAULT '0.00',
  `current_quantity` decimal(10,2) DEFAULT '0.00',
  `min_stock` decimal(10,2) DEFAULT '0.00',
  `max_stock` decimal(10,2) DEFAULT '999999.99',
  `location` varchar(255) DEFAULT NULL,
  `as_of_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_product_stock` (`product_id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_current_quantity` (`current_quantity`),
  KEY `idx_min_stock` (`min_stock`),
  KEY `idx_location` (`location`),
  CONSTRAINT `product_stock_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products_new` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_stock`
--

LOCK TABLES `product_stock` WRITE;
/*!40000 ALTER TABLE `product_stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `product_transaction_summary`
--

DROP TABLE IF EXISTS `product_transaction_summary`;
/*!50001 DROP VIEW IF EXISTS `product_transaction_summary`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `product_transaction_summary` AS SELECT 
 1 AS `product_id`,
 1 AS `product_name`,
 1 AS `total_in`,
 1 AS `total_out`,
 1 AS `net_quantity`,
 1 AS `total_transactions`,
 1 AS `last_transaction_date`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `product_transactions`
--

DROP TABLE IF EXISTS `product_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_id` bigint NOT NULL,
  `transaction_type` enum('SALE','PURCHASE','STOCK_ADJUSTMENT','STOCK_TRANSFER','RETURN','DAMAGE','EXPIRY') NOT NULL,
  `reference_number` varchar(100) DEFAULT NULL,
  `reference_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `quantity` decimal(10,2) NOT NULL,
  `unit_price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `total_amount` decimal(12,2) NOT NULL DEFAULT '0.00',
  `description` text,
  `transaction_date` datetime NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `notes` varchar(1000) DEFAULT NULL,
  `reference_id` bigint DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `total_value` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_product_id` (`product_id`),
  KEY `idx_transaction_type` (`transaction_type`),
  KEY `idx_transaction_date` (`transaction_date`),
  KEY `idx_reference_number` (`reference_number`),
  KEY `idx_product_date_range` (`product_id`,`transaction_date`),
  KEY `idx_type_date` (`transaction_type`,`transaction_date`),
  CONSTRAINT `FKbg52tks1f9aestxmmrdpmxd24` FOREIGN KEY (`product_id`) REFERENCES `products_new` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_transactions`
--

LOCK TABLES `product_transactions` WRITE;
/*!40000 ALTER TABLE `product_transactions` DISABLE KEYS */;
INSERT INTO `product_transactions` VALUES (211,4,'SALE','RS-00001','SALES_INVOICE',1.00,450.00,0.00,'Sale of 1 units of Brown Pant - 36\" at ₹450.00 per unit','2025-10-18 03:32:19','SYSTEM','2025-10-17 22:02:19','2025-10-18 09:02:19','Invoice: RS-00001, Customer: Monish',97,'COMPLETED',450.00),(212,5,'SALE','RS-00001','SALES_INVOICE',1.00,600.00,0.00,'Sale of 1 units of White Shirt - 46\" at ₹600.00 per unit','2025-10-18 03:32:19','SYSTEM','2025-10-17 22:02:19','2025-10-18 09:02:19','Invoice: RS-00001, Customer: Monish',97,'COMPLETED',600.00),(213,7,'SALE','RS-00001','SALES_INVOICE',1.00,850.00,0.00,'Sale of 1 units of Caprie Blue - 16\" at ₹850.00 per unit','2025-10-18 03:32:19','SYSTEM','2025-10-17 22:02:19','2025-10-18 09:02:19','Invoice: RS-00001, Customer: Monish',97,'COMPLETED',850.00),(214,4,'SALE','RS-00098','SALES_INVOICE',1.00,450.00,0.00,'Sale of 1 units of Brown Pant - 36\" at ₹450.00 per unit','2025-10-18 06:53:05','SYSTEM','2025-10-18 01:23:05','2025-10-18 12:23:05','Invoice: RS-00098, Customer: Sameer Khan',98,'COMPLETED',450.00),(215,5,'SALE','RS-00098','SALES_INVOICE',1.00,600.00,0.00,'Sale of 1 units of White Shirt - 46\" at ₹600.00 per unit','2025-10-18 06:53:05','SYSTEM','2025-10-18 01:23:05','2025-10-18 12:23:05','Invoice: RS-00098, Customer: Sameer Khan',98,'COMPLETED',600.00),(216,4,'SALE','RS-00099','SALES_INVOICE',1.00,450.00,0.00,'Sale of 1 units of Brown Pant - 36\" at ₹450.00 per unit','2025-10-18 06:56:16','SYSTEM','2025-10-18 01:26:16','2025-10-18 12:26:16','Invoice: RS-00099, Customer: Sohaib',99,'COMPLETED',450.00),(217,5,'SALE','RS-00099','SALES_INVOICE',1.00,600.00,0.00,'Sale of 1 units of White Shirt - 46\" at ₹600.00 per unit','2025-10-18 06:56:16','SYSTEM','2025-10-18 01:26:16','2025-10-18 12:26:16','Invoice: RS-00099, Customer: Sohaib',99,'COMPLETED',600.00),(218,7,'SALE','RS-00099','SALES_INVOICE',1.00,850.00,0.00,'Sale of 1 units of Caprie Blue - 16\" at ₹850.00 per unit','2025-10-18 06:56:16','SYSTEM','2025-10-18 01:26:16','2025-10-18 12:26:16','Invoice: RS-00099, Customer: Sohaib',99,'COMPLETED',850.00),(219,4,'STOCK_ADJUSTMENT','ADJ-11','STOCK_ADJUSTMENT',10.00,400.00,0.00,'Added 10 items to the stock','2025-10-19 11:03:11','SYSTEM','2025-10-19 05:33:11','2025-10-19 11:03:10',NULL,11,'COMPLETED',4000.00),(220,4,'STOCK_ADJUSTMENT','ADJ-12','STOCK_ADJUSTMENT',10.00,400.00,0.00,'Added 10 items to the stock','2025-10-19 11:03:11','SYSTEM','2025-10-19 05:33:11','2025-10-19 11:03:10',NULL,12,'COMPLETED',4000.00),(221,4,'STOCK_ADJUSTMENT','ADJ-10','STOCK_ADJUSTMENT',10.00,400.00,0.00,'Added 10 items to the stock','2025-10-19 11:03:11','SYSTEM','2025-10-19 05:33:11','2025-10-19 11:03:10',NULL,10,'COMPLETED',4000.00),(222,4,'STOCK_ADJUSTMENT','ADJ-13','STOCK_ADJUSTMENT',20.00,300.00,0.00,'Reduced 20 items from the stock','2025-10-19 11:04:03','SYSTEM','2025-10-19 05:34:03','2025-10-19 11:04:02',NULL,13,'COMPLETED',6000.00),(223,5,'STOCK_ADJUSTMENT','ADJ-15','STOCK_ADJUSTMENT',10.00,200.00,0.00,'Blah Blah Blah','2025-10-19 11:06:28','SYSTEM','2025-10-19 05:36:28','2025-10-19 11:06:28',NULL,15,'COMPLETED',2000.00),(224,4,'STOCK_ADJUSTMENT','ADJ-16','STOCK_ADJUSTMENT',30.00,200.00,0.00,'Added 30 pieces','2025-10-19 12:04:55','SYSTEM','2025-10-19 06:34:55','2025-10-19 12:04:55',NULL,16,'COMPLETED',6000.00),(225,4,'STOCK_ADJUSTMENT','ADJ-17','STOCK_ADJUSTMENT',0.00,200.00,0.00,'Blah blah','2025-10-19 12:05:30','SYSTEM','2025-10-19 06:35:30','2025-10-19 12:05:29',NULL,17,'COMPLETED',0.00),(226,4,'STOCK_ADJUSTMENT','ADJ-18','STOCK_ADJUSTMENT',30.00,300.00,0.00,'Blah Blah','2025-10-19 12:06:12','SYSTEM','2025-10-19 06:36:12','2025-10-19 12:06:12',NULL,18,'COMPLETED',9000.00),(227,4,'SALE','RS-00100','SALES_INVOICE',1.00,450.00,0.00,'Sale of 1 units of Brown Pant - 36\" at ₹450.00 per unit','2025-10-20 06:18:40','SYSTEM','2025-10-20 00:48:40','2025-10-20 11:48:40','Invoice: RS-00100, Customer: Monish',100,'COMPLETED',450.00),(228,5,'SALE','RS-00100','SALES_INVOICE',1.00,600.00,0.00,'Sale of 1 units of White Shirt - 46\" at ₹600.00 per unit','2025-10-20 06:18:40','SYSTEM','2025-10-20 00:48:40','2025-10-20 11:48:40','Invoice: RS-00100, Customer: Monish',100,'COMPLETED',600.00),(229,7,'SALE','RS-00100','SALES_INVOICE',1.00,850.00,0.00,'Sale of 1 units of Caprie Blue - 16\" at ₹850.00 per unit','2025-10-20 06:18:40','SYSTEM','2025-10-20 00:48:40','2025-10-20 11:48:40','Invoice: RS-00100, Customer: Monish',100,'COMPLETED',850.00),(230,4,'SALE','RS-00101','SALES_INVOICE',1.00,450.00,0.00,'Sale of 1 units of Brown Pant - 36\" at ₹450.00 per unit','2025-10-22 03:41:35','SYSTEM','2025-10-21 22:11:35','2025-10-22 09:11:35','Invoice: RS-00101, Customer: Shree Krishna Clothing and Garments Shop',101,'COMPLETED',450.00),(231,5,'SALE','RS-00101','SALES_INVOICE',1.00,600.00,0.00,'Sale of 1 units of White Shirt - 46\" at ₹600.00 per unit','2025-10-22 03:41:35','SYSTEM','2025-10-21 22:11:35','2025-10-22 09:11:35','Invoice: RS-00101, Customer: Shree Krishna Clothing and Garments Shop',101,'COMPLETED',600.00),(232,7,'SALE','RS-00101','SALES_INVOICE',1.00,850.00,0.00,'Sale of 1 units of Caprie Blue - 16\" at ₹850.00 per unit','2025-10-22 03:41:35','SYSTEM','2025-10-21 22:11:35','2025-10-22 09:11:35','Invoice: RS-00101, Customer: Shree Krishna Clothing and Garments Shop',101,'COMPLETED',850.00);
/*!40000 ALTER TABLE `product_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `barcode` varchar(100) NOT NULL,
  `category_id` int DEFAULT NULL,
  `tax_percent` decimal(5,2) DEFAULT '0.00',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_active` tinyint(1) DEFAULT '1',
  `size` varchar(255) DEFAULT NULL,
  `mrp` decimal(10,2) DEFAULT NULL,
  `buy_price` double DEFAULT NULL,
  `stock_date` date DEFAULT NULL,
  `stock_quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `barcode` (`barcode`),
  UNIQUE KEY `UK_qfr8vf85k3q1xinifvsl1eynf` (`barcode`),
  KEY `FK6t5dtw6tyo83ywljwohuc6g7k` (`category_id`),
  CONSTRAINT `FK6t5dtw6tyo83ywljwohuc6g7k` FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=269 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (11,'Sport Shoes','CFK000011',16,5.00,'2025-04-19 15:19:32','2025-07-01 13:50:02',1,'16',650.00,NULL,NULL,NULL),(12,'Capri','CFK000012',3,5.00,'2025-04-19 17:55:07','2025-04-19 17:55:07',1,'16',530.00,NULL,NULL,NULL),(13,'Full Sleeve Hoodie','CFK000013',9,12.00,'2025-04-19 18:53:13','2025-04-19 18:53:13',1,'16',450.00,NULL,NULL,NULL),(14,'Hoodie 1','CFK000007',1,5.00,'2025-04-20 14:53:11','2025-04-20 14:53:11',1,'4-5 Years',899.00,NULL,NULL,NULL),(15,'Hoodie 2','CFK000002',2,12.00,'2025-04-20 14:53:11','2025-04-20 14:53:11',1,'5-6 Years',899.00,NULL,NULL,NULL),(16,'T-Shirt 1','CFK000003',1,5.00,'2025-04-20 14:53:11','2025-04-20 14:53:11',1,'6-7 Years',499.00,NULL,NULL,NULL),(17,'T-Shirt 2','CFK000004',2,5.00,'2025-04-20 14:53:11','2025-04-20 14:53:11',1,'7-8 Years',499.00,NULL,NULL,NULL),(18,'Jeans 1','CFK000005',3,12.00,'2025-04-20 14:53:11','2025-04-20 14:53:11',1,'5-6 Years',999.00,NULL,NULL,NULL),(19,'Jeans 2','CFK000006',3,12.00,'2025-04-20 14:53:11','2025-04-20 14:53:11',1,'6-7 Years',1049.00,NULL,NULL,NULL),(26,'Hoodie 1','CFK000008',1,5.00,'2025-04-20 14:55:46','2025-04-20 14:55:46',1,'4-5 Years',899.00,NULL,NULL,NULL),(27,'Hoodie 2','CFK000009',2,12.00,'2025-04-20 14:55:46','2025-04-20 14:55:46',1,'5-6 Years',899.00,NULL,NULL,NULL),(28,'T-Shirt 1','CFK000010',1,5.00,'2025-04-20 14:55:46','2025-04-20 14:55:46',1,'6-7 Years',499.00,NULL,NULL,NULL),(29,'T-Shirt 2','CFK000014',2,5.00,'2025-04-20 14:55:46','2025-04-20 14:55:46',1,'7-8 Years',499.00,NULL,NULL,NULL),(30,'Jeans 1','CFK000015',3,12.00,'2025-04-20 14:55:46','2025-04-20 14:55:46',1,'5-6 Years',999.00,NULL,NULL,NULL),(31,'Jeans 2','CFK000016',3,12.00,'2025-04-20 14:55:46','2025-04-20 14:55:46',1,'6-7 Years',1049.00,NULL,NULL,NULL),(34,'Caprie - Red','CFKFBD773F5',3,5.00,'2025-07-02 04:13:02','2025-07-02 04:13:02',1,'16\"',586.01,NULL,NULL,NULL),(35,'Caprie - Red','CFK013653B0',3,5.00,'2025-07-02 04:13:20','2025-07-02 04:13:20',1,'18\"',432.31,NULL,NULL,NULL),(36,'Caprie - Red','CFKF22086FA',3,5.00,'2025-07-02 04:13:20','2025-07-02 04:13:20',1,'20\"',513.48,NULL,NULL,NULL),(37,'Caprie - Red','CFK8BBB531A',3,5.00,'2025-07-02 04:13:20','2025-07-02 04:13:20',1,'22\"',409.60,NULL,NULL,NULL),(38,'Caprie - Red','CFKB192C9D1',3,5.00,'2025-07-02 04:13:20','2025-07-02 04:13:20',1,'24\"',546.34,NULL,NULL,NULL),(39,'Caprie - black','CFK80496923',3,5.00,'2025-07-02 04:13:20','2025-07-02 04:13:20',1,'16\"',478.27,NULL,NULL,NULL),(40,'Caprie - black','CFK9592FAD8',3,5.00,'2025-07-02 04:13:20','2025-07-02 04:13:20',1,'18\"',465.82,NULL,NULL,NULL),(41,'Caprie - black','CFK5921251B',3,5.00,'2025-07-02 04:13:21','2025-07-02 04:13:21',1,'20\"',498.41,NULL,NULL,NULL),(42,'Caprie - black','CFK5E13FDA0',3,5.00,'2025-07-02 04:13:21','2025-07-02 04:13:21',1,'22\"',441.68,NULL,NULL,NULL),(43,'Caprie - black','CFKD2C2038A',3,5.00,'2025-07-02 04:13:21','2025-07-02 04:13:21',1,'24\"',516.34,NULL,NULL,NULL),(44,'Caprie - Brown','CFK4AB79CEE',3,5.00,'2025-07-02 04:13:21','2025-07-02 04:13:21',1,'16\"',254.40,NULL,NULL,NULL),(45,'Caprie - Brown','CFK5EA15F9D',3,5.00,'2025-07-02 04:13:21','2025-07-02 04:13:21',1,'18\"',431.82,NULL,NULL,NULL),(46,'Caprie - Brown','CFK51029A63',3,5.00,'2025-07-02 04:13:21','2025-07-02 04:13:21',1,'20\"',251.54,NULL,NULL,NULL),(47,'Caprie - Brown','CFK19D2BA24',3,5.00,'2025-07-02 04:13:21','2025-07-02 04:13:21',1,'22\"',519.85,NULL,NULL,NULL),(48,'Caprie - Brown','CFKFFA9AE1C',3,5.00,'2025-07-02 04:13:21','2025-07-02 04:13:21',1,'24\"',425.99,NULL,NULL,NULL),(49,'Caprie - Blue','CFK3E42B684',3,5.00,'2025-07-02 04:13:21','2025-07-02 04:13:21',1,'16\"',249.24,NULL,NULL,NULL),(50,'Caprie - Blue','CFK8A4A8C26',3,5.00,'2025-07-02 04:13:21','2025-07-02 04:13:21',1,'18\"',588.17,NULL,NULL,NULL),(51,'Caprie - Blue','CFK37F88A43',3,5.00,'2025-07-02 04:13:22','2025-07-02 04:13:22',1,'20\"',472.19,NULL,NULL,NULL),(52,'Caprie - Blue','CFK8DED9248',3,5.00,'2025-07-02 04:13:22','2025-07-02 04:13:22',1,'22\"',355.76,NULL,NULL,NULL),(53,'Caprie - Blue','CFK5EA10139',3,5.00,'2025-07-02 04:13:22','2025-07-02 04:13:22',1,'24\"',549.05,NULL,NULL,NULL),(54,'T-shirt - Red','CFK1A750771',1,5.00,'2025-07-02 04:13:22','2025-07-02 04:13:22',1,'16\"',542.18,NULL,NULL,NULL),(55,'T-shirt - Red','CFK80BA1CA9',1,5.00,'2025-07-02 04:13:22','2025-07-02 04:13:22',1,'18\"',421.32,NULL,NULL,NULL),(56,'T-shirt - Blue','CFK48C0D1E1',1,5.00,'2025-07-02 04:13:22','2025-07-02 04:13:22',1,'20\"',307.68,NULL,NULL,NULL),(57,'T-shirt - Green','CFK491ABE7B',1,5.00,'2025-07-02 04:13:22','2025-07-02 04:13:22',1,'22\"',494.18,NULL,NULL,NULL),(58,'T-shirt - Yellow','CFK319B4FD4',1,5.00,'2025-07-02 04:13:22','2025-07-02 04:13:22',1,'24\"',461.74,NULL,NULL,NULL),(59,'T-shirt - Black','CFKA78E1254',1,5.00,'2025-07-02 04:13:23','2025-07-02 04:13:23',1,'26\"',425.33,NULL,NULL,NULL),(60,'T-shirt - White','CFK1FDF0C28',1,5.00,'2025-07-02 04:13:23','2025-07-02 04:13:23',1,'28\"',572.71,NULL,NULL,NULL),(61,'T-shirt - Orange','CFKAD37BFD2',1,5.00,'2025-07-02 04:13:23','2025-07-02 04:13:23',1,'30\"',485.04,NULL,NULL,NULL),(62,'T-shirt - Purple','CFK51931384',1,5.00,'2025-07-02 04:13:23','2025-07-02 04:13:23',1,'32\"',356.08,NULL,NULL,NULL),(63,'T-shirt - Pink','CFK7FCE3E09',1,5.00,'2025-07-02 04:13:23','2025-07-02 04:13:23',1,'34\"',409.19,NULL,NULL,NULL),(64,'T-shirt - Gray','CFK5577CBB4',1,5.00,'2025-07-02 04:13:23','2025-07-02 04:13:23',1,'36\"',567.05,NULL,NULL,NULL),(65,'T-shirt - Blue','CFK4C4312E3',1,5.00,'2025-07-02 04:13:24','2025-07-02 04:13:24',1,'18\"',338.62,NULL,NULL,NULL),(66,'T-shirt - Green','CFKE1F38648',1,5.00,'2025-07-02 04:13:24','2025-07-02 04:13:24',1,'20\"',483.97,NULL,NULL,NULL),(67,'T-shirt - Yellow','CFKECE5B0A6',1,5.00,'2025-07-02 04:13:24','2025-07-02 04:13:24',1,'22\"',494.90,NULL,NULL,NULL),(68,'T-shirt - Black','CFK2C276229',1,5.00,'2025-07-02 04:13:24','2025-07-02 04:13:24',1,'24\"',490.21,NULL,NULL,NULL),(69,'T-shirt - White','CFKC519A0B1',1,5.00,'2025-07-02 04:13:24','2025-07-02 04:13:24',1,'26\"',298.27,NULL,NULL,NULL),(70,'T-shirt - Orange','CFKCCC7A967',1,5.00,'2025-07-02 04:13:24','2025-07-02 04:13:24',1,'28\"',256.37,NULL,NULL,NULL),(71,'T-shirt - Purple','CFK94E947C3',1,5.00,'2025-07-02 04:13:25','2025-07-02 04:13:25',1,'30\"',400.58,NULL,NULL,NULL),(72,'T-shirt - Pink','CFK1304517F',1,5.00,'2025-07-02 04:13:25','2025-07-02 04:13:25',1,'32\"',548.51,NULL,NULL,NULL),(73,'T-shirt - Gray','CFKCE82C1D2',1,5.00,'2025-07-02 04:13:25','2025-07-02 04:13:25',1,'34\"',304.92,NULL,NULL,NULL),(74,'T-shirt - Red','CFK953805AE',1,5.00,'2025-07-02 04:13:25','2025-07-02 04:13:25',1,'36\"',439.03,NULL,NULL,NULL),(75,'T-shirt - Blue','CFK96EB0FA1',1,5.00,'2025-07-02 04:13:25','2025-07-02 04:13:25',1,'16\"',270.17,NULL,NULL,NULL),(76,'T-shirt - Green','CFK89F8155A',1,5.00,'2025-07-02 04:13:25','2025-07-02 04:13:25',1,'18\"',436.33,NULL,NULL,NULL),(77,'T-shirt - Yellow','CFK6EE4E1D4',1,5.00,'2025-07-02 04:13:26','2025-07-02 04:13:26',1,'20\"',256.44,NULL,NULL,NULL),(78,'T-shirt - Black','CFK208FFC6B',1,5.00,'2025-07-02 04:13:26','2025-07-02 04:13:26',1,'22\"',422.20,NULL,NULL,NULL),(79,'T-shirt - White','CFKC5C6053B',1,5.00,'2025-07-02 04:13:26','2025-07-02 04:13:26',1,'24\"',415.14,NULL,NULL,NULL),(80,'T-shirt - Orange','CFKD8E23EF6',1,5.00,'2025-07-02 04:13:26','2025-07-02 04:13:26',1,'26\"',506.45,NULL,NULL,NULL),(81,'T-shirt - Purple','CFK905D1C63',1,5.00,'2025-07-02 04:13:26','2025-07-02 04:13:26',1,'28\"',320.82,NULL,NULL,NULL),(82,'T-shirt - Pink','CFK8B00AF3E',1,5.00,'2025-07-02 04:13:26','2025-07-02 04:13:26',1,'30\"',261.86,NULL,NULL,NULL),(83,'T-shirt - Gray','CFK13BA9369',1,5.00,'2025-07-02 04:13:26','2025-07-02 04:13:26',1,'32\"',598.84,NULL,NULL,NULL),(84,'T-shirt - Red','CFK32A32F18',1,5.00,'2025-07-02 04:13:27','2025-07-02 04:13:27',1,'34\"',295.87,NULL,NULL,NULL),(85,'T-shirt - Blue','CFK7D378D5F',1,5.00,'2025-07-02 04:13:27','2025-07-02 04:13:27',1,'36\"',246.70,NULL,NULL,NULL),(86,'T-shirt - Green','CFK8EA66748',1,5.00,'2025-07-02 04:13:27','2025-07-02 04:13:27',1,'16\"',421.92,NULL,NULL,NULL),(87,'T-shirt - Yellow','CFK3C04AB9F',1,5.00,'2025-07-02 04:13:27','2025-07-02 04:13:27',1,'18\"',330.89,NULL,NULL,NULL),(88,'T-shirt - Black','CFK11324AEC',1,5.00,'2025-07-02 04:13:27','2025-07-02 04:13:27',1,'20\"',261.35,NULL,NULL,NULL),(89,'T-shirt - White','CFK257F06F3',1,5.00,'2025-07-02 04:13:28','2025-07-02 04:13:28',1,'22\"',283.66,NULL,NULL,NULL),(90,'T-shirt - Orange','CFKB9E6A6D4',1,5.00,'2025-07-02 04:13:28','2025-07-02 04:13:28',1,'24\"',495.71,NULL,NULL,NULL),(91,'T-shirt - Purple','CFK0CE06C87',1,5.00,'2025-07-02 04:13:28','2025-07-02 04:13:28',1,'26\"',295.92,NULL,NULL,NULL),(92,'T-shirt - Pink','CFKE09F0DBE',1,5.00,'2025-07-02 04:13:28','2025-07-02 04:13:28',1,'28\"',314.15,NULL,NULL,NULL),(93,'T-shirt - Gray','CFKA5A13427',1,5.00,'2025-07-02 04:13:28','2025-07-02 04:13:28',1,'30\"',557.30,NULL,NULL,NULL),(94,'T-shirt - Red','CFK0A89A647',1,5.00,'2025-07-02 04:13:29','2025-07-02 04:13:29',1,'32\"',586.38,NULL,NULL,NULL),(95,'T-shirt - Blue','CFK4EB53026',1,5.00,'2025-07-02 04:13:29','2025-07-02 04:13:29',1,'34\"',319.50,NULL,NULL,NULL),(96,'T-shirt - Green','CFK3C81F936',1,5.00,'2025-07-02 04:13:29','2025-07-02 04:13:29',1,'36\"',520.42,NULL,NULL,NULL),(97,'T-shirt - Yellow','CFKF65E3AE4',1,5.00,'2025-07-02 04:13:30','2025-07-02 04:13:30',1,'16\"',524.46,NULL,NULL,NULL),(98,'T-shirt - Black','CFKEDBE8CA2',1,5.00,'2025-07-02 04:13:30','2025-07-02 04:13:30',1,'18\"',286.39,NULL,NULL,NULL),(99,'T-shirt - White','CFK96BC0D6B',1,5.00,'2025-07-02 04:13:30','2025-07-02 04:13:30',1,'20\"',248.18,NULL,NULL,NULL),(100,'T-shirt - Orange','CFK5F718884',1,5.00,'2025-07-02 04:13:30','2025-07-02 04:13:30',1,'22\"',352.58,NULL,NULL,NULL),(101,'T-shirt - Purple','CFK229516CD',1,5.00,'2025-07-02 04:13:31','2025-07-02 04:13:31',1,'24\"',470.41,NULL,NULL,NULL),(102,'T-shirt - Pink','CFK8710C7E4',1,5.00,'2025-07-02 04:13:31','2025-07-02 04:13:31',1,'26\"',502.76,NULL,NULL,NULL),(103,'T-shirt - Gray','CFK52B8786D',1,5.00,'2025-07-02 04:13:31','2025-07-02 04:13:31',1,'28\"',269.35,NULL,NULL,NULL),(104,'T-shirt - Red','CFK9286EA77',1,5.00,'2025-07-02 04:13:32','2025-07-02 04:13:32',1,'30\"',241.57,NULL,NULL,NULL),(105,'T-shirt - Blue','CFK2BABB6DA',1,5.00,'2025-07-02 04:13:32','2025-07-02 04:13:32',1,'32\"',382.37,NULL,NULL,NULL),(106,'T-shirt - Green','CFK8AD46914',1,5.00,'2025-07-02 04:13:32','2025-07-02 04:13:32',1,'34\"',248.22,NULL,NULL,NULL),(107,'T-shirt - Yellow','CFK7A4A0936',1,5.00,'2025-07-02 04:13:32','2025-07-02 04:13:32',1,'36\"',363.11,NULL,NULL,NULL),(108,'T-shirt - Black','CFK536C6155',1,5.00,'2025-07-02 04:13:33','2025-07-02 04:13:33',1,'16\"',491.41,NULL,NULL,NULL),(109,'T-shirt - White','CFKC8F9C717',1,5.00,'2025-07-02 04:13:33','2025-07-02 04:13:33',1,'18\"',262.87,NULL,NULL,NULL),(110,'T-shirt - Orange','CFKB042047C',1,5.00,'2025-07-02 04:13:33','2025-07-02 04:13:33',1,'20\"',477.08,NULL,NULL,NULL),(111,'T-shirt - Purple','CFK1CAB32C3',1,5.00,'2025-07-02 04:13:34','2025-07-02 04:13:34',1,'22\"',359.94,NULL,NULL,NULL),(112,'T-shirt - Pink','CFK16427BF8',1,5.00,'2025-07-02 04:13:34','2025-07-02 04:13:34',1,'24\"',274.52,NULL,NULL,NULL),(113,'T-shirt - Gray','CFK75845BA9',1,5.00,'2025-07-02 04:13:34','2025-07-02 04:13:34',1,'26\"',511.56,NULL,NULL,NULL),(114,'T-shirt - Red','CFK61EE86C4',1,5.00,'2025-07-02 04:13:34','2025-07-02 04:13:34',1,'28\"',530.78,NULL,NULL,NULL),(115,'T-shirt - Blue','CFK1DBD9FEE',1,5.00,'2025-07-02 04:13:35','2025-07-02 04:13:35',1,'30\"',316.69,NULL,NULL,NULL),(116,'T-shirt - Green','CFK88626745',1,5.00,'2025-07-02 04:13:36','2025-07-02 04:13:36',1,'32\"',390.08,NULL,NULL,NULL),(117,'T-shirt - Yellow','CFKD1AEB3DD',1,5.00,'2025-07-02 04:13:36','2025-07-02 04:13:36',1,'34\"',485.41,NULL,NULL,NULL),(118,'T-shirt - Black','CFK6D8F90A4',1,5.00,'2025-07-02 04:13:37','2025-07-02 04:13:37',1,'36\"',569.22,NULL,NULL,NULL),(119,'T-shirt - White','CFK6CCCD838',1,5.00,'2025-07-02 04:13:37','2025-07-02 04:13:37',1,'16\"',297.44,NULL,NULL,NULL),(120,'T-shirt - Orange','CFKD4B77EE0',1,5.00,'2025-07-02 04:13:37','2025-07-02 04:13:37',1,'18\"',537.53,NULL,NULL,NULL),(121,'T-shirt - Purple','CFK071F52BD',1,5.00,'2025-07-02 04:13:38','2025-07-02 04:13:38',1,'20\"',265.20,NULL,NULL,NULL),(122,'Cotton Pant - Red','CFK3F7B7DF3',17,5.00,'2025-07-02 04:13:38','2025-07-02 04:13:38',1,'32\"',342.37,NULL,NULL,NULL),(123,'Cotton Pant - Blue','CFK7B1DEA27',17,5.00,'2025-07-02 04:13:38','2025-07-02 04:13:38',1,'34\"',462.89,NULL,NULL,NULL),(124,'Cotton Pant - Green','CFKD74F52E9',17,5.00,'2025-07-02 04:13:39','2025-07-02 04:13:39',1,'36\"',378.42,NULL,NULL,NULL),(125,'Cotton Pant - Yellow','CFK0FFDDAAB',17,5.00,'2025-07-02 04:13:39','2025-07-02 04:13:39',1,'22\"',451.54,NULL,NULL,NULL),(126,'Cotton Pant - Black','CFKF2B8012E',17,5.00,'2025-07-02 04:13:39','2025-07-02 04:13:39',1,'24\"',359.28,NULL,NULL,NULL),(127,'Cotton Pant - White','CFKB19AE0A3',17,5.00,'2025-07-02 04:13:40','2025-07-02 04:13:40',1,'26\"',413.53,NULL,NULL,NULL),(128,'Cotton Pant - Orange','CFK90F2CE30',17,5.00,'2025-07-02 04:13:40','2025-07-02 04:13:40',1,'28\"',422.88,NULL,NULL,NULL),(129,'Cotton Pant - Purple','CFK536B34F9',17,5.00,'2025-07-02 04:13:40','2025-07-02 04:13:40',1,'30\"',310.90,NULL,NULL,NULL),(130,'Cotton Pant - Pink','CFK4C445E67',17,5.00,'2025-07-02 04:13:41','2025-07-02 04:13:41',1,'32\"',556.61,NULL,NULL,NULL),(131,'Cotton Pant - Gray','CFK9CBB047B',17,5.00,'2025-07-02 04:13:41','2025-07-02 04:13:41',1,'34\"',432.44,NULL,NULL,NULL),(132,'Cotton Pant - Red','CFK42F7567C',17,5.00,'2025-07-02 04:13:41','2025-07-02 04:13:41',1,'36\"',262.82,NULL,NULL,NULL),(133,'Cotton Pant - Blue','CFKB230C442',17,5.00,'2025-07-02 04:13:42','2025-07-02 04:13:42',1,'22\"',384.22,NULL,NULL,NULL),(134,'Cotton Pant - Green','CFKB624F9BC',17,5.00,'2025-07-02 04:13:42','2025-07-02 04:13:42',1,'24\"',430.91,NULL,NULL,NULL),(135,'Cotton Pant - Yellow','CFK02F2C9A0',17,5.00,'2025-07-02 04:13:42','2025-07-02 04:13:42',1,'26\"',359.40,NULL,NULL,NULL),(136,'Cotton Pant - Black','CFKF7123048',17,5.00,'2025-07-02 04:13:42','2025-07-02 04:13:42',1,'28\"',334.67,NULL,NULL,NULL),(137,'Cotton Pant - White','CFKE9A17E81',17,5.00,'2025-07-02 04:13:43','2025-07-02 04:13:43',1,'30\"',562.18,NULL,NULL,NULL),(138,'Cotton Pant - Orange','CFK3317BAFE',17,5.00,'2025-07-02 04:13:43','2025-07-02 04:13:43',1,'32\"',275.56,NULL,NULL,NULL),(139,'Cotton Pant - Purple','CFKB7510A40',17,5.00,'2025-07-02 04:13:43','2025-07-02 04:13:43',1,'34\"',435.58,NULL,NULL,NULL),(140,'Cotton Pant - Pink','CFK116BD6E8',17,5.00,'2025-07-02 04:13:44','2025-07-02 04:13:44',1,'36\"',498.29,NULL,NULL,NULL),(141,'Cotton Pant - Gray','CFK3C24BC58',17,5.00,'2025-07-02 04:13:44','2025-07-02 04:13:44',1,'22\"',563.99,NULL,NULL,NULL),(142,'Cotton Pant - Red','CFK5289AE6B',17,5.00,'2025-07-02 04:13:44','2025-07-02 04:13:44',1,'24\"',347.08,NULL,NULL,NULL),(143,'Cotton Pant - Blue','CFKEDCBAC49',17,5.00,'2025-07-02 04:13:45','2025-07-02 04:13:45',1,'26\"',577.07,NULL,NULL,NULL),(144,'Cotton Pant - Green','CFK9823B52D',17,5.00,'2025-07-02 04:13:45','2025-07-02 04:13:45',1,'28\"',551.88,NULL,NULL,NULL),(145,'Cotton Pant - Yellow','CFK873B579B',17,5.00,'2025-07-02 04:13:45','2025-07-02 04:13:45',1,'30\"',520.70,NULL,NULL,NULL),(146,'Cotton Pant - Black','CFK8E83B9C9',17,5.00,'2025-07-02 04:13:46','2025-07-02 04:13:46',1,'32\"',359.20,NULL,NULL,NULL),(147,'Cotton Pant - White','CFK691240A0',17,5.00,'2025-07-02 04:13:46','2025-07-02 04:13:46',1,'34\"',259.94,NULL,NULL,NULL),(148,'Cotton Pant - Orange','CFK439D0BEE',17,5.00,'2025-07-02 04:13:47','2025-07-02 04:13:47',1,'36\"',513.46,NULL,NULL,NULL),(149,'Cotton Pant - Purple','CFK7674E277',17,5.00,'2025-07-02 04:13:47','2025-07-02 04:13:47',1,'22\"',341.24,NULL,NULL,NULL),(150,'Cotton Pant - Pink','CFKFCBB182A',17,5.00,'2025-07-02 04:13:48','2025-07-02 04:13:48',1,'24\"',585.04,NULL,NULL,NULL),(151,'Cotton Pant - Gray','CFK8B06E1D2',17,5.00,'2025-07-02 04:13:48','2025-07-02 04:13:48',1,'26\"',300.05,NULL,NULL,NULL),(152,'Cotton Pant - Red','CFK8914AE5C',17,5.00,'2025-07-02 04:13:49','2025-07-02 04:13:49',1,'28\"',540.22,NULL,NULL,NULL),(153,'Cotton Pant - Blue','CFK14B1C133',17,5.00,'2025-07-02 04:13:49','2025-07-02 04:13:49',1,'30\"',573.47,NULL,NULL,NULL),(154,'Cotton Pant - Green','CFKAD6FDB55',17,5.00,'2025-07-02 04:13:50','2025-07-02 04:13:50',1,'32\"',453.12,NULL,NULL,NULL),(155,'Cotton Pant - Yellow','CFK3FD8FC5C',17,5.00,'2025-07-02 04:13:50','2025-07-02 04:13:50',1,'34\"',356.50,NULL,NULL,NULL),(156,'Cotton Pant - Black','CFK039460B0',17,5.00,'2025-07-02 04:13:51','2025-07-02 04:13:51',1,'36\"',248.04,NULL,NULL,NULL),(157,'Cotton Pant - White','CFKAB31AA13',17,5.00,'2025-07-02 04:13:52','2025-07-02 04:13:52',1,'22\"',598.99,NULL,NULL,NULL),(158,'Cotton Pant - Orange','CFK8F92FDFA',17,5.00,'2025-07-02 04:13:53','2025-07-02 04:13:53',1,'24\"',595.00,NULL,NULL,NULL),(159,'Cotton Pant - Purple','CFK7A5D8D5B',17,5.00,'2025-07-02 04:13:54','2025-07-02 04:13:54',1,'26\"',325.30,NULL,NULL,NULL),(160,'Cotton Pant - Pink','CFKF781E0AB',17,5.00,'2025-07-02 04:13:54','2025-07-02 04:13:54',1,'28\"',485.90,NULL,NULL,NULL),(161,'Cotton Pant - Gray','CFKDFE5E46A',17,5.00,'2025-07-02 04:13:55','2025-07-02 04:13:55',1,'30\"',359.74,NULL,NULL,NULL),(162,'Shirt - White','CFK1DDFFB0B',2,5.00,'2025-07-02 04:13:59','2025-07-02 04:13:59',1,'24\"',528.52,NULL,NULL,NULL),(163,'Shirt - Gray','CFKFD6B25D3',2,5.00,'2025-07-02 04:13:59','2025-07-02 04:13:59',1,'26\"',434.81,NULL,NULL,NULL),(164,'Shirt - Red','CFKA6C5E17A',2,5.00,'2025-07-02 04:14:00','2025-07-02 04:14:00',1,'28\"',492.11,NULL,NULL,NULL),(165,'Shirt - Blue','CFKF10E61AD',2,5.00,'2025-07-02 04:14:00','2025-07-02 04:14:00',1,'30\"',306.70,NULL,NULL,NULL),(166,'Shirt - Green','CFK1E64EF87',2,5.00,'2025-07-02 04:14:01','2025-07-02 04:14:01',1,'32\"',500.72,NULL,NULL,NULL),(167,'Cotton-pant - Red','CFK0CCE6E99',17,5.00,'2025-07-02 04:14:02','2025-07-02 04:14:02',1,'16\"',333.65,NULL,NULL,NULL),(168,'Cotton-pant - Blue','CFK7368B817',17,5.00,'2025-07-02 04:14:03','2025-07-02 04:14:03',1,'18\"',260.02,NULL,NULL,NULL),(169,'Cotton-pant - Green','CFKA2A0877C',17,5.00,'2025-07-02 04:14:04','2025-07-02 04:14:04',1,'20\"',497.28,NULL,NULL,NULL),(170,'Cotton-pant - Black','CFKCD4DE207',17,5.00,'2025-07-02 04:14:04','2025-07-02 04:14:04',1,'22\"',544.96,NULL,NULL,NULL),(171,'Cotton-pant - White','CFK90701118',17,5.00,'2025-07-02 04:14:05','2025-07-02 04:14:05',1,'24\"',430.02,NULL,NULL,NULL),(172,'Cotton-pant - Yellow','CFKE3633A67',17,5.00,'2025-07-02 04:14:05','2025-07-02 04:14:05',1,'26\"',326.42,NULL,NULL,NULL),(173,'Cotton-pant - Grey','CFKDBA58FA4',17,5.00,'2025-07-02 04:14:05','2025-07-02 04:14:05',1,'28\"',242.87,NULL,NULL,NULL),(174,'Cotton-pant - Brown','CFKECACD4D8',17,5.00,'2025-07-02 04:14:06','2025-07-02 04:14:06',1,'16\"',287.77,NULL,NULL,NULL),(175,'Cotton-pant - Pink','CFK7A941BAC',17,5.00,'2025-07-02 04:14:07','2025-07-02 04:14:07',1,'18\"',255.50,NULL,NULL,NULL),(176,'Cotton-pant - Purple','CFK8362FF23',17,5.00,'2025-07-02 04:14:07','2025-07-02 04:14:07',1,'20\"',362.70,NULL,NULL,NULL),(177,'Cotton-pant - Orange','CFK9E077418',17,5.00,'2025-07-02 04:14:08','2025-07-02 04:14:08',1,'22\"',269.15,NULL,NULL,NULL),(178,'Cotton-pant - Maroon','CFK4EC32F30',17,5.00,'2025-07-02 04:14:08','2025-07-02 04:14:08',1,'24\"',247.54,NULL,NULL,NULL),(179,'Cotton-pant - Navy','CFK48C0470D',17,5.00,'2025-07-02 04:14:09','2025-07-02 04:14:09',1,'26\"',451.30,NULL,NULL,NULL),(180,'Cotton-pant - Olive','CFK0F80AE62',17,5.00,'2025-07-02 04:14:09','2025-07-02 04:14:09',1,'28\"',404.82,NULL,NULL,NULL),(181,'Cotton-pant - Teal','CFKF0E2DF7F',17,5.00,'2025-07-02 04:14:09','2025-07-02 04:14:09',1,'16\"',250.13,NULL,NULL,NULL),(182,'Cotton-pant - Beige','CFK1C181E73',17,5.00,'2025-07-02 04:14:10','2025-07-02 04:14:10',1,'18\"',322.58,NULL,NULL,NULL),(183,'Cotton-pant - Violet','CFK5D7AC6BE',17,5.00,'2025-07-02 04:14:10','2025-07-02 04:14:10',1,'20\"',530.66,NULL,NULL,NULL),(184,'Cotton-pant - Turquoise','CFK3CC05F57',17,5.00,'2025-07-02 04:14:11','2025-07-02 04:14:11',1,'22\"',409.45,NULL,NULL,NULL),(185,'Cotton-pant - Magenta','CFK1CD51927',17,5.00,'2025-07-02 04:14:12','2025-07-02 04:14:12',1,'24\"',359.24,NULL,NULL,NULL),(186,'Cotton-pant - Lime','CFK17876889',17,5.00,'2025-07-02 04:14:12','2025-07-02 04:14:12',1,'26\"',344.54,NULL,NULL,NULL),(187,'Bottom-Jeans - Red','CFKA137083E',3,5.00,'2025-07-02 04:14:13','2025-07-02 04:14:13',1,'28\"',489.68,NULL,NULL,NULL),(188,'Bottom-Jeans - Blue','CFK4B7075A9',3,5.00,'2025-07-02 04:14:13','2025-07-02 04:14:13',1,'30\"',286.56,NULL,NULL,NULL),(189,'Bottom-Jeans - Green','CFK1C261151',3,5.00,'2025-07-02 04:14:14','2025-07-02 04:14:14',1,'32\"',424.20,NULL,NULL,NULL),(190,'Bottom-Jeans - Black','CFKA7CE5C6D',3,5.00,'2025-07-02 04:14:14','2025-07-02 04:14:14',1,'34\"',588.23,NULL,NULL,NULL),(191,'Bottom-Jeans - White','CFK210EBE21',3,5.00,'2025-07-02 04:14:15','2025-07-02 04:14:15',1,'36\"',533.47,NULL,NULL,NULL),(192,'Bottom-Jeans - Yellow','CFK805656B7',3,5.00,'2025-07-02 04:14:15','2025-07-02 04:14:15',1,'26\"',320.16,NULL,NULL,NULL),(193,'Bottom-Jeans - Grey','CFK7E0FDF56',3,5.00,'2025-07-02 04:14:16','2025-07-02 04:14:16',1,'28\"',489.84,NULL,NULL,NULL),(194,'Bottom-Jeans - Brown','CFKF5E47753',3,5.00,'2025-07-02 04:14:16','2025-07-02 04:14:16',1,'30\"',268.72,NULL,NULL,NULL),(195,'Bottom-Jeans - Pink','CFKBCFF7EC3',3,5.00,'2025-07-02 04:14:17','2025-07-02 04:14:17',1,'32\"',460.40,NULL,NULL,NULL),(196,'Bottom-Jeans - Purple','CFK99D29A73',3,5.00,'2025-07-02 04:14:17','2025-07-02 04:14:17',1,'34\"',571.28,NULL,NULL,NULL),(197,'Bottom-Jeans - Orange','CFKB26228BC',3,5.00,'2025-07-02 04:14:18','2025-07-02 04:14:18',1,'36\"',575.89,NULL,NULL,NULL),(198,'Bottom-Jeans - Maroon','CFK064E7DC3',3,5.00,'2025-07-02 04:14:18','2025-07-02 04:14:18',1,'26\"',532.30,NULL,NULL,NULL),(199,'Bottom-Jeans - Navy','CFK6D88A6E5',3,5.00,'2025-07-02 04:14:19','2025-07-02 04:14:19',1,'28\"',467.41,NULL,NULL,NULL),(200,'Bottom-Jeans - Olive','CFK5AD685A2',3,5.00,'2025-07-02 04:14:20','2025-07-02 04:14:20',1,'30\"',376.33,NULL,NULL,NULL),(201,'Bottom-Jeans - Teal','CFKCDA8F241',3,5.00,'2025-07-02 04:14:20','2025-07-02 04:14:20',1,'32\"',366.05,NULL,NULL,NULL),(202,'Bottom-Jeans - Beige','CFK3EAB8C78',3,5.00,'2025-07-02 04:14:21','2025-07-02 04:14:21',1,'34\"',339.95,NULL,NULL,NULL),(203,'Bottom-Jeans - Violet','CFKB4283CD5',3,5.00,'2025-07-02 04:14:21','2025-07-02 04:14:21',1,'36\"',467.52,NULL,NULL,NULL),(204,'Bottom-Jeans - Turquoise','CFK21078D23',3,5.00,'2025-07-02 04:14:22','2025-07-02 04:14:22',1,'26\"',369.02,NULL,NULL,NULL),(205,'Bottom-Jeans - Magenta','CFK3777685E',3,5.00,'2025-07-02 04:14:22','2025-07-02 04:14:22',1,'28\"',320.09,NULL,NULL,NULL),(206,'Bottom-Jeans - Lime','CFKAF725140',3,5.00,'2025-07-02 04:14:24','2025-07-02 04:14:24',1,'30\"',590.24,NULL,NULL,NULL),(207,'Bottom-Jeans - Cyan','CFKD6FE88B4',3,5.00,'2025-07-02 04:14:25','2025-07-02 04:14:25',1,'32\"',563.70,NULL,NULL,NULL),(208,'Bottom-Jeans - Indigo','CFK12E724EE',3,5.00,'2025-07-02 04:14:26','2025-07-02 04:14:26',1,'34\"',565.70,NULL,NULL,NULL),(209,'Bottom-Jeans - Gold','CFKF3C31623',3,5.00,'2025-07-02 04:14:26','2025-07-02 04:14:26',1,'36\"',302.71,NULL,NULL,NULL),(210,'Bottom-Jeans - Silver','CFK184B4769',3,5.00,'2025-07-02 04:14:27','2025-07-02 04:14:27',1,'26\"',513.47,NULL,NULL,NULL),(211,'Bottom-Jeans - Coral','CFKDF3F4D21',3,5.00,'2025-07-02 04:14:28','2025-07-02 04:14:28',1,'28\"',583.54,NULL,NULL,NULL),(212,'Bottom-Jeans - Peach','CFK141298F8',3,5.00,'2025-07-02 04:14:28','2025-07-02 04:14:28',1,'30\"',281.59,NULL,NULL,NULL),(213,'Bottom-Jeans - Mint','CFK570F57ED',3,5.00,'2025-07-02 04:14:29','2025-07-02 04:14:29',1,'32\"',430.67,NULL,NULL,NULL),(214,'Bottom-Jeans - Lavender','CFK28AAD378',3,5.00,'2025-07-02 04:14:30','2025-07-02 04:14:30',1,'34\"',309.01,NULL,NULL,NULL),(215,'Bottom-Jeans - Charcoal','CFK20D3EB53',3,5.00,'2025-07-02 04:14:30','2025-07-02 04:14:30',1,'36\"',265.18,NULL,NULL,NULL),(216,'Bottom-Jeans - Tan','CFK9F86D5D9',3,5.00,'2025-07-02 04:14:31','2025-07-02 04:14:31',1,'26\"',469.08,NULL,NULL,NULL),(217,'Bottom-Jeans - Aqua','CFK41B3C91E',3,5.00,'2025-07-02 04:14:32','2025-07-02 04:14:32',1,'28\"',493.94,NULL,NULL,NULL),(218,'Bottom-Jeans - Plum','CFK61210E55',3,5.00,'2025-07-02 04:14:32','2025-07-02 04:14:32',1,'30\"',355.74,NULL,NULL,NULL),(219,'Bottom-Jeans - Mustard','CFK1E6CF2F4',3,5.00,'2025-07-02 04:14:33','2025-07-02 04:14:33',1,'32\"',477.16,NULL,NULL,NULL),(220,'Bottom-Jeans - Ruby','CFK44575AB1',3,5.00,'2025-07-02 04:14:34','2025-07-02 04:14:34',1,'34\"',529.96,NULL,NULL,NULL),(221,'Bottom-Jeans - Sapphire','CFKBCA8629E',3,5.00,'2025-07-02 04:14:34','2025-07-02 04:14:34',1,'36\"',327.43,NULL,NULL,NULL),(222,'Bottom-Jeans - Emerald','CFKEBFCCBF4',3,5.00,'2025-07-02 04:14:35','2025-07-02 04:14:35',1,'26\"',458.03,NULL,NULL,NULL),(223,'Bottom-Jeans - Blue','CFK5BEB1E66',3,5.00,'2025-07-02 04:14:35','2025-07-02 04:14:35',1,'28\"',473.86,NULL,NULL,NULL),(224,'Pant - Red','CFK39C6B58C',17,5.00,'2025-07-02 04:14:36','2025-07-02 04:14:36',1,'16\"',349.28,NULL,NULL,NULL),(225,'Pant - Blue','CFKF068D95D',17,5.00,'2025-07-02 04:14:37','2025-07-02 04:14:37',1,'18\"',496.25,NULL,NULL,NULL),(226,'Pant - Green','CFK2E7A281D',17,5.00,'2025-07-02 04:14:37','2025-07-02 04:14:37',1,'20\"',299.78,NULL,NULL,NULL),(227,'Pant - Black','CFKB3915AE7',17,5.00,'2025-07-02 04:14:38','2025-07-02 04:14:38',1,'22\"',392.65,NULL,NULL,NULL),(228,'Pant - White','CFKC3199189',17,5.00,'2025-07-02 04:14:39','2025-07-02 04:14:39',1,'24\"',511.98,NULL,NULL,NULL),(229,'Pant - Yellow','CFK1509073C',17,5.00,'2025-07-02 04:14:39','2025-07-02 04:14:39',1,'16\"',350.00,NULL,NULL,NULL),(230,'Pant - Grey','CFKC649EE56',17,5.00,'2025-07-02 04:14:40','2025-07-02 04:14:40',1,'18\"',404.62,NULL,NULL,NULL),(231,'Pant - Brown','CFK97C2AA36',17,5.00,'2025-07-02 04:14:40','2025-07-02 04:14:40',1,'20\"',591.41,NULL,NULL,NULL),(232,'Pant - Pink','CFKA46E7F78',17,5.00,'2025-07-02 04:14:41','2025-07-02 04:14:41',1,'22\"',585.92,NULL,NULL,NULL),(233,'Pant - Purple','CFK24D1F42A',17,5.00,'2025-07-02 04:14:42','2025-07-02 04:14:42',1,'24\"',362.39,NULL,NULL,NULL),(234,'Pant - Orange','CFKA10E277F',17,5.00,'2025-07-02 04:14:42','2025-07-02 04:14:42',1,'16\"',393.97,NULL,NULL,NULL),(235,'Pant - Maroon','CFK7B696707',17,5.00,'2025-07-02 04:14:43','2025-07-02 04:14:43',1,'18\"',405.16,NULL,NULL,NULL),(236,'Pant - Navy','CFK18ADF0A8',17,5.00,'2025-07-02 04:14:44','2025-07-02 04:14:44',1,'20\"',488.78,NULL,NULL,NULL),(237,'Pant - Olive','CFKC4972746',17,5.00,'2025-07-02 04:14:44','2025-07-02 04:14:44',1,'22\"',480.77,NULL,NULL,NULL),(238,'Pant - Teal','CFKBB7215A0',17,5.00,'2025-07-02 04:14:45','2025-07-02 04:14:45',1,'24\"',243.62,NULL,NULL,NULL),(239,'Pant - Beige','CFK3DFA37D5',17,5.00,'2025-07-02 04:14:45','2025-07-02 04:14:45',1,'16\"',334.13,NULL,NULL,NULL),(240,'Pant - Violet','CFK7D59721D',17,5.00,'2025-07-02 04:14:46','2025-07-02 04:14:46',1,'18\"',261.52,NULL,NULL,NULL),(241,'Pant - Turquoise','CFKAB593884',17,5.00,'2025-07-02 04:14:47','2025-07-02 04:14:47',1,'20\"',514.78,NULL,NULL,NULL),(242,'Pant - Magenta','CFK213A4228',17,5.00,'2025-07-02 04:14:48','2025-07-02 04:14:48',1,'22\"',277.00,NULL,NULL,NULL),(243,'Pant - Lime','CFK0EADB759',17,5.00,'2025-07-02 04:14:49','2025-07-02 04:14:49',1,'24\"',478.90,NULL,NULL,NULL),(244,'Pant - Cyan','CFK7762138B',17,5.00,'2025-07-02 04:14:49','2025-07-02 04:14:49',1,'16\"',476.09,NULL,NULL,NULL),(245,'Pant - Indigo','CFK119BFFDF',17,5.00,'2025-07-02 04:14:50','2025-07-02 04:14:50',1,'18\"',438.40,NULL,NULL,NULL),(246,'Pant - Gold','CFK7095D6E2',17,5.00,'2025-07-02 04:14:51','2025-07-02 04:14:51',1,'20\"',298.82,NULL,NULL,NULL),(247,'Pant - Silver','CFKEA7AE38F',17,5.00,'2025-07-02 04:14:52','2025-07-02 04:14:52',1,'22\"',541.55,NULL,NULL,NULL),(248,'Pant - Coral','CFKB76E797D',17,5.00,'2025-07-02 04:14:52','2025-07-02 04:14:52',1,'24\"',412.09,NULL,NULL,NULL),(249,'Pant - Peach','CFKF09FB916',17,5.00,'2025-07-02 04:14:53','2025-07-02 04:14:53',1,'24\"',509.16,NULL,NULL,NULL),(250,'Pant - Mint','CFKB9138776',17,5.00,'2025-07-02 04:14:54','2025-07-02 04:14:54',1,'26\"',483.02,NULL,NULL,NULL),(251,'Pant - Lavender','CFK04F8C676',17,5.00,'2025-07-02 04:14:54','2025-07-02 04:14:54',1,'28\"',350.76,NULL,NULL,NULL),(252,'Pant - Charcoal','CFK8CF018EA',17,5.00,'2025-07-02 04:14:55','2025-07-02 04:14:55',1,'30\"',464.35,NULL,NULL,NULL),(253,'Pant - Tan','CFKA0C64F92',17,5.00,'2025-07-02 04:14:56','2025-07-02 04:14:56',1,'32\"',300.10,NULL,NULL,NULL),(254,'Pant - Aqua','CFK2C450E19',17,5.00,'2025-07-02 04:14:56','2025-07-02 04:14:56',1,'34\"',542.42,NULL,NULL,NULL),(255,'Pant - Plum','CFK86155A4A',17,5.00,'2025-07-02 04:14:57','2025-07-02 04:14:57',1,'36\"',295.07,NULL,NULL,NULL),(256,'Pant - Mustard','CFK480E5003',17,5.00,'2025-07-02 04:14:58','2025-07-02 04:14:58',1,'24\"',282.24,NULL,NULL,NULL),(257,'Pant - Ruby','CFK06C48858',17,5.00,'2025-07-02 04:14:58','2025-07-02 04:14:58',1,'26\"',292.96,NULL,NULL,NULL),(258,'Pant - Sapphire','CFKCC9D7982',17,5.00,'2025-07-02 04:14:59','2025-07-02 04:14:59',1,'28\"',477.05,NULL,NULL,NULL),(259,'Pant - Emerald','CFK07D28C29',17,5.00,'2025-07-02 04:15:00','2025-07-02 04:15:00',1,'30\"',511.33,NULL,NULL,NULL),(260,'Pant - Amber','CFK235D03D8',17,5.00,'2025-07-02 04:15:00','2025-07-02 04:15:00',1,'32\"',445.99,NULL,NULL,NULL),(261,'Pant - Bronze','CFK35087A2B',17,5.00,'2025-07-02 04:15:01','2025-07-02 04:15:01',1,'34\"',282.37,NULL,NULL,NULL),(262,'Pant - Copper','CFK67AADF8E',17,5.00,'2025-07-02 04:15:02','2025-07-02 04:15:02',1,'36\"',371.95,NULL,NULL,NULL),(263,'Frock','CFK000263',5,5.00,'2025-07-10 19:07:22','2025-07-10 19:07:22',1,'20\"',650.00,NULL,NULL,NULL),(264,'Formal Shoes - Brown','CFK000264',16,5.00,'2025-07-10 20:12:55','2025-07-10 20:12:55',1,'8\"',1000.00,NULL,NULL,NULL),(265,'Lipstick - Red Shade','CFK000265',15,5.00,'2025-07-17 13:35:38','2025-07-17 13:35:38',1,'1',180.00,NULL,'2025-07-17',NULL),(266,'Compaq Powder ','CFK000266',15,5.00,'2025-07-18 00:40:04','2025-07-18 00:40:04',1,'25 gm',250.00,NULL,NULL,NULL),(267,'Blush','CFK000267',15,5.00,'2025-07-18 21:51:22','2025-07-18 21:51:22',1,'1',450.00,NULL,NULL,NULL),(268,'Leggings - Black','CFK000268',7,5.00,'2025-07-19 21:17:54','2025-07-19 21:17:54',1,'Large',349.00,NULL,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products_new`
--

DROP TABLE IF EXISTS `products_new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products_new` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `unit_value` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `unit_label` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image_url` varchar(500) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sale_price` decimal(10,2) DEFAULT '0.00',
  `sale_price_type` enum('WITHOUT_TAX','WITH_TAX') COLLATE utf8mb4_unicode_ci DEFAULT 'WITHOUT_TAX',
  `discount_amount` decimal(10,2) DEFAULT '0.00',
  `discount_type` enum('PERCENTAGE','AMOUNT') COLLATE utf8mb4_unicode_ci DEFAULT 'PERCENTAGE',
  `opening_quantity` int DEFAULT '0',
  `at_price` decimal(10,2) DEFAULT '0.00',
  `as_of_date` datetime DEFAULT NULL,
  `min_stock` int DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `created_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `updated_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `purchase_price` decimal(10,2) DEFAULT NULL,
  `purchase_price_type` varchar(20) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hsn_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `location` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `product_code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  `tax_rate_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_product_name` (`name`),
  KEY `FKlx1pna9ugt9iewisod3acnd9` (`category_id`),
  KEY `FK10ki1dht9bdkglqmls92sl5cg` (`tax_rate_id`),
  CONSTRAINT `FK10ki1dht9bdkglqmls92sl5cg` FOREIGN KEY (`tax_rate_id`) REFERENCES `tax_rates` (`id`),
  CONSTRAINT `fk_products_tax_rate` FOREIGN KEY (`tax_rate_id`) REFERENCES `tax_rates` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products_new`
--

LOCK TABLES `products_new` WRITE;
/*!40000 ALTER TABLE `products_new` DISABLE KEYS */;
INSERT INTO `products_new` VALUES (4,'Brown Pant - 36\"','PIECES','PIECES (Pc)',NULL,450.00,'WITH_TAX',20.00,'AMOUNT',60,4999.99,'2025-08-09 00:19:00',0,'2025-08-09 10:11:31','2025-08-09 10:12:07','system','system',200.00,'WITH_TAX','5211.32.40',NULL,'44815716301',17,NULL),(5,'White Shirt - 46\"','PIECES','PIECES (Pc)',NULL,600.00,'WITH_TAX',50.00,'AMOUNT',15,450.00,'2025-08-10 00:43:00',0,'2025-08-09 19:22:30','2025-08-09 19:22:30','system','system',450.00,'WITH_TAX','6105',NULL,'89541908213',2,NULL),(7,'Caprie Blue - 16\"','PIECES','PIECES (Pc)','/images/products/1754936704571_Yin_Yang.png',850.00,'WITH_TAX',10.00,'PERCENTAGE',15,600.00,'2025-08-11 23:53:00',0,'2025-08-11 18:25:05','2025-08-11 18:25:05','system','system',600.00,'WITH_TAX','5211.32.40',NULL,'48811888676',3,NULL),(8,'Cotton Pant - 32\"','PIECES','PIECES (Pc)','/images/products/1754939322536_mobile_app_dev.jpg',850.00,'WITH_TAX',50.00,'AMOUNT',30,450.00,'2025-05-12 00:33:00',5,'2025-08-11 19:08:43','2025-08-11 19:08:43','system','system',450.00,'WITH_TAX','5211.32.40',NULL,'82554331799',17,NULL),(9,'Red Skirt - 32\"','PIECES','PIECES (Pc)',NULL,450.00,'WITH_TAX',10.00,'PERCENTAGE',25,200.00,'2025-08-12 00:47:00',5,'2025-08-11 19:28:25','2025-08-11 19:28:30','system','system',200.00,'WITH_TAX','6104',NULL,'56278818822',8,NULL),(15,'White Jeans - 32\"','PIECES','PIECES (Pc)',NULL,1000.00,'WITHOUT_TAX',49.97,'AMOUNT',25,650.00,'2025-09-07 17:12:00',2,'2025-09-07 11:43:52','2025-09-08 07:56:20','system','system',650.00,'WITHOUT_TAX','5211.32.40',NULL,'22948125905',3,3),(16,'Black T-Shirt 32\"','PIECES','PIECES (Pc)',NULL,500.00,'WITH_TAX',20.00,'AMOUNT',25,0.00,'2025-09-08 18:00:44',0,'2025-09-08 12:30:44','2025-09-08 12:30:44','system','system',300.00,'WITH_TAX','6105.10.20',NULL,'12453524483',1,3),(17,'Ladies Top - 32\"','PIECES','PIECES (Pc)',NULL,400.00,'WITHOUT_TAX',10.00,'AMOUNT',25,0.00,'2025-09-08 18:04:28',0,'2025-09-08 12:34:28','2025-09-08 12:34:28','system','system',250.00,'WITH_TAX','6109',NULL,'15778897598',1,3),(18,'Peach Rounded Neck T-shirt - 32\"','PIECES','PIECES (Pc)',NULL,600.00,'WITH_TAX',20.00,'AMOUNT',25,0.00,'2025-09-08 18:08:12',0,'2025-09-08 12:38:12','2025-09-08 12:38:12','system','system',500.00,'WITHOUT_TAX','6109',NULL,'24016421716',1,3),(19,'Navy Blue T-Shirt - 32\"','PIECES','PIECES (Pc)',NULL,400.00,'WITHOUT_TAX',10.00,'AMOUNT',25,0.00,'2025-09-08 18:09:41',0,'2025-09-08 12:39:41','2025-09-08 12:39:41','system','system',350.00,'WITHOUT_TAX','6109',NULL,'73388425870',1,3),(20,'Stone Jeans - 32\"','PIECES','PIECES (Pc)',NULL,700.00,'WITH_TAX',30.00,'AMOUNT',25,300.00,'2025-10-23 19:40:00',2,'2025-10-23 14:20:37','2025-10-23 14:20:37','system','system',500.00,'WITH_TAX','5211.32.40',NULL,'86932323495',15,3);
/*!40000 ALTER TABLE `products_new` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proforma_invoice_transactions`
--

DROP TABLE IF EXISTS `proforma_invoice_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proforma_invoice_transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `balance_amount` decimal(19,2) DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `discount_amount` decimal(15,2) NOT NULL,
  `item_count` int NOT NULL,
  `notes` varchar(1000) DEFAULT NULL,
  `party_id` bigint DEFAULT NULL,
  `party_name` varchar(255) DEFAULT NULL,
  `proforma_invoice_id` bigint DEFAULT NULL,
  `proforma_invoice_number` varchar(255) DEFAULT NULL,
  `status` varchar(255) NOT NULL,
  `tax_amount` decimal(15,2) NOT NULL,
  `total_amount` decimal(15,2) NOT NULL,
  `total_quantity` decimal(10,2) NOT NULL,
  `transaction_date` date NOT NULL,
  `transaction_number` varchar(255) NOT NULL,
  `transaction_time` time NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `estimate_quotation_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9iyf0m0i9te0pyrdgj9yki69p` (`transaction_number`),
  KEY `FKselyix2x2bot4eoadob262xg8` (`estimate_quotation_id`),
  KEY `FKpemjp83ups080bhmsf9r34swf` (`proforma_invoice_id`),
  CONSTRAINT `FKpemjp83ups080bhmsf9r34swf` FOREIGN KEY (`proforma_invoice_id`) REFERENCES `new_proforma_invoice` (`id`),
  CONSTRAINT `FKselyix2x2bot4eoadob262xg8` FOREIGN KEY (`estimate_quotation_id`) REFERENCES `new_estimate_quotation` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proforma_invoice_transactions`
--

LOCK TABLES `proforma_invoice_transactions` WRITE;
/*!40000 ALTER TABLE `proforma_invoice_transactions` DISABLE KEYS */;
INSERT INTO `proforma_invoice_transactions` VALUES (2,2310.00,'2025-12-10 07:31:00.387487','SYSTEM',90.00,3,'Proforma Invoice Data | Proforma: PI-00001',5,'Monish',NULL,'PI-00001','OPEN',0.00,2310.00,3.00,'2025-12-10','PI-00001','13:01:00','2025-12-10 07:31:00.387487','system',NULL),(3,3224.00,'2025-12-10 07:45:59.686293','SYSTEM',110.02,4,'Proforma Invoice Data | Proforma: PI-00001',5,'Monish',1,'PI-00001','OPEN',100.02,3224.00,4.00,'2025-12-10','PI-00003','13:15:59','2025-12-10 07:45:59.686293','system',NULL),(4,1470.00,'2025-12-10 08:10:38.979014','SYSTEM',76.99,2,'Proforma Invoice Data | Proforma: PI-00002',8,'Shree Krishna Clothing and Garments Shop',2,'PI-00002','OPEN',46.41,1470.00,2.00,'2025-12-10','PI-00004','13:40:38','2025-12-10 08:10:38.979014','system',NULL);
/*!40000 ALTER TABLE `proforma_invoice_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `supplier_id` bigint DEFAULT NULL,
  `bill_number` varchar(100) DEFAULT NULL,
  `purchase_date` date DEFAULT NULL,
  `payment_mode` bigint DEFAULT NULL,
  `description` text,
  `image_path` varchar(255) DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7wa1ltbppmk5drn8uqq1nxmut` (`supplier_id`),
  CONSTRAINT `FK7wa1ltbppmk5drn8uqq1nxmut` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (6,2,'5','2025-07-13',1,'Blah Blah','http://localhost:8080/[object File]',708.75),(7,2,'6','2025-07-16',1,'Blah Blah',NULL,283.50),(8,2,'9','2025-07-16',1,'Blah Blah',NULL,992.25);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_item`
--

DROP TABLE IF EXISTS `purchase_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `purchase_id` bigint DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `quantity` int NOT NULL,
  `price_per_unit` decimal(10,2) NOT NULL,
  `discount_percent` decimal(5,2) DEFAULT '0.00',
  `tax_percent` decimal(5,2) DEFAULT '0.00',
  `total_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKirur2g37wnivia672msyop0dj` (`product_id`),
  KEY `FK1mncc5yaore1sibgpj3jc4a7u` (`purchase_id`),
  CONSTRAINT `FK1mncc5yaore1sibgpj3jc4a7u` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`),
  CONSTRAINT `FKirur2g37wnivia672msyop0dj` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `purchase_item_purchase_id_fk` FOREIGN KEY (`purchase_id`) REFERENCES `purchase` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_item`
--

LOCK TABLES `purchase_item` WRITE;
/*!40000 ALTER TABLE `purchase_item` DISABLE KEYS */;
INSERT INTO `purchase_item` VALUES (9,6,67,3,250.00,10.00,5.00,708.75),(10,7,187,3,100.00,10.00,5.00,283.50),(11,8,86,7,150.00,10.00,5.00,992.25);
/*!40000 ALTER TABLE `purchase_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'superadmin','Full access to all modules','2025-04-07 06:42:05','2025-04-07 06:42:05'),(2,'cashier','Limited to sales and invoices','2025-04-07 06:46:14','2025-04-07 06:46:14'),(3,'manager','Can access reports and product management','2025-04-07 06:46:14','2025-04-07 06:46:14');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_invoice`
--

DROP TABLE IF EXISTS `sales_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_invoice` (
  `id` int NOT NULL AUTO_INCREMENT,
  `invoice_number` varchar(50) NOT NULL,
  `invoice_date` date NOT NULL,
  `customer_id` int DEFAULT NULL,
  `total_amount` decimal(10,2) NOT NULL,
  `discount_percent` decimal(5,2) DEFAULT '0.00',
  `tax_amount` decimal(10,2) DEFAULT '0.00',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `paid_amount` decimal(10,2) NOT NULL,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_status_id` int NOT NULL,
  `subtotal_amount` decimal(10,2) DEFAULT '0.00',
  `discount_amount` decimal(10,2) DEFAULT NULL,
  `due_amount` decimal(10,2) DEFAULT '0.00',
  `payment_mode_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `invoice_number` (`invoice_number`),
  UNIQUE KEY `UK_4fkkbu6klwpg1e2ayb6wpkw0o` (`invoice_number`),
  KEY `FKqypn8djscn4btakkcdrcjgxw8` (`payment_status_id`),
  KEY `FKny2cmshlycuj2juybyoy8lvae` (`customer_id`),
  CONSTRAINT `FKny2cmshlycuj2juybyoy8lvae` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`),
  CONSTRAINT `FKqypn8djscn4btakkcdrcjgxw8` FOREIGN KEY (`payment_status_id`) REFERENCES `payment_statuses` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_invoice`
--

LOCK TABLES `sales_invoice` WRITE;
/*!40000 ALTER TABLE `sales_invoice` DISABLE KEYS */;
INSERT INTO `sales_invoice` VALUES (1,'CFK-00001','2025-05-18',1,948.10,10.00,49.90,'2025-05-18 09:01:49',949.00,'2025-05-18 09:01:49',1,998.00,99.80,0.00,NULL),(2,'CFK-00002','2025-05-18',1,977.55,10.00,51.45,'2025-05-18 09:24:38',978.00,'2025-05-18 09:24:38',1,1029.00,102.90,0.00,NULL),(3,'CFK-00003','2025-05-18',1,948.10,10.00,49.90,'2025-05-18 14:15:22',950.00,'2025-05-18 14:15:22',1,998.00,99.80,0.00,NULL),(4,'CFK-00004','2025-05-20',1,1896.20,10.00,99.80,'2025-05-19 18:42:07',1897.00,'2025-05-19 18:42:07',1,1996.00,199.60,0.00,NULL),(5,'CFK-00005','2025-05-23',1,1080.45,0.00,51.45,'2025-05-22 20:33:17',1081.00,'2025-05-22 20:33:17',1,1029.00,0.00,0.00,NULL),(6,'CFK-00006','2025-05-27',1,1642.83,0.00,144.83,'2025-05-27 07:26:28',1643.00,'2025-05-27 07:26:28',1,1498.00,0.00,0.00,NULL),(7,'CFK-00007','2025-06-01',6,1027.95,0.00,78.95,'2025-06-01 16:06:10',1028.00,'2025-06-01 16:06:10',1,949.00,0.00,0.00,NULL),(8,'CFK-00008','2025-06-11',5,1493.03,10.00,144.83,'2025-06-10 21:32:20',1643.00,'2025-06-10 21:32:20',1,1498.00,149.80,0.00,NULL),(9,'CFK-00009','2025-06-11',1,2088.96,10.00,245.76,'2025-06-10 22:06:15',2089.00,'2025-06-10 22:06:15',1,2048.00,204.80,0.00,NULL),(10,'CFK-00010','2025-06-11',1,1493.03,10.00,144.83,'2025-06-10 22:23:53',1495.00,'2025-06-10 22:23:53',1,1498.00,149.80,0.00,NULL),(11,'CFK-00011','2025-06-15',1,1642.83,0.00,144.83,'2025-06-15 13:24:02',1643.00,'2025-06-15 13:24:02',1,1498.00,0.00,0.00,NULL),(12,'CFK-00012','2025-06-15',1,1493.03,10.00,144.83,'2025-06-15 13:31:49',1494.00,'2025-06-15 13:31:49',1,1498.00,149.80,0.00,NULL),(13,'CFK-00013','2025-06-27',1,523.95,0.00,24.95,'2025-06-26 22:54:37',524.00,'2025-06-26 22:54:37',1,499.00,0.00,0.00,NULL),(14,'CFK-00014','2025-06-28',1,523.95,0.00,24.95,'2025-06-28 06:21:02',524.00,'2025-06-28 06:21:02',1,499.00,0.00,0.00,NULL),(15,'CFK-00015','2025-06-28',6,977.55,10.00,51.45,'2025-06-28 06:34:49',978.00,'2025-06-28 06:34:49',1,1029.00,102.90,0.00,NULL),(16,'CFK-00016','2025-06-28',7,523.95,0.00,24.95,'2025-06-28 08:49:50',524.00,'2025-06-28 08:49:50',1,499.00,0.00,0.00,NULL),(17,'CFK-00017','2025-06-28',5,1080.45,0.00,51.45,'2025-06-28 12:49:24',1081.00,'2025-06-28 12:49:24',1,1029.00,0.00,0.00,NULL),(18,'CFK-00018','2025-06-28',1,2193.45,0.00,104.45,'2025-06-28 13:50:42',2194.00,'2025-06-28 13:50:42',1,2089.00,0.00,0.00,NULL),(19,'CFK-00019','2025-06-29',7,1952.03,10.00,198.83,'2025-06-28 18:40:11',1953.00,'2025-06-28 18:40:11',1,1948.00,194.80,0.00,NULL),(21,'CFK-00020','2025-07-03',8,1466.73,10.00,77.20,'2025-07-03 13:59:28',1467.00,'2025-07-03 13:59:28',1,1543.92,154.39,0.00,NULL),(22,'CFK-00022','2025-07-22',1,3243.93,0.00,154.47,'2025-07-22 11:18:14',3244.00,'2025-07-22 11:18:14',1,3089.46,0.00,0.00,NULL);
/*!40000 ALTER TABLE `sales_invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_invoice_items`
--

DROP TABLE IF EXISTS `sales_invoice_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_invoice_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `invoice_id` bigint DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_id` bigint DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `tax_percent` double DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_invoice_items`
--

LOCK TABLES `sales_invoice_items` WRITE;
/*!40000 ALTER TABLE `sales_invoice_items` DISABLE KEYS */;
INSERT INTO `sales_invoice_items` VALUES (1,1,499,11,1,5,523.95,NULL),(2,1,499,28,1,5,523.95,NULL),(3,2,530,12,1,5,556.5,NULL),(4,2,499,29,1,5,523.95,NULL),(5,3,499,28,1,5,523.95,NULL),(6,3,499,11,1,5,523.95,NULL),(7,4,499,11,2,5,1047.9,NULL),(8,4,499,28,2,5,1047.9,NULL),(9,5,530,12,1,5,556.5,NULL),(10,5,499,28,1,5,523.95,NULL),(11,6,499,29,1,5,523.95,NULL),(12,6,999,30,1,12,1118.88,NULL),(13,7,499,29,1,5,523.95,NULL),(14,7,450,13,1,12,504,NULL),(15,8,999,30,1,12,1118.88,NULL),(16,8,499,29,1,5,523.95,NULL),(17,9,999,30,1,12,1118.88,NULL),(18,9,1049,31,1,12,1174.88,NULL),(19,10,499,11,1,5,523.95,NULL),(20,10,999,30,1,12,1118.88,NULL),(21,11,499,11,1,5,523.95,NULL),(22,11,999,30,1,12,1118.88,NULL),(23,12,499,11,1,5,523.95,NULL),(24,12,999,30,1,12,1118.88,NULL),(25,16,499,11,1,5,523.95,'2025-06-28 14:21:18'),(26,17,499,11,1,5,523.95,'2025-06-28 18:19:24'),(27,17,530,12,1,5,556.5,'2025-06-28 18:19:24'),(28,18,499,11,1,5,523.95,'2025-06-28 19:20:42'),(29,18,530,12,3,5,1669.5,'2025-06-28 19:20:42'),(30,19,450,13,1,12,504,'2025-06-29 00:10:10'),(31,19,499,29,1,5,523.95,'2025-06-29 00:10:10'),(32,19,999,30,1,12,1118.88,'2025-06-29 00:10:10'),(33,20,295.87,84,2,5,621.327,'2025-07-03 18:55:11'),(34,21,295.87,84,2,5,621.327,'2025-07-03 19:29:29'),(35,21,476.09,244,2,5,999.789,'2025-07-03 19:29:29'),(36,22,513.48,36,3,5,1617.462,'2025-07-22 16:49:52'),(37,22,516.34,43,3,5,1626.471,'2025-07-22 16:51:39');
/*!40000 ALTER TABLE `sales_invoice_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales_transactions`
--

DROP TABLE IF EXISTS `sales_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_transactions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_number` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'Unique transaction identifier (ST-00001)',
  `invoice_id` bigint DEFAULT NULL COMMENT 'Reference to invoice if applicable',
  `invoice_number` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Invoice number for reference',
  `transaction_date` date NOT NULL COMMENT 'Date of the transaction',
  `transaction_time` time DEFAULT '00:00:00' COMMENT 'Time of the transaction',
  `total_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT 'Total sales amount before tax',
  `tax_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT 'Total tax amount',
  `discount_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT 'Total discount amount',
  `net_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT 'Final amount after tax and discount',
  `received_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT 'Amount received from customer',
  `balance_amount` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT 'Outstanding balance (net_amount - received_amount)',
  `payment_status` enum('PAID','PARTIAL','UNPAID') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'UNPAID' COMMENT 'Payment status',
  `payment_mode` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Payment method used',
  `transaction_type` enum('SALE','RETURN','REFUND','ADJUSTMENT') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'SALE' COMMENT 'Type of transaction',
  `item_count` int NOT NULL DEFAULT '0' COMMENT 'Number of items in transaction',
  `total_quantity` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT 'Total quantity of items sold',
  `profit_margin` decimal(15,2) DEFAULT NULL COMMENT 'Calculated profit margin',
  `cost_of_goods_sold` decimal(15,2) DEFAULT NULL COMMENT 'Total cost of goods sold',
  `sales_person_id` bigint DEFAULT NULL COMMENT 'ID of the sales person',
  `sales_person_name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Name of the sales person',
  `notes` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Additional notes about the transaction',
  `status` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'COMPLETED' COMMENT 'Transaction status',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Record creation timestamp',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Last update timestamp',
  `created_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT 'system' COMMENT 'User who created the transaction',
  `updated_by` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT 'system' COMMENT 'User who last updated the transaction',
  `party_id` bigint DEFAULT NULL,
  `party_name` varchar(1000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `transaction_number` (`transaction_number`),
  UNIQUE KEY `uk_transaction_number` (`transaction_number`),
  UNIQUE KEY `UK_mguajbpm6fvrj7pbp7i1k5y5` (`transaction_number`),
  KEY `idx_transaction_date` (`transaction_date`),
  KEY `idx_invoice_id` (`invoice_id`),
  KEY `idx_payment_status` (`payment_status`),
  KEY `idx_transaction_type` (`transaction_type`),
  KEY `idx_status` (`status`),
  KEY `idx_created_at` (`created_at`),
  KEY `idx_date_status` (`transaction_date`,`status`),
  KEY `idx_date_type` (`transaction_date`,`transaction_type`),
  KEY `idx_date_range_performance` (`transaction_date`,`status`,`transaction_type`),
  KEY `idx_amount_analysis` (`net_amount`,`transaction_date`),
  KEY `idx_payment_analysis` (`payment_status`,`transaction_date`,`balance_amount`),
  KEY `fk_sales_transaction_party` (`party_id`),
  KEY `FKt4aw6l41k9kh5hyfyft5j6547` (`customer_id`),
  CONSTRAINT `fk_sales_transaction_party` FOREIGN KEY (`party_id`) REFERENCES `parties` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_sales_transactions_invoice` FOREIGN KEY (`invoice_id`) REFERENCES `new_sales_invoice` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKkk4x5ym7ywtjg9nppsp2rnr5u` FOREIGN KEY (`invoice_id`) REFERENCES `new_sales_invoice` (`id`),
  CONSTRAINT `FKt4aw6l41k9kh5hyfyft5j6547` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='Records all sales transactions for reporting and analytics';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales_transactions`
--

LOCK TABLES `sales_transactions` WRITE;
/*!40000 ALTER TABLE `sales_transactions` DISABLE KEYS */;
INSERT INTO `sales_transactions` VALUES (68,'ST-00001',114,'RS-00001','2025-10-27','00:07:36',3260.00,122.00,140.00,3260.00,3260.00,0.00,'PAID','Cash','SALE',4,4.00,1660.00,1600.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00001','COMPLETED','2025-10-26 18:37:37','2025-10-26 18:37:37','SYSTEM','system',NULL,NULL,NULL),(69,'ST-00069',115,'RS-00115','2025-10-28','01:59:13',3850.00,115.50,150.00,3850.00,0.00,3850.00,'PAID','Cash','SALE',1,5.00,2350.00,1500.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00115','COMPLETED','2025-10-27 20:29:14','2025-10-27 20:29:14','SYSTEM','system',NULL,NULL,NULL),(70,'ST-00070',116,'RS-00116','2025-10-28','02:02:14',3850.00,115.50,150.00,3850.00,3850.00,0.00,'PAID','Cash','SALE',1,5.00,2350.00,1500.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00116','COMPLETED','2025-10-27 20:32:14','2025-10-27 20:32:14','SYSTEM','system',NULL,NULL,NULL),(71,'ST-00071',117,'RS-00117','2025-10-28','02:05:17',3850.00,115.50,150.00,3850.00,3850.00,0.00,'PAID','Cash','SALE',1,5.00,2350.00,1500.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00117','COMPLETED','2025-10-27 20:35:18','2025-10-27 20:35:18','SYSTEM','system',NULL,NULL,NULL),(72,'ST-00072',118,'RS-00118','2025-10-28','02:07:51',3850.00,115.50,150.00,3850.00,3850.00,0.00,'PAID','Cash','SALE',1,5.00,2350.00,1500.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00118','COMPLETED','2025-10-27 20:37:51','2025-10-27 20:37:51','SYSTEM','system',NULL,NULL,NULL),(73,'ST-00073',119,'RS-00119','2025-10-28','02:22:27',3850.00,115.50,150.00,3850.00,3850.00,0.00,'PAID','Cash','SALE',1,5.00,2350.00,1500.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00119','COMPLETED','2025-10-27 20:52:28','2025-10-27 20:52:28','SYSTEM','system',NULL,NULL,NULL),(74,'ST-00074',NULL,'RS-00120','2025-10-31','01:42:53',1540.00,0.00,60.00,1540.00,1540.00,0.00,'PAID','Cash','SALE',2,2.00,840.00,700.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00120','COMPLETED','2025-10-30 20:12:53','2025-10-30 20:12:53','SYSTEM','system',5,'Monish',NULL),(75,'ST-00075',NULL,'RS-00121','2025-10-31','01:52:09',3160.00,0.00,40.00,3160.00,3160.00,0.00,'PAID','Cash','SALE',2,4.00,1760.00,1400.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00121','COMPLETED','2025-10-30 20:22:10','2025-10-30 20:22:10','SYSTEM','system',5,'Monish',NULL),(76,'ST-00076',NULL,'RS-00122','2025-10-31','02:01:19',1540.00,0.00,60.00,1540.00,1540.00,0.00,'PAID','Cash','SALE',2,2.00,840.00,700.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00122','COMPLETED','2025-10-30 20:31:20','2025-10-30 20:31:20','SYSTEM','system',5,'Monish',NULL),(77,'ST-00077',NULL,'RS-00123','2025-10-31','02:04:01',1540.00,0.00,60.00,1540.00,1540.00,0.00,'PAID','Cash','SALE',2,2.00,840.00,700.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00123','COMPLETED','2025-10-30 20:34:02','2025-10-30 20:34:02','SYSTEM','system',5,'Monish',NULL),(78,'ST-00078',NULL,'RS-00124','2025-10-31','02:57:16',2310.00,0.00,90.00,2310.00,2310.00,0.00,'PAID','Cash','SALE',3,3.00,1210.00,1100.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00124','COMPLETED','2025-10-30 21:27:16','2025-10-30 21:27:16','SYSTEM','system',5,'Monish',NULL),(79,'ST-00079',127,'RS-00127','2025-10-31','03:07:20',2310.00,72.00,90.00,2310.00,2310.00,0.00,'PAID','Cash','SALE',3,3.00,1210.00,1100.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00127','COMPLETED','2025-10-30 21:37:21','2025-10-30 21:37:21','SYSTEM','system',5,'Monish',NULL),(80,'ST-00080',128,'RS-00128','2025-10-31','03:33:46',2310.00,72.00,90.00,2310.00,0.00,2310.00,'PAID','Cash','SALE',3,3.00,1210.00,1100.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00128','COMPLETED','2025-10-30 22:03:47','2025-10-30 22:03:47','SYSTEM','system',5,'Monish',NULL),(81,'ST-00081',129,'RS-00129','2025-10-31','03:55:03',2310.00,72.00,90.00,2310.00,2310.00,0.00,'PAID','Cash','SALE',3,3.00,1210.00,1100.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00129','COMPLETED','2025-10-30 22:25:03','2025-10-30 22:25:03','SYSTEM','system',5,'Monish',NULL),(82,'ST-00082',130,'RS-00130','2025-11-01','15:49:14',4750.00,142.50,250.00,4750.00,4750.00,0.00,'PAID','Cash','SALE',1,5.00,2250.00,2500.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00130','COMPLETED','2025-11-01 10:19:14','2025-11-01 10:19:14','SYSTEM','system',10,'Sameer International Exports Pvt. Ltd.',NULL),(83,'ST-00083',131,'RS-00131','2025-11-01','16:06:39',3850.00,115.50,150.00,3850.00,3850.00,0.00,'PAID','Cash','SALE',1,5.00,2350.00,1500.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00131','COMPLETED','2025-11-01 10:36:40','2025-11-01 10:36:40','SYSTEM','system',10,'Sameer International Exports Pvt. Ltd.',NULL),(84,'ST-00084',132,'RS-00132','2025-11-01','16:25:19',4750.00,142.50,250.00,4750.00,4750.00,0.00,'PAID','Cash','SALE',1,5.00,2250.00,2500.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00132','COMPLETED','2025-11-01 10:55:19','2025-11-01 10:55:19','SYSTEM','system',10,'Sameer International Exports Pvt. Ltd.',NULL),(85,'ST-00085',133,'RS-00133','2025-11-01','16:36:54',8035.92,300.18,300.08,8035.92,8035.92,0.00,'PAID','Cash','SALE',4,10.00,3435.92,4600.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00133','COMPLETED','2025-11-01 11:06:54','2025-11-01 11:06:54','SYSTEM','system',12,'Gala Traders ',NULL),(86,'ST-00086',134,'RS-00134','2025-11-01','19:44:12',11180.00,374.20,420.00,11180.00,11180.00,0.00,'PAID','Cash','SALE',4,14.00,5680.00,5500.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00134','COMPLETED','2025-11-01 14:14:13','2025-11-01 14:14:13','SYSTEM','system',12,'Gala Traders ',NULL),(87,'ST-00087',135,'RS-00135','2025-12-03','17:04:44',2948.00,92.04,120.04,2948.00,2948.00,0.00,'PAID','Cash','SALE',4,4.00,1298.00,1650.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00135','COMPLETED','2025-12-03 11:34:44','2025-12-03 11:34:44','SYSTEM','system',5,'Monish',NULL),(88,'ST-00088',136,'RS-00136','2025-12-09','16:09:08',770.00,24.00,30.00,770.00,770.00,0.00,'PAID','Cash','SALE',1,1.00,470.00,300.00,NULL,NULL,'POS sale via NewSalesNew | Invoice: RS-00136','COMPLETED','2025-12-09 10:39:08','2025-12-09 10:39:08','SYSTEM','system',11,'Mukesh Traders',NULL);
/*!40000 ALTER TABLE `sales_transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `states` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `state` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_active` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` VALUES (1,'None','2025-10-14 17:15:56',_binary ''),(2,'Andaman & Nicobar Islands','2025-10-14 17:15:56',_binary ''),(3,'Andhra Pradesh','2025-10-14 17:15:56',_binary ''),(4,'Arunachal Pradesh','2025-10-14 17:15:56',_binary ''),(5,'Assam','2025-10-14 17:15:56',_binary ''),(6,'Bihar','2025-10-14 17:15:56',_binary ''),(7,'Chhatisgarh','2025-10-14 17:15:56',_binary ''),(8,'Dadra & Nagar Haveli & Daman & Diu','2025-10-14 17:15:56',_binary ''),(9,'Daman & Diu','2025-10-14 17:15:56',_binary ''),(10,'Delhi','2025-10-14 17:15:56',_binary ''),(11,'Goa','2025-10-14 17:15:56',_binary ''),(12,'Gujarat','2025-10-14 17:15:56',_binary ''),(13,'Haryana','2025-10-14 17:15:56',_binary ''),(14,'Himachal Pradesh','2025-10-14 17:15:56',_binary ''),(15,'Jammu & Kashmir','2025-10-14 17:15:56',_binary ''),(16,'Jharkhand','2025-10-14 17:15:56',_binary ''),(17,'Karnataka','2025-10-14 17:15:56',_binary ''),(18,'Kerala','2025-10-14 17:15:56',_binary ''),(19,'Ladakh','2025-10-14 17:15:56',_binary ''),(20,'Lakshadweep','2025-10-14 17:15:56',_binary ''),(21,'Madhya Pradesh','2025-10-14 17:15:56',_binary ''),(22,'Maharashtra','2025-10-14 17:15:56',_binary ''),(23,'Manipur','2025-10-14 17:15:56',_binary ''),(24,'Meghalaya','2025-10-14 17:15:56',_binary ''),(25,'Mizoram','2025-10-14 17:15:56',_binary ''),(26,'Nagaland','2025-10-14 17:15:56',_binary ''),(27,'Odisha','2025-10-14 17:15:56',_binary ''),(28,'Puducherry','2025-10-14 17:15:56',_binary ''),(29,'Punjab','2025-10-14 17:15:56',_binary ''),(30,'Rajasthan','2025-10-14 17:15:56',_binary ''),(31,'Sikkim','2025-10-14 17:15:56',_binary ''),(32,'Tamil Nadu','2025-10-14 17:15:56',_binary ''),(33,'Telangana','2025-10-14 17:15:56',_binary ''),(34,'Tripura','2025-10-14 17:15:56',_binary ''),(35,'Uttar Pradesh','2025-10-14 17:15:56',_binary ''),(36,'Uttarakhand','2025-10-14 17:15:56',_binary ''),(37,'West Bengal','2025-10-14 17:15:56',_binary '');
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock_adjustments`
--

DROP TABLE IF EXISTS `stock_adjustments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock_adjustments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `adjustment_type` enum('ADD_STOCK','REDUCE_STOCK') NOT NULL,
  `quantity` decimal(10,2) NOT NULL,
  `at_price` decimal(10,2) DEFAULT '0.00',
  `description` varchar(500) DEFAULT NULL,
  `adjustment_date` datetime NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `created_by` varchar(100) DEFAULT NULL,
  `total_value` decimal(15,2) DEFAULT NULL,
  `item_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_adjustment_type` (`adjustment_type`),
  KEY `idx_adjustment_date` (`adjustment_date`),
  KEY `idx_created_at` (`created_at`),
  KEY `idx_created_by` (`created_by`),
  KEY `FKrqkv0el2m8rlrhy8w4wqjlvjs` (`item_id`),
  CONSTRAINT `fk_stock_adjustments_item` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKrqkv0el2m8rlrhy8w4wqjlvjs` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock_adjustments`
--

LOCK TABLES `stock_adjustments` WRITE;
/*!40000 ALTER TABLE `stock_adjustments` DISABLE KEYS */;
INSERT INTO `stock_adjustments` VALUES (20,'ADD_STOCK',20.00,300.00,'Added 20 numbers of Stone Jeans - 32\"','2025-10-25 18:30:00','2025-10-26 20:13:08',NULL,6000.00,1),(21,'ADD_STOCK',20.00,300.00,'Added 20 numbers of Stone Jeans - 32\"','2025-10-25 18:30:00','2025-10-26 20:39:52',NULL,6000.00,1),(22,'REDUCE_STOCK',5.00,500.00,'5 Items reduced from the stock','2025-10-26 18:30:00','2025-10-27 11:40:58',NULL,2500.00,8),(23,'ADD_STOCK',2.00,500.00,'2 items reduced from the stock','2025-10-26 18:30:00','2025-10-27 11:49:52',NULL,1000.00,8),(24,'REDUCE_STOCK',3.00,500.00,'3 Items Reduced from the stock','2025-10-26 18:30:00','2025-10-27 12:03:45',NULL,1500.00,8),(25,'ADD_STOCK',10.00,500.00,'10 Qty of stock added','2025-10-26 18:30:00','2025-10-27 12:06:45',NULL,5000.00,8);
/*!40000 ALTER TABLE `stock_adjustments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `suppliers` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES (1,'B-Star','839023456','bstar@gmail.com','Ulhasnagar','2025-06-22 13:38:37'),(2,'A-Star','9324567891','astar@gmail.com','Andheri','2025-06-22 14:49:28'),(3,'C-Star','7032456789','cstar@gmail.com','Ulhasnagar','2025-06-22 15:09:14'),(5,'D-Star','8769430428','dstar@gmail.com','Jogeshwari','2025-07-06 09:50:29');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tax_rates`
--

DROP TABLE IF EXISTS `tax_rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax_rates` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `label` varchar(50) NOT NULL COMMENT 'Display label for tax rate (e.g., "GST@18%")',
  `type` enum('GST','IGST') NOT NULL COMMENT 'Tax type: GST or IGST',
  `rate` decimal(5,2) NOT NULL COMMENT 'Tax rate percentage (e.g., 18.00)',
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Whether the rate is usable',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `index` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_tax_rates_rate` (`rate`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax_rates`
--

LOCK TABLES `tax_rates` WRITE;
/*!40000 ALTER TABLE `tax_rates` DISABLE KEYS */;
INSERT INTO `tax_rates` VALUES (1,'GST@0%','GST',0.00,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',0),(2,'IGST@0%','IGST',0.00,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',1),(3,'GST@2.5%','GST',2.50,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',2),(4,'IGST@2.5%','IGST',2.50,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',3),(5,'GST@3%','GST',3.00,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',4),(6,'IGST@3%','IGST',3.00,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',5),(7,'GST@5%','GST',5.00,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',6),(8,'IGST@5%','IGST',5.00,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',7),(9,'GST@12%','GST',12.00,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',8),(10,'IGST@12%','IGST',12.00,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',9),(11,'GST@15%','GST',15.00,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',10),(12,'IGST@15%','IGST',15.00,1,'2025-09-08 07:23:44','2025-10-31 06:32:22',11),(13,'GST@28%','GST',28.00,1,'2025-10-22 14:21:54','2025-10-31 06:32:22',12),(14,'IGST@28%','IGST',28.00,1,'2025-10-22 14:22:25','2025-10-31 06:32:22',13),(15,'GST@40%','GST',40.00,1,'2025-10-22 14:25:15','2025-10-31 06:32:22',14),(16,'IGST@40%','IGST',40.00,1,'2025-10-22 14:25:44','2025-10-31 06:32:22',15);
/*!40000 ALTER TABLE `tax_rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tax_rates_backup`
--

DROP TABLE IF EXISTS `tax_rates_backup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax_rates_backup` (
  `id` bigint NOT NULL DEFAULT '0',
  `label` varchar(50) NOT NULL COMMENT 'Display label for tax rate (e.g., "GST@18%")',
  `type` enum('GST','IGST') NOT NULL COMMENT 'Tax type: GST or IGST',
  `rate` decimal(5,2) NOT NULL COMMENT 'Tax rate percentage (e.g., 18.00)',
  `active` tinyint(1) NOT NULL DEFAULT '1' COMMENT 'Whether the rate is usable',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax_rates_backup`
--

LOCK TABLES `tax_rates_backup` WRITE;
/*!40000 ALTER TABLE `tax_rates_backup` DISABLE KEYS */;
INSERT INTO `tax_rates_backup` VALUES (1,'GST@0%','GST',0.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(2,'IGST@0%','IGST',0.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(3,'GST@3%','GST',3.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(4,'IGST@3%','IGST',3.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(5,'GST@5%','GST',5.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(6,'IGST@5%','IGST',5.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(7,'GST@12%','GST',12.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(8,'IGST@12%','IGST',12.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(9,'GST@18%','GST',18.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(10,'IGST@18%','IGST',18.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(11,'GST@28%','GST',28.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(12,'IGST@28%','IGST',28.00,1,'2025-09-08 07:23:44','2025-09-08 07:23:44'),(13,'GST@2.5%','GST',2.50,1,'2025-10-22 14:21:54','2025-10-22 14:21:54'),(14,'IGST@2.5%','IGST',2.50,1,'2025-10-22 14:22:25','2025-10-22 14:22:25'),(15,'GST@40%','GST',40.00,1,'2025-10-22 14:25:15','2025-10-22 14:25:15'),(16,'IGST@40%','IGST',40.00,1,'2025-10-22 14:25:44','2025-10-22 14:25:44');
/*!40000 ALTER TABLE `tax_rates_backup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tax_type`
--

DROP TABLE IF EXISTS `tax_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tax_type` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tax_type` varchar(100) NOT NULL,
  `tax_type_code` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tax_type`
--

LOCK TABLES `tax_type` WRITE;
/*!40000 ALTER TABLE `tax_type` DISABLE KEYS */;
INSERT INTO `tax_type` VALUES (1,'With Tax','WITH_TAX','2025-10-21 20:22:53','2025-10-21 20:22:53'),(2,'Without Tax','WITHOUT_TAX','2025-10-21 20:23:20','2025-10-21 20:23:20');
/*!40000 ALTER TABLE `tax_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `units`
--

DROP TABLE IF EXISTS `units`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `units` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `unit_name` varchar(100) NOT NULL,
  `unit_abbr` varchar(100) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `label` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `units`
--

LOCK TABLES `units` WRITE;
/*!40000 ALTER TABLE `units` DISABLE KEYS */;
INSERT INTO `units` VALUES (1,'BAGS','Bag','2025-10-22 01:36:17','2025-10-22 01:36:17','BAG (Bags)'),(2,'BOTTLES','Bt','2025-10-22 01:36:45','2025-10-22 01:36:45','BOTTLES (Bt)'),(3,'BOX','Box','2025-10-22 01:37:11','2025-10-22 01:37:11','BOX (Box)'),(4,'BUNDLES','Bdl','2025-10-22 01:37:41','2025-10-22 01:37:41','BUNDLES (Bdl)'),(5,'NUMBERS','Nos','2025-10-22 01:38:13','2025-10-22 01:38:13','NUMBERS (Nos)'),(6,'PIECES','Pcs','2025-10-22 01:38:46','2025-10-22 01:38:46','PIECES (Pcs)'),(7,'KILOGRAMS','Kg','2025-10-22 01:39:23','2025-10-22 01:39:23','KILOGRAMS (Kg)'),(8,'LITERS','Ltr','2025-10-22 01:39:49','2025-10-22 01:39:49','LITERS (Ltr)'),(9,'METERS','Mtr','2025-10-22 01:40:14','2025-10-22 01:40:14','METERS (Mtr)'),(10,'PAIRS','Pr','2025-10-22 01:40:37','2025-10-22 01:40:37','PAIRS (Pr)'),(11,'SETS','Set','2025-10-22 01:40:53','2025-10-22 01:40:53','SETS (Set)');
/*!40000 ALTER TABLE `units` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_id` int DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'sohaib.rahman','zIgRxVyp5R3sEbeDHy07qg==',1,'sohaib.rahman64@gmail.com','Sohaib');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `daily_sales_summary`
--

/*!50001 DROP VIEW IF EXISTS `daily_sales_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `daily_sales_summary` AS select `sales_transactions`.`transaction_date` AS `transaction_date`,count(0) AS `total_transactions`,sum(`sales_transactions`.`total_amount`) AS `total_sales_amount`,sum(`sales_transactions`.`received_amount`) AS `total_received_amount`,sum(`sales_transactions`.`balance_amount`) AS `total_balance_amount`,sum(`sales_transactions`.`tax_amount`) AS `total_tax_amount`,sum(`sales_transactions`.`discount_amount`) AS `total_discount_amount`,sum(`sales_transactions`.`net_amount`) AS `total_net_amount`,avg(`sales_transactions`.`net_amount`) AS `average_transaction_value`,sum(`sales_transactions`.`total_quantity`) AS `total_items_sold`,sum((case when (`sales_transactions`.`payment_status` = 'PAID') then 1 else 0 end)) AS `paid_transactions`,sum((case when (`sales_transactions`.`payment_status` = 'PARTIAL') then 1 else 0 end)) AS `partial_transactions`,sum((case when (`sales_transactions`.`payment_status` = 'UNPAID') then 1 else 0 end)) AS `unpaid_transactions` from `sales_transactions` where ((`sales_transactions`.`status` = 'COMPLETED') and (`sales_transactions`.`transaction_type` = 'SALE')) group by `sales_transactions`.`transaction_date` order by `sales_transactions`.`transaction_date` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `monthly_sales_summary`
--

/*!50001 DROP VIEW IF EXISTS `monthly_sales_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `monthly_sales_summary` AS select year(`sales_transactions`.`transaction_date`) AS `year`,month(`sales_transactions`.`transaction_date`) AS `month`,date_format(`sales_transactions`.`transaction_date`,'%Y-%m') AS `month_year`,count(0) AS `total_transactions`,sum(`sales_transactions`.`total_amount`) AS `total_sales_amount`,sum(`sales_transactions`.`received_amount`) AS `total_received_amount`,sum(`sales_transactions`.`balance_amount`) AS `total_balance_amount`,sum(`sales_transactions`.`tax_amount`) AS `total_tax_amount`,sum(`sales_transactions`.`discount_amount`) AS `total_discount_amount`,sum(`sales_transactions`.`net_amount`) AS `total_net_amount`,avg(`sales_transactions`.`net_amount`) AS `average_transaction_value`,sum(`sales_transactions`.`total_quantity`) AS `total_items_sold`,sum((case when (`sales_transactions`.`payment_status` = 'PAID') then 1 else 0 end)) AS `paid_transactions`,sum((case when (`sales_transactions`.`payment_status` = 'PARTIAL') then 1 else 0 end)) AS `partial_transactions`,sum((case when (`sales_transactions`.`payment_status` = 'UNPAID') then 1 else 0 end)) AS `unpaid_transactions` from `sales_transactions` where ((`sales_transactions`.`status` = 'COMPLETED') and (`sales_transactions`.`transaction_type` = 'SALE')) group by year(`sales_transactions`.`transaction_date`),month(`sales_transactions`.`transaction_date`) order by `year` desc,`month` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `product_transaction_summary`
--

/*!50001 DROP VIEW IF EXISTS `product_transaction_summary`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `product_transaction_summary` AS select `pt`.`product_id` AS `product_id`,`p`.`name` AS `product_name`,sum((case when (`pt`.`transaction_type` in ('PURCHASE','STOCK_ADJUSTMENT','OPENING_STOCK','RETURN_SALE')) then `pt`.`quantity` else 0 end)) AS `total_in`,sum((case when (`pt`.`transaction_type` in ('SALE','DAMAGE_LOSS','EXPIRY_WRITEOFF','RETURN_PURCHASE')) then `pt`.`quantity` else 0 end)) AS `total_out`,(sum((case when (`pt`.`transaction_type` in ('PURCHASE','STOCK_ADJUSTMENT','OPENING_STOCK','RETURN_SALE')) then `pt`.`quantity` else 0 end)) - sum((case when (`pt`.`transaction_type` in ('SALE','DAMAGE_LOSS','EXPIRY_WRITEOFF','RETURN_PURCHASE')) then `pt`.`quantity` else 0 end))) AS `net_quantity`,count(0) AS `total_transactions`,max(`pt`.`transaction_date`) AS `last_transaction_date` from (`product_transactions` `pt` join `products_new` `p` on((`pt`.`product_id` = `p`.`id`))) group by `pt`.`product_id`,`p`.`name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-12-10 18:06:52
