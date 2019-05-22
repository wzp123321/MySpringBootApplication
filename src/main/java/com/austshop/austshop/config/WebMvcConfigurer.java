package com.austshop.austshop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 写拦截器配置文件主类 WebMvcConfigurer
 * zpwan
 * 2019/3/18
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//拦截除登录外的所有请求
        registry.addInterceptor(new Myinterceptor()).addPathPatterns("/**").excludePathPatterns("/usermodule/admin/login")
                .excludePathPatterns("/usermodule/user/register").excludePathPatterns("/usermodule/user/login")
        .excludePathPatterns("/carmodule/car/typelist").excludePathPatterns("/carmodule/car/list").excludePathPatterns("/dicmodule/dic/list")
        .excludePathPatterns("carmodule/car/assess").excludePathPatterns("/ordermodule/order/list").excludePathPatterns("dicmodule/dic/typecodelist")
                .excludePathPatterns("/carmodule/car/carinfo").excludePathPatterns("/ordermodule/order/info")
        ;
        super.addInterceptors(registry);
    }
}
