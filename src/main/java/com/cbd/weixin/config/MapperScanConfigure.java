package com.cbd.weixin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Toby
 */

@Configuration
@MapperScan("com.cbd.weixin.mapper")
public class MapperScanConfigure {
}
