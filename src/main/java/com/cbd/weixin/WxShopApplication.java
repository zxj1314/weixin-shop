package com.cbd.weixin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * @ClassName
 * @Description 启动类
 * @author zhuxiaojin
 * @Date 2018-03-19
 */
@Slf4j
@SpringBootApplication
public class WxShopApplication extends SpringBootServletInitializer {
  public static void main(String[] args) {
    SpringApplication.run(WxShopApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(WxShopApplication.class);
  }
}
