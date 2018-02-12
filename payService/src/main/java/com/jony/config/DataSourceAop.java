package com.jony.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-1)
@Component
public class DataSourceAop {

    Logger log = LoggerFactory.getLogger(this.getClass());

    /*@Around("execution(* com.jony.service.impl.*.*(..)) ")  
    public void setCenterUserDataSourceType(ProceedingJoinPoint joinPoint) throws Exception {
    	
    	  log.info("dataSource == >：come in 路由db");   
    	  Object[] args = joinPoint.getArgs();
          String classType = joinPoint.getTarget().getClass().getName();    
          Class<?> clazz = Class.forName(classType);    
          String clazzName = clazz.getName();    
          String methodName = joinPoint.getSignature().getName(); //获取方法名称   
           //获取参数名称和值  
          Map<String,Object > nameAndArgs = this.getFieldsName(this.getClass(), clazzName, methodName,args);  
          String dbKey = (String) nameAndArgs.get("dbKey");  
    	  int key = Integer.valueOf(dbKey).intValue();
          if(key%2==1){
        	  DataSourceContextHolder.setDataSourceType(DataSourceType.master.getType());  
          }else{  	   
        	  DataSourceContextHolder.setDataSourceType(DataSourceType.slave.getType());
          }       
        
          log.info("dataSource == >: get out  路由db");
    }*/
    
    @Before("execution(* com.jony.service.impl.*.*(..)) ")  
    public void setCenterUserDataSourceType(JoinPoint point) throws Exception {
    	
    	  log.info("dataSource == >：come in 路由db");   
    	  Object[] args = point.getArgs();
    	  String dbKey = args[0].toString(); 
    	  int key = Integer.valueOf(dbKey).intValue();
          if(key%2==1){
        	  DataSourceContextHolder.setDataSourceType(DataSourceType.master.getType());  
          }else{  	   
        	  DataSourceContextHolder.setDataSourceType(DataSourceType.slave.getType());
          }       
        
          log.info("dataSource == >: get out  路由db");
    }

    /*@Before("execution(* com.py.service.read..*.*(..)) && args(userName)")
    public void setUserCenterDataSourceType(String userName) throws Throwable{
    	log.info("dataSource == >：come in read  读库 ");
    	
         if(userName.equals("laiqq")){
       	  DataSourceContextHolder.setDataSourceType(DataSourceType.master.getType());  
         }else{
       	  DataSourceContextHolder.setDataSourceType(DataSourceType.slave.getType());
         }       
        log.info("dataSource == >：read  读库");
    }*/
    

    @After("execution(* com.jony.service.impl.*.*(..)) ")
    public void afterReturning() throws Throwable {
        DataSourceContextHolder.clearDataSourceType();
        log.info("=====> clear dataSource aop ");
    }
}

