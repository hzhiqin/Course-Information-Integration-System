-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: schedule_database
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courses` (
  `id_course` varchar(45) NOT NULL,
  `course_name` varchar(200) NOT NULL,
  `crn` int(11) NOT NULL,
  `credits` float DEFAULT NULL,
  `instructor` varchar(45) DEFAULT NULL,
  `room` varchar(45) DEFAULT NULL,
  `day_of_week` varchar(45) DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `booklist` varchar(200) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `major` varchar(45) DEFAULT NULL,
  `pre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`crn`),
  UNIQUE KEY `crn_UNIQUE` (`crn`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('CSCI 6441','Database Management Systems',11518,3,'Fernandez, R','FNGR 222','R','18:10:00','20:40:00','2016-08-29','2016-12-12','http://www.bkstr.com/webapp/wcs/stores/servlet/booklookServlet?bookstore_id-1=122&term_id-1=201603&div-1=&dept-1=CSCI&course-1=6441&section-1=10',1,'computer science','CSCI 6221,CSCI 6461'),('CSCI 6461','Computer System Architecture',13333,3,'Lancaster, M','FNGR 223','W','18:10:00','20:40:00','2016-08-29','2016-12-12','http://www.bkstr.com/webapp/wcs/stores/servlet/booklookServlet?bookstore_id-1=122&term_id-1=201603&div-1=&dept-1=CSCI&course-1=6461&section-1=10',1,'computer science',' '),('EAP 6110','Academic Writing and Research for International Graduate Students I',13837,3,'VanLanduyt II, A','ROME 204','WF','09:35:00','10:50:00','2016-08-29','2016-12-12','http://www.bkstr.com/webapp/wcs/stores/servlet/booklookServlet?bookstore_id-1=122&term_id-1=201603&div-1=&dept-1=EAP&course-1=6110&section-1=19',0,'english for academic  purpose',' '),('CSCI 6221','Advanced Software Paradigms',13958,3,'Hwang, Y','Grad 618','S','13:00:00','15:30:00','2016-08-29','2016-12-12','http://www.bkstr.com/webapp/wcs/stores/servlet/booklookServlet?bookstore_id-1=122&term_id-1=201603&div-1=&dept-1=CSCI&course-1=6221&section-1=AR',1,'computer science',' '),('FINA 6224','Financial Management',13991,3,'Cohen, N','DUQUES 258','M','16:30:00','19:00:00','2016-08-29','2016-12-12','http://www.bkstr.com/webapp/wcs/stores/servlet/booklookServlet?bookstore_id-1=122&term_id-1=201603&div-1=&dept-1=FINA&course-1=6224&section-1=10',0,'finance',' '),('CSCI 6561','Design of Human Computer Interface',14817,3,'Heller, R','TOMP 309','T','18:00:10','20:40:00','2016-08-29','2016-12-12','http://www.bkstr.com/webapp/wcs/stores/servlet/booklookServlet?bookstore_id-1=122&term_id-1=201603&div-1=&dept-1=CSCI&course-1=6561&section-1=10',0,'computer science','CSCI 6212,CSCI 3411'),('FINA 4301','Financial Derivatives',17165,3,'Henderson, B','DUQUES 258','MW','14:20:00','15:35:00','2016-08-29','2016-12-12','http://www.bkstr.com/webapp/wcs/stores/servlet/booklookServlet?bookstore_id-1=122&term_id-1=201603&div-1=&dept-1=FINA&course-1=4301&section-1=10',2,'finance',' ');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'huangZQ','311'),(2,'YanZhihao','666'),(3,'coolboy','iluvmymom'),(4,'vanilla','59021');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_courses`
--

DROP TABLE IF EXISTS `user_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_courses` (
  `user_id` int(10) unsigned NOT NULL,
  `course_crn` int(11) unsigned NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_courses`
--

LOCK TABLES `user_courses` WRITE;
/*!40000 ALTER TABLE `user_courses` DISABLE KEYS */;
INSERT INTO `user_courses` VALUES (2,13333),(3,17165),(1,11518),(1,13837),(1,13958),(1,17165);
/*!40000 ALTER TABLE `user_courses` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-01 18:45:56
