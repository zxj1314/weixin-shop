package com.cbd.weixin.domain;

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

	private List<ArticleResponse> article;



	public static String[] CDATA_TAG = {"ToUserName",
			"FromUserName","MsgType","Event","MsgId","Content","MediaId","Title","Description","MusicUrl","HQMusicUrl","ThumbMediaId",
			"PicUrl","Url"
	};


	@XmlElement(name="ToUserName")
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	@XmlElement(name="FromUserName")
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	@XmlElement(name="CreateTime")
	public String getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	@XmlElement(name="MsgType")
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	@XmlElement(name="Content")
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	@XmlElement(name="ArticleCount")
	public String getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}
	@XmlElementWrapper(name="Articles")
	@XmlElement(name="item")
	public List<ArticleResponse> getArticle() {
		return article;
	}
	public void setArticle(List<ArticleResponse> article) {
		this.article = article;
	}

	@XmlElement(name="Event")
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
}
