package com.cbd.weixin.domain;

import org.springframework.stereotype.Repository;

/**
 * @author 痞老板
 * @Project: zheng-wechat-mp-service
 * @Package:com.zheng.wechat.mp.service.bean
 * @date 2018/1/16 11:21
 **/
@Repository
public class TextMessage {
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String Content;
    private String MsgId;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}
