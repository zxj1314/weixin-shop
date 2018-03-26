package com.cbd.weixin.config.weixin;


import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WeixinConfigure {
    private String token;
    private String appid;
    private String appsecret;


    @URL
    private String userinfoUrl;
    private String web_accesstoken_url;
    private String upload_url;
    private String uploadmedia_url;
    private String sendmsg_url;
}
