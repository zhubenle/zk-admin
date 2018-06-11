drop table if exists `user`;

create table `user` (
  `id` int(11) not null auto_increment comment '自增主键',
  `email` varchar(100) not null comment '邮箱地址',
  `password` varchar(64) not null comment '密码',
  `del` tinyint(2) not null default 0 comment '是否删除，1-是，0-否',
  primary key (`id`)
) engine=innodb default charset=utf8;