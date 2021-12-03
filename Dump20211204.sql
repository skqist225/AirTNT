-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: airtnt
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `addresses` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apartment_no_street` varchar(255) DEFAULT NULL,
  `city_id` int DEFAULT NULL,
  `country_id` int DEFAULT NULL,
  `state_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9fkb8qaj71tiyr9htkmn7r8y5` (`city_id`),
  KEY `FKn3sth7s3kur1rafwbbrqqnswt` (`country_id`),
  KEY `FKqcujk6a19pr29agrlk18h2c9y` (`state_id`),
  CONSTRAINT `FK9fkb8qaj71tiyr9htkmn7r8y5` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  CONSTRAINT `FKn3sth7s3kur1rafwbbrqqnswt` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`),
  CONSTRAINT `FKqcujk6a19pr29agrlk18h2c9y` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
INSERT INTO `addresses` VALUES (2,'121/16/20 DX06',1,1,1),(3,'121/16/5 DX06',1,1,1),(4,'121/16/21 DX06',1,1,1),(5,'121/16/22 DX06',1,1,1),(6,'121/16/23 DX06',1,1,1),(7,'121/16/24 DX06',1,1,1),(8,'121/16/24 DX06',2,1,2);
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amentities`
--

DROP TABLE IF EXISTS `amentities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amentities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `updated_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon_image` text NOT NULL,
  `name` varchar(255) NOT NULL,
  `amtcategory_id` int DEFAULT NULL,
  `favorite` bit(1) NOT NULL,
  `prominent` bit(1) NOT NULL,
  `safe` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9n0dovk7hr1b8wuo3lefu09mh` (`name`),
  KEY `FKmguva27b8e5am0be8ghv4rn5k` (`amtcategory_id`),
  CONSTRAINT `FKmguva27b8e5am0be8ghv4rn5k` FOREIGN KEY (`amtcategory_id`) REFERENCES `amentity_categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amentities`
--

LOCK TABLES `amentities` WRITE;
/*!40000 ALTER TABLE `amentities` DISABLE KEYS */;
INSERT INTO `amentities` VALUES (1,'2021-09-18 15:49:31.534000',1,'2021-09-18 16:16:03.697000','Khăn tắm, khăn trải giường, xà phòng và giấy vệ sinh','needed_amentities.svg','Tiện nghi thiết yếu',4,_binary '\0',_binary '\0',_binary '\0'),(2,'2021-09-18 15:49:31.547000',1,'2021-09-18 15:49:31.547000',NULL,'hairdryer.svg','Máy sấy tóc',1,_binary '\0',_binary '\0',_binary '\0'),(3,'2021-09-18 15:49:31.549000',1,'2021-09-18 15:49:31.549000',NULL,'shampoo.svg','Dầu gội đầu',1,_binary '\0',_binary '\0',_binary '\0'),(4,'2021-09-18 15:49:31.551000',1,'2021-09-18 15:49:31.551000',NULL,'tv.svg','TV',5,_binary '',_binary '\0',_binary '\0'),(5,'2021-09-18 15:49:31.553000',1,'2021-09-18 15:49:31.553000',NULL,'air_conditioner.svg','Điều hòa nhiệt độ',6,_binary '',_binary '\0',_binary '\0'),(6,'2021-09-18 15:49:31.556000',1,'2021-09-18 15:49:31.556000',NULL,'heating_system.svg','Hệ thống sưởi',6,_binary '\0',_binary '\0',_binary '\0'),(7,'2021-09-18 15:49:52.006000',1,'2021-09-18 15:49:52.006000',NULL,'camera.svg','Camera an ninh trong nhà',7,_binary '\0',_binary '\0',_binary '\0'),(8,'2021-09-18 15:49:52.031000',1,'2021-09-18 15:49:52.031000',NULL,'stove.svg','Bếp',7,_binary '',_binary '\0',_binary '\0'),(9,'2021-09-18 15:49:52.036000',1,'2021-09-18 15:49:52.036000',NULL,'washing_machine.svg','Máy giặt',7,_binary '',_binary '\0',_binary '\0'),(10,'2021-09-18 15:49:52.041000',1,'2021-09-18 16:16:03.684000','Chỗ ở này có thể không có máy báo khói. Hãy liên hệ với chủ nhà nếu bạn có bất kỳ câu hỏi nào.','may_bao_khoi.svg','Máy báo khói',7,_binary '\0',_binary '\0',_binary ''),(11,'2021-09-18 15:49:52.045000',1,'2021-09-18 16:16:03.695000','Chỗ ở này có thể không có máy phát hiện khí CO. Hãy liên hệ với chủ nhà nếu bạn có bất kỳ câu hỏi nào.','may_phat_hien_khoi_CO.svg','Máy phát hiện khói CO',7,_binary '\0',_binary '\0',_binary ''),(12,'2021-09-18 15:49:52.050000',1,'2021-09-18 15:49:52.050000',NULL,'private_entrance.svg','Lối vào riêng',7,_binary '\0',_binary '\0',_binary '\0'),(13,'2021-09-18 16:04:12.211000',1,'2021-09-18 16:16:03.699000','Cho phép ở từ 28 ngày trở lên','long_stay_allowed.svg','Cho phép ở dài hạn',5,_binary '\0',_binary '\0',_binary '\0'),(14,'2021-09-18 16:04:12.229000',1,'2021-09-18 16:16:03.700000','Có sẵn trong toàn bộ nhà/phòng cho thuê','wifi.svg','Wifi',5,_binary '',_binary '\0',_binary '\0'),(15,'2021-10-27 21:17:48.000000',1,NULL,NULL,'pool.svg','Bể bơi',NULL,_binary '\0',_binary '',_binary '\0'),(16,'2021-10-27 21:26:42.000000',1,NULL,NULL,'yard.svg','Sân',NULL,_binary '\0',_binary '',_binary '\0'),(17,'2021-10-27 21:26:42.000000',1,NULL,NULL,'bathtub.svg','Bồn tắm nước nóng',NULL,_binary '\0',_binary '',_binary '\0'),(18,'2021-10-27 21:26:42.000000',1,NULL,NULL,'lo_nuong_BBQ.svg','Lò nướng BBQ',NULL,_binary '\0',_binary '',_binary '\0'),(19,'2021-10-27 21:26:42.000000',1,NULL,NULL,'bep_dot_lua_trai.svg','Bếp đốt lửa trại',NULL,_binary '\0',_binary '',_binary '\0'),(20,'2021-10-27 21:26:42.000000',1,NULL,NULL,'ban_bi_da.svg','Bàn bi-da',NULL,_binary '\0',_binary '',_binary '\0'),(21,'2021-10-27 21:26:42.000000',1,NULL,NULL,'fireplace.svg','Lò sưởi trong nhà',NULL,_binary '\0',_binary '',_binary '\0'),(22,'2021-10-27 21:26:42.000000',1,NULL,NULL,'area_eat_outside.svg','Khu vực ăn uống ngoài trời',NULL,_binary '\0',_binary '',_binary '\0'),(23,'2021-10-27 21:26:42.000000',1,NULL,NULL,'gym_equipment.svg','Thiết bị tập thể dục',NULL,_binary '\0',_binary '',_binary '\0'),(24,'2021-10-28 10:51:39.057000',1,'2021-10-28 10:51:39.057000',NULL,'free_parking_lot.svg','Chỗ đỗ xe miễn phí ở nơi ở',NULL,_binary '',_binary '\0',_binary '\0'),(25,'2021-10-28 10:51:39.074000',1,'2021-10-28 10:51:39.074000',NULL,'non_free_parking_lot.svg','Chỗ đỗ xe có thu phí trong khuôn viên',NULL,_binary '',_binary '\0',_binary '\0'),(26,'2021-10-28 10:51:39.076000',1,'2021-10-28 10:51:39.076000',NULL,'private_space_for_work.svg','Không gian riêng để làm việc',NULL,_binary '',_binary '\0',_binary '\0'),(27,'2021-10-28 10:51:39.078000',1,'2021-10-28 10:51:39.078000',NULL,'voi_sen_tam_ngoai_troi.svg','Vòi sen tắm ngoài trời',NULL,_binary '',_binary '\0',_binary '\0'),(28,'2021-11-15 10:59:01.000000',1,NULL,NULL,'binh_chua_chay.svg','Bình chữa cháy',NULL,_binary '\0',_binary '\0',_binary ''),(29,'2021-11-15 11:01:32.000000',1,NULL,NULL,'aid_kit.svg','Bộ sơ cứu',NULL,_binary '\0',_binary '\0',_binary '');
/*!40000 ALTER TABLE `amentities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amentity_categories`
--

DROP TABLE IF EXISTS `amentity_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `amentity_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_g9ojc42itdmq11ppfepjcbnvk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amentity_categories`
--

