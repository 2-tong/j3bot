package com.a2tong.j3bot.listener;

import com.a2tong.j3bot.config.SysConfig;
import com.a2tong.j3bot.message.ChatMessage;
import com.a2tong.j3bot.message.DefaultMessageSender;
import com.a2tong.j3bot.message.MessageSender;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Properties;

public class SerendipityListener extends WebSocketClient {

    MessageSender messageSender;

    static String[] listenServers ;

    static String[] unListenSerendipity ;

    static {
        Yaml yaml = new Yaml();
        SysConfig sysConfig =yaml.loadAs(ClassLoader.getSystemResourceAsStream("conf.yml"),SysConfig.class);
        listenServers = sysConfig.getListenServers();
        unListenSerendipity = sysConfig.getUnListenSerendipity();
    }

    public SerendipityListener(URI serverUri, MessageSender messageSender) {
        super(serverUri);
        this.messageSender = messageSender;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("开始奇遇监听");
    }

    private boolean isListenServer(String server) {
        for (String s : listenServers) {
            if (server.contains(s))
                return true;
        }
        return false;
    }

    private boolean isListenSerendipity(String serendipity){
        for (String s : unListenSerendipity) {
            if (serendipity.equals(s))
                return false;
        }
        return true;
    }

    @Override
    public void onMessage(String message) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(message, JsonObject.class);
        if (jsonObject.get("type").getAsInt() == 2003) {
            JsonObject data = jsonObject.getAsJsonObject("data");
            String server = data.get("server").getAsString();
            String serendipity = data.get("serendipity").getAsString();
            if (isListenServer(server)&&isListenSerendipity(serendipity)) {
                ChatMessage msg = new ChatMessage();
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
                String text = data.get("server").getAsString() + "  "
                        + data.get("name").getAsString() + " 在 " + format.format(date)
                        + " 触发了 " +serendipity;

                msg.setMsg(text);
                msg.setMsgType(ChatMessage.MSG_TYPE.TEXT);
                msg.setTargetType(ChatMessage.TARGET_TYPE.GROUP);
                msg.setTarget(682406055);

                messageSender.sendText2Group(msg);
            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {

    }

    @Override
    public void onError(Exception ex) {

    }
}
