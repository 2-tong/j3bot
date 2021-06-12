package com.a2tong.j3bot.core.sync;

import com.a2tong.j3bot.core.CommandHandler;
import com.a2tong.j3bot.j3api.ApiQuery;
import com.a2tong.j3bot.j3api.GameApi;
import com.a2tong.j3bot.message.ApiMessage;
import com.a2tong.j3bot.message.ChatMessage;
import com.a2tong.j3bot.core.MessageHandler;
import com.a2tong.j3bot.message.MessageSender;

import java.util.Properties;

/**
 * 同步消息处理器,当api消息为同步获取时调用。
 *
 * @author liwei04
 * @time 2021年06月10日 11:51
 */
public class SyncMessageHandler implements MessageHandler {

    private final MessageSender messageSender;

    private final GameApi api;

    public SyncMessageHandler(MessageSender messageSender,GameApi api){
        this.messageSender = messageSender;
        this.api = api;
    }

    @Override
    public void handleChatMessage(ChatMessage msg) {
        if(msg.getMsgType() == ChatMessage.MSG_TYPE.TEXT
                && msg.getSourceType() == ChatMessage.TARGET_TYPE.GROUP){

                String text = (String) msg.getMsg();
                if(!text.startsWith("bot"))
                    return;

                text = text.substring(3);
                String[] commands = text.split(" ",2);
                if(commands.length == 0)
                    return;

                //解析命令
                CommandHandler handler = CommandHandler.getCommandHandlerByCommand(commands[0]);
                ApiQuery query = handler.parsingCommand(commands.length==2?commands[1] :null );

                //调用api并等待返回再解析
                String resultMsg = handler.parsingResult(api.callApi(query));

                messageSender.sendText2Group(ChatMessage.buildMessageForReturn(msg, ChatMessage.MSG_TYPE.TEXT,resultMsg));
        }
    }

    @Override
    public void handleJ3ApiMessage(ApiMessage msg) {

    }
}
