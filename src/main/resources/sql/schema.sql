create table if not exists `user` (
  `id` int(11) not null auto_increment comment '自增主键',
  `email` varchar(100) not null comment '邮箱地址',
  `password` varchar(64) not null comment '密码',
  `create_time` datetime not null comment '创建时间',
  `update_time` datetime not null comment '更新时间',
  `del` tinyint(2) not null default 0 comment '是否删除，1-是，0-否',
  primary key (`id`)
) engine=innodb default charset=utf8;

create table if not exists `zk_info` (
  `id` int(11) not null auto_increment comment '自增主键',
  `alias` varchar(100) not null comment '别名',
  `hosts` varchar(64) not null comment 'zk地址端口',
  `create_time` datetime not null comment '创建时间',
  `update_time` datetime not null comment '更新时间',
  `del` tinyint(2) not null default 0 comment '是否删除，1-是，0-否',
  primary key (`id`)
) engine=innodb default charset=utf8;