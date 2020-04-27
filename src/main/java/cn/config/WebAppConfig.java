package cn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.util.Arrays;
import java.util.List;

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    private static final List<String> EXCLUDE_PATH = Arrays.asList("/", "/css/**", "/font/**", "/images/**", "/lay/**","/myJs/**","/layui.js");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册TestInterceptor拦截器
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor());
        registration.addPathPatterns("/**");                      //所有路径都被拦截
        registration.excludePathPatterns(                         //添加不拦截路径
                "/index/login",
                "/loginAjax/**",
                "/index/timeOut")
                .excludePathPatterns("/static/**", "/static/**")
                .excludePathPatterns(EXCLUDE_PATH);
    }
}
