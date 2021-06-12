package com.a2tong.j3bot.j3api;

/**
 * TODO
 *
 * @author liwei04
 * @time 2021年06月10日 12:04
 */
public class ApiResponse {
    private ApiType type;
    private Boolean success;
    private ApiResult result;

    public ApiType getType() {
        return type;
    }

    public void setType(ApiType type) {
        this.type = type;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ApiResult getResult() {
        return result;
    }

    public void setResult(ApiResult result) {
        this.result = result;
    }
}
