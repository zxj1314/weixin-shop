package com.cbd.weixin.domain;



import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

//根元素为xml
@XmlRootElement(name = "xml")
//xml与domain互转
public class XmlMessageEntity {

	private String ToUserName;
	private String FromUserName;
	private String CreateTime;
	private String MsgType;
	private String Content;
	private String Event;
	private String EventKey;
	private String ArticleCount;
	private String MediaId;
	private String Title;
	private String Description;
	private String ThumbMediaId;

	private List<ArticleResponse> article;



	public static String[] CDATA_TAG = {"ToUserName",
			"FromUserName","MsgType","Event","MsgId","Content","MediaId","Title","Description","MusicUrl","HQMusicUrl","ThumbMediaId",
			"PicUrl","Url"
	};


	@XmlElement(name="ToUserName")//接收方账号
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	@XmlElement(name="MediaId")//素材ID
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String MediaId) {
		MediaId = MediaId;
	}

	@XmlElement(name="Title")//标题
	public String getTitle() {
		return Title;
	}
	public void setTitle(String Title) {
		Title = Title;
	}

	@XmlElement(name="Description")//描述
	public String getDescription() {
		return Description;
	}
	public void setDescription(String Description) {
		Description = Description;
	}

	@XmlElement(name="ThumbMediaId")//缩略文件ID
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String ThumbMediaId) {
		ThumbMediaId = ThumbMediaId;
	}


	@XmlElement(name="FromUserName")//开发者微信号
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}


	@XmlElement(name="CreateTime")//消息创建时间
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}


	@XmlElement(name="MsgType")//消息类型
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}


	@XmlElement(name="Content")//消息内容
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}

	@XmlElement(name="ArticleCount")//图文消息个数
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	@XmlElementWrapper(name="Articles")//多条图文消息
	@XmlElement(name="item")
	public List<ArticleResponse> getArticle() {
		return article;
	}
	public void setArticle(List<ArticleResponse> article) {
		this.article = article;
	}

	@XmlElement(name="Event")//事件
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}


	@XmlElement(name="EventKey")
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}


	@Override
	public String toString(){
		StringBuilder sb=new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA["+ToUserName+"]]></ToUserName>");
		sb.append("<FromUserName><![CDATA["+FromUserName+"]]></FromUserName>");
		sb.append("<CreateTime><![CDATA["+CreateTime+"]]></CreateTime>");
		sb.append("<MsgType><![CDATA["+MsgType+"]]></MsgType>");
		if(StringUtils.isNotBlank(Content)) {
			sb.append("<Content><![CDATA[" + Content + "]]></Content>");
		}
		if("image".equals(MsgType)) {
			sb.append("<Image>");
			sb.append("<MediaId><![CDATA["+MediaId+"]]></MediaId>");
			sb.append("</Image>");
		}
		if("music".equals(MsgType)){
			sb.append("<Music>");
			sb.append("<Title><![CDATA["+Title+"]]></Title>");
			sb.append("<Description><![CDATA["+Description+"]]></Description>");
			sb.append("<ThumbMediaId><![CDATA["+ThumbMediaId+"]]></ThumbMediaId>");
			sb.append("</Music>");
		}
		sb.append("</xml>");
		return sb.toString();
	}
}
