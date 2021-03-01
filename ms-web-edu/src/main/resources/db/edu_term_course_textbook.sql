/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : initializr_edu

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 01/03/2021 22:14:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for edu_term_course_textbook
-- ----------------------------
DROP TABLE IF EXISTS `edu_term_course_textbook`;
CREATE TABLE `edu_term_course_textbook` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cid` bigint(20) DEFAULT NULL COMMENT '外键，course表',
  `tid` bigint(20) DEFAULT NULL COMMENT '外键，term表',
  `name` varchar(255) DEFAULT NULL COMMENT '教材名称',
  `publisher` varchar(255) DEFAULT NULL COMMENT '出版社名称',
  `version` varchar(255) DEFAULT NULL COMMENT '教材版本',
  `able` int(8) NOT NULL COMMENT '可用性状态，0：不可用；1：可用；',
  `del` int(8) NOT NULL COMMENT '删除状态，0：未删除，1：已删除；',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='教科书表';

SET FOREIGN_KEY_CHECKS = 1;
