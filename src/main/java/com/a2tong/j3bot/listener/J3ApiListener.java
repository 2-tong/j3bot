package com.a2tong.j3bot.listener;

import com.a2tong.j3bot.config.SysConfig;
import com.a2tong.j3bot.message.chat.ChatMessage;
import com.a2tong.j3bot.message.MessageSender;
import com.a2tong.j3bot.message.chat.MSG_TYPE;
import com.a2tong.j3bot.message.chat.MsgTarget;
import com.a2tong.j3bot.message.chat.TARGET_TYPE;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.yaml.snakeyaml.Yaml;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class J3ApiListener extends WebSocketClient {

    MessageSender messageSender;

    static String[] listenServers ;

    static String[] unListenSerendipity ;

    static {
        Yaml yaml = new Yaml();
        SysConfig sysConfig =yaml.loadAs(ClassLoader.getSystemResourceAsStream("conf.yml"),SysConfig.class);
        listenServers = sysConfig.getListenServers();
        unListenSerendipity = sysConfig.getUnListenSerendipity();
    }



    public J3ApiListener(URI serverUri, MessageSender messageSender) {
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
        JsonObject data = jsonObject.getAsJsonObject("data");
        switch (jsonObject.get("type").getAsInt()){
            case 2003: serendipity(data);break;
            case 2001: serverRelease(data);break;
            case 2002: gameNews(data);break;
        }
    }

    private void serverRelease(JsonObject data){
        String server = data.get("server").getAsString();
        if(isListenServer(server)){
            String stauts = data.get("stauts").getAsString();
            String suffix = "1".equals(stauts)?"开服啦":"进入维护状态了";
            String text = server+" "+suffix;
            ChatMessage msg = new ChatMessage();
            msg.setMsg(text);
            msg.setMsgType(MSG_TYPE.TEXT);

            msg.setReceiver(new MsgTarget(TARGET_TYPE.GROUP,682406055L));
            messageSender.sendText2Group(msg);
        }
    }

    private void gameNews(JsonObject data){

    }

    private void serendipity(JsonObject data) {
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
            msg.setMsgType(MSG_TYPE.TEXT);
            msg.setReceiver(new MsgTarget(TARGET_TYPE.GROUP,682406055L));

            messageSender.sendText2Group(msg);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("监听ws断开------------尝试重连");
        this.connect();
    }

    @Override
    public void onError(Exception ex) {

    }
}
