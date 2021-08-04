package com.a2tong.j3bot.core;

import com.a2tong.j3bot.message.ApiMessage;
import com.a2tong.j3bot.message.chat.ChatMessage;

/**
 * TODO
 *
 * @author liwei04
 * @time 2021年06月10日 11:42
 */
public interface MessageHandler {

    void handleChatMessage(ChatMessage msg);

    void handleJ3ApiMessage(ApiMessage msg);


}
