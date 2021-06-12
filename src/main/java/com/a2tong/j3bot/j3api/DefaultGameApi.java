package com.a2tong.j3bot.j3api;

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

    protected DefaultGameApi() {
    }

    @Override
    public ApiResponse callApi(ApiQuery query) {
        ApiResponse response = new ApiResponse();
        if (query.getType() == ApiType.DAILY) {
            DailyParam dailyParam = (DailyParam) query.getParam();
            Gson gson = new Gson();
            String json = gson.toJson(dailyParam);

            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .header("Content-Type", "application/json")
                        .uri(new URI("https://jx3api.com/app/daily"))
                        .POST(HttpRequest.BodyPublishers.ofString(json))
                        .build();

                HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());

                response.setSuccess(res.statusCode() == 200);
                response.setType(ApiType.DAILY);
                if (res.statusCode() == 200) {
                    //TODO api与实体类字段对应的灵活配置
                    JsonObject dailyResult = gson.fromJson(res.body(), JsonObject.class);
                    response.setResult(gson.fromJson(dailyResult.getAsJsonObject("data"),DailyResult.class));
                }
            } catch (URISyntaxException | InterruptedException | IOException e) {
                response.setSuccess(false);
                e.printStackTrace();
            }
        }
        return response;
    }
}
