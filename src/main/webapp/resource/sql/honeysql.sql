/*
SQLyog v10.2 
MySQL - 5.7.12-log : Database - honey
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`honey` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `honey`;

/*Table structure for table `tb_banner` */

DROP TABLE IF EXISTS `tb_banner`;

CREATE TABLE `tb_banner` (
  `bannerId` int(11) NOT NULL AUTO_INCREMENT,
  `imgName` varchar(255) NOT NULL,
  `imgUrl` varchar(255) NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `delflag` int(11) DEFAULT '0',
  PRIMARY KEY (`bannerId`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Data for the table `tb_banner` */

insert  into `tb_banner`(`bannerId`,`imgName`,`imgUrl`,`sort`,`url`,`delflag`) values (47,'20170512C3DA.jpg','resource/uploadFile/20170512C3DA.jpg',1,'javascript:;',0);

/*Table structure for table `tb_permission` */

DROP TABLE IF EXISTS `tb_permission`;

CREATE TABLE `tb_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mark` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `father_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27645C40D4993F85` (`father_id`),
  CONSTRAINT `FK27645C40D4993F85` FOREIGN KEY (`father_id`) REFERENCES `tb_permission` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_permission` */

insert  into `tb_permission`(`id`,`mark`,`name`,`father_id`) values (1,'fatherMark','超级权限',NULL),(2,'*:*','超级权限',1);

/*Table structure for table `tb_roles` */

DROP TABLE IF EXISTS `tb_roles`;

CREATE TABLE `tb_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolesName` varchar(255) NOT NULL,
  `father_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFAC1F5ACDA9AEBC3` (`father_id`),
  CONSTRAINT `FKFAC1F5ACDA9AEBC3` FOREIGN KEY (`father_id`) REFERENCES `tb_roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `tb_roles` */

insert  into `tb_roles`(`id`,`rolesName`,`father_id`) values (1,'超级管理员',NULL),(2,'超级管理员',1);

/*Table structure for table `tb_roles_permission` */

DROP TABLE IF EXISTS `tb_roles_permission`;

CREATE TABLE `tb_roles_permission` (
  `roles_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`roles_id`,`permission_id`),
  KEY `FKC3422242AB9F5AC2` (`roles_id`),
  KEY `FKC3422242601B4632` (`permission_id`),
  CONSTRAINT `FKC3422242601B4632` FOREIGN KEY (`permission_id`) REFERENCES `tb_permission` (`id`),
  CONSTRAINT `FKC3422242AB9F5AC2` FOREIGN KEY (`roles_id`) REFERENCES `tb_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_roles_permission` */

insert  into `tb_roles_permission`(`roles_id`,`permission_id`) values (2,2);

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`password`,`username`) values (1,'okrxwj0H7bcuegXhFnZveQ==','lujiawei');

/*Table structure for table `tb_user_roles` */

DROP TABLE IF EXISTS `tb_user_roles`;

CREATE TABLE `tb_user_roles` (
  `user_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`roles_id`),
  KEY `FKB9812FBA2692B6B2` (`user_id`),
  KEY `FKB9812FBAAB9F5AC2` (`roles_id`),
  CONSTRAINT `FKB9812FBA2692B6B2` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`),
  CONSTRAINT `FKB9812FBAAB9F5AC2` FOREIGN KEY (`roles_id`) REFERENCES `tb_roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user_roles` */

insert  into `tb_user_roles`(`user_id`,`roles_id`) values (1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
