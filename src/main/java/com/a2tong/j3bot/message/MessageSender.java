package com.a2tong.j3bot.message;

/**
 * 向qq发送消息得接口
 *
 * @author liwei04
 * @time 2021年06月10日 11:15
 */
public interface MessageSender {

    static MessageSender getInstance(){
        return new DefaultMessageSender();
    }

    /**
     * 发送消息给好友
     * @param msg 消内容
     * @return 发送结果
     */
    MsgResponse sendText2Friend(ChatMessage msg);

    /**
     * 发送消息给群
     * @param msg 消内容
     * @return 发送结果
     */
    MsgResponse sendText2Group(ChatMessage msg);



}
