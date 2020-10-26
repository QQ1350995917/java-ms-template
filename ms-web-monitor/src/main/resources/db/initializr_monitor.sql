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

-- ----------------------------
-- Table structure for host_cpu
-- ----------------------------
DROP TABLE IF EXISTS `host_cpu`;
CREATE TABLE `host_cpu`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名称',
  `user` bigint(11) NULL DEFAULT NULL COMMENT '执行用户进程的耗时，包括ni耗时',
  `sys` bigint(11) NULL DEFAULT NULL COMMENT '内核运行耗时，包括IRQ和softirq耗时',
  `nice` bigint(11) NULL DEFAULT NULL COMMENT '调整进程优先级耗时',
  `idle` bigint(11) NULL DEFAULT NULL COMMENT '空闲期',
  `wait` bigint(11) NULL DEFAULT NULL COMMENT '等待I/O操作完成耗时',
  `irq` bigint(11) NULL DEFAULT NULL COMMENT '处理硬中断耗时',
  `soft_irq` bigint(11) NULL DEFAULT NULL COMMENT '处理软中断耗时',
  `stolen` bigint(11) NULL DEFAULT NULL COMMENT '等待虚拟CPU的耗时，此时hypervisor在为另一个虚拟处理器服务',
  `total` bigint(11) NULL DEFAULT NULL COMMENT '总使用量',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',

  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_cpu_core
-- ----------------------------
DROP TABLE IF EXISTS `host_cpu_core`;
CREATE TABLE `host_cpu_core`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名称',
  `index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '核心编号',
  `vendor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CPU生产商',
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'CPU类别',
  `cache_size` bigint(11) NULL DEFAULT NULL COMMENT 'CPU缓存数量',
  `mhz` int(11) NULL DEFAULT NULL COMMENT 'CPU的总量MHz',
  `total_cores` int(11) NULL DEFAULT NULL COMMENT '总核心数',
  `total_sockets` int(11) NULL DEFAULT NULL COMMENT '主板总路数',
  `cores_per_socket` int(11) NULL DEFAULT NULL COMMENT '主板每路支持最大核心数',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',

  PRIMARY KEY (`id`, `index`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_cpu_core_usage
-- ----------------------------
DROP TABLE IF EXISTS `host_cpu_core_usage`;
CREATE TABLE `host_cpu_core_usage`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机名称',
  `index` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核心编号',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '执行用户进程的耗时，包括ni耗时',
  `sys` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内核运行耗时，包括IRQ和softirq耗时',
  `nice` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调整进程优先级耗时',
  `idle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '空闲期',
  `wait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等待I/O操作完成耗时',
  `irq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理硬中断耗时',
  `soft_irq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '处理软中断耗时',
  `stolen` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '等待虚拟CPU的耗时，此时hypervisor在为另一个虚拟处理器服务',
  `combined` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总使用量',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'

) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_disk
-- ----------------------------
DROP TABLE IF EXISTS `host_disk`;
CREATE TABLE `host_disk`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名称',
  `dev_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '磁盘名称',
  `dir_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '磁盘挂载路径',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本地硬盘、光驱、网络文件系统等',
  `sys_type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'FAT32、NTFS',
  `options` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '磁盘选项',
  `type` int(11) NULL DEFAULT NULL COMMENT '磁盘类型，0：UNKNOWN；1：NONE；2：LOCAL_DISK；3：NETWORK；4：RAM_DISK；5：CDROM；6：SWAP；',
  `flags` bigint(11) NULL DEFAULT NULL COMMENT '磁盘标记',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',

  PRIMARY KEY (`id`, `dev_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_disk_usage
