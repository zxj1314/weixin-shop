package com.cbd.weixin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public class WeixinUtil {
    public static final String TOKEN = "zhuxiaojin";

    public static final String APPID = "wx0aef7875e1cc0332";

    public static final String APPSECRET = "1354b29d86100de9ff998f5b54a16ce5";

    //获取用户信息的url
    public static final String USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    //网页版获取accessToken
    //public static final String WEB_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    public static final String WEB_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //网页版获取用户信息
    public static final String WEB_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public static String accessToken;

    //失效时间
    public static Long expiresTime = 0L;


    public static String getAccessToken() {
        //如果accessToken为null或者已经失效就获取
        if (new Date().getTime() >= expiresTime) {
            //发送http请求
            String result = HttpUtil.get(WEB_ACCESSTOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET));
            JSONObject json = JSON.parseObject(result);
            accessToken = (String) json.get("access_token");
            //失效时间=当前时间+7200
            Integer expires_in = (Integer) json.get("expires_in");
            expiresTime = new Date().getTime() + ((expires_in - 10) * 1000);
        }
        return accessToken;
    }

}
