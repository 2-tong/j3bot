package com.a2tong.j3bot;

import com.a2tong.j3bot.core.MessageHandler;
import com.a2tong.j3bot.core.sync.SyncMessageHandler;
import com.a2tong.j3bot.j3api.DefaultGameApi;
import com.a2tong.j3bot.j3api.GameApi;
import com.a2tong.j3bot.message.ChatMessage;
import com.a2tong.j3bot.message.MessageSender;
import org.junit.Test;


public class ApiTest {

    @Test
    public void test(){
        MessageHandler messageHandler = new SyncMessageHandler(MessageSender.getInstance(), GameApi.getInstance());
        ChatMessage message = new ChatMessage();
        message.setMsg("bot日常");
        message.setSource(123456789L);
        message.setMsgType(ChatMessage.MSG_TYPE.TEXT);
        message.setSourceType(ChatMessage.TARGET_TYPE.GROUP);

        messageHandler.handleChatMessage(message);
    }
}
