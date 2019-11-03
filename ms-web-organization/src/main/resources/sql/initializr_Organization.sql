CREATE DATABASE `initializr_organization` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pid` bigint(20) NOT NULL DEFAULT '0' COMMENT '上级组织',
  `name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '组织名称',
  `logo` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '组织logo',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '组织描述',
  `slogan` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '组织slogan',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '组织等级',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '组织排序',
  `members` int(11) NOT NULL DEFAULT '1' COMMENT '组织成员数量',
  `progress` int(11) NOT NULL DEFAULT '0' COMMENT '组织审核进度',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态，0正常，1禁用，2删除',
  `create_time` bigint(11) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(11) NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC

CREATE TABLE `organization_member` (
  `org_id` bigint(20) NOT NULL COMMENT '外键，组织ID',
  `mem_id` bigint(20) NOT NULL COMMENT '外键，成员ID',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '级别，0普通成员，1管理员，2拥有者',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态，0正常，1禁用，2删除',
  `create_time` bigint(20) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`org_id`,`mem_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC

CREATE TABLE `organization_progress` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_id` bigint(20) NOT NULL COMMENT '组织ID',
  `applicant_id` bigint(20) NOT NULL COMMENT '申请者的ID',
  `applicant_content` text NOT NULL COMMENT '申请内容',
  `ref_id` bigint(20) DEFAULT NULL COMMENT '相关的记录Id',
  `auditor_id` bigint(20) DEFAULT NULL COMMENT '评审者ID',
  `auditor_content` text COMMENT '评审内容',
  `auditor_result` int(11) DEFAULT NULL COMMENT '评审结果',
  `auditor_time` bigint(20) DEFAULT NULL COMMENT '审核时间',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '0可用 1删除',
  `create_time` bigint(20) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8