/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : initializr_account

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 05/02/2021 23:42:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_account
-- ----------------------------
DROP TABLE IF EXISTS `admin_account`;
CREATE TABLE `admin_account`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` bigint(11) NOT NULL COMMENT '逻辑外键:admin_user.id',
  `login_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `login_pwd` char(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `pwd_salt` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码加密盐',
  `pwd_time` datetime(0) NOT NULL COMMENT '最近修改密码时间',
  `type` int(4) NOT NULL COMMENT '1:授权账号；2：电话号码+短信验证码账号；3：电话号码+密码账号；4：邮箱账号+直接登录链接账号；5：邮箱账号+验证码账号；6：邮箱账号+密码账号；7：微信认证账号；8：微博认证账号；9：QQ账号；',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name_UNIQUE`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 355 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_account
-- ----------------------------
INSERT INTO `admin_account` VALUES (354, 354, 'admin', '5c38edd906a81da21650c706bd8e99750d108e5f14697c4e0e958e9516caaffc09a187c88aee57cf55b82ce1144219bd4a48ac74f007a317e12ed9306b911a59', '8062dc2e671e1b40b3f0f1c50a4fadc1', '2021-02-05 18:36:24', 1, 0, 0, '2021-02-05 18:36:24', '2021-02-05 18:36:24', 0);

-- ----------------------------
-- Table structure for admin_config
-- ----------------------------
DROP TABLE IF EXISTS `admin_config`;
CREATE TABLE `admin_config`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `key` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置key',
  `value` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置value',
  `summary` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '描述',
  `able` int(11) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(11) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key_UNIQUE`(`key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_config
-- ----------------------------
INSERT INTO `admin_config` VALUES (5, 'System-Initialized', 'true', '标识是否对系统进行业务初始化操作，二次启动以此判断是否初始化数据（true：已初始化，false：未初始化）', 1, 0, '2021-02-05 18:36:22', '2021-02-05 18:36:22', 0);

-- ----------------------------
-- Table structure for admin_contact
-- ----------------------------
DROP TABLE IF EXISTS `admin_contact`;
CREATE TABLE `admin_contact`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` bigint(11) NOT NULL COMMENT '逻辑外键:admin_user.id',
  `type` int(4) NOT NULL COMMENT '1:电话号码；2：地址；3：邮箱；4：微信；5：微博；6：QQ；',
  `value` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Type字段对应的值',
  `mark` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_contact
-- ----------------------------

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别',
  `emp_no` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工号',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简介',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用性：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '首次创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `pin_UNIQUE`(`pin`) USING BTREE,
  UNIQUE INDEX `emp_no_UNIQUE`(`emp_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 355 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_user
-- ----------------------------
INSERT INTO `admin_user` VALUES (354, '0', 'admin', '1', NULL, NULL, 0, 0, '2021-02-05 18:36:23', '2021-02-05 18:36:23', 0);

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` bigint(11) NOT NULL COMMENT '逻辑外键:admin_user.id',
  `login_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `login_pwd` char(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `pwd_salt` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码加密盐',
  `pwd_time` datetime(0) NOT NULL COMMENT '最近修改密码时间',
  `type` int(4) NOT NULL COMMENT '1:授权账号；2：电话号码+短信验证码账号；3：电话号码+密码账号；4：邮箱账号+直接登录链接账号；5：邮箱账号+验证码账号；6：邮箱账号+密码账号；7：微信认证账号；8：微博认证账号；9：QQ账号；',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name_UNIQUE`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 352 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_account
-- ----------------------------

-- ----------------------------
-- Table structure for user_config
-- ----------------------------
DROP TABLE IF EXISTS `user_config`;
CREATE TABLE `user_config`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `key` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置key',
  `value` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置value',
  `summary` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '描述',
  `able` int(11) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(11) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key_UNIQUE`(`key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_config
-- ----------------------------

-- ----------------------------
-- Table structure for user_contact
-- ----------------------------
DROP TABLE IF EXISTS `user_contact`;
CREATE TABLE `user_contact`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` bigint(11) NOT NULL COMMENT '逻辑外键:admin_user.id',
  `type` int(4) NOT NULL COMMENT '1:电话号码；2：地址；3：邮箱；4：微信；5：微博；6：QQ；',
  `value` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'Type字段对应的值',
  `mark` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_contact
-- ----------------------------

-- ----------------------------
-- Table structure for user_user
-- ----------------------------
DROP TABLE IF EXISTS `user_user`;
CREATE TABLE `user_user`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '身份证号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '姓名',
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '性别',
  `emp_no` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '工号',
  `summary` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '简介',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用性：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '首次创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT 0 COMMENT '数据版本号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `pin_UNIQUE`(`pin`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 352 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
