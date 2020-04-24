CREATE TABLE `t_boot_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `gender` varchar(4) NOT NULL COMMENT '性别',
  `age` int(11) NOT NULL COMMENT '年龄',
  `email` varchar(64) NOT NULL COMMENT '注册邮箱',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '0：未激活 1：已激活 未激活不允许登录',
  `birthday` date NOT NULL COMMENT '出身日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;