/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.200.60-测试库
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : 192.168.200.60:3306
 Source Schema         : initializr_monitor

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 30/10/2020 18:02:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for host
-- ----------------------------
DROP TABLE IF EXISTS `host`;
CREATE TABLE `host`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名，逻辑主键',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名',
  `operating_system` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统名称',
  `hardware_platform` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '硬件平台（x86_64）',
  `system_up_since` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '启动时间',
  `kernel_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内核名称',
  `kernel_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内核版本',
  `kernel_release` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内核发布',
  `machine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '型号',
  `processor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `able` int(4) NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`node_name`, `group_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_cpu
-- ----------------------------
DROP TABLE IF EXISTS `host_cpu`;
CREATE TABLE `host_cpu`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名，逻辑主键',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名',
  `processor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `vendor_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cpu_family` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `model_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stepping` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `microcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cpu_mHz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cache_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `physical_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `siblings` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `core_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内核编号',
  `cpu_cores` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `apicid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `initial_apicid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fpu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fpu_exception` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cpuid_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flags` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `bogomips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `clflush_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cache_alignment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address_sizes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `power_management` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`node_name`, `core_id`, `group_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_cpu_stat
-- ----------------------------
DROP TABLE IF EXISTS `host_cpu_stat`;
CREATE TABLE `host_cpu_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名，逻辑主键',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名，逻辑主键',
  `cpu_core_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user` bigint(11) NULL DEFAULT 0,
  `nice` bigint(11) NULL DEFAULT 0,
  `system` bigint(11) NULL DEFAULT 0,
  `idle` bigint(11) NULL DEFAULT 0,
  `iowait` bigint(11) NULL DEFAULT 0,
  `irq` bigint(11) NULL DEFAULT 0,
  `softirq` bigint(11) NULL DEFAULT 0,
  `steal` bigint(11) NULL DEFAULT 0,
  `guest` bigint(11) NULL DEFAULT 0,
  `guest_nice` bigint(11) NULL DEFAULT 0,
  `able` int(4) NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_disk_stat
-- ----------------------------
DROP TABLE IF EXISTS `host_disk_stat`;
CREATE TABLE `host_disk_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '逻辑组名，逻辑主键',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名，逻辑主键',
  `major_device_number` int(11) NULL DEFAULT 0,
  `minor_device_number` int(11) NULL DEFAULT 0,
  `device_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `read` int(11) NULL DEFAULT 0,
  `read_merge` int(11) NULL DEFAULT 0,
  `read_sector` int(11) NULL DEFAULT 0,
  `read_spent_milliseconds` int(11) NULL DEFAULT 0,
  `write` int(11) NULL DEFAULT 0,
  `write_merge` int(11) NULL DEFAULT 0,
  `write_sector` int(11) NULL DEFAULT 0,
  `write_spent_milliseconds` int(11) NULL DEFAULT 0,
  `io_request` int(11) NULL DEFAULT 0,
  `io_spent_milliseconds` int(11) NULL DEFAULT 0,
  `io_spent_all_milliseconds` int(11) NULL DEFAULT 0,
  `able` int(4) NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_ethernet_stat
-- ----------------------------
DROP TABLE IF EXISTS `host_ethernet_stat`;
CREATE TABLE `host_ethernet_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '逻辑组名，逻辑主键',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名，逻辑主键',
  `inter_face` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receive_bytes` int(11) NULL DEFAULT 0,
  `receive_packets` int(11) NULL DEFAULT 0,
  `receive_errs` int(11) NULL DEFAULT 0,
  `receive_drop` int(11) NULL DEFAULT 0,
  `receive_fifo` int(11) NULL DEFAULT 0,
  `receive_frame` int(11) NULL DEFAULT 0,
  `receive_compressed` int(11) NULL DEFAULT 0,
  `receive_multicast` int(11) NULL DEFAULT 0,
  `transmit_bytes` int(11) NULL DEFAULT 0,
  `transmit_packets` int(11) NULL DEFAULT 0,
  `transmit_errs` int(11) NULL DEFAULT 0,
  `transmit_drop` int(11) NULL DEFAULT 0,
  `transmit_fifo` int(11) NULL DEFAULT 0,
  `transmit_colls` int(11) NULL DEFAULT 0,
  `transmit_carrier` int(11) NULL DEFAULT 0,
  `transmit_compressed` int(11) NULL DEFAULT 0,
  `able` int(4) NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_load_stat
-- ----------------------------
DROP TABLE IF EXISTS `host_load_stat`;
CREATE TABLE `host_load_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '逻辑组名，逻辑主键',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名，逻辑主键',
  `load_in1m` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '1分钟统计负载情况',
  `load_in5m` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '5分钟统计负载情况',
  `load_in15m` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '15分钟统计负载情况',
  `process_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '运行进程和总进程之比',
  `last_process_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最近运行进程的ID',
  `able` int(4) NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_logged_stat
