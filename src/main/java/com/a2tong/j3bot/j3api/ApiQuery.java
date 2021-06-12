package com.a2tong.j3bot.j3api;

/**
 * TODO
 *
 * @author liwei04
 * @time 2021年06月10日 12:06
 */
public class ApiQuery {
    ApiType type;
    ApiParam Param;

    public ApiType getType() {
        return type;
    }

    public void setType(ApiType type) {
        this.type = type;
    }

    public ApiParam getParam() {
        return Param;
    }

    public void setParam(ApiParam param) {
        Param = param;
    }
}
