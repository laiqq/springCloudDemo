将config-server、config-client都启动起来，
然后访问
http://localhost:8100/config-client/dev
可以看到该端点将会返回从git仓库中获取的配置信息
修改应用启动配置文件的git仓库地址、账号、密码