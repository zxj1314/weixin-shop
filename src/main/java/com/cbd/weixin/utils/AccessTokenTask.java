package com.cbd.weixin.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cbd.weixin.config.weixin.WeixinConfigure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName
 * @Description 每隔60分钟获取accessToken
 * @author zhuxiaojin
 * @Date 2018-03-26
 */
@Component
public class AccessTokenTask {

    @Autowired
    private WeixinConfigure config;



    @Scheduled(cron = "0 0/60 * * * ?")
    public void getAccessToken(){
        String access_token = null;
        try {
            String result = HttpUtil.get(config.getWeb_accesstoken_url().replace("APPID", config.getAppid()).replace("APPSECRET", config.getAppsecret()));
            JSONObject json = JSON.parseObject(result);
            access_token = (String) json.get("access_token");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("newAccessToken:"+access_token);
    }
}
