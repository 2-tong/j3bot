package com.a2tong.j3bot.core;

import com.a2tong.j3bot.core.command.Daily;
import com.a2tong.j3bot.j3api.ApiQuery;
import com.a2tong.j3bot.j3api.ApiResponse;
import com.a2tong.j3bot.message.ChatMessage;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class CommandHandler {

    /**
     * 通过命令指定对应得命令处理器
     */
    private static Map<String,CommandHandler> handlerMap;

    /**
     * 加载指令
     * TODO 更优雅得加载实现，例如配置文件或反射
     */
    private static void loadCommand(){
        handlerMap = new HashMap<>();
        handlerMap.put("日常",new Daily());
    }

    public static CommandHandler getCommandHandlerByCommand(String command){
        if(handlerMap == null){
            loadCommand();
        }
        return handlerMap.get(command);
    }


    public abstract ApiQuery parsingCommand(String param);

    public abstract String parsingResult(ApiResponse response);
}
