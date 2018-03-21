package com.cbd.weixin.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @ClassName
 * @Description 上传素材
 * @author zhuxiaojin
 * @Date 2018-03-21
 */
public class FileUpload {

    public final static String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";

    public final static String SENDMSG_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

    public final static String CLEAR_URL = "https://api.weixin.qq.com/cgi-bin/clear_quota?access_token=ACCESS_TOKEN";

    //上传永久素材
    public static JSONObject addMaterialEver(String fileurl, String type, String token) {
        try {
            File file = new File(fileurl);
            //上传素材
            String path = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=" + token + "&type=" + type;


            String result = connectHttpsByPost(path, null, file);

            result = result.replaceAll("[\\\\]", "");
            System.out.println("result:" + result);
            JSONObject resultJSON = (JSONObject) JSONObject.toJSON(result);
            if (resultJSON != null) {
                if (resultJSON.get("media_id") != null) {
                    System.out.println("上传" + type + "永久素材成功");
                    return resultJSON;
                } else {
                    System.out.println("上传" + type + "永久素材失败");
                }
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    //上传永久素材
    public static String connectHttpsByPost(String path, String KK, File file) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        URL urlObj = new URL(path);
        //连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        String result = null;
        con.setDoInput(true);

        con.setDoOutput(true);

        con.setUseCaches(false); // post方式不能使用缓存

        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type",
                "multipart/form-data; boundary="
                        + BOUNDARY);

        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\""

                + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);

        // 文件正文部分
        // 把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }



   //上传永久图文素材
    public static String upload(){
        String result = HttpUtil.post(UPLOAD_URL.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken()), "{\n" +
                "\n" +
                "        \"articles\": [{\n" +
                "\n" +
                "        \"title\":'砸金蛋',\n" +
                "\n" +
                "        \"thumb_media_id\":'5ac04inGBP9qXAdV-1jJGuh0ovOLKE3qRDgsXl90ZQA',\n" +
                "\n" +
                "        \"author\":'AUTHOR',\n" +
                "\n" +
                "        \"digest\":'DIGEST',\n" +
                "\n" +
                "        \"show_cover_pic\":'1',\n" +
                "\n" +
                "        \"content\":'砸金蛋',\n" +
                "\n" +
                "        \"content_source_url\":'http://zxj.nat300.top/egg2.jsp'\n" +
                "\n" +
                "        }\n" +
                "\n" +
                "        ]\n" +
                "\n" +
                "        }");
        return result;
    }

    //群发
    public static void sendMsg(){
        String msg = HttpUtil.post(SENDMSG_URL.replace("ACCESS_TOKEN", WeixinUtil.getAccessToken()), "{\n" +
                "   \"filter\":{\n" +
                "      \"is_to_all\":true\n" +
                "   },\n" +
                "   \"mpnews\":{\n" +
                "      \"media_id\":\"rDWxMoEPIgldc_vL8lrBWDfoJAr1DA7qqEHXU0OXvv4zJ26PzRKy9nMqQXPL-Jxb\"\n" +
                "   },\n" +
                "    \"msgtype\":\"mpnews\",\n" +
                "    \"send_ignore_reprint\":0\n" +
                "}");
        System.out.println(msg);
    }

    //清零
    public static void clear(){
        String clear=HttpUtil.post(CLEAR_URL.replace("ACCESS_TOKEN",WeixinUtil.getAccessToken()),"{\n" +
                "“appid”:“wx0aef7875e1cc0332”\n" +
                "}");
        System.out.println(clear);
    }

    public static void main(String[] args) {
       try {
            String token = WeixinUtil.getAccessToken();
            String path = "D:/curl/qhc.mp3";
            JSONObject object = addMaterialEver(path,"voice",token);
            System.out.println(object.toString());
        } catch (Exception e) {
           e.printStackTrace();
        }

    }
}


