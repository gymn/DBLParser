--
-- Create schema dblp
--
CREATE DATABASE IF NOT EXISTS dblp;
USE dblp;

--
-- Table structure for table `documents`
--

DROP TABLE IF EXISTS `documents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `documents` (
  `doc_id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_type` varchar(50) DEFAULT NULL,
  `doc_authors` text,
  `doc_title` text,
  `doc_year` int(11) DEFAULT '0',
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
