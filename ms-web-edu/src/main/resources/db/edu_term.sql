CREATE TABLE `edu_term` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `pid` bigint(20) DEFAULT NULL COMMENT '父节点ID',
  `zhcn_name` varchar(255) NOT NULL COMMENT '中文名',
  `enus_name` varchar(255) NOT NULL COMMENT '英文名',
  `scholastic` int(8) NOT NULL COMMENT '学校的，0：否；1：是',
  `order` int(20) NOT NULL DEFAULT '0' COMMENT '排序',
  `leaf` int(8) DEFAULT NULL COMMENT '叶子节点，0：否；1是',
  `able` int(8) NOT NULL COMMENT '可用性状态，0：不可用；1：可用；',
  `del` int(8) NOT NULL COMMENT '删除状态，0：未删除，1：已删除；',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;