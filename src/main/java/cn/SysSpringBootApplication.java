package cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = {"cn.yfyue.sysauth.mapper", "cn.yfyue.yyzx.mapper"}) //扫描mapper包
@EnableCaching
public class SysSpringBootApplication extends SpringBootServletInitializer {

    public static ConfigurableApplicationContext ac;

    public static void main(String[] args) {
       ac =  SpringApplication.run(SysSpringBootApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SysSpringBootApplication.class);
    }
}
