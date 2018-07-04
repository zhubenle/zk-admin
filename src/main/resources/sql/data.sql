-- 密码加密方式sha1
insert ignore into USER (username, password, email, create_time, update_time)
values ('admin', 'admin','', now(), now());

-- insert into ZK_INFO(ALIAS, HOSTS, CREATE_TIME) values ('localhost', '127.0.0.1:2181', now());