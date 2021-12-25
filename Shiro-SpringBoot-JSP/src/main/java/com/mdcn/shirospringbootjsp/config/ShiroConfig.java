package com.mdcn.shirospringbootjsp.config;

import com.mdcn.shirospringbootjsp.shiro.cache.RedisCacheManager;
import com.mdcn.shirospringbootjsp.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 整合Shiro框架相关配置类
 * @author: Medicine
 * @createTime: 2021-12-21 16:04
 */
@Configuration
public class ShiroConfig {
    /**
     * 1.创建shiroFilter
     * @param defaultWebSecurityManager 默认网页安全管理器
     * @return Shiro过滤器工厂对象
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        // 配置系统受限资源
        // 配置系统公共资源
        Map<String,String> map = new HashMap<>();
        map.put("/user/login.jsp","anon");  // anno设置为公共资源
        map.put("/user/register.jsp","anon");
        map.put("/user/getImage","anon"); // 放行验证码接口
        map.put("/register.jsp","anon");
        map.put("/**.jsp","authc");  // 所有资源全部需要验证
        // 默认认证路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 2.创建安全管理器
     * @param realm 自定义Realm对象
     * @return 默认网页安全管理器
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        // jsp的web项目中必须使用DefaultWebSecurityManager
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 给安全管理器设置Realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 3.创建自定义realm
     * @return 自定义realm
     */
    @Bean
    public Realm getRealm(){
        CustomerRealm customerRealm = new CustomerRealm();
        // 修改凭证校验匹配器(默认是equals匹配)
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        // 设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        // 设置散列次数
        credentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(credentialsMatcher);

        // 开启缓存管理
        customerRealm.setCacheManager(new RedisCacheManager());    // Redis缓存管理(自定义缓存管理)
        customerRealm.setCachingEnabled(true);  // 开启全局缓存
        customerRealm.setAuthenticationCachingEnabled(true);                // 开启认证缓存
        customerRealm.setAuthenticationCacheName("authenticationCache");    // 设置认证缓存名字
        customerRealm.setAuthorizationCachingEnabled(true);                 // 开启授权缓存
        customerRealm.setAuthenticationCacheName("authorizationCache");     // 设置授权缓存名字

        return customerRealm;
    }
}
