/*
Navicat MySQL Data Transfer

Source Server         : local_mysql
Source Server Version : 50625
Source Host           : 127.0.0.1:3306
Source Database       : test_hz

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2018-06-20 23:15:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', 'jack', 'admin', '123456');
