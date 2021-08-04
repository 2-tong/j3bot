package com.a2tong.j3bot;

import com.a2tong.j3bot.config.SysConfig;
import com.a2tong.j3bot.core.MessageHandler;
import com.a2tong.j3bot.core.sync.SyncMessageHandler;
import com.a2tong.j3bot.j3api.GameApi;
import com.a2tong.j3bot.message.chat.ChatMessage;
import com.a2tong.j3bot.message.MessageSender;
import com.a2tong.j3bot.message.chat.MSG_TYPE;
import com.a2tong.j3bot.message.chat.MsgTarget;
import com.a2tong.j3bot.message.chat.TARGET_TYPE;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ApiTest {

    @Test
    public void test(){
        MessageHandler messageHandler = new SyncMessageHandler(MessageSender.getInstance(), GameApi.getInstance());
        ChatMessage message = new ChatMessage();
        message.setMsg("bot日常");
        message.setMsgType(MSG_TYPE.TEXT);
        message.setSender(new MsgTarget(TARGET_TYPE.GROUP,123456789L));

        messageHandler.handleChatMessage(message);
    }

    @Test
    public void time(){
        int t = 1623558935;
        Date date = new Date(t);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss");
        System.out.println(format.format(date));

    }

    @Test
    public void yml(){
        Yaml yaml = new Yaml();
        SysConfig sysConfig =yaml.loadAs(ClassLoader.getSystemResourceAsStream("conf.yml"),SysConfig.class);
        System.out.println(sysConfig);
    }
}
