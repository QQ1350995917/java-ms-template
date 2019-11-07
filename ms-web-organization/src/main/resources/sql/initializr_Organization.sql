CREATE DATABASE `initializr_organization`; /*!40100 DEFAULT CHARACTER SET utf8 */;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

CREATE TABLE `organization_member` (
  `org_id` bigint(20) NOT NULL COMMENT '外键，组织ID',
  `mem_id` bigint(20) NOT NULL COMMENT '外键，成员ID',
  `level` int(11) NOT NULL DEFAULT '0' COMMENT '级别，0普通成员，1管理员，2拥有者',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态，0正常，1禁用，2删除',
  `create_time` bigint(20) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '最近修改时间',
  PRIMARY KEY (`org_id`,`mem_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC;

CREATE TABLE `organization_member_deal`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_id` bigint(20) NOT NULL COMMENT '组织ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `type` int(8) NOT NULL COMMENT '0:组织发起，1:用户发起',
  `deal` int(8) NOT NULL DEFAULT 0 COMMENT '0:未达成，1:已达成',
  `counter` int(16) NOT NULL DEFAULT 1 COMMENT '达成前的计数器',
  `status` int(8) NOT NULL DEFAULT 0 COMMENT '状态，0正常1禁用2删除',
  `create_time` bigint(20) NOT NULL COMMENT '首次创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '最近更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

CREATE TABLE `organization_review`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `org_id` bigint(20) NOT NULL COMMENT '组织ID',
  `editor_id` bigint(20) NOT NULL COMMENT '操作人的ID',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `ref_id` bigint(20) NULL DEFAULT NULL COMMENT '相关的记录Id',
  `type` int(11) NOT NULL COMMENT '记录类型，0：用户类型，1：管理员类型',
  `progress` int(20) NULL DEFAULT 0 COMMENT '审核进度，管理员类型不可为空，新建0--->待审核1--->审核中2，审核拒绝3，审核通过4--->重新复核5',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '0可用 1删除',
  `create_time` bigint(20) NULL DEFAULT NULL COMMENT '首次创建时间',
  `update_time` bigint(20) NOT NULL DEFAULT 0 COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
