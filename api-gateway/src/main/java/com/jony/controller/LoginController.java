package com.jony.controller;

import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.jony.model.LoginRes;
import com.jony.model.UserEntity;
import com.jony.utils.AesUtil;
import com.jony.utils.ConstantUtil;

@RestController
public class LoginController {
	
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @RequestMapping(value = "/api/login")
    @ResponseBody
    public LoginRes loginSuccess(@RequestBody UserEntity userEntity) {
    	LoginRes loginRes = new LoginRes();
    	loginRes.setResultCode("111111");
    	loginRes.setResultMsg("异常状态");
    	String userName = userEntity.getUserName();  	    	
    
    	if(StringUtils.isNotBlank(userName)){
    		if(redisTemplate.opsForValue().get(userName) == null){
    			String token = AesUtil.encrypt(ConstantUtil.KEY, userName);  //加密
        		stringRedisTemplate.opsForValue().set(token, token,60*10*24,TimeUnit.SECONDS);  
        		loginRes.setResultCode("999999");
            	loginRes.setResultMsg("授权通过");
            	loginRes.setToken(token);
        		logger.info(token);
    		}else{
    			String token = redisTemplate.opsForValue().get(userName);
    			loginRes.setResultCode("000000");
    			loginRes.setToken(token);
    	    	loginRes.setResultMsg("已经授权通过了，不重复登录");
    	    	logger.info(token);
    		}
    	} 
    	//stringRedisTemplate.opsForValue().setIfAbsent(key, token); //原子性操作，只能一个线程在操作     	
        return loginRes;
    }
    

}
