package com.setup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //设置视图映射，添加重定向
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/own/personal.html");
        registry.addViewController("/register.html").setViewName("register");
        registry.addViewController("/own/personInfo.html");
        registry.addViewController("/own/changeHead.html");
        registry.addViewController("/own/album.html");
    }

    //静态资源文件夹映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/setUp/**").addResourceLocations("file:D:/setUp/");
    }


}
