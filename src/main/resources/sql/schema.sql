drop table if exists `user`;
create table if not exists `user` (
  `id` int(11) not null auto_increment comment '自增主键',
  `username` varchar(20) not null comment '用户名',
  `password` varchar(64) not null comment '密码',
  `email` varchar(100) not null comment '邮箱地址',
  `create_time` datetime not null comment '创建时间',
  `update_time` datetime not null comment '更新时间',
  primary key (`id`),
  UNIQUE KEY `username_uk`(`username`) USING BTREE
) engine=innodb default charset=utf8;

drop table if exists `zk_info`;
create table if not exists `zk_info` (
  `id` int(11) not null auto_increment comment '自增主键',
  `alias` varchar(100) not null comment '别名',
  `hosts` varchar(64) not null comment 'zk地址端口',
  `create_time` datetime not null comment '创建时间',
  primary key (`id`),
  UNIQUE KEY `alias_uk`(`alias`) USING BTREE,
  UNIQUE KEY `hosts_uk`(`hosts`) USING BTREE
) engine=innodb default charset=utf8;