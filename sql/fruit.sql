/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.7.27 : Database - fmall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fmall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `fmall`;

/*Table structure for table `tb_fruit` */

DROP TABLE IF EXISTS `tb_fruit`;

CREATE TABLE `tb_fruit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '水果id',
  `fruit_name` varchar(150) NOT NULL COMMENT '水果名',
  `seller_id` bigint(20) NOT NULL COMMENT '商家id',
  `stock` int(10) unsigned NOT NULL COMMENT '库存数量',
  `each_price` decimal(6,2) unsigned NOT NULL COMMENT '单价',
  `disciption` varchar(300) NOT NULL COMMENT '商品描述',
  `img_url` varchar(32) NOT NULL COMMENT '商品图片',
  PRIMARY KEY (`id`),
  KEY `index_seller_id` (`seller_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `tb_fruit` */

insert  into `tb_fruit`(`id`,`fruit_name`,`seller_id`,`stock`,`each_price`,`disciption`,`img_url`) values (1,'台湾凤梨',1,838,'2.00','这是美味的西瓜，原产自澳大利亚，鲜美无比，你值得拥有。','/images/1.jpg'),(2,'龙蛇果',1,1000,'7.50','苹果是蔷薇科苹果亚科苹果属植物，其树为落叶乔木。苹果营养价值很高，富含矿物质和维生素，含钙量丰富。','/images/import_list2.jpg'),(3,'帝王香蕉',1,1000,'15.00','来自台湾香蕉之地，不买你就买不到啦，千万不要错过哦。','/images/buy_list2.jpg'),(4,'车厘子',1,941,'58.50','这是樱桃的进化版，好吃的很哦，想不到的美味，你值的拥有','/images/self_list11.jpg'),(5,'蓝莓',1,997,'4.45','自然耕作，自然成熟，天然无害，果粉完整，甜中带酸','/images/list1.jpg'),(6,'黄果猕猴桃',1,1000,'14.50','产自无机无害的天然果园，每天进行灌溉，长出成熟的果实。','/images/list2.jpg'),(7,'南非进口柠檬',1,1000,'29.00','正宗南非柠檬,丰富的维生素含量,美容的天然佳品。','/images/pic1.jpg'),(8,'鲜蓝莓',1,999,'20.00','采摘下来的野生蓝莓有的运到城市里，制成了蓝苺果汁，蓝苺果酱，或者蓝苺酒，有的运到阴房里制成了蓝苺干。蓝苺的营养价值丰富，是最健康的野生水果之一。','/images/pic2.jpg'),(9,'红啤梨',1,1000,'48.00','果体硬度好，肉细嫩、口感甜、果汁多，清甜的味道。','/images/pic3.jpg'),(10,'无仔红提',1,1000,'39.00','由红提取物品种改良为无籽,口味纯正,皮薄肉厚,味甜脆,果肉硬,色泽深红,爽口,甜度高。','/images/pic4.jpg'),(11,'水果沙拉',1,1000,'9.90','多种水果结合，营养丰富，鲜美无比。','/images/list3.jpg'),(12,'美味芒果',1,1000,'13.99','“芒芒”人海，有“果”相伴。“粒粒”红香果,“汁汁”甜润喉。','/images/buy_detail5.png'),(13,'美味百香果',1,1000,'17.99','百香，百味，百分!百种营养，千般滋味，泰泰百香果汁。','/images/buy_detail9.png'),(14,'红心柚',1,1000,'9.99','客天下金柚，有你有他“柚”客家!','/images/buy_detail4.png'),(15,'红蛇果',1,1000,'16.99','有身份的苹果，高品质的生活！','/images/buy_detail6.png'),(16,'香甜柑橘',1,1000,'8.50','爱上你，就喜欢这种如同初恋般的情感，酸甜只有你我知道！','/images/list10.jpg'),(17,'美国进口青苹果',1,500,'13.50','产自美国洛杉矶产业园，科学种植，营养丰富，酸酸甜甜！','/images/list11.jpg');

