package com.a2tong.j3bot.listener;

import com.a2tong.j3bot.core.MessageHandler;
import com.a2tong.j3bot.message.ChatMessage;
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
            chatMessage.setSourceType(ChatMessage.TARGET_TYPE.GROUP);
            long source = jObj.getAsJsonObject("sender").getAsJsonObject("group").get("id").getAsLong();
            chatMessage.setSource(source);
        }
        else if("FriendMessage".equals(type)){
            chatMessage.setSourceType(ChatMessage.TARGET_TYPE.FRIEND);
            long source = jObj.getAsJsonObject("sender").get("id").getAsLong();
            chatMessage.setSource(source);
        }

        JsonArray chains = jObj.getAsJsonArray("messageChain");
        String msg = "";
        for (JsonElement jb:chains){
            JsonObject obj = jb.getAsJsonObject();
            if("Plain".equals(obj.get("type").getAsString())){
                msg = msg+obj.get("text").getAsString();
            }
        }
        chatMessage.setMsgType(ChatMessage.MSG_TYPE.TEXT);
        chatMessage.setMsg(msg);

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
