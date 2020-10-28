/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : initializr_monitor

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 23/10/2020 11:40:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE `host`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名',
  `operating_system` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作系统名称',
  `hardware_platform` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '硬件平台（x86_64）',
  `system_up_since` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '启动时间',
  `kernel_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内核名称',
  `kernel_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内核版本',
  `kernel_release` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内核发布',
  `machine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '型号',
  `processor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`group_name`,`node_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `host_load_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名',
  `load_in1m` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '1分钟统计负载情况',
  `load_in5m` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '5分钟统计负载情况',
  `load_in15m` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '15分钟统计负载情况',
  `process_rate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '运行进程和总进程之比',
  `last_process_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '最近运行进程的ID',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE `host_logged_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录用户名称',
  `tty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录用户使用的终端名',
  `from` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录用户来源的主机名或IP地址',
  `login` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户登录时长',
  `idle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '自用户上一次与终端进行交互以来的空闲时间',
  `jcpu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户当前进程所用的时间',
  `pcpu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户当前的进程及选项/参数',
  `what` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户当前的进程及选项',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE `host_cpu`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名',

    `processor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `vendor_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `cpu_family` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `model_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `stepping` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `microcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `cpu_mHz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `cache_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `physical_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `siblings` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `core_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `cpu_cores` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `apicid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `initial_apicid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `fpu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `fpu_exception` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `cpuid_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `wp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `flags` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `bogomips` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `clflush_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `cache_alignment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `address_sizes` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',
    `power_management` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '',

  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`group_name`,`node_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;



CREATE TABLE `host_cpu_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名',

    `user` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `nice` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `system` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `idle` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `iowait` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `irq` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `softirq` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `steal` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `guest` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `guest_nice` int(11) NOT NULL DEFAULT 0 COMMENT '',


  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;



CREATE TABLE `host_memory_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名',


    `mem_total` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `mem_free` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `mem_available` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `buffers` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `cached` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `swap_cached` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `active` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `inactive` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `active_anon` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `inactive_anon` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `active_file` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `inactive_file` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `unevictable` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `mlocked` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `swap_total` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `swap_free` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `dirty` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `writeback` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `anon_pages` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `mapped` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `shmem` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `slab` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `s_reclaimable` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `s_unreclaim` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `kernel_stack` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `page_tables` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `n_fSUnstable` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `bounce` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `writeback_tmp` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `commit_limit` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `committed_aS` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `vmalloc_total` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `vmalloc_used` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `vmalloc_chunk` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `hardware_corrupted` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `anon_huge_pages` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `huge_pages_total` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `huge_pages_free` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `huge_pages_rsvd` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `huge_pages_surp` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `huge_pagesize` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `direct_map4k` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `direct_map2M` int(11) NOT NULL DEFAULT 0 COMMENT '',

  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


CREATE TABLE `host_disk_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名',


    `major_device_number` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `minor_device_number` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `device_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '',
    `read` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `read_merge` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `read_sector` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `read_spent_milliseconds` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `write` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `write_merge` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `write_sector` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `write_spent_milliseconds` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `io_request` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `io_spent_milliseconds` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `io_spent_all_milliseconds` int(11) NOT NULL DEFAULT 0 COMMENT '',

  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;



CREATE TABLE `host_ethernet_stat`  (
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '逻辑组名',
  `node_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名',

    `inter_face`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '',
    `receive_bytes` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `receive_packets` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `receive_errs` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `receive_drop` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `receive_fifo` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `receive_frame` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `receive_compressed` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `receive_multicast` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `transmit_bytes` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `transmit_packets` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `transmit_errs` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `transmit_drop` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `transmit_fifo` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `transmit_colls` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `transmit_carrier` int(11) NOT NULL DEFAULT 0 COMMENT '',
    `transmit_compressed` int(11) NOT NULL DEFAULT 0 COMMENT '',

  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
