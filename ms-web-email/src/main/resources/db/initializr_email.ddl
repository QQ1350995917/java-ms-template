/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : initializr_email

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 14/12/2020 20:03:35
*/
CREATE DATABASE `initializr_email` /*!40100 DEFAULT CHARACTER SET utf8 */

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for email
-- ----------------------------
DROP TABLE IF EXISTS `email`;
CREATE TABLE `email`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发送邮件的服务名',
  `sent` int(1) NOT NULL COMMENT '邮件发送状态，0：未发送；1：发送中；2：发送成功；3：发送失败',
  `del` int(1) NOT NULL COMMENT '数据删除状态，0：未删除；1：已删除',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email
-- ----------------------------

-- ----------------------------
-- Table structure for email_attachment
-- ----------------------------
DROP TABLE IF EXISTS `email_attachment`;
CREATE TABLE `email_attachment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` bigint(20) NOT NULL COMMENT 'email表外键',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '附件名称',
  `cid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件显示附件关联ID',
  `content_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件MEMI类型',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '附件地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `email_id`(`email_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email_attachment
-- ----------------------------

-- ----------------------------
-- Table structure for email_box
-- ----------------------------
DROP TABLE IF EXISTS `email_box`;
CREATE TABLE `email_box`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` bigint(20) NOT NULL COMMENT 'email表外键',
  `type` int(1) NOT NULL COMMENT '邮箱类型，0：发件箱，1：收件箱，2：抄送邮箱，3：密送邮箱',
  `mailbox` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮箱地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `email_id`(`email_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email_box
-- ----------------------------

-- ----------------------------
-- Table structure for email_content
-- ----------------------------
DROP TABLE IF EXISTS `email_content`;
CREATE TABLE `email_content`  (
  `email_id` bigint(20) NOT NULL COMMENT 'email表外键，同时作为主键',
  `subject` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮件标题',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '邮件内容',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`email_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email_content
-- ----------------------------

-- ----------------------------
-- Table structure for email_extend
-- ----------------------------
DROP TABLE IF EXISTS `email_extend`;
CREATE TABLE `email_extend`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `email_id` bigint(20) NOT NULL COMMENT 'email表外键',
  `key` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展内容的key值',
  `value` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '扩展内容的value值',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `email_id`(`email_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of email_extend
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
