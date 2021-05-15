package com.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description :
 *
 * @author : Sam
 * @created : 2021/5/14
 */
@Configuration
public class MyWebMvcConfig  implements WebMvcConfigurer {

    /**
     * 设置欢迎页
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //设置前台首页的访问路径
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        //设置后台登录访问路径
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
    }
}
