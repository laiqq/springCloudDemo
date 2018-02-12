注意事项：
服务提供者 springCloud 版本 Camden.SR4  ，springboot版本 1.4.3

注册中心 springCloud 版本 Dalston.SR4  ，springboot版本 1.5.6

配置中心 springCloud 版本 Dalston.SR4  ，springboot版本 1.5.6

服务提供者版本不对，服务无法启动

本地创建数据库test、test1,修改数据库配置,新建表

CREATE TABLE `t_user` (
  `user_name` varchar(64) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `uid` varchar(32) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8

启动服务，用postMan访问 http://localhost:8080/test/selectUser ,post请求,application/json格式
报文
{
"uid" : "11111",
"userName" : "pony1",
"sex" : 27,
"age" : 0
}