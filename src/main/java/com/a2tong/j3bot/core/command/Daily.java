package com.a2tong.j3bot.core.command;

import com.a2tong.j3bot.core.CommandHandler;
import com.a2tong.j3bot.j3api.*;
import com.a2tong.j3bot.j3api.param.DailyParam;
import com.a2tong.j3bot.j3api.result.DailyResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Daily extends CommandHandler {

    @Override
    public ApiQuery parsingCommand(String param) {
        if("".equals(param) || param == null){
            param = "唯我独尊"; //默认区服 TODO 实现动态加载
        }

        DailyParam dailyParam = new DailyParam();
        dailyParam.setServer(param);

        ApiQuery query = new ApiQuery();
        query.setType(ApiType.DAILY);
        query.setParam(dailyParam);

        return query;
    }

    @Override
    public String parsingResult(ApiResponse response) {
        if(response.getType()!= ApiType.DAILY){
            throw new RuntimeException("api返回类型错误,日常命令处理器不能处理"+response.getType());
        }
        if(!response.getSuccess())
            return "日常接口调用失败";

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        //TODO 星期不对
        DailyResult result = (DailyResult) response.getResult();
        return "今天是" + format.format(cal.getTime()) + "\n" +
                "大战：" + result.getDayWar() +
                "\n战场：" + result.getDayBattle() +
                "\n阵营：" + result.getDayCamp() +
                "\n公共日常：" + result.getDayCommon() +
                "\n美人图：" + result.getDayDraw() +
                "\n周常五人：" + result.getWeekFive() +
                "\n周公共日常：" + result.getWeekCommon() +
                "\n周常十人:" + result.getWeekTeam();
    }
}
