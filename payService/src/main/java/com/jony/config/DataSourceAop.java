package com.jony.config;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
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
    
    /**
    * @Description 获取字段名和字段值
    * @Author 刘俊重
    * @date 2017年7月6日 
    * @return Map<String,Object>
    */
    private Map<String,Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws Exception {   
            Map<String,Object > map=new HashMap<String,Object>(); 


            ClassPool pool = ClassPool.getDefault();    
            ClassClassPath classPath = new ClassClassPath(cls);    
            pool.insertClassPath(classPath);    
                
            CtClass cc = pool.get(clazzName);    
            CtMethod cm = cc.getDeclaredMethod(methodName);    
            MethodInfo methodInfo = cm.getMethodInfo();  
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();    
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);    
            if (attr == null) {    
                // exception    
            }    
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;    
            for (int i = 0; i < cm.getParameterTypes().length; i++){    
                map.put( attr.variableName(i + pos),args[i]);//paramNames即参数名    
            }    
            return map;    
        }       


    @After("execution(* com.jony.service.impl.*.*(..)) ")
    public void afterReturning() throws Throwable {
        DataSourceContextHolder.clearDataSourceType();
        log.info("=====> clear dataSource aop ");
    }
}

