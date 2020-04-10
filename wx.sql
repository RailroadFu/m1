/*
Navicat MySQL Data Transfer

Source Server         : 11
Source Server Version : 50532
Source Host           : localhost:3306
Source Database       : wx

Target Server Type    : MYSQL
Target Server Version : 50532
File Encoding         : 65001

Date: 2020-04-10 19:23:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `eventmap`
-- ----------------------------
DROP TABLE IF EXISTS `eventmap`;
CREATE TABLE `eventmap` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eventtype` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `mediaid` varchar(80) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of eventmap
-- ----------------------------
INSERT INTO `eventmap` VALUES ('1', 'subscribe', 'image', 'IOQWDzYVEpplxhM6qzOsWe_mKdfkPmOTiRvxvcYy2mG-Bc_KxtFRyVpHQgAcn_z0', '1');

-- ----------------------------
-- Table structure for `keywordmap`
-- ----------------------------
DROP TABLE IF EXISTS `keywordmap`;
CREATE TABLE `keywordmap` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `mediaid` varchar(80) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `keyword` (`keyword`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of keywordmap
-- ----------------------------
INSERT INTO `keywordmap` VALUES ('2', '123', 'image', 'IOQWDzYVEpplxhM6qzOsWe_mKdfkPmOTiRvxvcYy2mG-Bc_KxtFRyVpHQgAcn_z0', '1');
INSERT INTO `keywordmap` VALUES ('4', '123456', 'image', 'IOQWDzYVEpplxhM6qzOsWe_mKdfkPmOTiRvxvcYy2mG-Bc_KxtFRyVpHQgAcn_z0', '1');

-- ----------------------------
-- Table structure for `sucai`
-- ----------------------------
DROP TABLE IF EXISTS `sucai`;
CREATE TABLE `sucai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mediaid` varchar(80) NOT NULL,
  `type` varchar(20) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sucai
-- ----------------------------
INSERT INTO `sucai` VALUES ('1', 'IOQWDzYVEpplxhM6qzOsWe_mKdfkPmOTiRvxvcYy2mG-Bc_KxtFRyVpHQgAcn_z0', 'image', '1');

-- ----------------------------
-- Table structure for `unknownmap`
-- ----------------------------
DROP TABLE IF EXISTS `unknownmap`;
CREATE TABLE `unknownmap` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `msgtype` varchar(20) NOT NULL,
  `mediaid` varchar(80) NOT NULL,
  `type` varchar(20) NOT NULL,
  `userid` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of unknownmap
-- ----------------------------
INSERT INTO `unknownmap` VALUES ('1', 'text', 'IOQWDzYVEpplxhM6qzOsWe_mKdfkPmOTiRvxvcYy2mG-Bc_KxtFRyVpHQgAcn_z0', 'image', '1');
INSERT INTO `unknownmap` VALUES ('2', 'image', 'IOQWDzYVEpplxhM6qzOsWe_mKdfkPmOTiRvxvcYy2mG-Bc_KxtFRyVpHQgAcn_z0', 'image', '1');
INSERT INTO `unknownmap` VALUES ('3', 'voice', 'IOQWDzYVEpplxhM6qzOsWe_mKdfkPmOTiRvxvcYy2mG-Bc_KxtFRyVpHQgAcn_z0', 'image', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'abc', '123');
INSERT INTO `user` VALUES ('2', 'fumin', '123456');