LOCK TABLES `amentity_categories` WRITE;
/*!40000 ALTER TABLE `amentity_categories` DISABLE KEYS */;
INSERT INTO `amentity_categories` VALUES (1,'Phòng tắm',''),(2,'Phòng ngủ và giặt ủi',''),(3,'Giải trí',''),(4,'Hệ thống sưởi và làm mát',''),(5,'Internet và văn phòng',''),(6,'Dịch vụ',''),(7,'Không bao gồm',''),(8,'Tiện nghi nổi bật',''),(9,'Tiện nghi yêu thích',NULL),(10,'Tiện nghi đảm bảo an toàn',NULL);
/*!40000 ALTER TABLE `amentity_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `updated_at` datetime(6) DEFAULT NULL,
  `booking_date` datetime(6) DEFAULT NULL,
  `cancel_date` datetime(6) DEFAULT NULL,
  `checkin_date` datetime(6) DEFAULT NULL,
  `checkout_date` datetime(6) DEFAULT NULL,
  `is_complete` bit(1) NOT NULL,
  `is_refund` tinyint(1) DEFAULT '0',
  `number_of_days` smallint DEFAULT '0',
  `price_per_day` decimal(20,2) NOT NULL,
  `refund_paid` decimal(20,2) DEFAULT '0.00',
  `site_fee` decimal(20,2) NOT NULL,
  `total_fee` decimal(20,2) DEFAULT '0.00',
  `customer_id` int NOT NULL,
  `room_id` int NOT NULL,
  `review_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKib6gjgj2e9binkktxmm175bmm` (`customer_id`),
  KEY `FKrgoycol97o21kpjodw1qox4nc` (`room_id`),
  KEY `FK5kh29npp97yyxsxlouuhb35vr` (`review_id`),
  CONSTRAINT `FK5kh29npp97yyxsxlouuhb35vr` FOREIGN KEY (`review_id`) REFERENCES `reviews` (`id`),
  CONSTRAINT `FKib6gjgj2e9binkktxmm175bmm` FOREIGN KEY (`customer_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKrgoycol97o21kpjodw1qox4nc` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,'2021-11-29 18:05:55.758000',1,'2021-11-29 18:05:55.758000','2021-11-29 18:05:55.742566',NULL,'2021-12-05 00:00:00.000000','2021-12-25 00:00:00.000000',_binary '',0,21,645000.00,0.00,677250.00,13545000.00,17,4,NULL),(2,'2021-11-30 10:45:05.130000',1,'2021-11-30 10:45:05.130000','2021-11-30 10:45:05.121269',NULL,'2021-12-29 00:00:00.000000','2021-12-31 00:00:00.000000',_binary '',0,3,645000.00,0.00,96750.00,1935000.00,17,4,NULL),(3,'2021-12-29 00:00:00.000000',1,NULL,'2020-12-29 00:00:00.000000',NULL,'2022-12-29 00:00:00.000000','2022-12-29 00:00:00.000000',_binary '',0,6,645000.00,0.00,96750.00,100000.00,17,4,NULL),(4,'2021-11-30 21:53:25.173000',1,'2021-11-30 21:53:25.173000','2021-11-30 21:53:25.158439',NULL,'2021-12-01 00:00:00.000000','2021-12-04 00:00:00.000000',_binary '',0,4,645000.00,0.00,129000.00,2580000.00,17,4,NULL),(6,'2021-12-04 00:00:00.000000',1,NULL,'2021-10-30 21:53:25.158439',NULL,'2021-12-01 00:00:00.000000','2021-12-04 00:00:00.000000',_binary '',0,43,645000.00,0.00,129000.00,2580000.00,17,4,NULL),(7,'2021-12-04 00:00:00.000000',1,NULL,'2021-01-30 21:53:25.158439',NULL,'2021-12-01 00:00:00.000000','2021-12-04 00:00:00.000000',_binary '',0,43,645000.00,0.00,129000.00,2580000.00,17,4,NULL),(8,'2021-12-04 00:00:00.000000',1,NULL,'2021-03-30 21:53:25.158439',NULL,'2021-12-01 00:00:00.000000','2021-12-04 00:00:00.000000',_binary '',0,43,645000.00,0.00,129000.00,2580000.00,17,4,NULL),(9,'2021-12-04 00:00:00.000000',1,NULL,'2021-04-30 21:53:25.158439',NULL,'2021-12-01 00:00:00.000000','2021-12-04 00:00:00.000000',_binary '',0,43,645000.00,0.00,129000.00,25800000.00,2,4,NULL),(10,'2021-12-04 00:00:00.000000',1,NULL,'2021-04-30 21:53:25.158439',NULL,'2021-12-01 00:00:00.000000','2021-12-04 00:00:00.000000',_binary '',0,43,645000.00,0.00,129000.00,200000.00,17,4,NULL),(11,'2021-12-01 11:05:56.433000',1,'2021-12-03 15:01:35.631000','2021-12-01 11:05:56.415072',NULL,'2022-02-10 00:00:00.000000','2022-03-09 00:00:00.000000',_binary '',0,28,645000.00,0.00,903000.00,18060000.00,17,4,NULL),(12,'2021-12-01 11:12:48.320000',1,'2021-12-01 11:12:48.320000','2021-12-01 11:12:48.316831',NULL,'2022-04-01 00:00:00.000000','2022-04-09 00:00:00.000000',_binary '',0,9,645000.00,0.00,290250.00,5805000.00,17,4,NULL),(13,'2021-12-01 11:13:33.674000',1,'2021-12-01 11:13:33.674000','2021-12-01 11:13:33.673816',NULL,'2021-12-26 00:00:00.000000','2021-12-28 00:00:00.000000',_binary '',0,3,645000.00,0.00,96750.00,1935000.00,17,4,NULL),(14,'2021-12-01 11:28:30.509000',1,'2021-12-01 11:28:30.509000','2021-12-01 11:28:30.503448',NULL,'2021-12-01 00:00:00.000000','2021-12-02 00:00:00.000000',_binary '',0,2,364.00,0.00,36.40,728.00,17,1,NULL),(15,'2021-12-01 11:28:59.947000',1,'2021-12-01 11:28:59.947000','2021-12-01 11:28:59.946423',NULL,'2021-12-03 00:00:00.000000','2021-12-17 00:00:00.000000',_binary '',0,15,364.00,0.00,273.00,5460.00,17,1,NULL),(16,'2021-12-01 18:19:28.914000',1,'2021-12-02 16:19:44.429000','2021-12-01 18:19:28.903685','2021-12-02 16:19:44.428886','2022-03-14 00:00:00.000000','2022-03-26 00:00:00.000000',_binary '\0',1,13,645000.00,7965750.00,419250.00,8385000.00,17,4,NULL),(17,'2021-12-01 23:13:59.137000',1,'2021-12-02 16:17:31.422000','2021-12-01 23:13:59.134090','2021-12-02 16:17:31.421630','2021-12-18 00:00:00.000000','2021-12-31 00:00:00.000000',_binary '',1,14,364.00,4841.20,254.80,5096.00,17,1,NULL);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `updated_at` datetime(6) DEFAULT NULL,
  `icon` text NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'2021-09-18 16:56:11.537000',1,'2021-09-18 16:56:11.537000','ntc.jpg','Nhà trên cây'),(2,'2021-09-18 16:56:11.561000',1,'2021-09-18 16:56:11.561000','hbtv.jpg','Hồ bơi tuyệt vời'),(3,'2021-09-18 16:56:11.566000',1,'2021-09-18 16:56:11.566000','kz.jpg','Kezhan'),(4,'2021-09-18 16:56:11.572000',1,'2021-09-18 16:56:11.572000','nc.jpg','Nhà container'),(5,'2021-09-18 16:56:11.576000',1,'2021-09-18 16:56:11.576000','hb.jpg','Hướng biển'),(6,'2021-09-18 16:56:11.581000',1,'2021-09-18 16:56:11.581000','rk.jpg','Ryokan'),(7,'2021-09-18 16:56:11.585000',1,'2021-09-18 16:56:11.585000','nn.jpg','Nhà nhỏ'),(8,'2021-09-18 16:56:11.590000',1,'2021-09-18 16:56:11.590000','d.jpg','Đảo'),(9,'2021-09-18 16:56:11.594000',1,'2021-09-18 16:56:11.594000','cb.jpg','Cabin'),(10,'2021-09-18 16:56:11.599000',1,'2021-09-18 16:56:11.599000','nmv.jpg','Nhà mái vòm'),(11,'2021-09-18 16:56:11.604000',1,'2021-09-18 16:56:11.604000','tt.jpg','Trang trại'),(12,'2021-09-18 16:56:11.608000',1,'2021-09-18 16:56:11.608000','ld.jpg','Lâu đài');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `state_id` int DEFAULT NULL,
  `country_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_l61tawv0e2a93es77jkyvi7qa` (`name`),
  KEY `FKsu54e1tlhaof4oklvv7uphsli` (`state_id`),
  KEY `FK6gatmv9dwedve82icy8wrkdmk` (`country_id`),
  CONSTRAINT `FK6gatmv9dwedve82icy8wrkdmk` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`),
  CONSTRAINT `FKsu54e1tlhaof4oklvv7uphsli` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Osaka',NULL,NULL),(2,'Thành phố Thủ Dầu Một',2,NULL),(23,' Thị xã Thủ Dầu Một',23,NULL),(25,' Quận 9',24,NULL);
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `countries` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(10) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1pyiwrqimi3hnl3vtgsypj5r` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'AF','Afghanistan'),(2,'AL','Albania'),(3,'DZ','Algeria'),(4,'AD','Andorra'),(5,'AO','Angola'),(6,'AI','Anguilla'),(7,'AG','Antigua and Barbuda'),(8,'AR','Argentina'),(9,'AM','Armenia'),(10,'AW','Aruba'),(11,'AU','Australia'),(12,'AT','Austria'),(13,'AZ','Azerbaijan'),(14,'BS','Bahamas'),(15,'BH','Bahrain'),(16,'BD','Bangladesh'),(17,'BB','Barbados'),(18,'BY','Belarus'),(19,'BE','Belgium'),(20,'BZ','Belize'),(21,'BJ','Benin'),(22,'BM','Bermuda'),(23,'BT','Bhutan'),(24,'BA','Bosnia and Herzegovina'),(25,'BW','Botswana'),(26,'BV','Bouvet Island'),(27,'BR','Brazil'),(28,'IO','British Indian Ocean Territory'),(29,'BN','Brunei'),(30,'BG','Bulgaria'),(31,'BF','Burkina Faso'),(32,'BI','Burundi'),(33,'KH','Cambodia'),(34,'CM','Cameroon'),(35,'CA','Canada'),(36,'CV','Cape Verde'),(37,'KY','Cayman Islands'),(38,'CF','Central African Republic'),(39,'TD','Chad'),(40,'CL','Chile'),(41,'CN','China'),(42,'CX','Christmas Island'),(43,'CC','Cocos (Keeling) Islands'),(44,'CO','Colombia'),(45,'KM','Comoros'),(46,NULL,'Congo'),(47,'CK','Cook Islands'),(48,'CR','Costa Rica'),(49,'HR','Croatia'),(50,'CU','Cuba'),(51,'CY','Cyprus'),(52,'CZ','Czech Republic'),(53,'DK','Denmark'),(54,'DJ','Djibouti'),(55,'DM','Dominica'),(56,'DO','Dominican Republic'),(57,'EC','Ecuador'),(58,'EG','Egypt'),(59,'SV','El Salvador'),(60,'GQ','Equatorial Guinea'),(61,'ER','Eritrea'),(62,'EE','Estonia'),(63,'ET','Ethiopia'),(64,'FK','Falkland Islands'),(65,'FO','Faroe Islands'),(66,'FJ','Fiji'),(67,'FI','Finland'),(68,'FR','France'),(69,'PF','French Polynesia'),(70,'GA','Gabon'),(71,'GM','Gambia'),(72,'GE','Georgia'),(73,'DE','Germany'),(74,'GH','Ghana'),(75,'GI','Gibraltar'),(76,'GR','Greece'),(77,'GL','Greenland'),(78,'GD','Grenada'),(79,'GP','Guadeloupe'),(80,'GU','Guam'),(81,'GT','Guatemala'),(82,'GG','Guernsey'),(83,'GN','Guinea'),(84,'GW','Guinea-Bissau'),(85,'GY','Guyana'),(86,'HT','Haiti'),(87,'HM','Heard Island and McDonald Islands'),(88,'VA','Vatican City State (Holy See)'),(89,'HN','Honduras'),(90,'HK','Hong Kong'),(91,'HU','Hungary'),(92,'IS','Iceland'),(93,'IN','India'),(94,'ID','Indonesia'),(95,'IR','Iran'),(96,'IQ','Iraq'),(97,'IE','Ireland'),(98,'IM','Isle of Man'),(99,'IL','Israel'),(100,'IT','Italy'),(101,'JM','Jamaica'),(102,'JP','Japan'),(103,'JE','Jersey'),(104,'JO','Jordan'),(105,'KZ','Kazakhstan'),(106,'KE','Kenya'),(107,'KI','Kiribati'),(108,'KW','Kuwait'),(109,'KG','Kyrgyzstan'),(110,'LA','Laos'),(111,'LV','Latvia'),(112,'LB','Lebanon'),(113,'LS','Lesotho'),(114,'LR','Liberia'),(115,'LI','Liechtenstein'),(116,'LT','Lithuania'),(117,'LU','Luxembourg'),(118,'MO','Macau'),(119,'MG','Madagascar'),(120,'MW','Malawi'),(121,'MY','Malaysia'),(122,'MV','Maldives'),(123,'ML','Mali'),(124,'MT','Malta'),(125,'MH','Marshall Islands'),(126,'MQ','Martinique'),(127,'MR','Mauritania'),(128,'MU','Mauritius'),(129,'YT','Mayotte'),(130,'MX','Mexico'),(131,'MC','Monaco'),(132,'MN','Mongolia'),(133,'ME','Montenegro'),(134,'MS','Montserrat'),(135,'MA','Morocco'),(136,'MZ','Mozambique'),(137,'MM','Myanmar'),(138,'NA','Namibia'),(139,'NR','Nauru'),(140,'NP','Nepal'),(141,'NL','Netherlands'),(142,'NC','New Caledonia'),(143,'NZ','New Zealand'),(144,'NI','Nicaragua'),(145,'NE','Niger'),(146,'NG','Nigeria'),(147,'NU','Niue'),(148,'NF','Norfolk Island'),(149,'MP','Northern Mariana Islands'),(150,'NO','Norway'),(151,'OM','Oman'),(152,'PK','Pakistan'),(153,'PW','Palau'),(154,'PA','Panama'),(155,'PG','Papua New Guinea'),(156,'PY','Paraguay'),(157,'PE','Peru'),(158,'PH','Philippines'),(159,'PN','Pitcairn'),(160,'PL','Poland'),(161,'PT','Portugal'),(162,'PR','Puerto Rico'),(163,'QA','Qatar'),(164,'RE','Réunion'),(165,'RO','Romania'),(166,'RW','Rwanda'),(167,'KN','Saint Kitts and Nevis'),(168,'LC','Saint Lucia'),(169,'PM','Saint Pierre and Miquelon'),(170,'VC','Saint Vincent and the Grenadines'),(171,'WS','Samoa'),(172,'SM','San Marino'),(173,'ST','Sao Tome and Principe'),(174,'SA','Saudi Arabia'),(175,'SN','Senegal'),(176,'RS','Serbia'),(177,'SC','Seychelles'),(178,'SL','Sierra Leone'),(179,'SG','Singapore'),(180,'SK','Slovakia'),(181,'SI','Slovenia'),(182,'SB','Solomon Islands'),(183,'SO','Somalia'),(184,'ZA','South Africa'),(185,'GS','South Georgia and the South Sandwich Islands'),(186,'ES','Spain'),(187,'LK','Sri Lanka'),(188,'SD','Sudan'),(189,'SR','Suriname'),(190,'SZ','Swaziland'),(191,'SE','Sweden'),(192,'CH','Switzerland'),(193,'SY','Syria'),(194,'TW','Taiwan'),(195,'TJ','Tajikistan'),(196,'TH','Thailand'),(197,'TL','Timor-Leste'),(198,'TG','Togo'),(199,'TK','Tokelau'),(200,'TO','Tonga'),(201,'TT','Trinidad and Tobago'),(202,'TN','Tunisia'),(203,'TR','Turkey'),(204,'TM','Turkmenistan'),(205,'TC','Turks and Caicos Islands'),(206,'TV','Tuvalu'),(207,'UG','Uganda'),(208,'UA','Ukraine'),(209,'AE','United Arab Emirates'),(210,'GB','United Kingdom'),(211,'US','United States'),(212,'UM','United States Minor Outlying Islands'),(213,'UY','Uruguay'),(214,'UZ','Uzbekistan'),(215,'VU','Vanuatu'),(216,'VN','Vietnam'),(217,'WF','Wallis and Futuna'),(218,'YE','Yemen'),(219,'ZM','Zambia'),(220,'ZW','Zimbabwe');
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currencies`
--

DROP TABLE IF EXISTS `currencies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currencies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `symbol` varchar(10) NOT NULL,
  `unit` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nd7wu9uyva66k99nmgvqlyk8c` (`symbol`),
  UNIQUE KEY `UK_sw9snnq6m3xc7ahlqpb63cdtx` (`unit`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currencies`
--

LOCK TABLES `currencies` WRITE;
/*!40000 ALTER TABLE `currencies` DISABLE KEYS */;
INSERT INTO `currencies` VALUES (1,'$','USD'),(2,'đ','VND');
/*!40000 ALTER TABLE `currencies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdeh4h59nedlwji0j8e57hu9mf` (`room_id`),
  CONSTRAINT `FKdeh4h59nedlwji0j8e57hu9mf` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,'0',1),(2,'1',1),(3,'2',1),(4,'3',1),(5,'4',1),(6,'5',1),(7,'6',1),(8,'7',1),(9,'8',1),(10,'9',1),(11,'10',1),(12,'11',1),(13,'12',1),(14,'13',1),(15,'14',1),(16,'15',1),(17,'Capture.PNG',2),(18,'asdfsad.PNG',2),(19,'BG.png',2),(20,'247129345_909249943358568_6309293780230821703_n.jpg',2),(21,'251815822_4574231789326013_1714193150017519534_n.jpg',2),(27,'Capture.PNG',5),(28,'asdfsad.PNG',5),(29,'BG.png',5),(30,'247129345_909249943358568_6309293780230821703_n.jpg',5),(31,'251815822_4574231789326013_1714193150017519534_n.jpg',5),(32,'Capture.PNG',6),(33,'asdfsad.PNG',6),(34,'BG.png',6),(35,'247129345_909249943358568_6309293780230821703_n.jpg',6),(36,'251815822_4574231789326013_1714193150017519534_n.jpg',6),(37,'asdfsad.PNG',7),(38,'Capture.PNG',7),(39,'BG.png',7),(40,'251815822_4574231789326013_1714193150017519534_n.jpg',7),(41,'247129345_909249943358568_6309293780230821703_n.jpg',7),(42,'asdfsad.PNG',8),(43,'Capture.PNG',8),(44,'BG.png',8),(45,'Capture2.PNG',8),(46,'247129345_909249943358568_6309293780230821703_n.jpg',8),(47,'251815822_4574231789326013_1714193150017519534_n.jpg',8),(119,'asdfsad.PNG',4),(120,'Capture - Copy.PNG',4),(121,'BG.png',4),(122,'Untitled - Copy.png',4),(123,'Capture2.PNG',4),(124,'247129345_909249943358568_6309293780230821703_n.jpg',4);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `updated_at` datetime(6) DEFAULT NULL,
  `comment` varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL,
  `final_rating` tinyint DEFAULT '0',
  `accuracy_rating` tinyint DEFAULT '0',
  `checkin_rating` tinyint DEFAULT '0',
  `cleanliness_rating` tinyint DEFAULT '0',
  `contact_rating` tinyint DEFAULT '0',
  `location_rating` tinyint DEFAULT '0',
  `value_rating` tinyint DEFAULT '0',
  `booking_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK28an517hrxtt2bsg93uefugrm` (`booking_id`),
  CONSTRAINT `FK28an517hrxtt2bsg93uefugrm` FOREIGN KEY (`booking_id`) REFERENCES `bookings` (`id`),
  CONSTRAINT `reviews_chk_1` CHECK (((`final_rating` >= 0) and (`final_rating` <= 5))),
  CONSTRAINT `reviews_chk_2` CHECK (((`accuracy_rating` >= 0) and (`accuracy_rating` <= 5))),
  CONSTRAINT `reviews_chk_3` CHECK (((`checkin_rating` >= 0) and (`checkin_rating` <= 5))),
  CONSTRAINT `reviews_chk_4` CHECK (((`cleanliness_rating` >= 0) and (`cleanliness_rating` <= 5))),
  CONSTRAINT `reviews_chk_5` CHECK (((`contact_rating` >= 0) and (`contact_rating` <= 5))),
  CONSTRAINT `reviews_chk_6` CHECK (((`location_rating` >= 0) and (`location_rating` <= 5))),
  CONSTRAINT `reviews_chk_7` CHECK (((`value_rating` >= 0) and (`value_rating` <= 5)))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` VALUES (1,'2021-11-30 14:34:24.000000',1,NULL,'Good',5,5,5,5,5,5,5,1);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'Host'),(2,'User');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_groups`
--

DROP TABLE IF EXISTS `room_groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_groups` (
  `id` int NOT NULL AUTO_INCREMENT,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rf84hb3gew1auoelhpppgequc` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_groups`
--

LOCK TABLES `room_groups` WRITE;
/*!40000 ALTER TABLE `room_groups` DISABLE KEYS */;
INSERT INTO `room_groups` VALUES (1,'can_ho','Căn hộ'),(2,'nha','Nhà'),(3,'can_ho_phu','Căn hộ phụ'),(4,'khong_gian_doc_dao','Không gian độc đáo'),(5,'cho_nghi_phuc_vu_buoi_sang','Chỗ nghĩ phục vụ buổi sáng'),(6,'khach_san_boutique','Khách sạn boutique');
/*!40000 ALTER TABLE `room_groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_privacy`
--

DROP TABLE IF EXISTS `room_privacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_privacy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6os45y3f1m4q9t2weruhr6b8s` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_privacy`
--

LOCK TABLES `room_privacy` WRITE;
/*!40000 ALTER TABLE `room_privacy` DISABLE KEYS */;
INSERT INTO `room_privacy` VALUES (1,'Phòng chung'),(2,'Phòng riêng'),(3,'Toàn bộ nhà');
/*!40000 ALTER TABLE `room_privacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_types`
--

DROP TABLE IF EXISTS `room_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b70k1tp1aa52elkkxht660u36` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_types`
--

LOCK TABLES `room_types` WRITE;
/*!40000 ALTER TABLE `room_types` DISABLE KEYS */;
INSERT INTO `room_types` VALUES (1,'Căn hộ cho thuê','Một địa điểm cho thuê trong một tòa nhà chung cư hoặc khu phức hợp.'),(2,'Căn hộ (chung cư) cao cấp','Một chỗ ở trong một tòa chung cư hoặc khu phức hợp thuộc sở hữu của cư dân.'),(3,'Tầng lửng','Một căn hộ thường hoặc căn hộ chung cư cao cấp với thiết kế ưu tiên không gian thoáng, có thể có vách tường thấp bên trong.'),(4,'Căn hộ dịch vụ','Một căn hộ có tiện nghi như khách sạn, do một công ty quản lý chuyên nghiệp cung cấp dịch vụ.'),(5,'Casa particular','Một phòng riêng trong một ngôi nhà ở Cuba, cho cảm giác như chỗ nghỉ phục vụ bữa sáng.'),(6,'Nhà nghỉ dưỡng','Một chỗ ở cho thuê đầy đủ tiện nghi, có nhà bếp và phòng tắm, có thể cung cấp một số dịch vụ cho khách, chẳng hạn như quầy lễ tân.');
/*!40000 ALTER TABLE `room_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `updated_at` datetime(6) DEFAULT NULL,
  `accomodates_count` smallint NOT NULL DEFAULT '0',
  `bathroom_count` smallint NOT NULL DEFAULT '0',
  `bed_count` smallint NOT NULL DEFAULT '0',
  `bedroom_count` smallint NOT NULL DEFAULT '0',
  `description` text NOT NULL,
  `latitude` float NOT NULL DEFAULT '0',
  `longitude` float NOT NULL DEFAULT '0',
  `name` varchar(512) NOT NULL,
  `price` float NOT NULL,
  `price_type` varchar(20) NOT NULL,
  `rating` smallint DEFAULT NULL,
  `stay_type` varchar(20) NOT NULL,
  `category_id` int DEFAULT NULL,
  `city_id` int DEFAULT NULL,
  `country_id` int DEFAULT NULL,
  `currency_id` int DEFAULT NULL,
  `host_id` int DEFAULT NULL,
  `state_id` int DEFAULT NULL,
  `minimum_stay` int DEFAULT '1',
  `room_group_id` int DEFAULT NULL,
  `room_type_id` int DEFAULT NULL,
  `privacy_type` varchar(255) DEFAULT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `room_privacy_id` int DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKny0kelw84f5mokcnk9pga4qb9` (`category_id`),
  KEY `FKg3b03ni2v2le38dc78o8rcd4` (`city_id`),
  KEY `FKrscqoph26huxvm2lkewu1yw0m` (`country_id`),
  KEY `FKfd82wo2bvs7xb0vqybc6ksgj7` (`currency_id`),
  KEY `FKoxd2xovikhdpk4si9ffhyb83i` (`host_id`),
  KEY `FK8mo2x2ce9mde7dfh8omlitwcr` (`state_id`),
  KEY `FKknoei1pgkh3rfcr1cyj83o0nn` (`room_group_id`),
  KEY `FKh9m2n1paq5hmd3u0klfl7wsfv` (`room_type_id`),
  KEY `FK1hld7yr64yj7sossifs9kk80e` (`room_privacy_id`),
  CONSTRAINT `FK1hld7yr64yj7sossifs9kk80e` FOREIGN KEY (`room_privacy_id`) REFERENCES `room_privacy` (`id`),
  CONSTRAINT `FK8mo2x2ce9mde7dfh8omlitwcr` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`),
  CONSTRAINT `FKfd82wo2bvs7xb0vqybc6ksgj7` FOREIGN KEY (`currency_id`) REFERENCES `currencies` (`id`),
  CONSTRAINT `FKg3b03ni2v2le38dc78o8rcd4` FOREIGN KEY (`city_id`) REFERENCES `cities` (`id`),
  CONSTRAINT `FKh9m2n1paq5hmd3u0klfl7wsfv` FOREIGN KEY (`room_type_id`) REFERENCES `room_types` (`id`),
  CONSTRAINT `FKknoei1pgkh3rfcr1cyj83o0nn` FOREIGN KEY (`room_group_id`) REFERENCES `room_groups` (`id`),
  CONSTRAINT `FKny0kelw84f5mokcnk9pga4qb9` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`),
  CONSTRAINT `FKoxd2xovikhdpk4si9ffhyb83i` FOREIGN KEY (`host_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKrscqoph26huxvm2lkewu1yw0m` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'2021-09-18 17:04:52.572000',1,'2021-09-18 17:04:52.572000',2,1,2,1,'GOSHOBO is the oldest ryokan hotel in Arima Onsen, one of the three historical hot springs in Japan. It has served people since its foundation in the 12th century.\n\nThe guest room is a nostalgic tatami room with two beds, with a modern updated toilet attached. You can view a serene Zen garden and the refreshing Mt. Rokko.\n\nThe public hot spring bath in the hotel is open from 3 pm to 9:30 am.',11.0341,106.688,'A Relaxing Stay at a Historical Hot Spring Ryokan Hotel in Arima(a twin room, up to two people)歴史的木造建築の旅館,景観は庭のツイン【2名定員】',364,'PER_NIGHT',0,'DAY',6,1,1,1,2,1,0,3,3,NULL,'1',2,' 92 đường Man Thiện - P.Tăng Nhơn Phú A - Q9'),(4,'2021-11-20 13:36:05.022000',1,'2021-12-02 20:01:10.585000',3,3,3,3,'Vị trí trung tâm,Phù hợp cho gia đình',11.0341,106.688,'Stunning wooden stilted house near Ban Dam Museum',645000,'PER_NIGHT',0,'WEEK',1,25,216,2,17,24,1,3,3,NULL,'Capture - Copy.PNG',2,' 92 đường Man Thiện - P.Tăng Nhơn Phú A - Q9');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms_amentities`
--

DROP TABLE IF EXISTS `rooms_amentities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms_amentities` (
  `room_id` int NOT NULL,
  `amentity_id` int NOT NULL,
  PRIMARY KEY (`room_id`,`amentity_id`),
  KEY `FKaslg3vgkpd41lbv2nev6ex2m0` (`amentity_id`),
  CONSTRAINT `FK3vsc016iloj07mhxxgissyi4` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `FKaslg3vgkpd41lbv2nev6ex2m0` FOREIGN KEY (`amentity_id`) REFERENCES `amentities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms_amentities`
--

LOCK TABLES `rooms_amentities` WRITE;
/*!40000 ALTER TABLE `rooms_amentities` DISABLE KEYS */;
INSERT INTO `rooms_amentities` VALUES (1,1),(1,3),(1,4),(2,4),(4,4),(5,4),(6,4),(1,5),(4,5),(1,6),(1,7),(4,8),(4,9),(4,10),(2,11),(4,11),(5,11),(6,11),(4,14),(4,15),(4,16),(4,17),(4,18),(7,18),(2,19),(4,19),(5,19),(6,19),(4,20),(4,21),(4,22),(8,22),(4,23),(4,24),(4,25),(4,26),(7,26),(8,26),(4,27),(7,29),(8,29);
/*!40000 ALTER TABLE `rooms_amentities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms_rules`
--

DROP TABLE IF EXISTS `rooms_rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms_rules` (
  `room_id` int NOT NULL,
  `rule_id` int NOT NULL,
  PRIMARY KEY (`room_id`,`rule_id`),
  KEY `FKowo1c7x1smxmbbl4ri710abb0` (`rule_id`),
  CONSTRAINT `FKkna08eq1yg0wy7ulco4merufw` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `FKowo1c7x1smxmbbl4ri710abb0` FOREIGN KEY (`rule_id`) REFERENCES `rules` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms_rules`
--

LOCK TABLES `rooms_rules` WRITE;
/*!40000 ALTER TABLE `rooms_rules` DISABLE KEYS */;
INSERT INTO `rooms_rules` VALUES (1,1),(2,1),(4,1),(5,1),(6,1),(7,1),(8,1),(1,2),(2,2),(4,2),(5,2),(6,2),(7,2),(8,2),(1,3),(2,3),(4,3),(5,3),(6,3),(7,3),(8,3),(1,4),(2,4),(4,4),(5,4),(6,4),(7,4),(8,4),(1,5),(2,5),(4,5),(5,5),(6,5),(7,5),(8,5);
/*!40000 ALTER TABLE `rooms_rules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rules`
--

DROP TABLE IF EXISTS `rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rules` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `updated_at` datetime(6) DEFAULT NULL,
  `icon` text NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5ajydv93eveurhwiye50g5tpp` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rules`
--

LOCK TABLES `rules` WRITE;
/*!40000 ALTER TABLE `rules` DISABLE KEYS */;
INSERT INTO `rules` VALUES (1,'2021-09-18 16:58:01.521000',1,'2021-09-18 16:58:01.521000','<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 16px; width: 16px; fill: currentcolor;\"><path d=\"M16 1C7.716 1 1 7.716 1 16s6.716 15 15 15 15-6.716 15-15S24.284 1 16 1zm7.895 22.954l-9.526-5.5a1.5 1.5 0 0 1-.743-1.154l-.007-.145v-11h3V16.29l8.776 5.067-1.5 2.598z\"></path></svg>','Trả phòng: 10:00'),(2,'2021-09-18 16:58:09.464000',1,'2021-09-18 16:58:09.464000','<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 16px; width: 16px; fill: currentcolor;\"><path d=\"M19.862 24.105l1.535 1.536a2 2 0 0 0 2.327 2.327l1.55 1.548A4 4 0 0 1 19.365 26a3.913 3.913 0 0 1 .444-1.8l.052-.095zM11.757 16l8 8H10.81c.344.59.555 1.268.555 2a4 4 0 0 1-8 0c0-1.545.885-2.87 2.167-3.536.025-.039.05-.078.078-.116l.048-.055L9.95 18H8.364a3 3 0 0 0-1.976.743l-.145.136-3.829 3.829L1 21.294l3.829-3.83A5 5 0 0 1 8.1 16.008L8.364 16h3.393zM3.979 2.565l25.456 25.456-1.414 1.414L2.565 3.979l1.414-1.414zM7.365 24a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm23-22v2h-2.343a3 3 0 0 0-1.977.743l-.144.136-.536.535V19c0 .63-.122 1.23-.34 1.782l-8.929-8.929.06-.06-.184-.064-2.365-2.363A4 4 0 0 1 17.365 4a3.983 3.983 0 0 1 3.728 2.6l.066.192.499-.5.272-.27 2.556-2.558a5 5 0 0 1 3.271-1.457L28.022 2h2.343z\"></path></svg>','Không phù hợp với trẻ em và em bé'),(3,'2021-09-18 16:58:09.476000',1,'2021-09-18 16:58:09.476000','<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 16px; width: 16px; fill: currentcolor;\"><path d=\"M19.862 24.105l1.535 1.536a2 2 0 0 0 2.327 2.327l1.55 1.548A4 4 0 0 1 19.365 26a3.913 3.913 0 0 1 .444-1.8l.052-.095zM11.757 16l8 8H10.81c.344.59.555 1.268.555 2a4 4 0 0 1-8 0c0-1.545.885-2.87 2.167-3.536.025-.039.05-.078.078-.116l.048-.055L9.95 18H8.364a3 3 0 0 0-1.976.743l-.145.136-3.829 3.829L1 21.294l3.829-3.83A5 5 0 0 1 8.1 16.008L8.364 16h3.393zM3.979 2.565l25.456 25.456-1.414 1.414L2.565 3.979l1.414-1.414zM7.365 24a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm23-22v2h-2.343a3 3 0 0 0-1.977.743l-.144.136-.536.535V19c0 .63-.122 1.23-.34 1.782l-8.929-8.929.06-.06-.184-.064-2.365-2.363A4 4 0 0 1 17.365 4a3.983 3.983 0 0 1 3.728 2.6l.066.192.499-.5.272-.27 2.556-2.558a5 5 0 0 1 3.271-1.457L28.022 2h2.343z\"></path></svg>','Không hút thuốc'),(4,'2021-09-18 16:58:09.478000',1,'2021-09-18 16:58:09.478000','<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 16px; width: 16px; fill: currentcolor;\"><path d=\"M19.862 24.105l1.535 1.536a2 2 0 0 0 2.327 2.327l1.55 1.548A4 4 0 0 1 19.365 26a3.913 3.913 0 0 1 .444-1.8l.052-.095zM11.757 16l8 8H10.81c.344.59.555 1.268.555 2a4 4 0 0 1-8 0c0-1.545.885-2.87 2.167-3.536.025-.039.05-.078.078-.116l.048-.055L9.95 18H8.364a3 3 0 0 0-1.976.743l-.145.136-3.829 3.829L1 21.294l3.829-3.83A5 5 0 0 1 8.1 16.008L8.364 16h3.393zM3.979 2.565l25.456 25.456-1.414 1.414L2.565 3.979l1.414-1.414zM7.365 24a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm23-22v2h-2.343a3 3 0 0 0-1.977.743l-.144.136-.536.535V19c0 .63-.122 1.23-.34 1.782l-8.929-8.929.06-.06-.184-.064-2.365-2.363A4 4 0 0 1 17.365 4a3.983 3.983 0 0 1 3.728 2.6l.066.192.499-.5.272-.27 2.556-2.558a5 5 0 0 1 3.271-1.457L28.022 2h2.343z\"></path></svg>','Không thú cưng'),(5,'2021-09-18 16:58:09.480000',1,'2021-09-18 16:58:09.480000','<svg viewBox=\"0 0 32 32\" xmlns=\"http://www.w3.org/2000/svg\" aria-hidden=\"true\" role=\"presentation\" focusable=\"false\" style=\"display: block; height: 16px; width: 16px; fill: currentcolor;\"><path d=\"M19.862 24.105l1.535 1.536a2 2 0 0 0 2.327 2.327l1.55 1.548A4 4 0 0 1 19.365 26a3.913 3.913 0 0 1 .444-1.8l.052-.095zM11.757 16l8 8H10.81c.344.59.555 1.268.555 2a4 4 0 0 1-8 0c0-1.545.885-2.87 2.167-3.536.025-.039.05-.078.078-.116l.048-.055L9.95 18H8.364a3 3 0 0 0-1.976.743l-.145.136-3.829 3.829L1 21.294l3.829-3.83A5 5 0 0 1 8.1 16.008L8.364 16h3.393zM3.979 2.565l25.456 25.456-1.414 1.414L2.565 3.979l1.414-1.414zM7.365 24a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm23-22v2h-2.343a3 3 0 0 0-1.977.743l-.144.136-.536.535V19c0 .63-.122 1.23-.34 1.782l-8.929-8.929.06-.06-.184-.064-2.365-2.363A4 4 0 0 1 17.365 4a3.983 3.983 0 0 1 3.728 2.6l.066.192.499-.5.272-.27 2.556-2.558a5 5 0 0 1 3.271-1.457L28.022 2h2.343z\"></path></svg>','Không được tổ chức tiệc hoặc sự kiện');
/*!40000 ALTER TABLE `rules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `states`
--

DROP TABLE IF EXISTS `states`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `states` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `country_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_nau09mwrvhjj0n0a6gfo5xmp3` (`name`),
  KEY `FKskkdphjml9vjlrqn4m5hi251y` (`country_id`),
  CONSTRAINT `FKskkdphjml9vjlrqn4m5hi251y` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `states`
--

LOCK TABLES `states` WRITE;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` VALUES (1,NULL,'Kansai',1),(2,'74','Tỉnh Bình Dương',1),(23,NULL,' Binh Duong',216),(24,NULL,' Ho Chi Minh City 715100',216);
/*!40000 ALTER TABLE `states` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `status` tinyint(1) DEFAULT '1',
  `updated_at` datetime(6) DEFAULT NULL,
  `about` varchar(1024) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(48) NOT NULL,
  `is_supreme_host` tinyint(1) DEFAULT '0',
  `is_verified` tinyint(1) DEFAULT '0',
  `last_name` varchar(48) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone_number` varchar(10) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `address_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `supreme_host` tinyint(1) DEFAULT '0',
  `phone_verified` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FKe8vydtk7hf0y16bfm558sywbb` (`address_id`),
  KEY `FKp56c1712k691lhsyewcssf40f` (`role_id`),
  CONSTRAINT `FKe8vydtk7hf0y16bfm558sywbb` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`id`),
  CONSTRAINT `FKp56c1712k691lhsyewcssf40f` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'2021-09-18 17:04:41.193000',1,'2021-10-07 13:32:09.260000','Tôi tên là 陶泉 御所坊','avatar.png','2000-05-22 00:00:00.000000','kongngoxilau@gmail.com','陶泉',0,0,'御所坊','$2a$10$5Tyfy2l08EtsaFjxpZZsqOLBGx3ZwT330aHodYJACdt2GsbK9gSGu','0353996236','MALE',2,1,0,0),(17,'2021-10-22 16:41:45.427000',1,'2021-10-26 14:01:44.304000',NULL,'arturo-rodriguez-dHKMgpt0NEo-unsplash.jpg','2021-10-04 00:00:00.000000','thuan.leminhthuan.10.2999999@gmail.com','Thuận',0,0,'Lê','$2a$10$8XZPytlKxO0VcoMLFPicsuA7Lw2XQ1w/mOvsSEHGo.KaUfsbcwT1K','0353996239','MALE',8,2,0,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_favorite_rooms`
--

DROP TABLE IF EXISTS `users_favorite_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_favorite_rooms` (
  `user_id` int NOT NULL,
  `room_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`room_id`),
  KEY `FKdc4yxtd46kdpr2ygmmsvfvpei` (`room_id`),
  CONSTRAINT `FKdc4yxtd46kdpr2ygmmsvfvpei` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`),
  CONSTRAINT `FKpaue0mr2uyjg7rurcp7gfl2g5` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_favorite_rooms`
--

LOCK TABLES `users_favorite_rooms` WRITE;
/*!40000 ALTER TABLE `users_favorite_rooms` DISABLE KEYS */;
INSERT INTO `users_favorite_rooms` VALUES (17,4);
/*!40000 ALTER TABLE `users_favorite_rooms` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-04  0:42:20
