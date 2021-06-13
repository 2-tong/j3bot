package com.a2tong.j3bot;

import com.a2tong.j3bot.core.MessageHandler;
import com.a2tong.j3bot.core.sync.SyncMessageHandler;
import com.a2tong.j3bot.http.RestClient;
import com.a2tong.j3bot.j3api.GameApi;
import com.a2tong.j3bot.listener.ChatListener;
import com.a2tong.j3bot.listener.SerendipityListener;
import com.a2tong.j3bot.message.DefaultMessageSender;
import com.a2tong.j3bot.message.MessageSender;
import com.google.gson.JsonObject;

import java.net.URI;

public class main {
    public static void main(String[] args) throws Exception {
        String authKey = "INITKEY0F6Ct1GO";
        long qq = 3524598014L;
        RestClient client = new RestClient("http://127.0.0.1:9900");
        JsonObject auth = new JsonObject();
        auth.addProperty("authKey",authKey);
        String sessionKey = client.post("auth", auth,JsonObject.class).get("session").getAsString();
        DefaultMessageSender.Message.setDefaultKey(sessionKey);

        JsonObject verify = new JsonObject();
        verify.addProperty("sessionKey",sessionKey);
        verify.addProperty("qq",qq);
        client.post("verify",verify,JsonObject.class);

        JsonObject config = new JsonObject();
        config.addProperty("sessionKey",sessionKey);
        config.addProperty("enableWebsocket",true);
        client.post("config",config,JsonObject.class);
        MessageHandler handler = new SyncMessageHandler(MessageSender.getInstance(), GameApi.getInstance());
        ChatListener chatListener = new ChatListener(new URI("http://127.0.0.1:9900/message?sessionKey="+sessionKey), handler);

        chatListener.connect();

        SerendipityListener serendipityListener = new SerendipityListener(new URI("wss://socket.nicemoe.cn"),MessageSender.getInstance());
        serendipityListener.connect();
    }
}
