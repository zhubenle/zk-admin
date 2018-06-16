
insert into USER (username, password, email, create_time, update_time)
values ('admin', 'd033e22ae348aeb5660fc2140aec35850c4da997','zhubenle@foxmail.com', now(), now());

insert into ZK_INFO(ALIAS, HOSTS, CREATE_TIME) values ('localhost', '127.0.0.1:2181', now());