/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : initializr_account

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 09/08/2020 21:51:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin_account
-- ----------------------------
DROP TABLE IF EXISTS `admin_account`;
CREATE TABLE `admin_account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` bigint(11) NOT NULL COMMENT '逻辑外键:admin_user.id',
  `login_name` varchar(45) NOT NULL COMMENT '登录名',
  `login_pwd` varchar(45) NOT NULL COMMENT '登录密码',
  `pwd_time` datetime NOT NULL COMMENT '最近修改密码时间',
  `type` int(4) NOT NULL COMMENT '1:授权账号；2：电话号码+短信验证码账号；3：电话号码+密码账号；4：邮箱账号+直接登录链接账号；5：邮箱账号+验证码账号；6：邮箱账号+密码账号；7：微信认证账号；8：微博认证账号；9：QQ账号；',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admin_config
-- ----------------------------
DROP TABLE IF EXISTS `admin_config`;
CREATE TABLE `admin_config` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `key` varchar(45) NOT NULL COMMENT '配置key',
  `value` text NOT NULL COMMENT '配置value',
  `summary` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '描述',
  `able` int(11) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(11) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_UNIQUE` (`key`),
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admin_contact
-- ----------------------------
DROP TABLE IF EXISTS `admin_contact`;
CREATE TABLE `admin_contact` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` bigint(11) NOT NULL COMMENT '逻辑外键:admin_user.id',
  `type` int(4) NOT NULL COMMENT '1:电话号码；2：地址；3：邮箱；4：微信；5：微博；6：QQ；',
  `value` varchar(128) NOT NULL COMMENT 'Type字段对应的值',
  `mark` varchar(45) DEFAULT NULL COMMENT '描述',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for admin_user
-- ----------------------------
DROP TABLE IF EXISTS `admin_user`;
CREATE TABLE `admin_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `pin` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `gender` char(1) COLLATE utf8_bin NOT NULL COMMENT '性别',
  `emp_no` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
  `summary` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '简介',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用性：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '首次创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pin_UNIQUE` (`pin`),
  UNIQUE KEY `emp_no_UNIQUE` (`emp_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for user_account
-- ----------------------------
DROP TABLE IF EXISTS `user_account`;
CREATE TABLE `user_account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` bigint(11) NOT NULL COMMENT '逻辑外键:admin_user.id',
  `login_name` varchar(45) NOT NULL COMMENT '登录名',
  `login_pwd` varchar(45) NOT NULL COMMENT '登录密码',
  `pwd_time` datetime NOT NULL COMMENT '最近修改密码时间',
  `type` int(4) NOT NULL COMMENT '1:授权账号；2：电话号码+短信验证码账号；3：电话号码+密码账号；4：邮箱账号+直接登录链接账号；5：邮箱账号+验证码账号；6：邮箱账号+密码账号；7：微信认证账号；8：微博认证账号；9：QQ账号；',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name_UNIQUE` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_config
-- ----------------------------
DROP TABLE IF EXISTS `user_config`;
CREATE TABLE `user_config` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `key` varchar(45) NOT NULL COMMENT '配置key',
  `value` text NOT NULL COMMENT '配置value',
  `summary` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '描述',
  `able` int(11) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(11) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `key_UNIQUE` (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_contact
-- ----------------------------
DROP TABLE IF EXISTS `user_contact`;
CREATE TABLE `user_contact` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` bigint(11) NOT NULL COMMENT '逻辑外键:admin_user.id',
  `type` int(4) NOT NULL COMMENT '1:电话号码；2：地址；3：邮箱；4：微信；5：微博；6：QQ；',
  `value` varchar(128) NOT NULL COMMENT 'Type字段对应的值',
  `mark` varchar(45) DEFAULT NULL COMMENT '描述',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_user
-- ----------------------------
DROP TABLE IF EXISTS `user_user`;
CREATE TABLE `user_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `pin` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `gender` char(1) COLLATE utf8_bin NOT NULL COMMENT '性别',
  `emp_no` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '工号',
  `summary` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '简介',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用性：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '首次创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `pin_UNIQUE` (`pin`),
  UNIQUE KEY `emp_no_UNIQUE` (`emp_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