-- ----------------------------
DROP TABLE IF EXISTS `host_logged_stat`;
CREATE TABLE `host_logged_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '逻辑组名，逻辑主键',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名，逻辑主键',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录用户名称',
  `tty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录用户使用的终端名',
  `from` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录用户来源的主机名或IP地址',
  `login` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户登录时长',
  `idle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '自用户上一次与终端进行交互以来的空闲时间',
  `jcpu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户当前进程所用的时间',
  `pcpu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户当前的进程及选项/参数',
  `what` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户当前的进程及选项',
  `able` int(4) NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_memory_stat
-- ----------------------------
DROP TABLE IF EXISTS `host_memory_stat`;
CREATE TABLE `host_memory_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '逻辑组名，逻辑主键',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名，逻辑主键',
  `mem_total` bigint(11) NULL DEFAULT 0,
  `mem_free` bigint(11) NULL DEFAULT 0,
  `mem_available` bigint(11) NULL DEFAULT 0,
  `buffers` bigint(11) NULL DEFAULT 0,
  `cached` bigint(11) NULL DEFAULT 0,
  `swap_cached` bigint(11) NULL DEFAULT 0,
  `active` bigint(11) NULL DEFAULT 0,
  `inactive` bigint(11) NULL DEFAULT 0,
  `active_anon` bigint(11) NULL DEFAULT 0,
  `inactive_anon` bigint(11) NULL DEFAULT 0,
  `active_file` bigint(11) NULL DEFAULT 0,
  `inactive_file` bigint(11) NULL DEFAULT 0,
  `unevictable` bigint(11) NULL DEFAULT 0,
  `mlocked` bigint(11) NULL DEFAULT 0,
  `swap_total` bigint(11) NULL DEFAULT 0,
  `swap_free` bigint(11) NULL DEFAULT 0,
  `dirty` bigint(11) NULL DEFAULT 0,
  `writeback` bigint(11) NULL DEFAULT 0,
  `anon_pages` bigint(11) NULL DEFAULT 0,
  `mapped` bigint(11) NULL DEFAULT 0,
  `shmem` bigint(11) NULL DEFAULT 0,
  `slab` bigint(11) NULL DEFAULT 0,
  `s_reclaimable` bigint(11) NULL DEFAULT 0,
  `s_unreclaim` bigint(11) NULL DEFAULT 0,
  `kernel_stack` bigint(11) NULL DEFAULT 0,
  `page_tables` bigint(11) NULL DEFAULT 0,
  `n_fSUnstable` bigint(11) NULL DEFAULT 0,
  `bounce` bigint(11) NULL DEFAULT 0,
  `writeback_tmp` bigint(11) NULL DEFAULT 0,
  `commit_limit` bigint(11) NULL DEFAULT 0,
  `committed_aS` bigint(11) NULL DEFAULT 0,
  `vmalloc_total` bigint(11) NULL DEFAULT 0,
  `vmalloc_used` bigint(11) NULL DEFAULT 0,
  `vmalloc_chunk` bigint(11) NULL DEFAULT 0,
  `hardware_corrupted` bigint(11) NULL DEFAULT 0,
  `anon_huge_pages` bigint(11) NULL DEFAULT 0,
  `huge_pages_total` bigint(11) NULL DEFAULT 0,
  `huge_pages_free` bigint(11) NULL DEFAULT 0,
  `huge_pages_rsvd` bigint(11) NULL DEFAULT 0,
  `huge_pages_surp` bigint(11) NULL DEFAULT 0,
  `huge_pagesize` bigint(11) NULL DEFAULT 0,
  `direct_map4k` bigint(11) NULL DEFAULT 0,
  `direct_map2M` bigint(11) NULL DEFAULT 0,
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
