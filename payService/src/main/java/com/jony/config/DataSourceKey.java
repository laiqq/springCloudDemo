package com.jony.config;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  
  
/*
 * 自定义注解路由DataSource
 * */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface DataSourceKey  { 
	
	 /**
     * @return 数据源ID ,支持表达式如 : ${#param.id}
     * @see DynamicDataSource#getId()
     */
    String value();

    /**
     * @return 数据源不存在时, 是否使用默认数据源.
     * 如果为{@code false},当数据源不存在的时候,
     * 将抛出 {@link org.hswebframework.web.datasource.exception.DataSourceNotFoundException}
     * @see DataSourceHolder#currentExisting()
     */
    boolean fallbackDefault() default true;
     
}  