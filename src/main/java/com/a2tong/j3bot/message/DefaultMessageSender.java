package com.a2tong.j3bot.message;


/**
 * TODO 默认消息发送类
 */
public class DefaultMessageSender implements MessageSender{

    @Override
    public MsgResponse sendText2Friend(ChatMessage msg) {
        return null;
    }

    @Override
    public MsgResponse sendText2Group(ChatMessage msg) {
        System.out.println(msg);
        return new MsgResponse();
    }
}
