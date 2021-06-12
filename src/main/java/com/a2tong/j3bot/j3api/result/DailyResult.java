package com.a2tong.j3bot.j3api.result;

import com.a2tong.j3bot.j3api.ApiResult;

public class DailyResult extends ApiResult {
    /**
     * 大战
     */
    private String DayWar;
    /**
     * 战场
     */
    private String DayBattle;
    /**
     * 世界公共日常
     */
    private String DayCommon;
    /**
     * 阵营日常
     */
    private String DayCamp;
    /**
     * 美人图
     */
    private String DayDraw;
    /**
     * 世界公共周常
     */
    private String WeekCommon;
    /**
     * 五人周常
     */
    private String WeekFive;
    /**
     * 十人周常
     */
    private String WeekTeam;

    public String getDayWar() {
        return DayWar;
    }

    public void setDayWar(String dayWar) {
        DayWar = dayWar;
    }

    public String getDayBattle() {
        return DayBattle;
    }

    public void setDayBattle(String dayBattle) {
        DayBattle = dayBattle;
    }

    public String getDayCommon() {
        return DayCommon;
    }

    public void setDayCommon(String dayCommon) {
        DayCommon = dayCommon;
    }

    public String getDayCamp() {
        return DayCamp;
    }

    public void setDayCamp(String dayCamp) {
        DayCamp = dayCamp;
    }

    public String getDayDraw() {
        return DayDraw;
    }

    public void setDayDraw(String dayDraw) {
        DayDraw = dayDraw;
    }

    public String getWeekCommon() {
        return WeekCommon;
    }

    public void setWeekCommon(String weekCommon) {
        WeekCommon = weekCommon;
    }

    public String getWeekFive() {
        return WeekFive;
    }

    public void setWeekFive(String weekFive) {
        WeekFive = weekFive;
    }

    public String getWeekTeam() {
        return WeekTeam;
    }

    public void setWeekTeam(String weekTeam) {
        WeekTeam = weekTeam;
    }
}
