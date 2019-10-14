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

insert  into `tb_fruit`(`id`,`fruit_name`,`seller_id`,`stock`,`each_price`,`disciption`,`img_url`) values (1,'台湾凤梨',1,838,'2.00','这是美味的西瓜，原产自澳大利亚，鲜美无比，你值得拥有。','/images/1.jpg'),(2,'龙蛇果',1,1000,'7.50','苹果是蔷薇科苹果亚科苹果属植物，其树为落叶乔木。苹果营养价值很高，富含矿物质和维生素，含钙量丰富。','/images/import_list2.jpg'),(3,'帝王香蕉',1,1000,'15.00','来自台湾香蕉之地，不买你就买不到啦，千万不要错过哦。','/images/buy_list2.jpg'),(4,'车厘子',1,941,'58.50','这是樱桃的进化版，好吃的很哦，想不到的美味，你值的拥有','/images/self_list11.jpg'),(5,'蓝莓',1,1000,'4.45','自然耕作，自然成熟，天然无害，果粉完整，甜中带酸','/images/list1.jpg'),(6,'黄果猕猴桃',1,1000,'14.50','产自无机无害的天然果园，每天进行灌溉，长出成熟的果实。','/images/list2.jpg'),(7,'南非进口柠檬',1,1000,'29.00','正宗南非柠檬,丰富的维生素含量,美容的天然佳品。','/images/pic1.jpg'),(8,'鲜蓝莓',1,1000,'20.00','采摘下来的野生蓝莓有的运到城市里，制成了蓝苺果汁，蓝苺果酱，或者蓝苺酒，有的运到阴房里制成了蓝苺干。蓝苺的营养价值丰富，是最健康的野生水果之一。','/images/pic2.jpg'),(9,'红啤梨',1,1000,'48.00','果体硬度好，肉细嫩、口感甜、果汁多，清甜的味道。','/images/pic3.jpg'),(10,'无仔红提',1,1000,'39.00','由红提取物品种改良为无籽,口味纯正,皮薄肉厚,味甜脆,果肉硬,色泽深红,爽口,甜度高。','/images/pic4.jpg'),(11,'水果沙拉',1,1000,'9.90','多种水果结合，营养丰富，鲜美无比。','/images/list3.jpg'),(12,'美味芒果',1,1000,'13.99','“芒芒”人海，有“果”相伴。“粒粒”红香果,“汁汁”甜润喉。','/images/buy_detail5.png'),(13,'美味百香果',1,1000,'17.99','百香，百味，百分!百种营养，千般滋味，泰泰百香果汁。','/images/buy_detail9.png'),(14,'红心柚',1,1000,'9.99','客天下金柚，有你有他“柚”客家!','/images/buy_detail4.png'),(15,'红蛇果',1,1000,'16.99','有身份的苹果，高品质的生活！','/images/buy_detail6.png'),(16,'香甜柑橘',1,1000,'8.50','爱上你，就喜欢这种如同初恋般的情感，酸甜只有你我知道！','/images/list10.jpg'),(17,'美国进口青苹果',1,500,'13.50','产自美国洛杉矶产业园，科学种植，营养丰富，酸酸甜甜！','/images/list11.jpg');

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `tb_member` */

