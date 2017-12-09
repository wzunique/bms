/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50635
Source Host           : localhost:3306
Source Database       : testjdbc

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-06-26 13:17:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bmsadmin`
-- ----------------------------
DROP TABLE IF EXISTS `bmsadmin`;
CREATE TABLE `bmsadmin` (
  `id` varchar(10) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bmsadmin
-- ----------------------------
INSERT INTO `bmsadmin` VALUES ('admin', 'admin');

-- ----------------------------
-- Table structure for `bmsbook`
-- ----------------------------
DROP TABLE IF EXISTS `bmsbook`;
CREATE TABLE `bmsbook` (
  `bookid` varchar(50) NOT NULL,
  `bookname` varchar(100) NOT NULL,
  `bookauthor` varchar(100) NOT NULL,
  `bookpress` varchar(100) NOT NULL,
  `booktype` varchar(100) DEFAULT NULL,
  `state` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`bookid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bmsbook
-- ----------------------------
INSERT INTO `bmsbook` VALUES ('160506005', '天后', '陈势安', '音乐出版', '娱乐', '1');
INSERT INTO `bmsbook` VALUES ('2017010', '悲惨世界', '维克多·雨果', '法国作家', '文学', '1');
INSERT INTO `bmsbook` VALUES ('201703', '谁动了我的奶酪', '斯宾塞·约翰逊', '中信出版社', '文学', '1');
INSERT INTO `bmsbook` VALUES ('201706', '背影', '朱自清', '北京大学出版', '文学', '0');
INSERT INTO `bmsbook` VALUES ('201707', '巴黎圣母院', '维克多·雨果', '法国作家', '文学', '1');
INSERT INTO `bmsbook` VALUES ('201708', '童年', '马克西姆·高尔基', '苏联作家', '文学', '1');
INSERT INTO `bmsbook` VALUES ('201709', '红与黑', '司汤达', '法国作家', '文学', '1');
INSERT INTO `bmsbook` VALUES ('201711', 'JAVA学习笔记', '阿尔法', '清华出版社', '文学', '1');

-- ----------------------------
-- Table structure for `bmsuser`
-- ----------------------------
DROP TABLE IF EXISTS `bmsuser`;
CREATE TABLE `bmsuser` (
  `id` varchar(20) NOT NULL,
  `Uname` varchar(10) NOT NULL,
  `sex` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bmsuser
-- ----------------------------
INSERT INTO `bmsuser` VALUES ('001', '陈泽登', '男');
INSERT INTO `bmsuser` VALUES ('002', '皮皮栓', '女');
INSERT INTO `bmsuser` VALUES ('160506403', '皮皮鳝', '男');

-- ----------------------------
-- Table structure for `bmsuser_numer`
-- ----------------------------
DROP TABLE IF EXISTS `bmsuser_numer`;
CREATE TABLE `bmsuser_numer` (
  `userid` varchar(20) NOT NULL,
  `numer` varchar(50) NOT NULL,
  `pwd` varchar(50) NOT NULL,
  `booksize` int(10) unsigned NOT NULL DEFAULT '10',
  PRIMARY KEY (`numer`),
  KEY `user_usernumer_id` (`userid`),
  CONSTRAINT `user_usernumer_id` FOREIGN KEY (`userid`) REFERENCES `bmsuser` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bmsuser_numer
-- ----------------------------
INSERT INTO `bmsuser_numer` VALUES ('002', '111', '222', '10');
INSERT INTO `bmsuser_numer` VALUES ('001', '123', '777', '10');
INSERT INTO `bmsuser_numer` VALUES ('160506403', '835033913', '000000', '10');

-- ----------------------------
-- Table structure for `borrowandreturn`
-- ----------------------------
DROP TABLE IF EXISTS `borrowandreturn`;
CREATE TABLE `borrowandreturn` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) NOT NULL,
  `bookid` varchar(50) NOT NULL,
  `time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `bookid` (`bookid`),
  CONSTRAINT `bookid` FOREIGN KEY (`bookid`) REFERENCES `bmsbook` (`bookid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `bmsuser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrowandreturn
-- ----------------------------
