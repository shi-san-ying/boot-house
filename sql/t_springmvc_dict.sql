drop table  if exists t_springmvc_dict;
create table if not exists t_springmvc_dict(
		id int not null primary key AUTO_INCREMENT,
	group_id varchar(12) not null  comment '字典项分组id',
	name varchar(32) not null comment '字典名称',
	value varchar(16) not null comment '字典值',
	sort int not null comment '排序字段'

);