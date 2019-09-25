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

 Date: 25/09/2019 10:25:31
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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 5, '000000', '000000', 0, 0, 1569056125916, 1569056125916);
INSERT INTO `account` VALUES (2, 6, '000000', '000000', 0, 0, 1569157853487, 1569157853487);
INSERT INTO `account` VALUES (3, 7, '18511694468', NULL, 1, 0, 1569301125579, 1569301125579);

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
-- Records of entrance
-- ----------------------------
INSERT INTO `entrance` VALUES (1, 0, '申请账号', NULL, 0, 0, 1569313255558, 1569313255558);
INSERT INTO `entrance` VALUES (2, 1, '手机注册', NULL, 0, 0, 1569313255558, 1569313255558);
INSERT INTO `entrance` VALUES (3, 2, '邮箱注册', NULL, 0, 0, 1569313255558, 1569313255558);
INSERT INTO `entrance` VALUES (4, 3, '微信注册', NULL, 0, 0, 1569313255558, 1569313255558);
INSERT INTO `entrance` VALUES (5, 4, '微博注册', NULL, 0, 0, 1569313255558, 1569313255558);
INSERT INTO `entrance` VALUES (6, 0, '申请账号', NULL, 1, 0, 1569313631610, 1569313631610);
INSERT INTO `entrance` VALUES (7, 1, '手机登录', NULL, 1, 0, 1569313631610, 1569313631610);
INSERT INTO `entrance` VALUES (8, 2, '邮箱登录', NULL, 1, 0, 1569313631610, 1569313631610);
INSERT INTO `entrance` VALUES (9, 3, '微信登录', NULL, 1, 0, 1569313631610, 1569313631610);
INSERT INTO `entrance` VALUES (10, 4, '微博登录', NULL, 1, 0, 1569313631610, 1569313631610);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID，主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `status` int(8) NOT NULL DEFAULT 0 COMMENT '用户状态，0启用，1禁用，2删除',
  `create_time` bigint(11) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(11) NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'pwd-test-0', 0, 1569055616899, 1569055616899);
INSERT INTO `user` VALUES (2, 'pwd-test-0', 0, 1569055718761, 1569055718761);
INSERT INTO `user` VALUES (4, 'pwd-test-0', 0, 1569055940369, 1569055940369);
INSERT INTO `user` VALUES (5, 'pwd-test-0', 0, 1569056125916, 1569056125916);
INSERT INTO `user` VALUES (6, 'pwd-test-0', 0, 1569157853487, 1569157853487);
INSERT INTO `user` VALUES (7, '18511694468', 0, 1569301125579, 1569301125579);

SET FOREIGN_KEY_CHECKS = 1;
