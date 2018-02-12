package com.jony.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.jony.base.SystemConfigure;
import com.jony.model.BaseRespEntity;
import com.jony.utils.AesUtil;
import com.jony.utils.ConstantUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class AccessFilter extends ZuulFilter  {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return "pre";//路由之前
        //routing：路由之时
        //post： 路由之后
        //error：发送错误调用
    }

    @Override
    public int filterOrder() {//过滤顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;//是否要过滤
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURL().toString();
        if(url.endsWith("/api/login")){
            return null;
        }

        String token = request.getHeader("token");
        String username = AesUtil.decrypt(ConstantUtil.KEY, token);  //解密
        logger.info(String.format("%s request to %s,token:%s", request.getMethod(), url,token,username));
        if(StringUtils.isNotBlank(token)){
        	String accessToken = redisTemplate.opsForValue().get(username);
    		if(StringUtils.isBlank(accessToken)){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            returnJson(ctx.getResponse(),new BaseRespEntity<Object>(SystemConfigure.commonEnum.RESULT_FAIL.getValue(),
                    SystemConfigure.errorEnum.TOKEN_EXPIRE.getValue(),SystemConfigure.errorEnum.TOKEN_EXPIRE.getDescribe()));
            logger.info("access token fail");
        }
        }
        //重置token有效期
        stringRedisTemplate.opsForValue().set(username, token,60*10*24,TimeUnit.SECONDS); 
        logger.info("access success");
        return null;
    }

    private void returnJson(HttpServletResponse response, BaseRespEntity<?> responseParam) {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String json = JSON.toJSONString(responseParam);
        try {
            writer = response.getWriter();
            writer.print(json);
        } catch (IOException e) {
            logger.error("response error",e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
