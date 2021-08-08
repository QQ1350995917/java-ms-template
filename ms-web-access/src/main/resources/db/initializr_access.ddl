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

 Date: 08/08/2021 16:44:58
*/

CREATE DATABASE `initializr_access` /*!40100 DEFAULT CHARACTER SET utf8mb4 */

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for access
-- ----------------------------
DROP TABLE IF EXISTS `access`;
CREATE TABLE `access` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `type` bigint(20) NOT NULL COMMENT '权限类型',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for access_function
-- ----------------------------
DROP TABLE IF EXISTS `access_function`;
CREATE TABLE `access_function` (
  `aid` bigint(20) NOT NULL COMMENT '外键，权限表主键',
  `fid` bigint(20) NOT NULL COMMENT '外键，功能表主键',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`aid`,`fid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for access_menu
-- ----------------------------
DROP TABLE IF EXISTS `access_menu`;
CREATE TABLE `access_menu` (
  `aid` bigint(20) NOT NULL COMMENT '外键，权限表主键',
  `mid` bigint(20) NOT NULL COMMENT '外键，菜单表主键',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`aid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for access_storage
-- ----------------------------
DROP TABLE IF EXISTS `access_storage`;
CREATE TABLE `access_storage` (
  `aid` bigint(20) NOT NULL COMMENT '外键，权限表主键',
  `sid` bigint(20) NOT NULL COMMENT '外键，文件存储主键',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`aid`,`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for function
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `uri` varchar(255) NOT NULL COMMENT '资源URI',
  `method` varchar(16) NOT NULL COMMENT '请求方式',
  `summary` varchar(255) NOT NULL COMMENT '描述',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NOT NULL COMMENT '父用户组ID',
  `name` varchar(255) NOT NULL COMMENT '用户组名称',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for group_role
-- ----------------------------
DROP TABLE IF EXISTS `group_role`;
CREATE TABLE `group_role` (
  `gid` bigint(20) NOT NULL COMMENT '外键，用户组表主键',
  `rid` bigint(20) NOT NULL COMMENT '外键，角色表主键',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`gid`,`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for group_user
-- ----------------------------
DROP TABLE IF EXISTS `group_user`;
CREATE TABLE `group_user` (
  `gid` bigint(20) NOT NULL COMMENT '外键，用户组表主键',
  `uid` bigint(20) NOT NULL COMMENT '外键，用户表主键',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`gid`,`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for role_access
-- ----------------------------
DROP TABLE IF EXISTS `role_access`;
CREATE TABLE `role_access` (
  `rid` bigint(20) NOT NULL COMMENT '外键，角色表主键',
  `aid` bigint(20) NOT NULL COMMENT '外键，权限表主键',
  `able` int(4) NOT NULL DEFAULT '0' COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '最近更新时间',
  `version` bigint(11) NOT NULL DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`rid`,`aid`)
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
