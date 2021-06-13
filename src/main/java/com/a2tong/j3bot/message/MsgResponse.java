package com.a2tong.j3bot.message;

/**
 * 发送消息返回结果
 *
 * @author liwei04
 * @time 2021年06月10日 11:19
 */
public class MsgResponse {
    private int code;
    private String ms;
    private long messageId;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }
}
