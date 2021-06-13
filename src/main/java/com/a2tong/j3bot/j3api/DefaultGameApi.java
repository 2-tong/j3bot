package com.a2tong.j3bot.j3api;

import com.a2tong.j3bot.http.RestClient;
import com.a2tong.j3bot.j3api.param.DailyParam;
import com.a2tong.j3bot.j3api.result.DailyResult;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DefaultGameApi implements GameApi {

    HttpClient client = HttpClient.newHttpClient();
    RestClient restClient = new RestClient("https://jx3api.com/app/");

    protected DefaultGameApi() {
    }

    @Override
    public ApiResponse callApi(ApiQuery query) {
        ApiResponse response = new ApiResponse();
        if (query.getType() == ApiType.DAILY) {
            response.setType(ApiType.DAILY);
            DailyParam dailyParam = (DailyParam) query.getParam();
            try {
                DailyResult result =restClient.post("daily",dailyParam,DailyResult.class,"data");
                response.setResult(result);
                response.setSuccess(true);
            } catch (Exception e) {
                e.printStackTrace();
                response.setSuccess(false);
            }
            return response;
        }
        return response;
    }
}
