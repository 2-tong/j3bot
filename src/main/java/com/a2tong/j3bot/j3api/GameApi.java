package com.a2tong.j3bot.j3api;

/**
 * 游戏数据查询api
 *
 * @author liwei04
 * @time 2021年06月10日 12:03
 */
public interface GameApi {
    static GameApi getInstance(){
        return new DefaultGameApi();
    }

    ApiResponse callApi(ApiQuery query);
}
