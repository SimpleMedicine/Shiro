package com.mdcn.shirospringbootjsp.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @description: ApplicationContext工厂类, 手动创建和获得Bean
 * @author: Medicine
 * @createTime: 2021-12-21 22:00
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    /**
     * 根据bean的名字获取工厂中指定bean对象, 实际上是ByName注入方式
     * @param beanName bean名字
     * @return bean对象
     */
    public static Object getBeanByName(String beanName){
        return context.getBean(beanName);
    }
}