insert  into `tb_member`(`id`,`tel`,`password`,`status`,`creat_time`,`last_update_time`,`nick_name`,`age`,`sex`,`seller`,`salt`,`email`) values (1,13981973370,'09df07d78bb2171bb4898de0e9071d18',NULL,'2019-10-01 19:52:32','2019-10-01 19:52:32',NULL,NULL,NULL,NULL,'CxqPJve8',NULL),(2,13981973371,'83fdd615b95197f06ca91cba84b094d3',NULL,'2019-10-03 17:40:44','2019-10-03 17:40:44',NULL,23,NULL,NULL,'mUo4oJrf',NULL),(3,13981973373,'82836ef9f5ff0585bea3389cbf3bb22f',NULL,'2019-10-03 18:02:48','2019-10-03 18:02:48',NULL,23,NULL,NULL,'heSsas8V',NULL),(4,13981973380,'0754ee6de395e080eadffb130400ed21',1,'2019-10-07 16:48:21','2019-10-07 16:48:21','',NULL,0,0,'TMmsN1ro',NULL),(5,13981973390,'603d334ec278f9e12966667a44ba7b1f',1,'2019-10-07 20:38:13','2019-10-07 20:38:13','',NULL,0,0,'YRVERHVd',NULL),(8,17380512503,'b6d9d9e7ac368764d23fce53e8ff25d3',1,'2019-10-12 23:17:18','2019-10-12 23:17:18','NULL',NULL,0,0,'YDJiAcuw',NULL),(9,13981999999,'6110fc75e3e22d230581dc230088cf63',1,'2019-10-13 00:37:58','2019-10-13 00:37:58','NULL',NULL,0,0,'b3uvOTSC',NULL),(10,11111111111,'baaa03b9aa79b0ecf23982877a8c8c1e',1,'2019-10-13 00:39:25','2019-10-13 00:39:25','NULL',NULL,0,0,'260K9yeZ',NULL),(11,13000000000,'d8deb45c025ec517591f9eeaf10423e7',1,'2019-10-13 00:40:32','2019-10-13 00:40:32','NULL',NULL,0,0,'fK6MfimT',NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `tb_member_address` */

insert  into `tb_member_address`(`id`,`member_id`,`recive_name`,`tel`,`prov`,`city`,`area`,`stree_name`,`default_address`,`create_time`,`last_update_time`,`number`) values (1,2,'欧根',13981973370,'四川省','成都市','成华区','成都理工大学银杏园362寝室',0,'2019-10-03 18:45:43','2019-10-03 18:45:43','0'),(2,2,'欧根',13981973370,'四川省','成都市','成华区','成都理工大学银杏园362寝室',0,'2019-10-03 19:37:41','2019-10-03 19:37:41','0'),(3,2,'欧根',13981973370,'四川省','成都市','成华区','成都理工大学银杏园362寝室',0,'2019-10-03 19:39:57','2019-10-03 19:39:57','0'),(4,2,'欧根',13981973370,'四川省','成都市','成华区','成都理工大学银杏园362寝室',0,'2019-10-03 21:07:19','2019-10-03 21:07:19',NULL),(5,2,'欧根',13981973370,'四川省','成都市','成华区','成都理工大学银杏园362寝室',1,'2019-10-03 21:09:03','2019-10-03 21:09:03',NULL),(6,2,'欧根',13981973370,'四川省','成都市','成华区','成都理工大学银杏园362寝室',0,'2019-10-03 21:24:05','2019-10-05 21:24:05',NULL),(7,2,'欧根',13981973370,'四川省','成都市','成华区','成都理工大学银杏园362寝室',0,'2019-10-03 21:26:48','2019-10-03 21:26:48',NULL),(8,2,'欧根',13981973370,'四川省','成都市','成华区','成都理工大学银杏园362寝室',0,'2019-10-03 21:27:31','2019-10-03 21:27:31',NULL),(9,2,'欧根',13981973370,'四川省','成都市','成华区','成都理工大学银杏园362寝室',0,'2019-10-03 21:28:47','2019-10-03 21:28:47',NULL),(10,5,'欧根',13981973370,'四川省','成都市','成华区','六三零大院',1,'2019-10-11 11:40:05','2019-10-11 11:40:05',NULL),(11,5,'张鑫瑜',13981973370,'浙江省','桐乡市','百乐小区','振华街百乐小区',0,'2019-10-11 11:41:12','2019-10-11 11:41:12',NULL);

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

insert  into `tb_order`(`id`,`fruit_id`,`fruit_name`,`member_id`,`nick_name`,`create_time`,`last_update_time`,`order_state`,`pay_time`,`order_total`,`ideal_pay`,`actual_pay`,`post_way`,`free_post`,`post_fee`,`consign_time`,`each_price`,`favorable_id`,`voucher`,`voucher_fee`,`seller_id`,`stree_name`,`address_id`,`img_url`) values ('519101200871',4,'车厘子',5,NULL,'2019-10-12 14:56:00','2019-10-12 14:55:22',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101202052',4,'车厘子',5,NULL,'2019-10-12 15:15:35','2019-10-12 15:14:54',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101202651',4,'车厘子',5,NULL,'2019-10-12 15:27:52','2019-10-12 15:27:09',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101206570',4,'车厘子',5,NULL,'2019-10-12 17:53:44','2019-10-12 17:52:37',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101206823',4,'车厘子',5,NULL,'2019-10-12 15:51:47','2019-10-12 15:51:00',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101207307',4,'车厘子',5,NULL,'2019-10-12 16:13:06','2019-10-12 16:12:15',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101208085',4,'车厘子',NULL,NULL,'2019-10-12 12:44:09','2019-10-12 12:43:53',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'振华街百乐小区',11,'/images/self_list11.jpg'),('519101208501',4,'车厘子',5,NULL,'2019-10-12 12:51:38','2019-10-12 12:51:21',-1,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012101808',4,'车厘子',5,NULL,'2019-10-12 16:47:51','2019-10-12 16:46:55',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101210433',4,'车厘子',5,NULL,'2019-10-12 16:13:52','2019-10-12 16:13:01',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012106135',4,'车厘子',5,NULL,'2019-10-12 18:10:22','2019-10-12 18:09:12',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012116641',4,'车厘子',5,NULL,'2019-10-12 16:58:23','2019-10-12 16:57:25',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012118361',4,'车厘子',5,NULL,'2019-10-12 18:11:18','2019-10-12 18:10:07',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012120301',4,'车厘子',5,NULL,'2019-10-12 18:13:58','2019-10-12 18:12:47',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012126480',4,'车厘子',5,NULL,'2019-10-12 16:59:32','2019-10-12 16:58:33',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012132288',4,'车厘子',5,NULL,'2019-10-12 18:27:26','2019-10-12 18:26:13',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012132375',4,'车厘子',5,NULL,'2019-10-12 17:01:45','2019-10-12 17:00:47',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101213558',4,'车厘子',5,NULL,'2019-10-12 14:59:56','2019-10-12 14:59:18',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101213801',4,'车厘子',5,NULL,'2019-10-12 17:54:03','2019-10-12 17:52:56',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012144227',4,'车厘子',5,NULL,'2019-10-12 17:06:18','2019-10-12 17:05:18',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012144547',4,'车厘子',5,NULL,'2019-10-12 18:32:17','2019-10-12 18:31:03',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012153831',4,'车厘子',5,NULL,'2019-10-12 17:07:04','2019-10-12 17:06:05',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012157506',4,'车厘子',5,NULL,'2019-10-12 18:32:46','2019-10-12 18:31:32',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101215821',4,'车厘子',5,NULL,'2019-10-12 15:39:28','2019-10-12 15:38:43',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('5191012166636',4,'车厘子',5,NULL,'2019-10-12 17:10:32','2019-10-12 17:09:32',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101217423',4,'车厘子',5,NULL,'2019-10-12 15:15:57','2019-10-12 15:15:16',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101217447',4,'车厘子',NULL,NULL,'2019-10-12 12:45:34','2019-10-12 12:45:18',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'振华街百乐小区',11,'/images/self_list11.jpg'),('519101217714',4,'车厘子',5,NULL,'2019-10-12 15:52:24','2019-10-12 15:51:37',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101220313',4,'车厘子',5,NULL,'2019-10-12 15:40:42','2019-10-12 15:39:56',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101221481',4,'车厘子',5,NULL,'2019-10-12 15:52:35','2019-10-12 15:51:47',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101223345',4,'车厘子',5,NULL,'2019-10-12 16:14:13','2019-10-12 16:13:22',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101224453',4,'车厘子',5,NULL,'2019-10-12 17:55:46','2019-10-12 17:54:38',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101225465',4,'车厘子',5,NULL,'2019-10-12 15:17:54','2019-10-12 15:17:12',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101226531',4,'车厘子',5,NULL,'2019-10-12 15:12:44','2019-10-12 15:12:03',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101232810',4,'车厘子',5,NULL,'2019-10-12 16:16:17','2019-10-12 16:15:26',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101233316',4,'车厘子',5,NULL,'2019-10-12 17:56:56','2019-10-12 17:55:49',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101234003',4,'车厘子',5,NULL,'2019-10-12 15:19:52','2019-10-12 15:19:10',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101234888',4,'车厘子',5,NULL,'2019-10-12 15:42:04','2019-10-12 15:41:18',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101240122',4,'车厘子',5,NULL,'2019-10-12 15:22:54','2019-10-12 15:22:12',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101244140',4,'车厘子',5,NULL,'2019-10-12 15:43:57','2019-10-12 15:43:12',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101247055',4,'车厘子',5,NULL,'2019-10-12 16:17:25','2019-10-12 16:16:33',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101248856',4,'车厘子',5,NULL,'2019-10-12 17:57:33','2019-10-12 17:56:26',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101250208',4,'车厘子',5,NULL,'2019-10-12 15:24:52','2019-10-12 15:24:09',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101250810',4,'车厘子',5,NULL,'2019-10-12 17:58:11','2019-10-12 17:57:03',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101251027',4,'车厘子',5,NULL,'2019-10-12 15:45:11','2019-10-12 15:44:25',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101256786',4,'车厘子',5,NULL,'2019-10-12 16:39:44','2019-10-12 16:38:49',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101262185',4,'车厘子',5,NULL,'2019-10-12 16:39:54','2019-10-12 16:38:59',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101264438',4,'车厘子',5,NULL,'2019-10-12 15:46:50','2019-10-12 15:46:04',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101267368',4,'车厘子',5,NULL,'2019-10-12 18:01:00','2019-10-12 17:59:52',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101270201',4,'车厘子',5,NULL,'2019-10-12 16:40:21','2019-10-12 16:39:26',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101278034',4,'车厘子',5,NULL,'2019-10-12 15:48:56','2019-10-12 15:48:09',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101278564',4,'车厘子',5,NULL,'2019-10-12 18:05:14','2019-10-12 18:04:05',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101282520',4,'车厘子',5,NULL,'2019-10-12 16:40:38','2019-10-12 16:39:43',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101288464',4,'车厘子',5,NULL,'2019-10-12 18:06:47','2019-10-12 18:05:37',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101293183',4,'车厘子',5,NULL,'2019-10-12 16:40:56','2019-10-12 16:40:01',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('519101297870',4,'车厘子',5,NULL,'2019-10-12 18:08:30','2019-10-12 18:07:21',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg'),('null19101134747',4,'车厘子',NULL,NULL,'2019-10-11 22:20:06','2019-10-11 22:18:06',0,NULL,1,'58.50','68.50','中通',0,'10.00',NULL,'58.50',NULL,NULL,NULL,1,'六三零大院',10,'/images/self_list11.jpg');

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

insert  into `tb_panel`(`id`,`fruit_id`,`panel_img_url`,`order`,`state`,`alive_time`,`panel_location`,`create_time`,`last_update_time`,`fruit_name`,`each_price`,`seller_id`) values (1,5,'/images/list1.jpg',1,1,1,2,'2019-10-04 23:14:06','2019-10-04 23:14:06','芒果','4.00',1),(2,6,'/images/list2.jpg',1,1,1,2,'2019-10-04 23:14:31','2019-10-04 23:14:31','苹果','9.00',1),(3,11,'/images/list3.jpg',1,1,1,2,'2019-10-05 12:04:16','2019-10-05 12:04:16','西瓜','17.00',1),(4,7,'/images/pic1.jpg',1,1,1,4,'2019-10-05 15:37:55','2019-10-05 15:37:55','南非进口柠檬','29.00',1),(5,8,'/images/pic2.jpg',1,1,1,4,'2019-10-05 15:39:48','2019-10-05 15:39:48','鲜蓝莓','20.00',1),(6,9,'/images/pic3.jpg',1,1,1,4,'2019-10-05 15:42:08','2019-10-05 15:42:08','红啤梨','48.00',1),(7,10,'/images/pic4.jpg',1,1,1,4,'2019-10-05 15:43:29','2019-10-05 15:43:29','无仔红提','39.00',1),(8,3,'/images/buy_list2.jpg',1,1,1,4,'2019-10-05 15:44:46','2019-10-05 15:44:46','帝王蕉','15.00',1),(9,4,'/images/self_list11.jpg',1,1,1,4,'2019-10-05 15:46:14','2019-10-05 15:46:14','车厘子','30.50',1),(10,12,'/images/buy_detail5.png',1,1,1,3,'2019-10-05 16:48:51','2019-10-05 16:48:51','美味芒果','13.99',1),(11,13,'/images/buy_detail9.png',1,1,1,3,'2019-10-05 16:50:13','2019-10-05 16:50:13','美味百香果','17.99',1),(12,14,'/images/buy_detail4.png',1,1,1,3,'2019-10-05 16:51:30','2019-10-05 16:51:30','红心柚','9.99',1),(13,15,'/images/buy_detail6.png',1,1,1,3,'2019-10-05 16:52:23','2019-10-05 16:52:23','红蛇果','16.99',1),(14,0,'/images/banner1.jpg',1,1,1,0,'2019-10-05 23:18:38','2019-10-05 23:18:38','\"\"','0.00',1),(15,0,'/images/banner2.jpg',1,1,1,0,'2019-10-05 23:20:01','2019-10-05 23:20:01','\"\"','0.00',2),(16,0,'/images/banner7.jpg',1,1,1,0,'2019-10-05 23:20:52','2019-10-05 23:20:52','\"\"','0.00',3),(17,16,'/images/list10.jpg',1,1,1,1,'2019-10-05 23:28:36','2019-10-05 23:28:36','香甜柑橘','8.50',1),(18,17,'/images/list11.jpg',1,1,1,1,'2019-10-05 23:31:40','2019-10-05 23:31:40','美国青苹果','13.50',1),(19,4,'images/list12.jpg',1,1,1,1,'2019-10-05 23:33:16','2019-10-05 23:33:16','智利车厘子','58.50',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
