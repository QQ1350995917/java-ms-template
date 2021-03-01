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

 Date: 01/03/2021 22:14:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for edu_term_course
-- ----------------------------
DROP TABLE IF EXISTS `edu_term_course`;
CREATE TABLE `edu_term_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tid` bigint(20) DEFAULT NULL COMMENT '外键，term表',
  `name` varchar(255) DEFAULT NULL COMMENT '课程名称',
  `order` int(20) DEFAULT NULL COMMENT '排序',
  `able` int(8) NOT NULL COMMENT '可用性状态，0：不可用；1：可用；',
  `del` int(8) NOT NULL COMMENT '删除状态，0：未删除，1：已删除；',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