/*Table structure for table `tb_member` */

DROP TABLE IF EXISTS `tb_member`;

CREATE TABLE `tb_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `tel` bigint(20) unsigned NOT NULL COMMENT '用户手机号',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `status` tinyint(4) DEFAULT '1' COMMENT '用户状态',
  `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `nick_name` varchar(24) DEFAULT 'NULL' COMMENT '昵称',
  `age` tinyint(4) DEFAULT NULL COMMENT '年级',
  `sex` tinyint(1) unsigned DEFAULT '0' COMMENT '性别 0男 1女',
  `seller` tinyint(1) DEFAULT '0' COMMENT '是否商家 0否 1是',
  `salt` varchar(64) NOT NULL COMMENT '加密盐值',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`),
  KEY `tel_index` (`tel`),
  KEY `index_emaill` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `tb_member` */

/*Table structure for table `tb_member_address` */

DROP TABLE IF EXISTS `tb_member_address`;

CREATE TABLE `tb_member_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `member_id` bigint(20) NOT NULL COMMENT '用户id',
  `recive_name` varchar(64) NOT NULL COMMENT '收货人',
  `tel` bigint(20) NOT NULL COMMENT '手机号',
  `prov` varchar(24) NOT NULL COMMENT '省份',
  `city` varchar(24) NOT NULL COMMENT '城市',
  `area` varchar(24) DEFAULT NULL COMMENT '市区',
  `stree_name` varchar(255) NOT NULL COMMENT '街道',
  `default_address` tinyint(4) DEFAULT '0' COMMENT '是否为默认地址 0否',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `number` char(12) DEFAULT 'NULL' COMMENT '邮政编码',
  PRIMARY KEY (`id`),
  KEY `index_default_address_member_id` (`default_address`,`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `tb_member_address` */

/*Table structure for table `tb_order` */

DROP TABLE IF EXISTS `tb_order`;

CREATE TABLE `tb_order` (
  `id` varchar(64) NOT NULL COMMENT '订单id',
  `fruit_id` bigint(20) DEFAULT NULL COMMENT '水果id',
  `fruit_name` varchar(64) NOT NULL COMMENT '水果名',
  `member_id` bigint(20) unsigned DEFAULT NULL COMMENT '用户id',
  `nick_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次修改时间',
  `order_state` tinyint(4) DEFAULT '0' COMMENT '订单状态',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  `order_total` int(10) unsigned NOT NULL DEFAULT '1' COMMENT '订单数',
  `ideal_pay` decimal(6,2) NOT NULL COMMENT '应付金额',
  `actual_pay` decimal(6,2) DEFAULT NULL COMMENT '实付金额',
  `post_way` varchar(60) NOT NULL COMMENT '邮寄方式',
  `free_post` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0免邮 1不免邮',
  `post_fee` decimal(6,2) unsigned DEFAULT NULL COMMENT '邮寄费用',
  `consign_time` timestamp NULL DEFAULT NULL COMMENT '发货时间',
  `each_price` decimal(6,2) unsigned NOT NULL COMMENT '单价',
  `favorable_id` bigint(20) unsigned DEFAULT NULL COMMENT '优惠Id',
  `voucher` varchar(64) DEFAULT NULL COMMENT '抵用券',
  `voucher_fee` decimal(6,2) unsigned DEFAULT NULL COMMENT '抵用费用',
  `seller_id` bigint(20) unsigned DEFAULT NULL COMMENT '卖家商店id',
  `stree_name` varchar(300) NOT NULL COMMENT '收获地址',
  `address_id` bigint(20) unsigned NOT NULL COMMENT '地址id',
  `img_url` varchar(64) NOT NULL COMMENT '水果图片地址',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_order` */

/*Table structure for table `tb_panel` */

DROP TABLE IF EXISTS `tb_panel`;

CREATE TABLE `tb_panel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `fruit_id` bigint(20) unsigned NOT NULL COMMENT '水果id',
  `panel_img_url` varchar(64) NOT NULL COMMENT 'panel图片地址',
  `order` tinyint(3) unsigned NOT NULL COMMENT '优先级',
  `state` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态',
  `alive_time` tinyint(4) NOT NULL COMMENT '还剩几天过期',
  `panel_location` tinyint(4) NOT NULL COMMENT '播放位置',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `last_update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次修改时间',
  `fruit_name` varchar(64) NOT NULL COMMENT '水果名',
  `each_price` decimal(6,2) NOT NULL COMMENT '水果价格',
  `seller_id` bigint(20) NOT NULL COMMENT '商家id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `tb_panel` */

insert  into `tb_panel`(`id`,`fruit_id`,`panel_img_url`,`order`,`state`,`alive_time`,`panel_location`,`create_time`,`last_update_time`,`fruit_name`,`each_price`,`seller_id`) values (1,5,'/images/list1.jpg',1,1,1,2,'2019-10-04 23:14:06','2019-10-04 23:14:06','芒果','4.00',1),(2,6,'/images/list2.jpg',1,1,1,2,'2019-10-04 23:14:31','2019-10-04 23:14:31','苹果','9.00',1),(3,11,'/images/list3.jpg',1,1,1,2,'2019-10-05 12:04:16','2019-10-05 12:04:16','西瓜','17.00',1),(4,7,'/images/pic1.jpg',1,1,1,4,'2019-10-05 15:37:55','2019-10-05 15:37:55','南非进口柠檬','29.00',1),(5,8,'/images/pic2.jpg',1,1,1,4,'2019-10-05 15:39:48','2019-10-05 15:39:48','鲜蓝莓','20.00',1),(6,9,'/images/pic3.jpg',1,1,1,4,'2019-10-05 15:42:08','2019-10-05 15:42:08','红啤梨','48.00',1),(7,10,'/images/pic4.jpg',1,1,1,4,'2019-10-05 15:43:29','2019-10-05 15:43:29','无仔红提','39.00',1),(8,3,'/images/buy_list2.jpg',1,1,1,4,'2019-10-05 15:44:46','2019-10-05 15:44:46','帝王蕉','15.00',1),(9,4,'/images/self_list11.jpg',1,1,1,4,'2019-10-05 15:46:14','2019-10-05 15:46:14','车厘子','30.50',1),(10,12,'/images/buy_detail5.png',1,1,1,3,'2019-10-05 16:48:51','2019-10-05 16:48:51','美味芒果','13.99',1),(11,13,'/images/buy_detail9.png',1,1,1,3,'2019-10-05 16:50:13','2019-10-05 16:50:13','美味百香果','17.99',1),(12,14,'/images/buy_detail4.png',1,1,1,3,'2019-10-05 16:51:30','2019-10-05 16:51:30','红心柚','9.99',1),(13,15,'/images/buy_detail6.png',1,1,1,3,'2019-10-05 16:52:23','2019-10-05 16:52:23','红蛇果','16.99',1),(14,0,'/images/banner1.jpg',1,1,1,0,'2019-10-05 23:18:38','2019-10-05 23:18:38','\"\"','0.00',1),(15,0,'/images/banner2.jpg',1,1,1,0,'2019-10-05 23:20:01','2019-10-05 23:20:01','\"\"','0.00',2),(16,0,'/images/banner7.jpg',1,1,1,0,'2019-10-05 23:20:52','2019-10-05 23:20:52','\"\"','0.00',3),(17,16,'/images/list10.jpg',1,1,1,1,'2019-10-05 23:28:36','2019-10-05 23:28:36','香甜柑橘','8.50',1),(18,17,'/images/list11.jpg',1,1,1,1,'2019-10-05 23:31:40','2019-10-05 23:31:40','美国青苹果','13.50',1),(19,4,'/images/list12.jpg',1,1,1,1,'2019-10-05 23:33:16','2019-10-05 23:33:16','智利车厘子','58.50',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
