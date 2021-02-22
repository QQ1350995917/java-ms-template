/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : initializr_access

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 22/02/2021 23:47:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NOT NULL COMMENT '上级菜单',
  `name` varchar(255) NOT NULL COMMENT '菜单名称',
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `order` int(20) NOT NULL COMMENT '排序',
  `leaf` int(8) NOT NULL COMMENT '叶子节点；0：非叶子节点；1：叶子节点；',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for operation
-- ----------------------------
DROP TABLE IF EXISTS `operation`;
CREATE TABLE `operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mid` bigint(20) NOT NULL COMMENT '所属菜单',
  `name` varchar(255) NOT NULL COMMENT '按钮名称',
  `curd` char(4) NOT NULL COMMENT '权限类别；C：创建；U：更新；R：读取；D：删除',
  `path` varchar(255) NOT NULL COMMENT '资源路径',
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `order` int(20) NOT NULL COMMENT '排序',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NOT NULL COMMENT '上级角色',
  `name` varchar(255) NOT NULL COMMENT '角色名称，具有唯一性',
  `summary` varchar(255) DEFAULT NULL COMMENT '简介',
  `order` int(20) NOT NULL COMMENT '排序',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for role_menu_operation
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_operation`;
CREATE TABLE `role_menu_operation` (
  `rid` bigint(20) NOT NULL COMMENT '外键，角色表主键',
  `mid` bigint(20) NOT NULL COMMENT '外键，菜单表主键',
  `oid` bigint(20) NOT NULL COMMENT '外键，操作表主键',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`rid`,`mid`,`oid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` bigint(20) NOT NULL COMMENT '外键，用户表主键',
  `rid` bigint(20) NOT NULL COMMENT '外键，角色表主键',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`uid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
