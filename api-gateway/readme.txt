网关 工程
系统上线后，所有请求微服务都需要经过该网关认证、授权，然后路由到对应的服务。
主要功能 在请求头中获取token参数，解析，解密，然后和从redis取得的session中的token对比，
一致就通过，否则返回未认证、未授权等标识代码、错误信息、错误页面等

启动前先在本地起 redis服务，不然会报错

使用postMan访问 http://localhost:5555/api/login      post请求，格式application/json
报文：
{
"uid" : "11111",
"userName" : "pony1",
"sex" : 27,
"age" : 0
}
获取token,记住token

后面的请求在请求头加入token参数名，token值 ,这样就可以拦截那些没有token的请求了，
token可以设置过期时间。获取token的方式可以是账号密码登录，也可以是appID、appKey的方式来校验获取

比如 http://localhost:5555/api-pay/test/selectUser
这个post请求里面加入token