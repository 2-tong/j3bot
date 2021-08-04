package com.a2tong.j3bot.message.chat;

/**
 * TODO
 *
 * @author liwei04
 * @time 2021年06月10日 11:48
 */
public class ChatMessage {

    public static ChatMessage buildMessageForReturn(MsgTarget receiver, MSG_TYPE type, Object context) {
        ChatMessage msg = new ChatMessage();
        msg.setReceiver(receiver);
        msg.setMsgType(type);
        msg.setMsg(context);
        return msg;
    }

    private Object msg;

    private MSG_TYPE msgType;

    public MsgTarget getReceiver() {
        return receiver;
    }

    public void setReceiver(MsgTarget receiver) {
        this.receiver = receiver;
    }

    private MsgTarget receiver;

    private MsgTarget sender;

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public MSG_TYPE getMsgType() {
        return msgType;
    }

    public void setMsgType(MSG_TYPE msgType) {
        this.msgType = msgType;
    }

    public MsgTarget getSender() {
        return sender;
    }

    public void setSender(MsgTarget sender) {
        this.sender = sender;
    }
}
