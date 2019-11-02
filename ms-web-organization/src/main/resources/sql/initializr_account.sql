/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : initializr_account

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 27/10/2019 21:09:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键，账户ID',
  `user_id` bigint(11) NOT NULL COMMENT '外键，用户ID',
  `identify` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '登录名称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '登录密码，author认证为空',
  `type` int(8) NOT NULL COMMENT '账户类型，0开通账户，1手机号码账户，2邮件账户',
  `status` int(8) NOT NULL COMMENT '账户状态，0启用，1禁用，2删除',
  `create_time` bigint(11) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(11) NOT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for entrance
-- ----------------------------
DROP TABLE IF EXISTS `entrance`;
CREATE TABLE `entrance`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `key` int(8) NOT NULL COMMENT '0标记开通，1标记手机号，2标记邮件',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '名称',
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '显示的logo',
  `type` int(8) NOT NULL COMMENT '类别，0注册，1登录',
  `status` int(8) NOT NULL COMMENT '状态，0可用，1禁用，2删除',
  `create_time` bigint(11) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(11) NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for organization
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '上级组织',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组织名称',
  `logo` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组织logo',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组织描述',
  `slogan` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '组织slogan',
  `level` int(11) NOT NULL DEFAULT 0 COMMENT '组织等级',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '组织排序',
  `members` int(11) NOT NULL DEFAULT 1 COMMENT '组织成员数量',
  `progress` int(11) NOT NULL DEFAULT 0 COMMENT '组织审核进度',
  `status` int(11) NOT NULL COMMENT '状态，0正常，1禁用，2删除',
  `create_time` bigint(11) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(11) NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system
-- ----------------------------
DROP TABLE IF EXISTS `system`;
CREATE TABLE `system`  (
  `id` int(11) NOT NULL,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户手机号码',
  `status` int(8) NOT NULL DEFAULT 0 COMMENT '用户状态，0启用，1禁用，2删除',
  `create_time` bigint(11) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(11) NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_org
-- ----------------------------
DROP TABLE IF EXISTS `user_org`;
CREATE TABLE `user_org`  (
  `user_id` bigint(20) NOT NULL COMMENT '外键，用户ID',
  `org_id` bigint(20) NOT NULL COMMENT '外键，组织ID',
  `level` int(11) NOT NULL DEFAULT 0 COMMENT '级别，0普通成员，1管理员，2拥有者',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态，0正常，1禁用，2删除',
  `create_time` bigint(20) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`user_id`, `org_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