-- ----------------------------
DROP TABLE IF EXISTS `host_disk_usage`;
CREATE TABLE `host_disk_usage`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机名称',
  `dev_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `total` bigint(11) NULL DEFAULT NULL COMMENT '文件系统总大小',
  `free` bigint(11) NULL DEFAULT NULL COMMENT '剩余大小',
  `used` bigint(11) NULL DEFAULT NULL COMMENT '已经使用量',
  `avail` bigint(11) NULL DEFAULT NULL COMMENT '可用大小',
  `files` bigint(11) NULL DEFAULT NULL,
  `free_files` bigint(11) NULL DEFAULT NULL,
  `disk_reads` bigint(11) NULL DEFAULT NULL,
  `disk_writes` bigint(11) NULL DEFAULT NULL,
  `disk_read_bytes` bigint(11) NULL DEFAULT NULL,
  `disk_write_bytes` bigint(11) NULL DEFAULT NULL,
  `disk_queue` bigint(11) NULL DEFAULT NULL,
  `disk_service_time` bigint(11) NULL DEFAULT NULL,
  `use_percent` bigint(11) NULL DEFAULT NULL COMMENT '资源的利用率',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'

) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_ethernet
-- ----------------------------
DROP TABLE IF EXISTS `host_ethernet`;
CREATE TABLE `host_ethernet`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名称',
  `hwaddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网卡MAC地址',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网络设备名',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网卡类型',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网卡描述信息',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `destination` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标地址',
  `broadcast` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网关广播地址',
  `netmask` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '子网掩码',
  `flags` bigint(11) NULL DEFAULT NULL COMMENT '网卡标记',
  `mtu` bigint(11) NULL DEFAULT NULL COMMENT '网卡MTU',
  `metric` bigint(11) NULL DEFAULT NULL COMMENT '跃点数量',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',

  PRIMARY KEY (`id`, `hwaddr`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_ethernet_stat
-- ----------------------------
DROP TABLE IF EXISTS `host_ethernet_stat`;
CREATE TABLE `host_ethernet_stat`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机名称',
  `hwaddr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网卡名称',
  `rx_bytes` bigint(11) NULL DEFAULT NULL COMMENT '接收到的总字节数',
  `rx_packets` bigint(11) NULL DEFAULT NULL COMMENT '接收的总包裹数',
  `rx_errors` bigint(11) NULL DEFAULT NULL COMMENT '接收到的错误包数',
  `rx_dropped` bigint(11) NULL DEFAULT NULL COMMENT '系统接收时丢弃的包数，未进入系统内核',
  `rx_overruns` bigint(11) NULL DEFAULT NULL COMMENT '网卡接收时丢弃的包数，未进入网卡缓存',
  `rx_frame` bigint(11) NULL DEFAULT NULL COMMENT '网卡接收最大巨帧',
  `tx_bytes` bigint(11) NULL DEFAULT NULL COMMENT '发送的总字节数',
  `tx_packets` bigint(11) NULL DEFAULT NULL COMMENT '发送的总包裹数',
  `tx_errors` bigint(11) NULL DEFAULT NULL COMMENT '发送的错误包数',
  `tx_dropped` bigint(11) NULL DEFAULT NULL COMMENT '系统发送时丢弃的包数，未进入系统内核',
  `tx_overruns` bigint(11) NULL DEFAULT NULL COMMENT '网卡发送时丢弃的包数，未进入网卡缓存',
  `tx_collisions` bigint(11) NULL DEFAULT NULL,
  `tx_carrier` bigint(11) NULL DEFAULT NULL,
  `speed` bigint(11) NULL DEFAULT NULL,
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'

) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_memory
-- ----------------------------
DROP TABLE IF EXISTS `host_memory`;
CREATE TABLE `host_memory`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名称',
  `total` bigint(11) NULL DEFAULT NULL COMMENT '总内存量',
  `ram` bigint(11) NULL DEFAULT NULL COMMENT '可用内存总量',
  `used` bigint(11) NULL DEFAULT NULL COMMENT '当前内存使用量',
  `free` bigint(11) NULL DEFAULT NULL COMMENT '当前内存空闲量',
  `actual_used` bigint(11) NULL DEFAULT NULL COMMENT '实际内存使用量',
  `actual_free` bigint(11) NULL DEFAULT NULL COMMENT '实际内存空闲量',
  `used_percent` bigint(11) NULL DEFAULT NULL COMMENT '使用百分比',
  `free_percent` bigint(11) NULL DEFAULT NULL COMMENT '空闲百分比',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_os
-- ----------------------------
DROP TABLE IF EXISTS `host_os`;
CREATE TABLE `host_os`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统名称',
  `version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统的版本号',
  `arch` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统内核架构如： 386、486、586等x86',
  `machine` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'machine',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'description',
  `patch_level` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'patchLevel',
  `vendor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统的卖主',
  `vendor_version` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统卖主类型',
  `vendor_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统名称',
  `vendor_code_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卖主名称',
  `data_model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统位数',
  `cpu_endian` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统CPU大端/小端',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间',

  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_swap
-- ----------------------------
DROP TABLE IF EXISTS `host_memory_swap`;
CREATE TABLE `host_memory_swap`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主机名称',
  `total` bigint(11) NULL DEFAULT NULL COMMENT 'swap总量',
  `used` bigint(11) NULL DEFAULT NULL COMMENT 'swap使用量',
  `free` bigint(11) NULL DEFAULT NULL COMMENT 'swap空闲量',
  `page_in` bigint(11) NULL DEFAULT NULL COMMENT 'swapPageIn',
  `page_out` bigint(11) NULL DEFAULT NULL COMMENT 'swapPageOut',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for host_who
-- ----------------------------
DROP TABLE IF EXISTS `host_who`;
CREATE TABLE `host_who`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机名',
  `user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统进程表中的用户名',
  `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统进程表中的控制台名称',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统进程表中的HOST名称',
  `time` bigint(11) NULL DEFAULT NULL COMMENT '系统进程时长',
  `able` int(4) NOT NULL DEFAULT 0 COMMENT '可用状态：0:不可用；1:可用',
  `del` int(4) NOT NULL DEFAULT 0 COMMENT '删除状态：0:未删除；1:已删除',
  `create_time` datetime(0) NOT NULL COMMENT '数据创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '最近更新时间'

) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
