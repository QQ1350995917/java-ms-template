CREATE DATABASE `initializr_account` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `initializr_account`;

create table admin_account
(
	id bigint(11) auto_increment comment '自增主键'
		primary key,
	uid bigint(11) not null comment '逻辑外键:admin_user.id',
	login_name varchar(45) not null,
	login_pwd varchar(45) not null,
	pwd_time datetime not null,
	type int(4) not null comment '1:授权账号；2：电话号码+短信验证码账号；3：电话号码+密码账号；4：邮箱账号+直接登录链接账号；5：邮箱账号+验证码账号；6：邮箱账号+密码账号；7：微信认证账号；8：微博认证账号；9：QQ账号；',
	enable int(4) default '0' not null comment '可用状态：0:不可用；1:可用',
	del int(4) default '0' not null comment '删除状态：0:未删除；1:已删除',
	create_time datetime not null comment '数据创建时间',
	update_time datetime not null comment '最近更新时间'
)
;

create table admin_config
(
	id bigint(11) auto_increment comment '自增主键'
		primary key,
	`key` varchar(45) not null comment '配置key',
	value text not null comment '配置value',、
  `summary` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '描述',
	able int default '0' not null comment '可用状态：0:不可用；1:可用',
	del int default '0' not null comment '删除状态：0:未删除；1:已删除',
	create_time datetime not null comment '数据创建时间',
	update_time datetime not null comment '最近更新时间',
	constraint key_UNIQUE
		unique (`key`)
)
;

create table admin_contact
(
	id bigint(11) auto_increment comment '自增主键'
		primary key,
	uid bigint(11) not null comment '逻辑外键:admin_user.id',
	type int(4) not null comment '1:电话号码；2：地址；3：邮箱；4：微信；5：微博；6：QQ；',
	value varchar(128) not null comment 'Type字段对应的值',
	mark varchar(45) null comment '描述',
	enable int(4) default '0' not null comment '可用状态：0:不可用；1:可用',
	del int(4) default '0' not null comment '删除状态：0:未删除；1:已删除',
	create_time int(4) not null comment '数据创建时间',
	update_time int(4) not null comment '最近更新时间'
)
;

create table admin_user
(
	id bigint(11) auto_increment comment '自增主键'
		primary key,
	pin varchar(20) null comment '身份证号',
	name varchar(255) not null comment '姓名',
	gender char not null comment '性别',
	emp_no varchar(45) null comment '工号',
	summary varchar(255) null comment '简介',
	able int(4) default '0' not null comment '可用性：0:不可用；1:可用',
	del int(4) default '0' not null comment '状态：0:未删除；1:已删除',
	create_time datetime not null comment '首次创建时间',
	update_time datetime not null comment '最近更新时间',
	constraint pin_UNIQUE
		unique (pin)
)
collate=utf8_bin
;

create table user_account
(
	id bigint(11) auto_increment comment '自增主键'
		primary key,
	uid bigint(11) not null comment '逻辑外键:admin_user.id',
	login_name varchar(45) not null,
	login_pwd varchar(45) not null,
	pwd_time datetime not null,
	type int(4) not null comment '1:授权账号；2：电话号码+短信验证码账号；3：电话号码+密码账号；4：邮箱账号+直接登录链接账号；5：邮箱账号+验证码账号；6：邮箱账号+密码账号；7：微信认证账号；8：微博认证账号；9：QQ账号；',
	enable int(4) default '0' not null comment '可用状态：0:不可用；1:可用',
	del int(4) default '0' not null comment '删除状态：0:未删除；1:已删除',
	create_time datetime not null comment '数据创建时间',
	update_time datetime not null comment '最近更新时间'
)
;

create table user_config
(
	id bigint(11) auto_increment comment '自增主键'
		primary key,
	`key` varchar(45) not null comment '配置key',
	value text not null comment '配置value',
  `summary` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '描述',
	able int default '0' not null comment '可用状态：0:不可用；1:可用',
	del int default '0' not null comment '删除状态：0:未删除；1:已删除',
	create_time datetime not null comment '数据创建时间',
	update_time datetime not null comment '最近更新时间',
	constraint key_UNIQUE
		unique (`key`)
)
;

create table user_contact
(
	id bigint(11) auto_increment comment '自增主键'
		primary key,
	uid bigint(11) not null comment '逻辑外键:admin_user.id',
	type int(4) not null comment '1:电话号码；2：地址；3：邮箱；4：微信；5：微博；6：QQ；',
	value varchar(128) not null comment 'Type字段对应的值',
	mark varchar(45) null comment '描述',
	enable int(4) default '0' not null comment '可用状态：0:不可用；1:可用',
	del int(4) default '0' not null comment '删除状态：0:未删除；1:已删除',
	create_time int(4) not null comment '数据创建时间',
	update_time int(4) not null comment '最近更新时间'
)
;

create table user_user
(
	id bigint(11) auto_increment comment '自增主键'
		primary key,
	pin varchar(20) null comment '身份证号',
	name varchar(255) not null comment '姓名',
	gender char not null comment '性别',
	emp_no varchar(45) null comment '工号',
	summary varchar(255) null comment '简介',
	able int(4) default '0' not null comment '可用性：0:不可用；1:可用',
	del int(4) default '0' not null comment '状态：0:未删除；1:已删除',
	create_time datetime not null comment '首次创建时间',
	update_time datetime not null comment '最近更新时间',
	constraint pin_UNIQUE
		unique (pin)
)
collate=utf8_bin
;

