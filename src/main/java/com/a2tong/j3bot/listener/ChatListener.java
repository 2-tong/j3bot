package com.a2tong.j3bot.listener;

import com.a2tong.j3bot.core.MessageHandler;
import com.a2tong.j3bot.message.chat.ChatMessage;
import com.a2tong.j3bot.message.chat.MSG_TYPE;
import com.a2tong.j3bot.message.chat.MsgTarget;
import com.a2tong.j3bot.message.chat.TARGET_TYPE;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class ChatListener extends WebSocketClient {

    MessageHandler messageHandler;

    public ChatListener(URI serverUri,MessageHandler messageHandler) {
        super(serverUri);
        this.messageHandler = messageHandler;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("聊天监听开始");
    }

    @Override
    public void onMessage(String s) {
        Gson gson = new Gson();
        JsonObject jObj = gson.fromJson(s,JsonObject.class);
        ChatMessage chatMessage = new ChatMessage();
        String type = jObj.get("type").getAsString();
        if("GroupMessage".equals(type)){
            long source = jObj.getAsJsonObject("sender").getAsJsonObject("group").get("id").getAsLong();
            chatMessage.setSender(new MsgTarget(TARGET_TYPE.GROUP,source));
        }
        else if("FriendMessage".equals(type)){
            long source = jObj.getAsJsonObject("sender").get("id").getAsLong();
            chatMessage.setSender(new MsgTarget(TARGET_TYPE.FRIEND,source));
        }

        JsonArray chains = jObj.getAsJsonArray("messageChain");
        StringBuilder msg = new StringBuilder();
        for (JsonElement jb:chains){
            JsonObject obj = jb.getAsJsonObject();
            if("Plain".equals(obj.get("type").getAsString())){
                msg.append(obj.get("text").getAsString());
            }
        }
        chatMessage.setMsgType(MSG_TYPE.TEXT);
        chatMessage.setMsg(msg.toString());

        messageHandler.handleChatMessage(chatMessage);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("断开聊天监控");
    }

    @Override
    public void onError(Exception e) {

    }

}
