package com.a2tong.j3bot.message.sync;

import com.a2tong.j3bot.message.ApiMessage;
import com.a2tong.j3bot.message.ChatMessage;
import com.a2tong.j3bot.message.MessageHandler;
import com.a2tong.j3bot.message.MessageSender;

/**
 * 同步消息处理器,当api消息为同步获取时调用。
 *
 * @author liwei04
 * @time 2021年06月10日 11:51
 */
public class SyncMessageHandler implements MessageHandler {

    private MessageSender messageSender;

    public SyncMessageHandler(MessageSender messageSender){
        this.messageSender = messageSender;
    }

    @Override
    public void handleChatMessage(ChatMessage msg) {

    }

    @Override
    public void handleJ3ApiMessage(ApiMessage msg) {

    }
}
