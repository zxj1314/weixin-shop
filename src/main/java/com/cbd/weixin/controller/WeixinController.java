package com.cbd.weixin.controller;

import com.cbd.weixin.domain.ArticleResponse;
import com.cbd.weixin.domain.Client;
import com.cbd.weixin.domain.TextMessage;
import com.cbd.weixin.domain.XmlMessageEntity;
import com.cbd.weixin.service.IClientService;
import com.cbd.weixin.utils.HttpUtil;
import com.cbd.weixin.utils.MessageUtil;
import com.cbd.weixin.utils.SecurityUtil;
import com.cbd.weixin.utils.WeixinUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName
 * @Description 微信的事件，如关注，自动回复
 * @author zhuxiaojin
 * @Date 2018-03-19
 */
@Controller
public class WeixinController {
	@Autowired
	private IClientService clientService;

	//get验证
	@RequestMapping(value = "/weixin", method = RequestMethod.GET)
	@ResponseBody
	public String checkSignature(String signature, String timestamp, String nonce, String echostr) {
		//微信加密签名
		System.out.println(signature);
		//时间戳
		System.out.println(timestamp);
		//随机数
		System.out.println(nonce);
		//随机字符串
		System.out.println(echostr);

		//加密/校验流程如下：
		String[] arr = { WeixinUtil.TOKEN, timestamp, nonce };

		//1）将token、timestamp、nonce三个参数进行字典序排序
		Arrays.sort(arr);

		String str = "";
		//2）将三个参数字符串拼接成一个字符串进行sha1加密
		for (String temp : arr) {
			str += temp;
		}

		//3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if (signature.equals(SecurityUtil.SHA1(str))) {
			System.out.println("接入成功！");
			return echostr;
		}

		System.out.println("接入失败！");
		return null;
	}

	//post消息处理
	@RequestMapping(value = "/weixin", method = RequestMethod.POST)
	@ResponseBody
	public String handlerMessage(@RequestBody XmlMessageEntity entity) throws Exception{
		System.out.println(entity.getMsgType()+entity.getContent());
		//要发送的对象
		XmlMessageEntity newEntity = new XmlMessageEntity();

		//设置发送方
		newEntity.setFromUserName(entity.getToUserName());
		//设置接收方
		newEntity.setToUserName(entity.getFromUserName());
		//设置发送时间
		newEntity.setCreateTime(new Date().getTime()+"");
		//=================================

		//要发送的对象
		TextMessage text=null;

		//关注事件(点击关注事件相关的业务/取消关注相关的业务)
		if ("event".equals(entity.getMsgType())) {
			//如果是关注事件
			if ("subscribe".equals(entity.getEvent())) {
				//获取用户信息
				String result = HttpUtil.get(WeixinUtil.USERINFO_URL.replace("ACCESS_TOKEN",
						WeixinUtil.getAccessToken()).replace("OPENID", entity.getFromUserName()));
				//转成json对象
				JSONObject json = JSON.parseObject(result);
				String openid = json.getString("openid");
				String nickname = json.getString("nickname");
				Client client = new Client();
				//查询数据库中是否已经存在该用户
				Long id = clientService.getClientId(openid);
				if (clientService.getClientId(openid) != null) {
					//客户已经存在数据库中,只需要修改客户的状态和关注时间,和客户的昵称
					client.setStatus("关注");//设置关注状态
					client.setConcernTime(new Date());//重新设置关注时间
					client.setCancelTime(null);//设置取消关注时间为null
					client.setId(id);
					//修改信息
					clientService.update(client);
				} else {
					//数据库中不存在该客户,需要新增该客户
					client.setConcernTime(new Date());//设置关注时间
					client.setStatus("关注");//设置关注状态
					client.setOpenID(openid);//设置用户的openid
					client.setCancelTime(null);//设置取消关注时间为null
					client.setNickname(nickname);//设置昵称
					clientService.insert(client);
				}
				//发送内容
				newEntity.setContent("来啦,亮仔!");
			} else if ("unsubscribe".equals(entity.getEvent())) {
				System.out.println("================");
				String fromUserName = entity.getFromUserName();//获取要取消的关注的客户的账号
				//去数据库中查询该客户id,后修改取消关注的客户的关注状态
				Long id = clientService.getClientId(fromUserName);
				//取消关注事件
				Client client = new Client();
				client.setId(id);
				client.setStatus("取消关注");//设置关注状态
				client.setConcernTime(null);//设置关注时间为null
				client.setCancelTime(new Date());//设置取消时间
				clientService.update(client);
			}
				newEntity.setMsgType("text");
				return " ";
		}


		if(entity.getMsgType()!=null && "推荐".equals(entity.getContent())){
			/*text=new TextMessage();
			text.setContent("澳门首家皇家线上赌场开业啦！");
			text.setToUserName(entity.getToUserName());
			text.setFromUserName(entity.getFromUserName());
			text.setCreateTime(new Date().getTime());
			text.setMsgType("text");
            String respMessage = MessageUtil.textMessageToXml(text);*/
            //System.out.println("=============accessToken:"+WeixinUtil.getAccessToken());
            newEntity.setMsgType("text");
            newEntity.setContent("上传成功");
			return newEntity.toString();
		}

		//发送类型
		return " ";
	}

	//用于测试被动回复消息
	@ResponseBody()
	@RequestMapping(value = "/123" ,method = RequestMethod.POST,produces = "application/xml; charset=utf-8")
	public String Reply2(HttpServletRequest request) throws IOException {
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<note>\n" +
				"    <FromUserName>Tove</FromUserName>\n" +
				"    <ToUserName>Jani</ToUserName>>\n" +
				"    <MsgType>Reminder</MsgType>\n" +
				"    <Content>Don't forget me this weekend!</Content>\n" +
				"</note>";
	}
}
